package br.com.treinamento.main.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class Produto {
	
	//Atributos
	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private BigDecimal preco;

	//Construtor
	public Produto(String nome, BigDecimal preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public Produto() {

	}
	
}
