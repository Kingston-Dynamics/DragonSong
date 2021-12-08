package com.kdyncs.dragonsong.database.schema.prototype.model;

import javax.persistence.*;

@Entity(name = "MailModel")
@Table(name = "mail", schema = "ds_prototype")
public class MailModel {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String subject;
    
    @Column
    private String body;
    
}
