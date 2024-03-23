package br.com.treinamento.main.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class Cliente {
	
	//Atributos
	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String email;
	
	//Construtor
	public Cliente(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public Cliente(){
		
	}
	
	

}
