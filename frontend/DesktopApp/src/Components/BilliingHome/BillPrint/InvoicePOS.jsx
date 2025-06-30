import React from 'react';
import './print.css'; // Assuming you save your CSS in a file named 'InvoicePOS.css'

const InvoicePOS = ({ settings, cartItems, printPayload }) => {
  const date = new Date(Date.now());
  
  return (


    <div id="invoice-POS">
      <center id="top">
        <div className=""><img
                        src={settings.business_logo}
                        className='mr-2'
                        alt="EasyBilling Logo"
                        style={{ height: '60px',borderRadius:'999px',width:'60px' }}
                    /></div>
        <div className="info">
          <h2>{settings.business_name}</h2>
        </div>
      </center>

      <div id="mid">
        <div className="info">
          <p>
            Address : {settings.business_address}
            <br />
            Email   : {settings.business_email}
            <br />
            Phone   : {settings.business_mobile}
            <br />
            Date   : {date.toLocaleString()}
            <br />
            <strong>Customer Name:{printPayload.customerSelected.custFullName}</strong> <br />
            <strong>Customer Mobile:{printPayload.customerSelected.custMobile}</strong>
          </p>
        </div>
      </div>

      <div id="bot">
        <div id="table">
          <table>
            <thead>
              <tr className="tabletitle">
                <td className="item"><h2>Item</h2></td>
                <td className="Hours"><h2>price</h2></td>
                <td className="Hours"><h2>Qty</h2></td>
                <td className="Rate"><h2>Sub Total</h2></td>
              </tr>

            </thead>
            <tbody>
              {cartItems.map((item, index) =>
                <tr className="service" key={index}>
                  <td className="tableitem"><p className="itemtext">{item.prodName}</p></td>
                  <td className="tableitem"><p className="itemtext">{item.prodPrice}</p></td>
                  <td className="tableitem"><p className="itemtext">{item.quantity}</p></td>
                  <td className="tableitem"><p className="itemtext">{item.prodPrice * item.quantity}</p></td>
                </tr>
              )}






              <tr className="tabletitle">
                <td></td>
                <td className="Rate"><h2>Net Total</h2></td>
                <td className="payment"><h2>{printPayload.grandTotal}</h2></td>
                <td></td>
              </tr>
            </tbody>
          </table>

          <div className="info">
            <p>
              Total Amount : {printPayload.totalAmount}
              <br />
              Tax Amount   : {printPayload.taxAmount}
              <br />
              {
                printPayload.discountAmount > 0
                  ?
                  <>
                    Discount Percent   :{printPayload.discountPercent}
                    <br />
                    Discount Amount   :{printPayload.discountAmount}
                    <br />
                  </> :
                  <></>
              }
              Service Charge   : {printPayload.serviceCharge}
              <br />
              <strong>Net Total :{printPayload.grandTotal}</strong>

            </p>
          </div>
        </div>

        <div id="legalcopy">
          <p className="legal"><strong>Thank you, Visit Again..</strong> </p>
        </div>
      </div>
    </div>
  );
};

export default InvoicePOS;
