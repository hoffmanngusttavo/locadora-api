package com.hoffmann.locadora.api.dtos;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;



public class VeiculoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Optional<Long> id = Optional.empty();
	
	
	@NotEmpty(message = "Marca não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Marca deve conter entre 3 e 200 caracteres.")
	private String marca;
	
	@NotEmpty(message = "Modelo não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Modelo deve conter entre 3 e 200 caracteres.")
	private String modelo;
	
	@NotEmpty(message = "Placa não pode ser vazio.")
	@Length(min = 8, max = 8, message = "Placa deve conter 8 caracteres.")
	private String placa;
	
	@NotNull(message = "Quilometragem não pode ser nulo.")
	private Long quilometragem;
	
	private Optional<String> cor = Optional.empty();

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Optional<String> getCor() {
		return cor;
	}

	public void setCor(Optional<String> cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "VeiculoDTO [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", placa="
				+ placa + ", quilometragem=" + quilometragem + ", cor=" + cor + "]";
	}
	
}
