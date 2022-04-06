package br.com.letscode.shop.produto;

import br.com.letscode.shop.fabricante.FabricanteEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ProdutoEntity>> getAll() {
        List<ProdutoEntity> entities = produtoService.getAll();
        return ResponseEntity.ok(entities);
    }

    @PostMapping("create")
    public ResponseEntity<ProdutoEntity> create(@RequestBody ProdutoRequest request) {
        ProdutoEntity entity = produtoService.create(request);
        return new ResponseEntity<ProdutoEntity>(entity, HttpStatus.CREATED);
    }

    @GetMapping("/fabricantes")
    public ResponseEntity<List<FabricanteEntity>> listarFabricantes() {
        List<FabricanteEntity> fabricanteEntities = produtoService.listarFabricantes();
        return ResponseEntity.ok(fabricanteEntities);
    }
}
