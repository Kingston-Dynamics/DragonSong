/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  peter
 * Created: Aug 16, 2018
 */

--------------------------------------------------
-- Master script to re/generate entire database --
--------------------------------------------------
------------------
-- Drop Schemas --
------------------
	
start transaction;
	
	drop schema if exists ds_audit cascade;
	drop schema if exists ds_vendor cascade;
	drop schema if exists ds_service cascade;
	drop schema if exists ds_software cascade;
	drop schema if exists ds_config cascade;
	drop schema if exists ds_deployment cascade;
	
commit;
	
------------------------
-- Create All Schemas --
------------------------
	
start transaction;
	
	create schema ds_vendor;
	create schema ds_software;
	create schema ds_audit;

commit;
	
	
	
-----------------------
-- Create All Tables --
-----------------------
start transaction;
	
	------------------
    -- Account Info --
    ------------------
    
    create table ds_vendor.account
    ( 
		-- Columns
        ID uuid,
        USERNAME VARCHAR(255) UNIQUE NOT NULL, 
        Email VARCHAR (255) NOT NULL,
        Pass VARCHAR (60) NOT NULL,
        
        /**
         * AUDIT TRAIL
         */
        CreateTimeStamp TIMESTAMP NOT NULL,
        
        -- KEY CONSTRAINTS
        PRIMARY KEY (ID)
    );
    
    --------------------
    -- SERVICE ACCESS --
    --------------------
    
    CREATE TABLE ds_software.application
    (
		-- COLUMNS
		ID uuid,
		
		SoftwareName VARCHAR(255),
        
		/**
		 * DEPLOYMENT DETAILS
		 */
		Deployed boolean,
		APIKey uuid,
		AppID VARCHAR(60),
		AppVer VARCHAR(60),
		
		/**
		 * AUDIT TRAIL
		 */
        CreateTimeStamp TIMESTAMP,
        
		-- KEY CONSTAINTS
		PRIMARY KEY (ID)
    );

	--------------------
    -- Logging Tables --
    --------------------
   	
	create table ds_audit.chatlog
	(
		-- COLUMNS
		ID uuid,
		content VARCHAR(255),
		
		/**
		 * AUDIT TRAIL
		 */
		CreateTimeStamp TIMESTAMP,
		
		-- KEY CONTRAINTS
		PRIMARY KEY (ID)
	);

--ROLLBACK;
COMMIT;
