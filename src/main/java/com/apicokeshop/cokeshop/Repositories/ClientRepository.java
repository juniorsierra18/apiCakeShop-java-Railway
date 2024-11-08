package com.apicokeshop.cokeshop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apicokeshop.cokeshop.Entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
