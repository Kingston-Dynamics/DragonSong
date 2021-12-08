package com.kingstondynamics.dragonsong.database.schema.prototype.dao;

import com.kingstondynamics.dragonsong.database.schema.prototype.model.AddressModel;
import com.kingstondynamics.dragonsong.database.schema.prototype.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDAO {
    
    private AddressRepository repo;
    
    @Autowired
    public AddressDAO(AddressRepository repo) {
        this.repo = repo;
    }
    
    public void save(AddressModel item) {
        repo.save(item);
    }
}
