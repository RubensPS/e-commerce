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

    @PostMapping
    public ResponseEntity<CarrinhoEntity> criar(@RequestBody CarrinhoRequest carrinhoRequest){
        CarrinhoEntity carrinhoEntity = carrinhoService.criar(carrinhoRequest);
        return new ResponseEntity(carrinhoEntity, HttpStatus.CREATED);
    }

    @GetMapping("/consultar/{userId}")
    public ResponseEntity<List<CarrinhoEntity>> consultarCarrinho(@PathVariable Long userId) {

        List<CarrinhoEntity> carrinhoEntities = carrinhoService.consultarCarrinhoPorId(userId);
        return ResponseEntity.ok(carrinhoEntities);
    }
}
