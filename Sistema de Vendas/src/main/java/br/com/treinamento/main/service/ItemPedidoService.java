package br.com.treinamento.main.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import br.com.treinamento.main.dao.ItemPedidoDao;
import br.com.treinamento.main.dto.ResumoPedidoDTO;
import br.com.treinamento.main.model.ItemPedido;

public class ItemPedidoService {

    public void adicionarItemPedido(ItemPedido itemPedido) {
        
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

        try {
            itemPedidoDao.criarItemPedido(itemPedido);    
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item no pedido: "+ e.getMessage());
        }

    }

    public ResumoPedidoDTO getTotais(Integer idPedido) throws SQLException{
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();     
        return itemPedidoDao.buscaResumoPorPedido(idPedido);
    }

    public void atualizaTotalPedido(Integer idPedido, BigDecimal totalPedido) {

        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
        try {
            itemPedidoDao.atualizarValorTotal(idPedido, totalPedido);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o valor do pedido: "+ e.getMessage());
        }


    }
}
