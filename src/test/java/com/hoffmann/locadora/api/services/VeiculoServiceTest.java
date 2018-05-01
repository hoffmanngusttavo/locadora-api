package com.hoffmann.locadora.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hoffmann.locadora.api.entities.Veiculo;
import com.hoffmann.locadora.api.repositories.VeiculoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoServiceTest {

	
	@MockBean
	private VeiculoRepository repository;
	
	@Autowired
	private VeiculoService service;
	
	
	@Before
	public void setUp() throws Exception {
		
		BDDMockito.given(this.repository.save(Mockito.any(Veiculo.class))).willReturn(new Veiculo());
	}
	
	
	@Test
	public void testPersistirVeiculo() {
		
		Veiculo lancamento = this.service.salvar(new Veiculo());

		assertNotNull(lancamento);
	}
	
	
	@Test
	public void testBuscarVeiculoPorId() {
		
		Optional<Veiculo> lancamento = this.service.buscarPorId(1L);

		assertTrue(lancamento.isPresent());
	}
	
	
	
}
