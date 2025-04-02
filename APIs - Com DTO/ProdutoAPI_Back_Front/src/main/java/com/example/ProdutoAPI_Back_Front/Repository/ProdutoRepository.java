package com.example.ProdutoAPI_Back_Front.Repository;

import com.example.ProdutoAPI_Back_Front.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
