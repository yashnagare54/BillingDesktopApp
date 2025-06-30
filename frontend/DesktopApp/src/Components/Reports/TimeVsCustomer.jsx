import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Card, Typography } from '@mui/material';

const data = [
  { month: 'Jan', customers: 400 },
  { month: 'Feb', customers: 300 },
  { month: 'Mar', customers: 200 },
  { month: 'Apr', customers: 278 },
  { month: 'May', customers: 189 },
  { month: 'Jun', customers: 239 },
  { month: 'Jul', customers: 349 },
  { month: 'Aug', customers: 400 },
  { month: 'Sep', customers: 300 },
  { month: 'Oct', customers: 200 },
  { month: 'Nov', customers: 278 },
  { month: 'Dec', customers: 189 }
];

const TimeVsCustomerChart = () => (
  <Card style={{ padding: '20px' }}>
    <Typography variant="h6" gutterBottom>
      Time in Month vs. Customers
    </Typography>
    <ResponsiveContainer width="100%" height={350}>
      <LineChart
        data={data}
        
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="month" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="customers" stroke="#8884d8" activeDot={{ r: 8 }} />
      </LineChart>
    </ResponsiveContainer>
  </Card>
);

export default TimeVsCustomerChart;
