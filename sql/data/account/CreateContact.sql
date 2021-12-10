/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  peter
 * Created: Aug 12, 2018
 */

INSERT INTO ds_vendor.contact(
    ID, 
        
    FirstName, 
    LastName, 
        
    TelPrimary, 
    TelOther,
        
    CreateTimeStamp,
       
    VendorID
    
)VALUES
( 
    ?, 
    ?,
    ?,
    ?,
    ?,
    CURRENT_TIMESTAMP,
    ?
);