import React, { useEffect, useState } from 'react';
import { Row, Col, Card, Button } from 'react-bootstrap';
import BillingSidebar from './BillingSidebar';
import ReactDOMServer from 'react-dom/server';
import './Billinghome.css';
import axios from 'axios';
import { styled } from '@mui/material';
import InvoicePOS from './BillPrint/InvoicePOS';

function BillingHome({ triggerMessage, settings }) {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [products, setProducts] = useState([]);
  const [allCategories, setAllCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('All Category');
  const [searchQuery, setSearchQuery] = useState('');
  const [cartItems, setCartItems] = useState([]);
  const [paymentMethod, setPaymentMethod] = useState('Not Selected');
  const [discountPercent, setDiscountPercent] = useState(0);
  const [discountAmount, setDiscountAmount] = useState(0);
  const [taxPercent, setTaxPercent] = useState(0);
  const [taxAmount, setTaxAmount] = useState(0);
  const [serviceCharge, setServiceCharge] = useState(0);
  const [totalAmount, setTotalAmount] = useState(0);
  const [grandTotal, setGrandTotal] = useState(0);
  const [customerSelected, setCustomerSelected] = useState(null);

  const printPayload = {
    customerSelected,
    grandTotal,
    totalAmount,
    serviceCharge,
    taxAmount,
    taxPercent,
    discountAmount,
    discountPercent,
    paymentMethod,
  };

  const StyledCard = styled(Card)(({ theme }) => ({
    backgroundColor: theme.palette.secondary.main,
  }));

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const handlePrintBill = async () => {
    if (customerSelected == null) {
      triggerMessage('select Customer', 'error');
      return;
    }
    
    console.log(cartItems);
    console.log(customerSelected);

    try {
      const orderItems = cartItems.map((item) => ({
        product: {
          prodId: item.prodId,
          prodName: item.prodName,
          prodPrice: item.prodPrice,
          prodImageUrl: item.prodImageUrl,
          prodCreatedOn: item.prodCreatedOn,
          prodLastUpdatedOn: item.prodLastUpdatedOn,
        },
        quantity: item.quantity,
      }));
      if (!orderItems.length>0) {
        triggerMessage('Cart is empty', 'error');
        return;
      }

      const response = await axios.post('/myapi/api/order/place-order', {
        subTotalAmt: totalAmount,
        discountPercentage: discountPercent,
        discountAmount: discountAmount,
        netAmount: grandTotal,
        paymentMode:paymentMethod,
        customer: customerSelected,
        orderItems: orderItems,
      });

      console.log(response);
      if (response.status == 201) {
        triggerMessage('saved Successfully, Printing...', 'success');
        handleClearCart()

      }
    } catch (error) {
      console.log(error);
      console.log('@@@@@@@@@@@ errrrrr');
    }


    const printWindow = window.open('', '_blank');
    const printContent = ReactDOMServer.renderToString(
      <InvoicePOS
        settings={settings}
        cartItems={cartItems}
        printPayload={printPayload}
      />
    );
    printWindow.document.write(`
          <html>
            <head>
              <title>Bill Details</title>
              <style>
              @media print {
        .page-break {
            display: block;
            page-break-before: always;
        }
    }

    #invoice-POS {
        box-shadow: 0 0 1in -0.25in rgba(0, 0, 0, 0.5);
        padding: 2mm;
        margin: 0 auto;
        width: 58mm;
        background: #FFF;
    }

    #invoice-POS ::selection {
        background: #f31544;
        color: #FFF;
    }

    #invoice-POS ::moz-selection {
        background: #f31544;
        color: #FFF;
    }

    #invoice-POS h1 {
        font-size: 1.5em;
        color: #222;
    }

    #invoice-POS h2 {
        font-size: .9em;
    }

    #invoice-POS h3 {
        font-size: 1.2em;
        font-weight: 300;
        line-height: 2em;
    }

    #invoice-POS p {
        font-size: .4em;
        color: #666;
        line-height: 1.2em;
    }

    #invoice-POS #top,
    #invoice-POS #mid,
    #invoice-POS #bot {
        /* Targets all id with 'col-' */
        border-bottom: 1px solid #EEE;
    }

    #invoice-POS #top {
        min-height: 100px;
    }

    #invoice-POS #mid {
        min-height: 50px;
    }

    #invoice-POS #bot {
        min-height: 50px;
    }

    #invoice-POS #top .logo {
        height: 60px;
        width: 60px;
        background: url(http://michaeltruong.ca/images/logo1.png) no-repeat;
        background-size: 60px 60px;
    }

    #invoice-POS .clientlogo {
        float: left;
        height: 60px;
        width: 60px;
        background: url(http://michaeltruong.ca/images/client.jpg) no-repeat;
        background-size: 60px 60px;
        border-radius: 50px;
    }

    #invoice-POS .info {
        display: block;
        margin-left: 0;
    }

    #invoice-POS .title {
        float: right;
    }

    #invoice-POS .title p {
        text-align: right;
    }

    #invoice-POS table {
        width: 100%;
        border-collapse: collapse;
    }

    #invoice-POS .tabletitle {
        font-size: .5em;
        background: #EEE;
    }

    #invoice-POS .service {
        border-bottom: 1px solid #EEE;
    }

    #invoice-POS .item {
        width: 24mm;
    }

    #invoice-POS .itemtext {
        font-size: .5em;
    }

    #invoice-POS #legalcopy {
        margin-top: 5mm;
        text-align: center;
    }
              </style>
            </head>
            <body>${printContent}</body>
            <script>
              window.print();
              window.onafterprint = function() { window.close(); };
            </script>
          </html>
        `);
    printWindow.document.close();
  };

  const fetchProducts = async () => {
    const response = await axios.get('/myapi/api/product/allproducts');
    const Allproducts = response.data;
    const ActiveProducts=Allproducts.filter(product => product.category.active);
    setProducts(ActiveProducts)

    
    console.log(response.data);
    
  };

  const fetchCategories = async () => {
    const response = await axios.get('/myapi/api/category/active-categories');
    setAllCategories(response.data);
  };

  useEffect(() => {
    fetchProducts();
    fetchCategories();
  }, []);

  const filteredProducts = products.filter((product) => {
    return (
      (selectedCategory === 'All Category' ||
        product.category.cateName === selectedCategory) &&
      (searchQuery === '' ||
        product.prodName.toLowerCase().includes(searchQuery))
    );
  });

  const handleCategorySelect = (category) => {
    setSelectedCategory(category);
    setIsDropdownOpen(false);
  };

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value.toLowerCase());
  };

  const handleAddToCart = (product) => {
    setCartItems((prevItems) => {
      const existingItem = prevItems.find(
        (item) => item.prodId === product.prodId
      );
      if (existingItem) {
        return prevItems.map((item) =>
          item.prodId === product.prodId
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }
      return [...prevItems, { ...product, quantity: 1 }];
    });
  };

  const handleQuantityChange = (productId, quantity) => {
    setCartItems((prevItems) =>
      prevItems
        .map((item) =>
          item.prodId === productId ? { ...item, quantity: quantity } : item
        )
        .filter((item) => item.quantity > 0)
    );
  };

  const handleRemoveFromCart = (productId) => {
    setCartItems((prevItems) =>
      prevItems.filter((item) => item.prodId !== productId)
    );
  };

  const handleClearCart = () => {
    setCartItems([]);
  };

  useEffect(() => {
    calculateTotal();
  }, [
    totalAmount,
    cartItems,
    taxPercent,
    discountPercent,
    serviceCharge,
    setTaxPercent,
    taxAmount,
    discountAmount,
  ]);

  const calculateTotal = () => {
    let subtotal = cartItems.reduce(
      (total, item) => total + parseFloat(item.prodPrice) * item.quantity,
      0
    );
    setTotalAmount(subtotal);
    setDiscountAmount((subtotal * discountPercent) / 100);
    setTaxAmount((subtotal * taxPercent) / 100);

    setGrandTotal(subtotal - discountAmount + taxAmount + parseFloat(serviceCharge));
  };

  return (
    <div className="billing-home d-flex flex-column min-vh-50">
      <div className="content d-flex flex-grow-1" style={{ maxHeight: '80vh' }}>
        <div className="left flex-grow-1 d-flex flex-column">
          <div className="top-btn d-flex align-items-center mb-4">
            <div
              className="dropdown flex-shrink-1 mr-3"
              style={{ flexBasis: '35%' }}
            >
              <button
                className="dropdown-toggle btn btn-light w-100 text-bg-light p-2"
                onClick={toggleDropdown}
              >
                {selectedCategory}
              </button>
              {isDropdownOpen && (
                <div className="dropdown-menu show w-100">
                  <button
                    className="dropdown-item"
                    onClick={() => handleCategorySelect('All Category')}
                  >
                    All Category
                  </button>
                  {allCategories.map((category, index) => (
                    <button
                      key={index}
                      className="dropdown-item"
                      onClick={() => handleCategorySelect(category.cateName)}
                    >
                      {category.cateName}
                    </button>
                  ))}
                </div>
              )}
            </div>
            <div className="flex-grow-1">
              <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Product"
                  aria-label="Search"
                  onChange={handleSearchChange}
                />
              </div>
            </div>
          </div>
          <div className="product-list-container">
            {filteredProducts.map((product) => (
              <StyledCard
                className="card productCardInPOSHome"
                key={product.prodId}
                onClick={() => handleAddToCart(product)}
              >
                <Card.Img
                  variant="top"
                  src={`/myapi/api/images?imageName=${product.prodImageUrl}`}
                  className="card-img"
                  style={{padding:'15px'}}
                />
                <Card.Body className="d-flex flex-column cardBodyInTheProduct">
                  <Card.Title className="mb-2 textInTheCard text-center">
                    {product.prodName}
                  </Card.Title>
                  <Card.Text className="textInTheCard text-center">
                    {product.prodPrice}
                  </Card.Text>
                </Card.Body>
              </StyledCard>
            ))}
          </div>
        </div>
        <div
          className="right flex-shrink-0"
          style={{ width: '470px', overflowY: 'auto' }}
        >
          <BillingSidebar
            cartItems={cartItems}
            onQuantityChange={handleQuantityChange}
            onRemoveFromCart={handleRemoveFromCart}
            onClearCart={handleClearCart}
            triggerMessage={triggerMessage}
            setCustomerSelected={setCustomerSelected}
            customerSelected={customerSelected}
          />
        </div>
      </div>

      <footer className="footer d-flex justify-content-between align-items-center p-2 footerInPOSHome">
        <div className="form-container d-flex align-items-center">
          <div className="form-group mb-0 mr-2">
            <label htmlFor="discount" className="form-label">
              Discount
            </label>
            <select
              className="form-control form-control-sm"
              id="discount"
              value={discountPercent}
              onChange={(e) => setDiscountPercent(e.target.value)}
            >
              <option value={0}>No Discount</option>
              <option value={10}>10% Off</option>
              <option value={20}>20% Off</option>
              <option value={30}>30% Off</option>
            </select>
          </div>
          <div className="form-group mb-0 mr-2">
            <label htmlFor="tax" className="form-label">
              Tax
            </label>
            <input
              type="text"
              className="form-control form-control-sm"
              id="tax"
              value={taxPercent}
              onChange={(e) => {
                if (/^\d*(\.\d*)?$/.test(e.target.value)) {
                  setTaxPercent(e.target.value)
                } else {
                  triggerMessage('Price should contain only numbers..', 'error');
                }
              }}
              placeholder="Tax (0%)"
            />
          </div>
          <div className="form-group mb-0 mr-2">
            <label htmlFor="serviceCharge" className="form-label">
              Service Charge
            </label>
            <input
              type="text"
              className="form-control form-control-sm"
              id="serviceCharge"
              value={serviceCharge}
              onChange={(e) =>{
                if (/^\d*(\.\d*)?$/.test(e.target.value)) {
                  setServiceCharge(e.target.value || 0)
                } else {
                  triggerMessage('Price should contain only numbers..', 'error');
                }
              }
              }
              placeholder="Service Charge"
            />
          </div>
          <div className="form-group mb-0 mr-2">
            <label htmlFor="paymentMethod" className="form-label">
              Payment Method
            </label>
            <select
              className="form-control form-control-sm"
              id="paymentMethod"
              value={paymentMethod}
              onChange={(e) => setPaymentMethod(e.target.value)}
            >
              <option>Not Selected</option>
              <option>Cash</option>
              <option>Card Payment</option>
              <option>UPI</option>
             
            </select>
          </div>
        </div>
        <div className="summary">
          <strong>Total:</strong> {totalAmount.toFixed(2)}
        </div>
        <div className="summary">
          <strong>Net Total:</strong> {grandTotal.toFixed(2)}
        </div>
        <div className="buttons d-flex gap-2">
          <button className="btn btn-danger btn-sm" onClick={handleClearCart}>
            Clear All
          </button>
          <button className="btn btn-dark btn-sm" onClick={handlePrintBill}>
            Place Order
          </button>
        </div>
      </footer>
    </div>
  );
}

export default BillingHome;
