package com.kdyncs.dragonsong.database.schema.prototype.repository;

import com.kdyncs.dragonsong.database.schema.prototype.model.MailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<MailModel, Integer> {

}
