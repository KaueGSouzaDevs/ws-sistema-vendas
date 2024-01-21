package br.com.treinamento.main.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class Produtos {
	
	//Atributos
	@Getter @Setter
	private Integer codigo;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private BigDecimal preco;

	//Construtor
	public Produtos(Integer codigo, String nome, BigDecimal preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}
	
	
	

}
