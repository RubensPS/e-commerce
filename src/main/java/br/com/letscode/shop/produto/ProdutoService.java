package br.com.letscode.shop.produto;

import br.com.letscode.shop.fabricante.FabricanteEntity;
import br.com.letscode.shop.fabricante.FabricanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProdutoService {

    public ProdutoRepository produtoRepository;
    public FabricanteRepository fabricanteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, FabricanteRepository fabricanteRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public List<ProdutoEntity> getAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity create(ProdutoRequest request) {
        Optional<FabricanteEntity> oFabricanteEntity = fabricanteRepository.findById(request.toEntity().getFabricante().getId());
        ProdutoEntity entity = toEntity(request, oFabricanteEntity.get());
        return produtoRepository.save(entity);
    }

    public ProdutoEntity toEntity(ProdutoRequest produtoRequest, FabricanteEntity fabricanteEntity) {
        return new ProdutoEntity(
                produtoRequest.getNome(),
                produtoRequest.getDescricao(),
                produtoRequest.getValor(),
                produtoRequest.getCodigoBarra(),
                fabricanteEntity,
                produtoRequest.getPeso(),
                produtoRequest.getPesoUnidadeMedida());
    }

}
