package com.kingstondynamics.dragonsong.database.schema.data.model;

import javax.persistence.*;

@Entity(name = "SubdivisionModel")
@Table(name = "subdivision", schema = "ds_data")
public class SubdivisionModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private Integer countryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}
