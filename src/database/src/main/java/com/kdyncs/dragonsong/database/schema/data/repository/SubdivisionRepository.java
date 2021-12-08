package com.kdyncs.dragonsong.database.schema.data.repository;

import com.kdyncs.dragonsong.database.schema.data.model.SubdivisionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SubdivisionRepository extends JpaRepository<SubdivisionModel, Integer> {

}
