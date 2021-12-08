package com.kdyncs.dragonsong.database.schema.organization.dao;

import com.kdyncs.dragonsong.database.schema.organization.model.OrganizationModel;
import com.kdyncs.dragonsong.database.schema.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationDAO {
    
    private OrganizationRepository repo;
    
    @Autowired
    public OrganizationDAO(OrganizationRepository repo) {
        this.repo = repo;
    }
    
    public void save(OrganizationModel item) {
        repo.save(item);
    }
}
