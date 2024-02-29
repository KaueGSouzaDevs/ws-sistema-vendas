package br.com.treinamento.main.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class ItemPedido {
	
	//Atributos
	@Getter @Setter
	private Produto produto;
	@Getter @Setter
	private Integer quantidade;
	@Getter 
	private BigDecimal valorUnitario;
	@Getter
	private BigDecimal valorTotal;;
	
	//Construtor
	public ItemPedido(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = produto.getPreco();
		this.valorTotal = valorUnitario.multiply(new BigDecimal(quantidade));
	}
	
	
	
}
