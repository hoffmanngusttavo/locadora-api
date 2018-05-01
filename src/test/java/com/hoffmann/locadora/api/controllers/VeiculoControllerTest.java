package com.hoffmann.locadora.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoffmann.locadora.api.dtos.VeiculoDTO;
import com.hoffmann.locadora.api.entities.Veiculo;
import com.hoffmann.locadora.api.services.VeiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private VeiculoService service;

	private static final String URL_BASE = "/api/veiculos/";
	
	private static final Long ID_VEICULO = 1L;
	private static final String MARCA = "HYNDAI";
	private static final String MODELO = "I30";
	private static final String PLACA = "MGS-6070";
	private static final Long QUILOMETRAGEM = 100L;
	private static final Optional<String> COR = Optional.of("Prata");

	@Test
	public void testCadastrarVeiculo() throws Exception {

		Veiculo veiculo = obterDadosVeiculo();

		BDDMockito.given(this.service.salvar(Mockito.any(Veiculo.class))).willReturn(veiculo);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID_VEICULO))
				.andExpect(jsonPath("$.data.marca").value(MARCA))
				.andExpect(jsonPath("$.data.modelo").value(MODELO))
				.andExpect(jsonPath("$.data.placa").value(PLACA))
				.andExpect(jsonPath("$.data.quilometragem").value(QUILOMETRAGEM))
				.andExpect(jsonPath("$.data.cor").value(COR.get()))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	
	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		VeiculoDTO dto = new VeiculoDTO();
		dto.setId(null);
		dto.setMarca(MARCA);
		dto.setModelo(MODELO);
		dto.setPlaca(PLACA);
		dto.setQuilometragem(QUILOMETRAGEM);
		dto.setCor(COR);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

	private Veiculo obterDadosVeiculo() {
		Veiculo veiculo = new Veiculo();
		veiculo.setMarca(MARCA);
		veiculo.setModelo(MODELO);
		veiculo.setPlaca(PLACA);
		veiculo.setQuilometragem(QUILOMETRAGEM);
		veiculo.setCor(COR.get());
		return veiculo;
	}	
	
	

}
