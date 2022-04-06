package br.com.letscode.shop.produto;

import br.com.letscode.shop.fabricante.FabricanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        ProdutoEntity entity = toEntity(request);
        return produtoRepository.save(entity);
    }

    public ProdutoEntity toEntity(ProdutoRequest request) {
        return new ProdutoEntity(
                request.getNome(),
                request.getDescricao(),
                request.getValor(),
                request.getCodigoBarra(),
                fabricanteRepository.findByNomeFabricante(request.getNomeFabricante()),
                request.getPeso(),
                request.getPesoUnidadeMedida());
    }

}
