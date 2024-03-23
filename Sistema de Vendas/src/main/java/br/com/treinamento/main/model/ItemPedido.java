package br.com.treinamento.main.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class ItemPedido {
	
	@Getter @Setter
	private Long id;
	@Getter @Setter
	private Pedido pedido;
	@Getter @Setter
	private Produto produto;
	@Getter @Setter
	private Integer quantidade;
	@Getter @Setter
	private BigDecimal valorUnitario;
	@Getter @Setter
	private BigDecimal valorTotal;


	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = produto.getPreco();
		this.valorTotal = new BigDecimal(quantidade).multiply(valorUnitario);
	}

}
