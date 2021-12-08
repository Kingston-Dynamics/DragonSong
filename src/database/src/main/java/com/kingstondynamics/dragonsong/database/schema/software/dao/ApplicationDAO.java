package com.kingstondynamics.dragonsong.database.schema.software.dao;

import com.kingstondynamics.dragonsong.commons.SafeList;
import com.kingstondynamics.dragonsong.database.schema.software.model.ApplicationModel;
import com.kingstondynamics.dragonsong.database.schema.software.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationDAO {
    
    private final ApplicationRepository repository;
    
    @Autowired
    public ApplicationDAO(ApplicationRepository repository) {
        this.repository = repository;
    }
    
    public List<ApplicationModel> getAllActiveApplications() {
        return SafeList.get(repository.findAllActiveDeployments());
    }
}
