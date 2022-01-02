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
	drop schema if exists ds_software cascade;
	
commit;
	
------------------------
-- Create All Schemas --
------------------------
	
start transaction;
	
	create schema ds_audit;
	create schema ds_vendor;
	create schema ds_software;
	
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
        password VARCHAR (60) NOT NULL,
        
        create_timestamp TIMESTAMP NOT NULL,
        
        -- KEY CONSTRAINTS
        PRIMARY KEY (ID)
    );

    -------------------
    -- SOFTWARE INFO --
    -------------------

	CREATE TABLE ds_software.application
	(
		-- COLUMNS
		id uuid,
		name VARCHAR(255) NOT NULL,
		active boolean NOT NULL,
		api_key uuid NOT NULL,

		-- KEY CONSTRAINTS
		PRIMARY KEY (id)
	);

	------------------
    -- Audit Tables --
    ------------------

	create table ds_audit.connection_log
	(
		-- COLUMNS
		id uuid,
		address VARCHAR(255),
		create_timestamp TIMESTAMP,

		-- KEY CONSTRAINTS
		PRIMARY KEY (id)
	);

COMMIT;