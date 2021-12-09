package com.kdyncs.dragonsong.database.schema.software.dao;

import com.kdyncs.dragonsong.common.SafeList;
import com.kdyncs.dragonsong.database.schema.software.repository.ApplicationRepository;
import com.kdyncs.dragonsong.database.schema.software.model.ApplicationModel;
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
