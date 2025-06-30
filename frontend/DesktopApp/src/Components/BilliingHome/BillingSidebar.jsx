import React, { useEffect, useState } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { styled } from '@mui/system';
import './billingSidebar.css';
import axios from 'axios';
import AddCustomer from './AddCustomer';

function BillingSidebar({ cartItems, onQuantityChange, onRemoveFromCart, onClearCart, triggerMessage, setCustomerSelected, customerSelected }) {
  const [customers, setCustomers] = useState([]);
  const [filteredCustomers, setFilteredCustomers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [open, setOpen] = useState(false);

  const handleOpenCustomerForm = () => {
    setOpen(!open);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const fetchCustomer = async () => {
    try {
      const response = await axios.get("/myapi/api/customer/allcustomers");
      setCustomers(response.data);
    } catch (error) {
      console.error('Error fetching customers', error);
    }
  };

  useEffect(() => {
    fetchCustomer();
  }, []);

  useEffect(() => {
    if (customerSelected) {
      setSearchTerm(customerSelected.custFullName);
    }
  }, [customerSelected]);

  useEffect(() => {
    if (searchTerm === '') {
      setFilteredCustomers([]);
    } else {
      const filtered = customers.filter(customer =>
        customer.custFullName.toLowerCase().includes(searchTerm.toLowerCase()) ||
        customer.custMobile.includes(searchTerm)
      );
      setFilteredCustomers(filtered);
    }
  }, [searchTerm, customers]);

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
    if (event.target.value === '') {
      setCustomerSelected(null); // Clear selected customer when search term is empty
    }
  };

  const handleCustomerSelect = (customer) => {
    setCustomerSelected(customer);
    setSearchTerm(customer.custFullName); // Update search term when customer is selected
  };

  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + parseFloat(item.price.replace('$', '')) * item.quantity, 0);
  };

  const handleQuantityChange = (productId, quantity) => {
    onQuantityChange(productId, quantity);
  };

  const StyledButton = styled(Button)(({ theme }) => ({
    backgroundColor: theme.palette.primary.main,
    border: 'none',
    justifyContent: 'center',
    '&:hover': {
      backgroundColor: theme.palette.primary.darker,
      cursor: 'pointer',
    },
    '&:focus': {
      boxShadow: 'none',
    },
  }));

  return (
    <div className="billing-sidebar p-1">
      <div className="mb-4">
        <Row>
          <Col md={9} style={{ position: 'relative' }}>
            <input
              type="text"
              className="form-control"
              placeholder="Search Customer"
              aria-label="Search"
              value={searchTerm}
              onChange={handleSearchChange}
            />
            {searchTerm && filteredCustomers.length > 0 && (
              customerSelected===null ? 
              <ul className="dropdown-menu show ml-3 p-0" style={{ position: 'absolute', width: '90%', zIndex: 1000 }}>
                {filteredCustomers.map(customer => (
                  <li key={customer.custId} className="dropdown-item" onClick={() => handleCustomerSelect(customer)}
                    style={{ cursor: 'pointer' }}
                  >
                    {customer.custFullName} ({customer.custMobile})
                  </li>
                ))}
              </ul> : <></>
            )}
          </Col>
          <Col md={3}>
            <StyledButton onClick={handleOpenCustomerForm}>+ Add</StyledButton>
          </Col>
        </Row>
        <div className="container">
          <div className="row border-bottom mb-2 font-weight-bold">
            {/* <div className="col-2">Image</div> */}
            <div className="col-3">Name</div>
            <div className="col-3">Quantity</div>
            <div className="col-2">Price</div>
            <div className='col-2'>Total</div>
            <div className="col-2">Action</div>
          </div>
          {cartItems.map(item => (
            <div className="row mb-2" key={item.prodId}>
              {/* <div className="col-2 d-flex align-items-center">
                <img src={`/myapi/api/images?imageName=${item.prodImageUrl}`} alt={item.title} className="img-thumbnail" style={{ width: '40px', height: '40px' }} />
              </div> */}
              <div className="col-3 d-flex align-items-center">
                {item.prodName}
              </div>
              <div className="col-3 d-flex align-items-center">
                <div className="d-flex align-items-center">
                  <button
                    className="btn btn-sm btn-outline-secondary"
                    onClick={() => handleQuantityChange(item.prodId, Math.max(item.quantity - 1, 0))}
                    style={{ width: '25px' }}
                  >
                    -
                  </button>
                  <span className="mx-2">{item.quantity}</span>
                  <button
                    className="btn btn-sm btn-outline-secondary"
                    onClick={() => handleQuantityChange(item.prodId, item.quantity + 1)}
                    style={{ width: '25px' }}
                  >
                    +
                  </button>
                </div>
              </div>
              <div className="col-2 d-flex align-items-center">
                {item.prodPrice}
              </div>
              <div className="col-2 d-flex align-items-center">
                {item.prodPrice*item.quantity}
              </div>
              
              <div className="col-2 d-flex align-items-center">
                <button className="btn btn-sm btn-outline-danger" onClick={() => onRemoveFromCart(item.prodId)}>🗑</button>
              </div>
            </div>
          ))}
        </div>
        {open && <AddCustomer open={open} handleClose={handleClose} triggerMessage={triggerMessage} fetchCustomer={fetchCustomer} customers={customers} handleCustomerSelect={handleCustomerSelect} />}
      </div>
    </div>
  );
}

export default BillingSidebar;
