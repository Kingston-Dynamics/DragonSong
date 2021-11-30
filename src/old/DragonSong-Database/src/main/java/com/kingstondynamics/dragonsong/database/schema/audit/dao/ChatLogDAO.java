package com.kingstondynamics.dragonsong.database.schema.audit.dao;

import com.kingstondynamics.dragonsong.database.schema.audit.model.ChatLogModel;
import com.kingstondynamics.dragonsong.database.schema.audit.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatLogDAO {
    
    private final ChatLogRepository repository;
    
    @Autowired
    public ChatLogDAO(ChatLogRepository repository) {
        this.repository = repository;
    }
    
    public void save(ChatLogModel log) {
        repository.save(log);
    }
}
