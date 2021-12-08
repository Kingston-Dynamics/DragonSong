package com.kingstondynamics.dragonsong.database.schema.prototype.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AddressModel")
@Table(name = "address", schema = "ds_prototype")
public class AddressModel {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
