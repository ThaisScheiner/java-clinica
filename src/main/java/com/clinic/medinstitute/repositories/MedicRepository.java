package com.clinic.medinstitute.repositories;

import com.clinic.medinstitute.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.DoctorEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicRepository extends JpaRepository<DoctorEntity, Long>{
    @Query(value = "select name from tb_doctor m where upper(trim(m.name)) like %?1%")
    List<DoctorEntity> searchDoctorName(String name);
}
