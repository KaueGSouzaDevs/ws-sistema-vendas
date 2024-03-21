package br.com.treinamento.main.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class Pedido {
	
	//Atributos
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private Cliente cliente;

	@Getter @Setter
	private BigDecimal totalPedido = BigDecimal.ZERO;

	public Pedido(Cliente cliente) {
		this.cliente = cliente;

	}


	
}
