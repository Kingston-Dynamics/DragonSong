package com.kingstondynamics.dragonsong.database.schema.organization.repository;

import com.kingstondynamics.dragonsong.database.schema.organization.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel, Integer> {

}
