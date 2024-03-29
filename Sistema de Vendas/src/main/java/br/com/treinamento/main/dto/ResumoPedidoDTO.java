package br.com.treinamento.main.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class ResumoPedidoDTO {
    
    @Getter @Setter
    private Integer totalItens;
    
    @Getter @Setter
    private BigDecimal valorTotal;
    
}
