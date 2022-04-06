package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.produto.ProdutoRepository;
import br.com.letscode.shop.usuario.UsuarioEntity;
import br.com.letscode.shop.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoEntity criar(CarrinhoRequest carrinhoRequest) {
        CarrinhoEntity carrinhoEntity = toEntity(carrinhoRequest);
        return carrinhoRepository.save(carrinhoEntity);
    }

    public List<CarrinhoEntity> consultarCarrinhoPorId(Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId).orElseThrow();
        List<CarrinhoEntity> carrinhoEntities = carrinhoRepository.findAllByUserId(usuario);
        return carrinhoEntities;
    }

    public CarrinhoEntity toEntity(CarrinhoRequest carrinhoRequest) {
        return new CarrinhoEntity(
                produtoRepository.findByCodigoBarra(carrinhoRequest.getCodigoBarra()),
                carrinhoRequest.getQuantidade(),
                usuarioRepository.findById(carrinhoRequest.getUsuarioId()).orElseThrow());
    }
}
