/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
        id uuid,
        username VARCHAR(255) UNIQUE NOT NULL,
        email VARCHAR (255) NOT NULL,
        password VARCHAR (60) NOT NULL,
        
        create_timestamp TIMESTAMP NOT NULL,
        
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
        
        -- KEY CONSTRAINTS
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
        
		-- KEY CONSTRAINTS
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

		-- KEY CONSTRAINTS
		PRIMARY KEY (ID)
	);

	CREATE TABLE ds_service.application
	(
		
		-- COLUMNS
		ID uuid,

		ApplicationName VARCHAR(255) NOT NULL,

		-- KEY CONSTRAINTS
		PRIMARY KEY (ID)

	);

	CREATE TABLE ds_service.deployment
	(
		-- COLUMNS
		ID uuid,

		ApplicationID uuid,
		EnvironmentID uuid,

		DeploymentName VARCHAR(255) NOT NULL,
		
		-- KEY CONSTRAINTS
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
        
		-- KEY CONSTRAINTS
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
        
		-- KEY CONSTRAINTS
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
		
		-- KEY CONSTRAINTS
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
		
		-- KEY CONSTRAINTS
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
		id uuid,
		createTimeStamp TIMESTAMP,
		active boolean,
		
		vendorID uuid NOT NULL,
		
		applicationKeyID uuid,
		configID uuid,
		ruleID uuid,
		
		
		-- KEY CONSTRAINTS
		PRIMARY KEY (id),
		
		FOREIGN KEY (vendorID)
			REFERENCES ds_vendor.account(ID),
		
		FOREIGN KEY (applicationKeyID)
			REFERENCES ds_software.applicationKey(ID),
			
		FOREIGN KEY (configID)
			REFERENCES ds_config.config(ID),
			
		FOREIGN KEY (ruleID)
			REFERENCES ds_config.rules(ID)
			
   	);
   
	------------------
    -- Audit Tables --
    ------------------

    create table ds_audit.chatlog
	(
		-- COLUMNS
		id uuid,
		content VARCHAR(255),
		create_timestamp TIMESTAMP,

		-- KEY CONSTRAINTS
		PRIMARY KEY (id)
	);

	create table ds_audit.connection_log
	(
		-- COLUMNS
		id uuid,
		address VARCHAR(255),
		create_timestamp TIMESTAMP,

		-- KEY CONSTRAINTS
		PRIMARY KEY (id)
	);

--ROLLBACK;
COMMIT;
