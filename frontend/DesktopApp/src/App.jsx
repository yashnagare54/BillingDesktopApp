import { ThemeProvider } from '@mui/material/styles';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import theme from './Components/Themes.js';
import Login from './Components/Login/Login.jsx';
import Dashboard from './Components/Dashboard/Dashboard.jsx';
import { useEffect, useState } from 'react';
import axios from 'axios';
import logo from './assets/assetsLocal/logo.png';

function App() {
  const [settings, setSettings] = useState({
    settingid: '1',
    business_name: 'Billing Buddys',
    business_mobile: '7249772495',
    business_email: 'omkarpagade141@gmail.com',
    business_address: 'Wagholi',
    business_gst_number: '12121212',
    business_logo: logo,
  });

  const fetchSetting = async () => {
    const response = await axios.get('/myapi/api/settings');

    if (response.status == 200) {
      setSettings({
        settingid: 1,
        business_name: response.data.businessName,
        business_mobile: response.data.businessMobile,
        business_email: response.data.businessEmail,
        business_address: response.data.businessAddress,
        business_gst_number: response.data.businessGSTNumber,
        business_logo: `/myapi/api/business-logo?businessLogo=${response.data.businessLogoImagePath}`,
      });
    }
  };

  useEffect(() => {
    fetchSetting();
  }, []);

  return (
    <>
      <ThemeProvider theme={theme}>
        <Router>
          <div>
            <Routes>
              <Route path="/" element={<Login settings={settings} />} />
              <Route
                path="/dashboard/*"
                element={
                  <Dashboard settings={settings} fetchSetting={fetchSetting} />
                }
              />
            </Routes>
          </div>
        </Router>
      </ThemeProvider>
    </>
  );
}

export default App;
