import React, { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import { Row, Col, Card, Form } from 'react-bootstrap';
import { styled } from '@mui/material/styles';
import axios from 'axios';

const AddCategory = ({ open, handleClose, fetchcategories, triggerMessage }) => {
    const [categoryName, setCategoryName] = useState('');
    const [image, setImage] = useState(null);

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

    const handleSubmit = async (e) => {
        e.preventDefault(); // Prevent the form from submitting and refreshing the page
        const formData = new FormData();
        formData.append('cateName', categoryName);
        formData.append('imageName', image);

        try {
            const response = await axios.post('/myapi/api/category/add_catewithimg', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            if (response.status === 201) {
                console.log(response.data);
                fetchcategories();
                handleClose();
                triggerMessage('Category added successfully', 'success');
            }
        } catch (error) {
            triggerMessage('Error, category not added..', 'error');
            console.log(error);
        }
    };

    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Add Category</DialogTitle>
            <DialogContent>
                <Row>
                    <Col md={12}>
                        <Card className='fixed-card'>
                            <Card.Body>
                                <Row>
                                    <Col md={12}>
                                        <Form onSubmit={handleSubmit}>
                                            <Form.Group controlId="formCategoryName">
                                                <Form.Label>Category Name:</Form.Label>
                                                <Form.Control
                                                    type="text"
                                                    required
                                                    placeholder="Enter category name"
                                                    value={categoryName}
                                                    onChange={(e) => {
                                                        const value = e.target.value;
                                                        if (/^[A-Za-z\s]*$/.test(value) && value.length <= 18) {
                                                            setCategoryName(value);
                                                        } else {
                                                            // Optionally, you could use feedback here
                                                            triggerMessage('Only characters allowed and max length is 18', 'error');
                                                        }
                                                    }}
                                                />
                                            </Form.Group>
                                            <Form.Group controlId="formCategoryImage">
                                                <Form.Label className='mt-3'>Category Image:</Form.Label>
                                                <Form.Control
                                                    type="file"
                                                    required
                                                    onChange={(e) => {
                                                        const file = e.target.files[0];
                                                        if (file) {
                                                            // Check the file type
                                                            const fileType = file.type;
                                                            const allowedTypes = ['image/jpeg', 'image/png'];

                                                            if (!allowedTypes.includes(fileType)) {
                                                                triggerMessage('Please select a .jpg, .jpeg, or .png file.', 'error');
                                                                setImage(null);
                                                                return;
                                                            }else{
                                                                setImage(file);
                                                            }

                                                            
                                                        }
                                                    }}
                                                />
                                            </Form.Group>
                                            <Button color="primary" type='submit' className='mt-3'>Add Category</Button>
                                            <Button onClick={handleClose} color="primary" className='mt-3'>
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
};

export default AddCategory;
