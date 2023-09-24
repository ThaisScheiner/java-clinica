package com.clinic.medinstitute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.ClientEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    @Query(value = "select name from tb_client c where upper(trim(c.name)) like %?1%")
    List<ClientEntity> searchUserName(String name);
}
