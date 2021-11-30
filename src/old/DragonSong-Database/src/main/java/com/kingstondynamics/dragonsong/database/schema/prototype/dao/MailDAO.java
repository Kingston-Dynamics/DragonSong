package com.kingstondynamics.dragonsong.database.schema.prototype.dao;

import com.kingstondynamics.dragonsong.database.schema.prototype.model.MailModel;
import com.kingstondynamics.dragonsong.database.schema.prototype.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailDAO {
    
    private MailRepository repo;
    
    @Autowired
    public MailDAO(MailRepository repo) {
        this.repo = repo;
    }
    
    public void save(MailModel item) {
        repo.save(item);
    }
    
}
