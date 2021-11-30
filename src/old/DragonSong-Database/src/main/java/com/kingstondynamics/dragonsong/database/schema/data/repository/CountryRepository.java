package com.kingstondynamics.dragonsong.database.schema.data.repository;

import com.kingstondynamics.dragonsong.database.schema.data.model.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CountryRepository extends JpaRepository<CountryModel, Integer> {

}
