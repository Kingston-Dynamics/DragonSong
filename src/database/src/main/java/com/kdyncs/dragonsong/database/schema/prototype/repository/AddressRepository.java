package com.kdyncs.dragonsong.database.schema.prototype.repository;

import com.kdyncs.dragonsong.database.schema.prototype.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

}