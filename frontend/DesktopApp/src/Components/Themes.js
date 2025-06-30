import { createTheme } from '@mui/material/styles';

// Define your custom theme
const theme = createTheme({
    palette: {
        primary: {
            main: '#264653',  
            darker:'#337691'
            // main: '#ff0000',
        },
        secondary: {
            main: '#f5f5f5',  
        },
        background: {
            default: 'white',

        },
        
    },
    typography: {
        fontFamily: 'Roboto, Arial, sans-serif',  
        
    },
});

export default theme;
