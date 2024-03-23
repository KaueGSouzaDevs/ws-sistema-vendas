package br.com.treinamento.main.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Pedido {
	
	//Atributos
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private Cliente cliente;

	@Getter @Setter
	private BigDecimal totalPedido = BigDecimal.ZERO;

	@Getter @Setter
	private List<ItemPedido> itensPedido;

	public Pedido(Cliente cliente) {
		this.cliente = cliente;

	}


	
}
