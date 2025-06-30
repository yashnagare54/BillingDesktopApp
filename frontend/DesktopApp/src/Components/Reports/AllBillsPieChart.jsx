// src/PieChartComponent.jsx
import React, { useState } from 'react';
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts';
import { Paper } from '@mui/material';

// Sample data
// const data = [
//   { name: 'Cash', value: 10 },
//   { name: 'Card Payment', value: 10 },
//   { name: 'UPI', value: 30 },
//   { name: 'Not Selected', value: 300 },
   
// ];

// Define colors for the pie chart segments
const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

const PieChartComponent = ({data }) => {
  



  return (
    <Paper style={{ padding: 20 } } >
      <PieChart width={350} height={390} >
        <Pie
          data={data}
          cx="50%"
          cy="50%"
          outerRadius={130}
          fill="#8884d8"
          dataKey="value"
        >
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
          ))}
        </Pie>
        <Tooltip />
        <Legend />
      </PieChart>
    </Paper>
  );
};

export default PieChartComponent;
