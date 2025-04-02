package com.example.ProdutoAPI_Back_Front.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    // Atributos
    private Long idProduto;
    private String nome;
    private double valor;
    private int saldo;
    private int saldoMinimo;
}
