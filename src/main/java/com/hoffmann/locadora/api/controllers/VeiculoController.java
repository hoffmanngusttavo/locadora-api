package com.hoffmann.locadora.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoffmann.locadora.api.dtos.VeiculoDTO;
import com.hoffmann.locadora.api.entities.Veiculo;
import com.hoffmann.locadora.api.response.Response;
import com.hoffmann.locadora.api.services.VeiculoService;




@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

	private static final Logger log = LoggerFactory.getLogger(VeiculoController.class);
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	
	
	
	
	/**
	 * Retorna a listagem de veículos.
	 * 
	 * @return ResponseEntity<Response<VeiculoDTO>>
	 */
	@GetMapping
	public ResponseEntity<Response<Page<VeiculoDTO>>> listar(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		
		log.info("Buscando veículos página: {}", pag);
		
		Response<Page<VeiculoDTO>> response = new Response<Page<VeiculoDTO>>();
		
		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<Veiculo> veiculos = this.veiculoService.buscarTodos(pageRequest);
		
		Page<VeiculoDTO> veiculosDTO = veiculos.map(veiculo -> this.converterVeiculoDto(veiculo));

		response.setData(veiculosDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	
	/**
	 * Retorna um veículo por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<VeiculoDTO>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<VeiculoDTO>> listarPorId(@PathVariable("id") Long id) {
		
		log.info("Buscando veículo por ID: {}", id);
		
		Response<VeiculoDTO> response = new Response<VeiculoDTO>();
		
		Optional<Veiculo> veiculo = this.veiculoService.buscarPorId(id);

		if (!veiculo.isPresent()) {
			log.info("Veículo não encontrado para o ID: {}", id);
			response.getErrors().add("Veículo não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterVeiculoDto(veiculo.get()));
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	/**
	 * Adiciona um novo veículo.
	 * 
	 * @param veiculoDTO
	 * @param result
	 * @return ResponseEntity<Response<VeiculoDTO>>
	 * @throws ParseException 
	 */
	@PostMapping
	public ResponseEntity<Response<VeiculoDTO>> adicionar(@Valid @RequestBody VeiculoDTO veiculoDTO,
			BindingResult result)  {
		
		log.info("Adicionando veículo: {}", veiculoDTO.toString());
		
		Response<VeiculoDTO> response = new Response<VeiculoDTO>();
		
		validarPlaca(veiculoDTO, result);
		
		Veiculo veiculo = this.converterDtoParaVeiculo(veiculoDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando veículo: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Veiculo veiculoPersistido = this.veiculoService.salvar(veiculo);
		
		response.setData(this.converterVeiculoDto(veiculoPersistido));
		
		return ResponseEntity.ok(response);
	}



	/**
	 * Atualiza os dados de um veículo.
	 * 
	 * @param id
	 * @param veiculoDTO
	 * @return ResponseEntity<Response<VeiculoDTO>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<VeiculoDTO>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody VeiculoDTO veiculoDTO, BindingResult result) {
		
		log.info("Atualizando veículo: {}", veiculoDTO.toString());
		
		Response<VeiculoDTO> response = new Response<VeiculoDTO>();
		
		veiculoDTO.setId(Optional.of(id));
		
		Veiculo veiculo = this.converterDtoParaVeiculo(veiculoDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Veiculo veiculoAtualizado = this.veiculoService.salvar(veiculo);
		
		response.setData(this.converterVeiculoDto(veiculoAtualizado));
		
		return ResponseEntity.ok(response);
	}
	
	
	
	

	
	
	/**
	 * Remove um veículo por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		
		log.info("Removendo veículo: {}", id);
		
		Response<String> response = new Response<String>();
		
		Optional<Veiculo> veiculo = this.veiculoService.buscarPorId(id);

		if (!veiculo.isPresent()) {
			log.info("Erro ao remover devido ao veículo ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover veículo. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.veiculoService.remover(id);
		
		return ResponseEntity.ok(new Response<String>());
	}
	

	

	

	/**
	 * Converte um veiculo para um dto.
	 * 
	 * @param veiculo
	 * @return VeiculoDTO
	 */
	private VeiculoDTO converterVeiculoDto(Veiculo veiculo) {
		
		log.info("Convertendo veículo para DTO : ", veiculo.toString());
		
		VeiculoDTO dto = new VeiculoDTO();
		
		dto.setId(Optional.of(veiculo.getId()));
		dto.setMarca(veiculo.getMarca());
		dto.setModelo(veiculo.getModelo());
		dto.setPlaca(veiculo.getPlaca());
		dto.setQuilometragem(veiculo.getQuilometragem());
		dto.setCor(Optional.of(veiculo.getCor()));
		
		return dto;
	}




	/**
	 * Converte um dto para um veículo.
	 * 
	 * @param veiculoDTO
	 * @param result
	 * @return Veiculo
	 */
	private Veiculo converterDtoParaVeiculo(@Valid VeiculoDTO veiculoDTO, BindingResult result) {
		
		log.info("Convertendo DTO para um veiculo : ", veiculoDTO.toString());
		
		Veiculo veiculo = new Veiculo();

		if (veiculoDTO.getId().isPresent()) {
			Optional<Veiculo> lanc = this.veiculoService.buscarPorId(veiculoDTO.getId().get());
			if (lanc.isPresent()) {
				veiculo = lanc.get();
			} else {
				result.addError(new ObjectError("veiculo", "Veículo não encontrado."));
			}
		} 
		
		veiculo.setMarca(veiculoDTO.getMarca());
		veiculo.setModelo(veiculoDTO.getModelo());
		veiculo.setPlaca(veiculoDTO.getPlaca());
		veiculo.setQuilometragem(veiculoDTO.getQuilometragem());
		veiculo.setCor(veiculoDTO.getCor().isPresent() ? veiculoDTO.getCor().get() : "");

		return veiculo;
	}
	
	
	
	/**
	 * Validar se existe um veículo cadastrado no sistema com uma placa.
	 * 
	 * @param veiculoDTO
	 * @param result
	 */
	private void validarPlaca(@Valid VeiculoDTO veiculoDTO, BindingResult result) {
		
		log.info("Validando veiculo : ", veiculoDTO.toString());
		
		Optional<Veiculo> veiculo = this.veiculoService.buscarPorPlaca(veiculoDTO.getPlaca());
		
		if (veiculo.isPresent()) {
			result.addError(new ObjectError("veiculo", "Veículo com placa já cadastrada"));
		}
		
		
	}
	
	
}
