import React, { useState } from 'react';
import { styled } from '@mui/system';
import { Row, Col, Card, Form, Button } from 'react-bootstrap';
import './setting.css';
import axios from 'axios';

const Setting = ({ triggerMessage, fetchSetting,settings }) => {
  const [businessName, setBusinessName] = useState('');
  const [businessMobile, setBusinessMobile] = useState('');
  const [businessEmail, setBusinessEmail] = useState('');
  const [businessAddress, setBusinessAddress] = useState('');
  const [businessLogo, setBusinessLogo] = useState(null);
  const [businessGSTNumber, setBusinessGSTNumber] = useState('');

  const validateBusinessMobile = (value) => {
    if (!/^\d*$/.test(value)) {
      triggerMessage('Mobile number must contain only digits.', 'error');
      return false;
    }


    return true;
  };



  const validateBusinessGSTNumber = (value) => {
    // Check if value contains only uppercase letters and digits
    if (/[^A-Z0-9]/.test(value)) {
      triggerMessage('GST number must contain only uppercase letters and digits.', 'error');
      return false;
    }

    // Check if length exceeds 15 characters
    if (value.length > 15) {
      triggerMessage('GST number cannot exceed 15 characters.', 'error');
      return false;
    }

    return true;
  };


  const handleBusinessMobileChange = (e) => {
    const value = e.target.value;
    const newValue = value.replace(/[^0-9]/g, '').slice(0, 10);
    setBusinessMobile(newValue);
    // Validate on change and show message if invalid
    if (!validateBusinessMobile(newValue)) {
      return; // Avoid setting message repeatedly
    }
  };

  const handleBusinessEmailChange = (e) => {
    const value = e.target.value;
    setBusinessEmail(value);
    // Validate on change and show message if invalid
  };

  const handleBusinessGSTNumberChange = (e) => {
    const value = e.target.value;
    setBusinessGSTNumber(value);
    // Validate on change and show message if invalid
    if (!validateBusinessGSTNumber(value)) {
      return; // Avoid setting message repeatedly
    }

  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Final validation check before submitting
    if (!validateBusinessMobile(businessMobile) || !validateBusinessGSTNumber(businessGSTNumber)) {
      return; // Stop submission if validation fails
    }


    const formData = new FormData();
    formData.append('projectSetting', new Blob([JSON.stringify({
      businessName,
      businessMobile,
      businessEmail,
      businessAddress,
      businessGSTNumber
    })], { type: 'application/json' }));
    if (businessLogo) {
      formData.append('businessLogoImagePath', businessLogo);
    }else{
      triggerMessage('Please upload a logo for your business.','error');
      return;
    }
    

    try {
      const response = await axios.post('/myapi/api/settings/upsert', formData);
      if (response.status === 201) {
        fetchSetting();
        triggerMessage('Setting updated successfully', 'success');
      } else {
        triggerMessage('Setting update failed', 'error');
      }
    } catch (error) {
      console.error('Error updating settings:', error);
      triggerMessage('Setting update failed', 'error');
    }
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
    <div className="container mt-5 settingMasterFormDiv">
      <Row className='justify-content-md-center'>
        <Col md={8}>
          <Card className='p-3 mt-4'>
            <form onSubmit={handleSubmit}>
              <Row>
                <Col md={6} className='mt-1'>
                  <Form.Group controlId="formBusinessName">
                    <Form.Label>Business Name:</Form.Label>
                    <Form.Control
                      type="text"
                      required
                      placeholder="Enter business name"
                      defaultValue={settings.business_name}
                      onChange={(e) => setBusinessName(e.target.value)}
                    />
                  </Form.Group>
                </Col>
                <Col md={6} className='mt-1'>
                  <Form.Group controlId="formBusinessMobile">
                    <Form.Label>Business Mobile:</Form.Label>
                    <Form.Control
                      type="tel"
                      required
                      placeholder="Enter mobile number"
                      defaultValue={settings.business_mobile}
                      onChange={handleBusinessMobileChange}
                    />
                  </Form.Group>
                </Col>
                <Col md={6} className='mt-4'>
                  <Form.Group controlId="formBusinessEmail">
                    <Form.Label>Business Email:</Form.Label>
                    <Form.Control
                      type="email"
                      required
                      placeholder="Enter email.."
                      defaultValue={settings.business_email}
                      onChange={handleBusinessEmailChange}
                    />
                  </Form.Group>
                </Col>
                <Col md={6} className='mt-4'>
                  <Form.Group controlId="formBusinessGSTNumber">
                    <Form.Label>Business GST Number:</Form.Label>
                    <Form.Control
                      type="text"
                      required
                      placeholder="Enter GST Number"
                      defaultValue={settings.business_gst_number}
                      onChange={handleBusinessGSTNumberChange}
                    />
                  </Form.Group>
                </Col>
                <Col md={6} className='mt-4'>
                  <Form.Group controlId="formBusinessLogo">
                    <Form.Label>Business Logo:</Form.Label>
                    <Form.Control
                      type="file"
                      required
                      onChange={(e) => {
                        const file = e.target.files[0];
                        if (file) {
                          const fileType = file.type;
                          const allowedTypes = ['image/jpeg', 'image/png'];

                          if (!allowedTypes.includes(fileType)) {
                            triggerMessage('Please select a .jpg, .jpeg, or .png file.', 'error');
                            setBusinessLogo(null);
                            return;
                          } else {
                            setBusinessLogo(file)
                          }
                        }
                      }}
                    />
                  </Form.Group>
                </Col>
                <Col md={6} className='mt-4'>
                  <Form.Group controlId="formBusinessAddress">
                    <Form.Label>Business Address:</Form.Label>
                    <Form.Control
                      type="text"
                      required
                      placeholder="Enter Address.."
                      defaultValue={settings.business_address}
                      onChange={(e) => setBusinessAddress(e.target.value)}
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row className='justify-content-md-center mt-4 p-2'>
                <StyledButton type='submit'>Update</StyledButton>
              </Row>
            </form>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default Setting;
