import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'

function AllCustomerList() {
    const [customers, setCustomers] = useState([])


    const fetchCustomer = async () => {
        const response = await axios.get("/myapi/api/customer/allcustomers")
        setCustomers(response.data)
        console.log(response.data);
        
    }
    useEffect(() => {
        fetchCustomer();
    }, [])
    return (
        <div>
            <Table>
                <thead>
                    <th>Sr No</th>
                    <th>Customer Id</th>
                    <th>Customer Name</th>
                    <th>Contact Number</th>
                </thead>
                <tbody>
                    {customers.map((cust,index) => (
                        <tr key={index}>
                            <td>{index+1}</td>
                            <td>{cust.custId}</td>
                            <td>{cust.custFullName}</td>
                            <td>{cust.custMobile}</td>
                        </tr>
                    ))}


                </tbody>
            </Table>
        </div>
    )
}

export default AllCustomerList
