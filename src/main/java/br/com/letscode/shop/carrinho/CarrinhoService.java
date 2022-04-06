package br.com.letscode.shop.carrinho;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoEntity criar(CarrinhoRequest carrinhoRequest) {
        CarrinhoEntity carrinhoEntity = carrinhoRequest.toEntity();
        return carrinhoRepository.save(carrinhoEntity);
    }

    public List<CarrinhoEntity> consultarCarrinhoPorId(Long userId) {
        List<CarrinhoEntity> carrinhoEntities = carrinhoRepository.findAllByUserId(userId);
        return carrinhoEntities;
    }
}
