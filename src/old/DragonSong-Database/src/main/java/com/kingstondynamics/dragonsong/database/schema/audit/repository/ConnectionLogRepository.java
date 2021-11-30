package com.kingstondynamics.dragonsong.database.schema.audit.repository;

import com.kingstondynamics.dragonsong.database.schema.audit.model.ConnectionLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ConnectionLogRepository extends JpaRepository<ConnectionLogModel, Integer> {

}
