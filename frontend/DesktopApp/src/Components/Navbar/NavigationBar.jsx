import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import npprofile from '../../assets/assetsLocal/noprofile1.jpg';
import { AppBar, Toolbar, Typography, Button, Container, Menu, MenuItem } from '@mui/material';
import { NavLink } from 'react-router-dom';
import { styled } from '@mui/system';

// Styled components
const StyledAppBar = styled(AppBar)(({ theme }) => ({
    backgroundColor: theme.palette.primary.main,
    width: '100%',
    top: 0,
    height: 45,
    justifyContent: 'center',
}));

const StyledToolbar = styled(Toolbar)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'space-between', // Distribute items between start and end
}));

const StyledTypography = styled(Typography)(({ theme }) => ({
    fontFamily: theme.typography.fontFamily,
}));

const StyledNavLink = styled(Button)(({ theme }) => ({
    textDecoration: 'none',
    color: 'inherit',
    marginRight: theme.spacing(5), // Adjust spacing between links using theme spacing
}));

const ProfileImage = styled('img')(({ theme }) => ({
    height: '40px',
    borderRadius: '999px',
    cursor: 'pointer',
}));

const LogoutUser=()=>{
    window.location.href = '/';
}
const NavigationBar = ({ settings }) => {
    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const navigate = useNavigate(); // Initialize useNavigate

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleSettingsClick = () => {
        navigate('/dashboard/setting'); // Navigate to settings page
        handleClose(); // Close the menu
    };

    return (
        <StyledAppBar>
            <StyledToolbar component={Container}>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <img
                        src={settings.business_logo}
                        className='mr-2'
                        alt="EasyBilling Logo"
                        style={{ height: '40px',borderRadius:'999px',width:'40px' }}
                    />
                    <StyledTypography className='mr-4' variant="h6" component="div">
                        {settings.business_name}
                    </StyledTypography>
                    <StyledNavLink component={NavLink} to="/dashboard">
                    POS
                    </StyledNavLink>
                    <StyledNavLink component={NavLink} to="/dashboard/category">
                        Category
                    </StyledNavLink>
                    <StyledNavLink component={NavLink} to="/dashboard/products">
                        Products
                    </StyledNavLink>
                    <StyledNavLink component={NavLink} to="/dashboard/reports">
                        Reports
                    </StyledNavLink>
                    
                </div>
                <ProfileImage
                    src={npprofile}
                    alt="Profile Icon"
                    onClick={handleClick}
                />
                <Menu
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    PaperProps={{
                        style: {
                            top: 45, // Adjust this if needed
                            right: 0,
                            width: '200px', // Adjust width as needed
                        },
                    }}
                >
                    <MenuItem onClick={handleClose}>Profile Details</MenuItem>
                    <MenuItem onClick={handleSettingsClick}>Business Settings</MenuItem>
                    <MenuItem onClick={LogoutUser}>Logout</MenuItem>
                </Menu>
            </StyledToolbar>
        </StyledAppBar>
    );
};

export default NavigationBar;
