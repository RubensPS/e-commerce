package br.com.letscode.shop.carrinho;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CarrinhoResponse>> consultarCarrinho(@PathVariable Long usuarioId) {
        try {
            var carrinhoEntities = carrinhoService.buscarPorUsuário(usuarioId);
            return ResponseEntity.ok(carrinhoEntities);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CarrinhoResponse> adicionarProdutoNoCarrinho(@RequestBody CarrinhoRequest carrinhoRequest){
        try {
            return new ResponseEntity(carrinhoService.criar(carrinhoRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
