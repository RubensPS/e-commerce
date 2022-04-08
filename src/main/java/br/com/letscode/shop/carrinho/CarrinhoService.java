package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.produto.ProdutoRepository;
import br.com.letscode.shop.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    public ProdutoRepository produtoRepository;
    public UsuarioRepository usuarioRepository;
    public CarrinhoRepository carrinhoRepository;

    public CarrinhoService(
            ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository,
            CarrinhoRepository carrinhoRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.carrinhoRepository = carrinhoRepository;
    }

    @Transactional(readOnly = true)
    public List<CarrinhoResponse> buscarPorUsuário(Long usuarioId) throws Exception {
        var usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new Exception("Usuario não encontrado"));

        List<CarrinhoEntity> allByByUsuario = carrinhoRepository.findAllByUsuario(usuario);

        return allByByUsuario.stream().map(CarrinhoResponse::new).collect(Collectors.toList());
    }


    @Transactional
    public CarrinhoResponse criar(CarrinhoRequest carrinhoRequest) throws Exception {
        var usuario = usuarioRepository
                .findById(carrinhoRequest.getUsuarioId())
                .orElseThrow(() -> new Exception("Usuario não encontrado"));
        var produto = produtoRepository
                .findById(carrinhoRequest.getProdutoId())
                .orElseThrow(() -> new Exception("produto não encontrado"));;



        var carrinhoProduto = new CarrinhoEntity();

        carrinhoProduto.setUsuario(usuario);
        carrinhoProduto.setProduto(produto);
        carrinhoProduto.setAmount(carrinhoRequest.getQuantidade());

        return new CarrinhoResponse(carrinhoRepository.save(carrinhoProduto));
    }

}
