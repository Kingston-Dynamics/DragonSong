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

package com.kdyncs.dragonsong.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    
    public static void main(String[] args) {
        
        //Log Shit
        Logger log = LogManager.getLogger();
        log.info("Starting up DragonSong API");
        
        // Create a spring Application so we have more control over initialization
        SpringApplication application = new SpringApplication(Application.class);
        
        // Custom Properties so that we can pass additional arguments to Spring
        //Properties properties = new Properties();
        
        // We'll use this to assemble a list of Config Locations
        //StringBuilder configLocations = new StringBuilder();
        
        // Add a config folder
        //configLocations.append("classpath:config/database/,");
        //configLocations.append("classpath:config/server/,");
        //configLocations.append("classpath:config/services/");
        
        // Add the Folders as Config Locations
        //properties.setProperty("spring.config.additional-location", configLocations.toString());
        //application.setDefaultProperties(properties);
        
        
        //Run Application For Real
        SpringApplication.run(Application.class, args);
    }
}
