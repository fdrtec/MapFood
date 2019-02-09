package com.devwarriors.mapfood.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devwarriors.mapfood.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
	public List<Cliente> findByClienteId(String clienteId);
}