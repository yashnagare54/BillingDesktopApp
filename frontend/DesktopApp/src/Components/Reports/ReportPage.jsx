import React, { useEffect, useState } from 'react';
import { Row, Col, Card } from 'react-bootstrap';
import { Container } from '@mui/material';
import PieChartComponent from './AllBillsPieChart';
import TimeVsCustomerChart from './TimeVsCustomer';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function ReportPage() {
  const navigate = useNavigate();
  const [noOfProd, setNoOfProd] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [AllBillCount, setAllBillCount] = useState([]);
  const [todaysBillCount, setTodaysBillCount] = useState(0); // State for today's bill count
  const [todayBusinessAmt,setTodayBusinessAmt]=useState(0)
  const [monthlyBusinessAmt,setMonthlyBusinessAmt]=useState(0)
  const [rawData,setRowData]=useState([])
  const [data,setData]=useState([
    { name: 'Cash', value: 0 },
    { name: 'Card Payment', value: 0},
    { name: 'UPI', value: 0 },
    { name: 'Not Selected', value: 1 },
  ])

  const openAllCustomer = () => {
    navigate('/dashboard/allcustomers');
  };

  const openAllProducts = () => {
    navigate('/dashboard/products');
  };

  const fetchCustomer = async () => {
    const response = await axios.get("/myapi/api/customer/allcustomers");
    setCustomers(response.data);
  };

  const fetchAllBills = async () => {
    const response = await axios.get('/myapi/api/order/get-all');
    setAllBillCount(response.data);
    calculateTodaysBills(response.data); // Calculate today's bills once data is fetched
    todayBusiness(response.data)
    monthlyBusiness(response.data)
    setPieChartVlues(response.data)
    setRowData(response.data)
  };

  const fetchProducts = async () => {
    const response = await axios.get('/myapi/api/product/allproducts');
    setNoOfProd(response.data);
  };

  // Function to get today's date in YYYY-MM-DD format
  function getTodayDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  function getCurrentMonth() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    return `${year}-${month}`;
  }
  const monthlyBusiness=(allorders)=>{
    const currentMonth=getCurrentMonth();
    const monthlyOrders = allorders.filter(order => order.orderCreatedOn.startsWith(currentMonth));
    const monthlyBusiness = monthlyOrders.reduce((acc, order) => acc + order.netAmount,0);
    setMonthlyBusinessAmt(monthlyBusiness)
  }

  const setPieChartVlues=(allbills)=>{
    const count1 = allbills.filter(order => order.paymentMode==='Cash').length;
    const count2 = allbills.filter(order => order.paymentMode==='Card Payment').length;
    const count3 = allbills.filter(order => order.paymentMode==='UPI').length;
    const count4 = allbills.filter(order => order.paymentMode=== 'Not Selected').length;
    setData([
      { name: 'Cash', value: count1 },
      { name: 'Card Payment', value: count2},
      { name: 'UPI', value: count3 },
      { name: 'Not Selected', value: count4 },
    ])

    

  }

  // Function to count orders from today's date
  function calculateTodaysBills(orders) {
    const todayDate = getTodayDate();
    const count = orders.filter(order => order.orderCreatedOn === todayDate).length;
    setTodaysBillCount(count); // Update the state with today's bill count
    
  }

  const todayBusiness=(allorder)=>{
    const todayDate = getTodayDate();
    const todayOrders = allorder.filter(order => order.orderCreatedOn === todayDate);
    const totalAmountToday = todayOrders.reduce((acc, order) => acc + order.netAmount, 0);
    setTodayBusinessAmt(totalAmountToday)
  }

  useEffect(() => {
    fetchProducts();
    fetchCustomer();
    fetchAllBills();
  }, []);

  return (
    <div className='scrollToReportDiv p-1'>
      <Row className='mt-4'>
        <Col md={12}>
          <Row>
            <Col md={2}>
              <Card className='fixed-card' style={{ height: '90px' }}>
                <Card.Body>
                  <Card.Title className='text-center' style={{ fontSize: '15px' }}>All Bills</Card.Title>
                  <Row>
                    <Col md={12} className='text-center'>
                      <h3>{AllBillCount.length}</h3>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
            <Col md={2}>
              <Card className='fixed-card' style={{ height: '90px' }}>
                <Card.Body>
                  <Card.Title className='text-center' style={{ fontSize: '15px' }}>Today's Bills</Card.Title>
                  <Row>
                    <Col md={12} className='text-center'>
                      <h3>{todaysBillCount}</h3>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
            <Col md={2}>
              <Card className='fixed-card' style={{ height: '90px' }} onClick={openAllCustomer}>
                <Card.Body>
                  <Card.Title className='text-center' style={{ fontSize: '15px' }}>All Customers</Card.Title>
                  <Row>
                    <Col md={12} className='text-center'>
                      <h3>{customers.length}</h3>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
            <Col md={2}>
              <Card className='fixed-card' style={{ height: '90px' }} onClick={openAllProducts}>
                <Card.Body>
                  <Card.Title className='text-center' style={{ fontSize: '15px' }}>Total Products</Card.Title>
                  <Row>
                    <Col md={12} className='text-center'>
                      <h3>{noOfProd.length}</h3>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
            <Col md={2}>
              <Card className='fixed-card' style={{ height: '90px' }}>
                <Card.Body>
                  <Card.Title className='text-center' style={{ fontSize: '15px' }}>Today's Business</Card.Title>
                  <Row>
                    <Col md={12} className='text-center'>
                      <h3>{todayBusinessAmt} Rs</h3>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
            <Col md={2}>
              <Card className='fixed-card' style={{ height: '90px' }}>
                <Card.Body>
                  <Card.Title className='text-center' style={{ fontSize: '14px' }}>Current Month Business</Card.Title>
                  <Row>
                    <Col md={12} className='text-center'>
                      <h3>{monthlyBusinessAmt} Rs</h3>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Col>
      </Row>
      <Row style={{ marginTop: '15px' }}>
        <Col md={4}>
          <Container>
            <PieChartComponent data={data}   />
          </Container>
        </Col>
        <Col md={8}>
          <div>
            <TimeVsCustomerChart rawData={rawData} />
          </div>
        </Col>
      </Row>
    </div>
  );
}

export default ReportPage;
