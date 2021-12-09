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

package com.kdyncs.dragonsong.database.schema.data.model;

import javax.persistence.*;

@Entity(name = "CountryModel")
@Table(name = "country", schema = "ds_data")
public class CountryModel {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String code;
    
    @Column
    private String name;

    @Column
    private String alpha_two;

    @Column
    private String alpha_three;

    @Column
    private String numeric;
    
    public Integer getId() {
        return id;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }

    public String getAlpha_two() {
        return alpha_two;
    }

    public String getAlpha_three() {
        return alpha_three;
    }

    public String getNumeric() {
        return numeric;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlpha_two(String alpha_two) {
        this.alpha_two = alpha_two;
    }

    public void setAlpha_three(String alpha_three) {
        this.alpha_three = alpha_three;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }
}
