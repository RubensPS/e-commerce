package br.com.letscode.shop.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public ResponseEntity<List<ProdutoResponse>> getAll() {
        List<ProdutoEntity> entities = produtoService.getAll();
        return ResponseEntity.ok(entities.stream().map(ProdutoResponse::new).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity<ProdutoResponse> create(@RequestBody ProdutoRequest request) {
        try {
            ProdutoResponse entity =  new ProdutoResponse(produtoService.create(request));
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        } catch (Exception exception){
            return  ResponseEntity.badRequest().build();
        }
    }
}
