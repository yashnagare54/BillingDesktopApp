import React, { useEffect, useState } from 'react';
import { Table, Row, Col, Card, Button, Form } from 'react-bootstrap';
import { styled } from '@mui/system';
import axios from 'axios';
import AddCategory from './AddCategory';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import SaveIcon from '@mui/icons-material/Save';
import './Category.css'; // Make sure to create this CSS file or include the styles in your global CSS

function Category({ triggerMessage }) {

    const [open, setOpen] = useState(false);
    const [editMode, setEditMode] = useState(null);
    const [categories, setCategories] = useState([]);
    const [updatedCatName, setUpdatedCatName] = useState('');
    const [updatedCatImage, setUpdatedCatImage] = useState(null);
    const [activeStatus, setActiveStatus] = useState('True');

    const fetchcategories = async () => {
        try {
            const response = await axios.get('/myapi/api/category/allcategories');
            setCategories(response.data);
            
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    useEffect(() => {
        fetchcategories();
    }, []);

    const toggleCategoryForm = () => {
        setOpen(!open);
    };

    const handleClose = () => {
        setOpen(false);
    };

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
        const category = categories.find(cat => cat.cateId === id);
        let imageToSend = updatedCatImage;

        if (activeStatus === '') {
            triggerMessage('Select Active/Inactive Status', 'error');
            return;
        }

        try {
            const formData = new FormData();
            formData.append('cateName', updatedCatName || category.cateName);
            formData.append('isActive', activeStatus);

            if (imageToSend) {
                formData.append('imageName', imageToSend);
            } else {
                // Fetch existing image if not updated
                const imageResponse = await axios.get(`/myapi/api/images?imageName=${category.cateImageUrl}`, { responseType: 'blob' });
                imageToSend = new File([imageResponse.data], category.cateImageUrl, { type: imageResponse.headers['content-type'] });
                formData.append('imageName', imageToSend);
            }

            const response = await axios.put(`/myapi/api/category/update/${id}`, formData);

            if (response.status === 200) {
                fetchcategories();
                setEditMode(null);
                setActiveStatus('True');
                triggerMessage('Category updated successfully', 'success');
            }
        } catch (error) {
            triggerMessage('Failed to update category', 'error');
        }
    };

    const handleEdit = (id) => {
        setEditMode(id);
    };

    const handleDelete = async (id) => {
        const confirmed = window.confirm('Delete the category?');
        if (confirmed) {
            try {
                const response = await axios.delete(`/myapi/api/category/delete/${id}`);
                if (response.status === 200) {
                    fetchcategories();
                    triggerMessage('Category deleted successfully', 'success');
                }
            } catch (error) {
                triggerMessage('Failed to delete category', 'error');
            }
        }
    };

    return (
        <div className='container-fixed'>
            <div className='content-wrapper'>
                <Row className='mt-5'>
                    <Col xs={10}>
                        <h3>All Categories</h3>
                    </Col>
                    <Col xs={2}>
                        <StyledButton variant="contained" color="primary" onClick={toggleCategoryForm}>+ Add Category</StyledButton>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <Card className='mt-2 scrollable-card'>
                            <Card.Body>
                                <Card.Title className='text-center'>All Category List</Card.Title>
                                <div className='table-container'>
                                    {categories.length > 0 ? (
                                        <Table striped bordered hover>
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>NAME</th>
                                                    <th>IMAGE</th>
                                                    <th>ACTIVE/INACTIVE</th>
                                                    <th>ACTION</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                
                                                {categories.map(item => (
                                                    <tr key={item.cateId} className='TableRowInCategory'>
                                                        <td>{item.cateId}</td>
                                                        {editMode === item.cateId ? (
                                                            <td>
                                                                <input
                                                                    type="text"
                                                                    defaultValue={item.cateName}
                                                                    onInput={(e) => {
                                                                        const newValue = e.target.value;
                                                                        if (/^[A-Za-z\s]*$/.test(newValue)) {
                                                                            setUpdatedCatName(newValue);
                                                                        } else {
                                                                            e.target.value = updatedCatName;
                                                                            if (newValue !== '') {
                                                                                triggerMessage('Category name should contain only letters and spaces.', 'error');
                                                                            }
                                                                        }
                                                                    }}
                                                                />
                                                            </td>
                                                        ) : (
                                                            <td>{item.cateName}</td>
                                                        )}

                                                        {editMode === item.cateId ? (
                                                            <td>
                                                                <input
                                                                    type="file"
                                                                    onChange={(e) => {
                                                                        const file = e.target.files[0];
                                                                        if (file) {
                                                                            const fileType = file.type;
                                                                            const allowedTypes = ['image/jpeg', 'image/png'];

                                                                            if (!allowedTypes.includes(fileType)) {
                                                                                triggerMessage('Please select a .jpg, .jpeg, or .png file.', 'error');
                                                                                setUpdatedCatImage(null);
                                                                                return;
                                                                            } else {
                                                                                setUpdatedCatImage(file);
                                                                            }
                                                                        }
                                                                    }}
                                                                />
                                                            </td>
                                                        ) : (
                                                            <td>
                                                                <img
                                                                    src={`/myapi/api/images?imageName=${item.cateImageUrl}`}
                                                                    alt={item.cateName}
                                                                    style={{ width: '30px', height: '30px' }}
                                                                />
                                                            </td>
                                                        )}

                                                        {editMode === item.cateId ? (
                                                            <td>
                                                                <Form.Check
                                                                    type="radio"
                                                                    label="Active"
                                                                    name={`status-${item.cateId}`}
                                                                    checked={activeStatus === 'True'}
                                                                    onChange={() => setActiveStatus('True')}
                                                                />
                                                                <Form.Check
                                                                    type="radio"
                                                                    label="Inactive"
                                                                    name={`status-${item.cateId}`}
                                                                    checked={activeStatus === 'False'}
                                                                    onChange={() => setActiveStatus('False')}
                                                                />
                                                            </td>
                                                        ) : (
                                                            <td>
                                                                {item.active ? 'Active' : 'Inactive'}
                                                            </td>
                                                        )}

                                                        <td>
                                                            {editMode === item.cateId ? (
                                                                <IconButton className='IconButton' onClick={() => handleSave(item.cateId)}>
                                                                    <SaveIcon className="saveIcon" />
                                                                </IconButton>
                                                            ) : (
                                                                <IconButton className='IconButton' onClick={() => handleEdit(item.cateId)}>
                                                                    <EditIcon className="editIcon" />
                                                                </IconButton>
                                                            )}
                                                            <IconButton className='IconButton' onClick={() => handleDelete(item.cateId)}>
                                                                <DeleteIcon className="deleteIcon" />
                                                            </IconButton>
                                                        </td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </Table>
                                    ) : (
                                        <h2>No Categories Found</h2>
                                    )}
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                {open && <AddCategory open={open} handleClose={handleClose} fetchcategories={fetchcategories} triggerMessage={triggerMessage} />}
            </div>
        </div>
    );
}

export default Category;
