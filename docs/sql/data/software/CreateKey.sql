/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  peter
 * Created: Aug 30, 2018
 */

insert into ds_software.applicationkey (
	id,
	appkey,
	createtimestamp,
	applicationid,
	deploymentid
) values (
	?,
	?,
	current_timestamp,
	?,
	?
);