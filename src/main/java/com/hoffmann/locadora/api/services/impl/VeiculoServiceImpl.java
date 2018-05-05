package com.hoffmann.locadora.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hoffmann.locadora.api.entities.Veiculo;
import com.hoffmann.locadora.api.repositories.VeiculoRepository;
import com.hoffmann.locadora.api.services.VeiculoService;

/**
 * Classe de Serviço que implementa uma interface
 */
@Service
public class VeiculoServiceImpl implements VeiculoService{

	private static final Logger log = LoggerFactory.getLogger(VeiculoServiceImpl.class);
	
	/**
	 * Injeção de dependência do repostitory.
	 */
	@Autowired
	private VeiculoRepository repository;
	
	
	/**
	 * Retorna uma lista paginada de veiculos.
	 * 
	 * @param pageRequest
	 * @return Page<Veiculo>
	 */
	@Override
	public Page<Veiculo> buscarTodos(PageRequest pageRequest) {
		log.info("Buscando todos os veículos");
		return this.repository.findAll(pageRequest);
	}

	/**
	 * Retorna um veículo por ID.
	 * 
	 * @param id
	 * @return Optional<Veiculo>
	 */
	@Override
	public Optional<Veiculo> buscarPorId(Long id) {
		log.info("Buscando um veículo pelo ID {}", id);
		return this.repository.findById(id);
	}
	
	
	/**
	 * Salvar um veiculo no banco em memória.
	 * 
	 * @param veiculo
	 * @return Veiculo
	 */
	@Override
	public Veiculo salvar(Veiculo veiculo) {
		log.info("Persistindo o veículo: {}", veiculo);
		return this.repository.save(veiculo);
	}

	/**
	 * Remove um veiculo pelo id.
	 * 
	 * @param id
	 */
	@Override
	public void remover(Long id) {
		log.info("Removendo o veículo ID {}", id);
		this.repository.deleteById(id);
	}

	
	/**
	 * Retorna um veículo buscando pela placa.
	 * 
	 * @param placa
	 * @return Optional<Veiculo>
	 */
	@Override
	public Optional<Veiculo> buscarPorPlaca(String placa) {
		log.info("Buscando um veículo pela Placa {}", placa);
		return this.repository.findByPlaca(placa);
	}

}
