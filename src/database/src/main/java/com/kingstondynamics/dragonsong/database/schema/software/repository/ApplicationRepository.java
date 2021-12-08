package com.kingstondynamics.dragonsong.database.schema.software.repository;

import com.kingstondynamics.dragonsong.database.schema.software.model.ApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author peter
 */

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, UUID> {
    
    @Query("from ApplicationModel app where app.deployed=true")
    List<ApplicationModel> findAllActiveDeployments();
    
}
