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

    @GetMapping("/consultar/{codigoBarra}")
    public ResponseEntity<ProdutoResponse> findByCodigoBarra(@PathVariable String codigoBarra) {
        try {
            ProdutoResponse produtoResponse = produtoService.consultarCodigoBarra(codigoBarra);
            return ResponseEntity.ok(produtoResponse);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{codigoBarra}")
    public ResponseEntity<?> excluirProduto(@PathVariable String codigoBarra) throws Exception{
        try {
            produtoService.excluirProduto(codigoBarra);
            return ResponseEntity.ok("Produto excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
