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
	
-----------------
-- Drop Tables --
-----------------
	/*
start transaction;
	

	-- Drop Account Tables
	drop table if exists ds_vendor.account cascade;
	drop table if exists ds_vendor.details cascade;
	drop table if exists ds_vendor.contact cascade;

	-- Drop Service Tables
	drop table if exists ds_service.environment cascade;
	drop table if exists ds_service.application cascade;
	drop table if exists ds_service.deployment cascade;
	
	-- Drop Vendor Application Tables
	drop table if exists ds_software.application cascade;
	drop table if exists ds_software.applicationkey cascade;
		
	-- Drop Config Tables
	drop table if exists ds_config.config cascade;
	drop table if exists ds_config.rules cascade;
	
	-- Drop Deployment Tables
	drop table if exists ds_deployment.deployment cascade;
	
	-- Drop logging tables
	drop table if exists ds_audit.chatlog cascade;
	
commit;
	*/
	
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
	
	create schema ds_audit;
	create schema ds_vendor;
	create schema ds_service;
	create schema ds_software;
	create schema ds_config;
	create schema ds_deployment;
	
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
        
        CreateTimeStamp TIMESTAMP NOT NULL,
        
        -- KEY CONSTRAINTS
        PRIMARY KEY (ID)
    );
    
    CREATE TABLE ds_vendor.details
    (
		-- COLUMNS
		ID uuid,
        
        VendorName VARCHAR(255) NOT NULL,
        
        CreateTimeStamp TIMESTAMP NOT NULL,
        
        VendorID uuid NOT NULL,
        
        -- KEY CONSTAINTS
		PRIMARY KEY (ID),
        
		FOREIGN KEY (VendorID)
			REFERENCES ds_vendor.account(ID)
    );
    
    CREATE TABLE ds_vendor.contact
    (
		-- COLUMNS
		ID uuid,
        
		FirstName VARCHAR(255) NOT NULL,
        LastName VARCHAR(255) NOT NULL,
        
        TelPrimary VARCHAR(50),
        TelOther VARCHAR(50),
        
        CreateTimeStamp TIMESTAMP NOT NULL,
        
        VendorID uuid NOT NULL,
        
		-- KEY CONSTAINTS
		PRIMARY KEY (ID),
        
        FOREIGN KEY (VendorID)
			REFERENCES ds_vendor.account(ID)
    );
	
    ------------------
    -- SERVICE INFO --
    ------------------
		
	CREATE TABLE ds_service.environment
	(
		-- COLUMNS
		ID uuid,

		EnvironmentName VARCHAR(255) NOT NULL,

		-- KEY CONSTAINTS
		PRIMARY KEY (ID)
	);

	CREATE TABLE ds_service.application
	(
		
		-- COLUMNS
		ID uuid,

		ApplicationName VARCHAR(255) NOT NULL,

		-- KEY CONSTAINTS
		PRIMARY KEY (ID)

	);

	CREATE TABLE ds_service.deployment
	(
		-- COLUMNS
		ID uuid,

		ApplicationID uuid,
		EnvironmentID uuid,

		DeploymentName VARCHAR(255) NOT NULL,
		
		-- KEY CONSTAINTS
		PRIMARY KEY (ID),

		FOREIGN KEY (ApplicationID)
			REFERENCES ds_service.application(ID),

		FOREIGN KEY (EnvironmentID)
			REFERENCES ds_service.environment(ID)

	);
    
    --------------------
    -- SERVICE ACCESS --
    --------------------
    
    CREATE TABLE ds_software.application
    (
		-- COLUMNS
		ID uuid,
        
		SoftwareName VARCHAR(255) NOT NULL,
        
        CreateTimeStamp TIMESTAMP NOT NULL,
        VendorID uuid NOT NULL,
        
		-- KEY CONSTAINTS
		PRIMARY KEY (ID),
        
        FOREIGN KEY (VendorID)
			REFERENCES ds_vendor.account(ID)
    );
        
    CREATE TABLE ds_software.applicationkey
	(
		-- COLUMNS
		ID uuid,
        
        APPKey uuid unique not null,
        
        CreateTimeStamp TIMESTAMP NOT NULL,
        ApplicationID uuid NOT NULL,
        DeploymentID uuid NOT NULL,
        
		-- KEY CONSTAINTS
		PRIMARY KEY (ID),
        
        FOREIGN KEY (ApplicationID)
			REFERENCES ds_software.application(ID),
            
		FOREIGN KEY (DeploymentID)
			REFERENCES ds_service.deployment(ID)
    );
    
	
	---------------------------
	-- Special Configuration --
	---------------------------
	
    create table ds_config.config
    (
    	-- COLUMNS
		ID uuid,
        
		fileData text,
		fileName varchar(255),
		
		vendorID uuid NOT NULL,
		
		FOREIGN KEY (vendorID)
			REFERENCES ds_vendor.account(ID),
		
		-- KEY CONSTAINTS
		PRIMARY KEY (ID)    
    );
	
    create table ds_config.rules
    (
    	-- COLUMNS
		ID uuid,
        
		fileData text,
		fileName varchar(255),
		dsl boolean,
		
		vendorID uuid NOT NULL,
		
		-- KEY CONSTAINTS
		PRIMARY KEY (ID),
		
		FOREIGN KEY (vendorID)
			REFERENCES ds_vendor.account(ID)
		
    );
	
   	-----------------------
   	-- Deployment Tables --
   	-----------------------
   
   	create table ds_deployment.deployment
   	(
   		-- COLUMNS
		ID uuid,
		createTimeStamp TIMESTAMP,
		active boolean,
		
		vendorID uuid NOT NULL,
		
		applicationKeyID uuid,
		configID uuid,
		ruleID uuid,
		
		
		-- KEY CONSTAINTS
		PRIMARY KEY (ID),
		
		FOREIGN KEY (vendorID)
			REFERENCES ds_vendor.account(ID),
		
		FOREIGN KEY (applicationKeyID)
			REFERENCES ds_software.applicationKey(ID),
			
		FOREIGN KEY (configID)
			REFERENCES ds_config.config(ID),
			
		FOREIGN KEY (ruleID)
			REFERENCES ds_config.rules(ID)
			
   	);
   
	--------------------
    -- Logging Tables --
    --------------------
   	
	create table ds_audit.chatlog
	(
		-- COLUMNS
		ID uuid,
		content VARCHAR(255),
		CreateTimeStamp TIMESTAMP,
		
        APPKey uuid NOT NULL,
        
		-- KEY CONTRAINTS
		PRIMARY KEY (ID),
        
        FOREIGN KEY (APPKey)
			REFERENCES ds_software.applicationkey(APPKey)
	);

--ROLLBACK;
COMMIT;
