package br.com.treinamento.main.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Pedido {
	
	//Atributos
	@Getter
	private Integer numeroPedido;
	private Integer contador = 0;
	
	@Getter
	private Cliente cliente;
	@Getter @Setter
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
	@Getter @Setter
	private BigDecimal totalPedido = BigDecimal.ZERO;
	
	public Pedido(Cliente cliente) {
		this.numeroPedido = ++contador;
		this.cliente = cliente;
	}
	
	public void adicionarItem(ItemPedido item) {
		itensPedido.add(item);
	}

	public void calcularTotal(ItemPedido item) {
		totalPedido = totalPedido.add(item.getValorTotal());
	}

	
}
