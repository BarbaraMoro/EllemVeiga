package com.tcc2.ellemVeigaOficial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.tcc2.ellemVeigaOficial.models.Produto;
import com.tcc2.ellemVeigaOficial.services.ProdutoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "Produto", description = "Operações relacionadas a Produtos")
public class ProdutoController {
    private ProdutoService service;

    @PostMapping("/produto")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto){
        Produto produtoCriado = service.addProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto updateProduto = service.update(id, produto);
            return ResponseEntity.ok(updateProduto);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produto/buscar")
    public ResponseEntity<List<Produto>> buscarProdutos(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome) {
        List<Produto> produtos = service.buscarProdutos(id, nome);
        return ResponseEntity.ok(produtos);
    }
}