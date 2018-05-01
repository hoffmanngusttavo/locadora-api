package com.hoffmann.locadora.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hoffmann.locadora.api.entities.Veiculo;




@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoRepositoryTest {

	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	
	
	@Before
	public void setUp() throws Exception {
		
		this.veiculoRepository.save(obterDadosVeiculo());
		
	}

	@After
	public void tearDown() throws Exception {
		this.veiculoRepository.deleteAll();
	}

	
	
	
	@Test
	public void testBuscarVeiculoId() {
		
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(1L);
		
		assertTrue(veiculo.isPresent());
	}
	
	
	
	@Test
	public void testBuscarVeiculosPaginado() {
		
		PageRequest page = PageRequest.of(0, 10);
		
		Page<Veiculo> veiculos = this.veiculoRepository.findAll(page);
		
		assertEquals(1, veiculos.getTotalElements());
	}
	
	

	

	private Veiculo obterDadosVeiculo() {
		
		Veiculo veiculo = new Veiculo();
		veiculo.setMarca("Hyndai");
		veiculo.setModelo("I30");
		veiculo.setPlaca("MGS-6070");
		veiculo.setQuilometragem(60000L);
		veiculo.setCor("Prata");
		
		return veiculo;
	}
	
	
	
}
