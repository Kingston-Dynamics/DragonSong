package com.kingstondynamics.dragonsong.database.schema.audit.repository;

import com.kingstondynamics.dragonsong.database.schema.audit.model.ChatLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author peter
 */

@Repository
public
interface ChatLogRepository extends JpaRepository<ChatLogModel, UUID> {

}
