import React, { useEffect, useState } from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@mui/material/Button";
import { Row, Col, Card, Form } from "react-bootstrap";
import { styled } from "@mui/material";
import axios from "axios";

const AddProduct = ({ open, handleClose , triggerMessage , fetchProducts }) => {
  const [productName, setProductName] = useState("");
  const [Categoryid, setCategoryid] = useState("");
  const [categories,setCategories]=useState([])
  const [image, setImage] = useState(null);
  const [productPrice, setproductPrice] = useState("");

  const StyledButton = styled(Button)(({ theme }) => ({
    backgroundColor: theme.palette.primary.main,
    border: "none",
    justifyContent: "center",
    "&:hover": {
      backgroundColor: theme.palette.primary.darker,
      cursor: "pointer",
    },
    "&:focus": {
      boxShadow: "none",
    },
  }));

  useEffect(()=>{
    const fetchcategories= async()=>{
       const response= await axios.get('/myapi/api/category/active-categories')
       setCategories(response.data)
        
    }
    fetchcategories()

},[])


  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent the form from submitting and refreshing the page
    const formData = new FormData();
    formData.append("cateId", Categoryid);
    formData.append("prodName", productName);
    formData.append("prodPrice", productPrice);
    formData.append("imageName", image);
    try {
      const response = await axios.post(`/myapi/api/product/category/${Categoryid}/add_product`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      if (response.status==201) {
        fetchProducts()
        triggerMessage('Product Added Successfully','success')
        handleClose();
        
      }
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogContent>
        <Row>
          <Col md={12}>
            <Card className="fixed-card">
              <Card.Body>
                <Card.Title className="text-center">Add Product</Card.Title>
                <Row>
                  <Col md={12}>
                    <Form onSubmit={handleSubmit}>
                      <Form.Group controlId="formtProductName">
                        <Form.Label>Category Id:</Form.Label>
                        <Form.Control
                          as="select"
                          required
                          value={Categoryid}
                          onChange={(e) => setCategoryid(e.target.value)}
                        >
                          <option value="">Select a category</option>
                           
                          {categories.map((category) => (
                            <option key={category.cateId} value={category.cateId}>
                              {category.cateName}
                            </option>
                          ))}
                        </Form.Control>
                      </Form.Group>
                      <Form.Group controlId="formtProductName">
                        <Form.Label>Product Name:</Form.Label>
                        <Form.Control
                          type="text"
                          required
                          placeholder="Enter product name"
                          value={productName}
                          onChange={(e) => {
                            // Allow letters and spaces, with a maximum length of 20 characters
                            if (/^[A-Za-z\s]*$/.test(e.target.value) && e.target.value.length <= 18) {
                                setProductName(e.target.value);
                            } else {
                                triggerMessage('Only letters and spaces allowed, up to 18 characters.', 'error');
                            }
                        }}
                        />
                      </Form.Group>

                      <Form.Group>
                        <Form.Label className="mt-3">Product Price:</Form.Label>
                        <Form.Control
                          type="text"
                          required
                          placeholder="Enter product price"
                          value={productPrice}
                          onChange={(e) => {
                            if (/^\d*(\.\d*)?$/.test(e.target.value) && e.target.value.length<=6) {
                              setproductPrice(e.target.value); 
                            } else {
                              triggerMessage('Price should contain only numbers..', 'error');
                            }
                          }}
                        />
                      </Form.Group>
                      <Form.Group controlId="formtProductName">
                        <Form.Label className="mt-3">Product Image:</Form.Label>
                        <Form.Control
                          type="file"
                          required
                          placeholder="Enter product name"
                          onChange={(e) => setImage(e.target.files[0])}
                        />
                      </Form.Group>
                      <Button color="primary" type="submit">
                        Add Product
                      </Button>
                      <Button onClick={handleClose} color="primary">
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

export default AddProduct;
