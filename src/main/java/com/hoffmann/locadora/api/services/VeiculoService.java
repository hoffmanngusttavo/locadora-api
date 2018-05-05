package com.hoffmann.locadora.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hoffmann.locadora.api.entities.Veiculo;



public interface VeiculoService {

	/**
	 * Retorna uma lista paginada de veiculos.
	 * 
	 * @param pageRequest
	 * @return Page<Veiculo>
	 */
	Page<Veiculo> buscarTodos(PageRequest pageRequest);
	
	/**
	 * Retorna um veículo por ID.
	 * 
	 * @param id
	 * @return Optional<Veiculo>
	 */
	Optional<Veiculo> buscarPorId(Long id);
	
	/**
	 * Retorna um veículo buscando pela placa.
	 * 
	 * @param placa
	 * @return Optional<Veiculo>
	 */
	Optional<Veiculo> buscarPorPlaca(String placa);
	
	/**
	 * Salvar um veiculo na base de dados.
	 * 
	 * @param veiculo
	 * @return Veiculo
	 */
	Veiculo salvar(Veiculo veiculo);
	
	/**
	 * Remove um veiculo pelo id.
	 * 
	 * @param id
	 */
	void remover(Long id);
	
	
}
