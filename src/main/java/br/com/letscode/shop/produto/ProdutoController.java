package br.com.letscode.shop.produto;

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

    @GetMapping()
    public ResponseEntity<List<ProdutoEntity>> getAll() {
        List<ProdutoEntity> entities = produtoService.getAll();
        return ResponseEntity.ok(entities);
    }

    @PostMapping()
    public ResponseEntity<ProdutoEntity> create(@RequestBody ProdutoRequest request) {
        try {
            ProdutoEntity entity = produtoService.create(request);
            return new ResponseEntity<ProdutoEntity>(entity, HttpStatus.CREATED);
        }catch (Exception exception){
            return  ResponseEntity.badRequest().build();
        }
    }
}
