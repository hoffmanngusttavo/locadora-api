package com.hoffmann.locadora.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoffmann.locadora.api.entities.Veiculo;

/**
 * Interface que extende  JpaRepository,
 * contendo as principais funções para um CRUD.
 * 
 */
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	/**
	 * Retorna um veículo buscando pela placa.
	 * 
	 * @param placa
	 * @return Optional<Veiculo>
	 */
	Optional<Veiculo> findByPlaca(String placa);


}
