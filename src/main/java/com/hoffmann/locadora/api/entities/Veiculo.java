package com.hoffmann.locadora.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String marca;
	
	@Column
	private String modelo;
	
	@Column
	private String placa;
	
	@Column
	private Long quilometragem;
	
	private String cor;
	
	
	public Veiculo() {
		
	}
	
	
	public Veiculo(String marca, String modelo, String placa, Long quilometragem) {
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.quilometragem = quilometragem;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", placa=" + placa + ", quilometragem="
				+ quilometragem + ", cor=" + cor + "]";
	}
	

}
