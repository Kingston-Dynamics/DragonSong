package com.kdyncs.dragonsong.database.schema.audit.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity(name = "ConnectionLogModel")
@Table(name = "connectionlog", schema = "ds_audit")
public class ConnectionLogModel {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String address;
    
    @Column
    private String code;
    
    @Column
    private OffsetDateTime created;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public OffsetDateTime getCreated() {
        return created;
    }
    
    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }
}
