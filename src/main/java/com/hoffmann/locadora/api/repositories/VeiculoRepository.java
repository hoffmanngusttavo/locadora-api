package com.hoffmann.locadora.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoffmann.locadora.api.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	
	Optional<Veiculo> findByPlaca(String placa);


}
