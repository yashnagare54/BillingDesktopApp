import React, { useEffect, useState } from 'react';
import { Table, Row, Col, Card, Button } from 'react-bootstrap';
import { styled } from '@mui/system';
import axios from 'axios';
import AddProduct from './AddProduct';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import SaveIcon from '@mui/icons-material/Save';
import './Product.css';

function Products({ triggerMessage }) {
    const [open, setOpen] = useState(false);
    const [editMode, setEditMode] = useState(null);
    const [products, setProducts] = useState([]);
    const [updProdName, setUpdProdName] = useState('');
    const [updProdPrice, setUpdProdPrice] = useState('');
    const [updProdImage, setUpdProdImage] = useState(null);

    const toggleProductForm = () => setOpen(!open);
    const handleClose = () => setOpen(false);

    const fetchProducts = async () => {
        try {
            const response = await axios.get('/myapi/api/product/allproducts');
            setProducts(response.data);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    const StyledButton = styled(Button)(({ theme }) => ({
        backgroundColor: theme.palette.primary.main,
        border: 'none',
        justifyContent: 'center',
        color: 'white',
        '&:hover': {
            backgroundColor: theme.palette.primary.darker,
            cursor: 'pointer',
        },
        '&:focus': {
            boxShadow: 'none',
        },
    }));

    const handleSave = async (id) => {
        const product = products.find(prod => prod.prodId === id);
        let imageToSend = updProdImage;

        if (!imageToSend) {
            try {
                const imageResponse = await axios.get(`/myapi/api/images?imageName=${product.prodImageUrl}`, { responseType: 'blob' });
                imageToSend = new File([imageResponse.data], product.prodImageUrl, { type: imageResponse.headers['content-type'] });
            } catch (error) {
                triggerMessage('Failed to fetch existing image', 'error');
                return;
            }
        }

        if (!/^[A-Za-z\s]*$/.test(updProdName)) {
            triggerMessage('Product name should contain only letters and spaces.', 'error');
            return;
        }

        const formData = new FormData();
        formData.append('prodName', updProdName || product.prodName);
        formData.append('prodPrice', updProdPrice || product.prodPrice);
        formData.append('imageName', imageToSend);

        try {
            const response = await axios.put(`/myapi/api/product/update_product/${id}`, formData);
            if (response.status === 200) {
                triggerMessage('Product updated successfully', 'success');
                setEditMode(null);
                fetchProducts();
            }
        } catch (error) {
            triggerMessage('Failed to update product', 'error');
        }
    };

    const handleEdit = (id) => {
        setEditMode(id);
    };

    const handleDelete = async (prodId) => {
        if (window.confirm('Delete the product?')) {
            try {
                const response = await axios.delete(`/myapi/api/product/delete/${prodId}`);
                if (response.status === 200) {
                    triggerMessage('Product deleted Successfully', 'success');
                    fetchProducts();
                }
            } catch (error) {
                triggerMessage('Failed to delete product', 'error');
            }
        }
    };

    return (
        <div className='container-fixed'>
            <div className='content-wrapper'>
                <Row className='mt-5'>
                    <Col xs={10}>
                        <h3>All Products</h3>
                    </Col>
                    <Col xs={2}>
                        <StyledButton variant="contained" color="primary" onClick={toggleProductForm}>+ Add Product</StyledButton>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <Card className='mt-2 scrollable-card'>
                            <Card.Body>
                                <Card.Title className='text-center'>All Product List</Card.Title>
                                <div className='table-container'>
                                    {products.length > 0 ? (
                                        <Table striped bordered hover>
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>CATEGORY</th>
                                                    <th>NAME</th>
                                                    <th>PRICE</th>
                                                    <th>IMAGE</th>
                                                    <th>ACTION</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {products.map(item => (
                                                    <tr key={item.prodId} className='TableRowInCategory'>
                                                        <td>{item.prodId}</td>
                                                        <td>{item.category.cateName}</td>
                                                        <td>
                                                            {editMode === item.prodId ? (
                                                                <input
                                                                    type="text"
                                                                    defaultValue={item.prodName}
                                                                    onInput={(e) => {
                                                                        const newValue = e.target.value;
                                                                        if (/^[A-Za-z\s]*$/.test(newValue)) {
                                                                            setUpdProdName(newValue);
                                                                        } else {
                                                                            updProdName.length > 0 ? e.target.value = updProdName
                                                                                : e.target.value = item.prodName;
                                                                            if (newValue !== '') {
                                                                                triggerMessage('Product name should contain only letters and spaces.', 'error');
                                                                            }
                                                                        }
                                                                    }}
                                                                />
                                                            ) : (
                                                                item.prodName
                                                            )}
                                                        </td>
                                                        <td>
                                                            {editMode === item.prodId ? (
                                                                <input
                                                                    type="text"
                                                                    defaultValue={item.prodPrice}
                                                                    onChange={(e) => {
                                                                        const newValue = e.target.value;
                                                                        if (/^\d*(\.\d*)?$/.test(newValue)) {
                                                                            setUpdProdPrice(newValue);
                                                                        } else {
                                                                            if (newValue !== '') {
                                                                                triggerMessage('Price should contain only numbers.', 'error');
                                                                            }
                                                                        }
                                                                    }}
                                                                />
                                                            ) : (
                                                                item.prodPrice
                                                            )}
                                                        </td>
                                                        <td>
                                                            {editMode === item.prodId ? (
                                                                <input
                                                                    type="file"
                                                                    onChange={(e) => {
                                                                        const file = e.target.files[0];
                                                                        if (file) {
                                                                            const fileType = file.type;
                                                                            const allowedTypes = ['image/jpeg', 'image/png'];

                                                                            if (!allowedTypes.includes(fileType)) {
                                                                                triggerMessage('Please select a .jpg, .jpeg, or .png file.', 'error');
                                                                                setUpdProdImage(null);
                                                                                return;
                                                                            } else {
                                                                                setUpdProdImage(file)
                                                                            }
                                                                        }
                                                                    }}
                                                                />
                                                            ) : (
                                                                <img src={`/myapi/api/images?imageName=${item.prodImageUrl}`} alt="image" style={{ width: '30px', height: '30px' }} />
                                                            )}
                                                        </td>
                                                        <td>
                                                            {editMode === item.prodId ? (
                                                                <IconButton className='IconButton' onClick={() => handleSave(item.prodId)}>
                                                                    <SaveIcon className="saveIcon" />
                                                                </IconButton>
                                                            ) : (
                                                                <IconButton className='IconButton' onClick={() => handleEdit(item.prodId)}>
                                                                    <EditIcon className="editIcon" />
                                                                </IconButton>
                                                            )}
                                                            <IconButton className='IconButton' onClick={() => handleDelete(item.prodId)}>
                                                                <DeleteIcon className="deleteIcon" />
                                                            </IconButton>
                                                        </td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </Table>
                                    ) : (
                                        <h3>No products found</h3>
                                    )}
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                {open && <AddProduct open={open} handleClose={handleClose} triggerMessage={triggerMessage} fetchProducts={fetchProducts} />}
            </div>
        </div>
    );
}

export default Products;
