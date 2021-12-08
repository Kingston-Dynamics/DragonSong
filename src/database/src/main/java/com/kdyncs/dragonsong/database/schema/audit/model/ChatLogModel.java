package com.kdyncs.dragonsong.database.schema.audit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author peter
 */

@Entity(name = "ChatLogModel")
@Table(name = "chatlog", schema = "ds_audit")
public class ChatLogModel implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 7272592350952536722L;
    
    @Id
    private UUID id;
    
    @Column
    private String content;
    
    @Column
    private OffsetDateTime createtimestamp;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public OffsetDateTime getCreatetimestamp() {
        return createtimestamp;
    }
    
    public void setCreatetimestamp(OffsetDateTime createtimestamp) {
        this.createtimestamp = createtimestamp;
    }
}
