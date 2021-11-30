/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  peter
 * Created: Aug 7, 2018
 */

INSERT INTO ds_vendor.account (
    ID, 
    USERNAME,
    Email, 
    Pass, 
    CreateTimeStamp
) VALUES
(
    ?, 
    ?,
    ?,
    ?,
    CURRENT_TIMESTAMP
);