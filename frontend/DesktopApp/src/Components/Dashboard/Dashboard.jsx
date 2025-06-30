import React, { useState } from 'react'
import { Routes, Route, Link, Outlet } from 'react-router-dom';
import NavigationBar from '../Navbar/NavigationBar'
import BillingHome from '../BilliingHome/BillingHome'
import Category from '../Category/Category';
import Products from '../Prodoucts/Products';
import ReportPage from '../Reports/ReportPage';
import MessageBox from '../MessageBox/Messagebox';
import Setting from '../SettingMaster/Setting';
import InvoicePOS from '../BilliingHome/BillPrint/InvoicePOS';
import AllCustomerList from '../Reports/AllCustomerList';



function Dashboard({settings,fetchSetting}) {
  const [messageData, setMessageData] = useState({ message: '', severity: 'success', timestamp: Date.now() });

  const triggerMessage = (msg, sev) => {
    setMessageData({ message: msg, severity: sev, timestamp: Date.now() });
  }
  return (
    <>
    <NavigationBar settings={settings} /> 
    <MessageBox message={messageData.message} severity={messageData.severity} timestamp={messageData.timestamp}/>
    <div style={{ paddingTop: '40px' }}>
     
      <Routes>
        <Route path="/" element={<BillingHome settings={settings} triggerMessage={triggerMessage}/>} />
        <Route path="/billprint" element={<InvoicePOS settings={settings} triggerMessage={triggerMessage}/>} />
        <Route path="/category" element={<Category settings={settings} triggerMessage={triggerMessage}/>} />
        <Route path="/products" element={<Products settings={settings} triggerMessage={triggerMessage}/>} />
        <Route path="/reports" element={<ReportPage settings={settings} triggerMessage={triggerMessage}/>} />
        <Route path="/setting" element={<Setting settings={settings} triggerMessage={triggerMessage} fetchSetting={fetchSetting}/>} />
        <Route path="/allcustomers" element={<AllCustomerList settings={settings} triggerMessage={triggerMessage}/>} />
      </Routes>
      <Outlet />  
    </div>
    </>
  )
}

export default Dashboard
