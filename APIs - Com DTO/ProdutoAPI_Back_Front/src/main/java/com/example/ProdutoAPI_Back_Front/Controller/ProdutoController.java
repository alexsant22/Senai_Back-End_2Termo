package com.example.ProdutoAPI_Back_Front.Controller;

import com.example.ProdutoAPI_Back_Front.DTO.ProdutoDTO;
import com.example.ProdutoAPI_Back_Front.Entity.Produto;
import com.example.ProdutoAPI_Back_Front.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        Optional<ProdutoDTO> produtoDTO = produtoService.getById(id);

        if (produtoDTO.isPresent()) {
            return ResponseEntity.ok(produtoDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoDTO> created(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoDTONew = produtoService.saveDTO(produtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTONew);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoDTO> dtoOptional = produtoService.updateProduto(id, produtoDTO);

        if (dtoOptional.isPresent()) {
            return ResponseEntity.ok(dtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (produtoService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
