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


@Service
public class VeiculoServiceImpl implements VeiculoService{

	private static final Logger log = LoggerFactory.getLogger(VeiculoServiceImpl.class);
	
	@Autowired
	private VeiculoRepository repository;
	
	@Override
	public Page<Veiculo> buscarTodos(PageRequest pageRequest) {
		log.info("Buscando todos os veículos");
		return this.repository.findAll(pageRequest);
	}

	@Override
	public Optional<Veiculo> buscarPorId(Long id) {
		log.info("Buscando um veículo pelo ID {}", id);
		return this.repository.findById(id);
	}

	@Override
	public Veiculo salvar(Veiculo veiculo) {
		log.info("Persistindo o veículo: {}", veiculo);
		return this.repository.save(veiculo);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o veículo ID {}", id);
		this.repository.deleteById(id);
	}

	@Override
	public Optional<Veiculo> buscarPorPlaca(String placa) {
		log.info("Buscando um veículo pela Placa {}", placa);
		return this.repository.findByPlaca(placa);
	}

}
