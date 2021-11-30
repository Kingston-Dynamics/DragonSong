package com.kingstondynamics.dragonsong.database.schema.software.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author peter
 */

@Entity(name = "ApplicationModel")
@Table(name = "applications", schema = "ds_software")
public class ApplicationModel {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Integer id;
    
    @Column (name = "application_name")
    private String name;
    
    @Column(name = "is_deployed")
    private boolean deployed;

    @GeneratedValue
    @Column (name = "application_key")
    private UUID key;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String softwarename) {
        this.name = softwarename;
    }
    
    public boolean isDeployed() {
        return deployed;
    }
    
    public void setDeployed(boolean deployed) {
        this.deployed = deployed;
    }
    
    public UUID getKey() {
        return key;
    }
    
    public void setKey(UUID apikey) {
        this.key = apikey;
    }
}
