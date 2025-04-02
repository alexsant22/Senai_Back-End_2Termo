package com.example.ProdutoAPI_Back_Front.Service;

import com.example.ProdutoAPI_Back_Front.DTO.ProdutoDTO;
import com.example.ProdutoAPI_Back_Front.Entity.Produto;
import com.example.ProdutoAPI_Back_Front.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Converte de ProdutoDTO para Produto
    public Produto fromDTO(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();

        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setSaldo(produtoDTO.getSaldo());
        produto.setSaldoMinimo(produtoDTO.getSaldoMinimo());

        return produto;
    }

    // Converte de Produto para ProdutoDTO
    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setIdProduto(produto.getIdProduto());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValor(produto.getValor());
        produtoDTO.setSaldo(produto.getSaldo());
        produtoDTO.setSaldoMinimo(produto.getSaldoMinimo());

        return produtoDTO;
    }

    public List<Produto> getAll() { // Buscar todos
        return produtoRepository.findAll();
    }

    public ProdutoDTO saveDTO(ProdutoDTO produtoDTO) {
        Produto produto = this.fromDTO(produtoDTO);

        Produto produtoBd = produtoRepository.save(produto);

        return this.toDTO(produtoBd);
    }
}
