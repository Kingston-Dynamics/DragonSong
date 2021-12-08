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
