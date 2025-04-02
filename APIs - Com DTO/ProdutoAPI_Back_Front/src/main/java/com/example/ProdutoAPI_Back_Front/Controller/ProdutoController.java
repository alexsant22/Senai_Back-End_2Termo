package com.example.ProdutoAPI_Back_Front.Controller;

import com.example.ProdutoAPI_Back_Front.DTO.ProdutoDTO;
import com.example.ProdutoAPI_Back_Front.Entity.Produto;
import com.example.ProdutoAPI_Back_Front.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getAll());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoDTO> created(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoDTONew = produtoService.saveDTO(produtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTONew);
    }
}
