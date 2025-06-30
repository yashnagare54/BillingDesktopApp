import React, { useState } from 'react';
import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import Button from "@mui/material/Button";
import { Row, Col, Card, Form } from "react-bootstrap";
import axios from 'axios';

function AddCustomer({ handleClose, open, triggerMessage, fetchCustomer, customers, handleCustomerSelect }) {
    const [customerName, setCustomerName] = useState('');
    const [contactNumber, setContactNumber] = useState('');
    const [contactNumberError, setContactNumberError] = useState('');

    const handleContactNumberChange = (e) => {
        const value = e.target.value;
        // Allow only digits and restrict length to 10
        const newValue = value.replace(/[^0-9]/g, '').slice(0, 10);
        setContactNumber(newValue);
    };

    const validateContactNumber = (value) => {
        // Check if the value contains exactly 10 digits
        if (value.length !== 10) {
            setContactNumberError('Contact number must be exactly 10 digits and contain only numbers.');
            return false;
        }
        setContactNumberError('');
        return true;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Validate contact number length and numeric value before submission
        if (!validateContactNumber(contactNumber)) {
            return;
        }

        // Check if the contact number already exists
        const existingCustomer = customers.find(customer => customer.custMobile === contactNumber);

        if (existingCustomer) {
            triggerMessage('Customer with this contact number already exists.', 'error');
            return;
        }

        try {
            const response = await axios.post("/myapi/api/customer/register_customer", {
                "custFullName": customerName,
                "custMobile": contactNumber
            });

            if (response.status === 201) {
                handleCustomerSelect(response.data);
                triggerMessage('Customer Added Successfully', 'success');
                fetchCustomer(); // Fetch updated customer list
                handleClose(); // Close the dialog
            }
        } catch (error) {
            console.error('Error adding customer:', error);
            triggerMessage('Error adding customer', 'danger');
        }
    };

    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogContent>
                <Row>
                    <Col md={12}>
                        <Card className="fixed-card">
                            <Card.Body>
                                <Card.Title className="text-center">Add Customer</Card.Title>
                                <Row>
                                    <Col md={12}>
                                        <Form onSubmit={handleSubmit}>
                                            <Form.Group controlId="formCustomerName">
                                                <Form.Label>Customer Name:</Form.Label>
                                                <Form.Control
                                                    type="text"
                                                    required
                                                    placeholder="Enter customer name"
                                                    value={customerName}
                                                    onChange={(e) => setCustomerName(e.target.value)}
                                                />
                                            </Form.Group>

                                            <Form.Group>
                                                <Form.Label className="mt-3">Customer Contact No:</Form.Label>
                                                <Form.Control
                                                    type="tel"
                                                    required
                                                    placeholder="Enter contact number"
                                                    value={contactNumber}
                                                    onChange={handleContactNumberChange}
                                                />
                                                {/* Display the error message only if there is an error */}
                                                {contactNumberError && (
                                                    <Form.Text className="text-danger">{contactNumberError}</Form.Text>
                                                )}
                                            </Form.Group>

                                            <Button type="submit" className="mt-3">
                                                Add Customer
                                            </Button>
                                            <Button onClick={handleClose} color="primary" className="mt-3 ms-2">
                                                Close
                                            </Button>
                                        </Form>
                                    </Col>
                                </Row>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </DialogContent>
        </Dialog>
    );
}

export default AddCustomer;
