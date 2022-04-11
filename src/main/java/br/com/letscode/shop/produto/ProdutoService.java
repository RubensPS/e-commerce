package br.com.letscode.shop.produto;

import br.com.letscode.shop.fabricante.FabricanteEntity;
import br.com.letscode.shop.fabricante.FabricanteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    public ProdutoRepository produtoRepository;
    public FabricanteRepository fabricanteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, FabricanteRepository fabricanteRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    @Transactional(readOnly = true)
    public ProdutoResponse consultarCodigoBarra(String codigoBarra) throws Exception {
        ProdutoEntity produtoEntity = produtoRepository.findByCodigoBarra(codigoBarra);
        if (produtoEntity == null) {
            throw new Exception("Produto não encontrado");
        }
        return new ProdutoResponse(produtoEntity);
    }

    @Transactional
    public void excluirProduto(String codigoBarra) throws Exception {
        ProdutoEntity produtoEntity = produtoRepository.findByCodigoBarra(codigoBarra);
        if (produtoEntity == null) {
            throw new Exception("Produto não encontrado");
        }
        produtoRepository.deleteByCodigoBarra(codigoBarra);
    }

    public List<ProdutoEntity> getAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity create(ProdutoRequest request) throws Exception {
        ProdutoEntity entity = toEntity(request);
        if (produtoRepository.findByCodigoBarra(entity.getCodigoBarra()) != null) {
            throw new Exception("Código de barra já cadastrado");
        }
        return produtoRepository.save(entity);
    }

    public ProdutoEntity toEntity(ProdutoRequest request) throws Exception {
        FabricanteEntity fabricante = fabricanteRepository.findByNomeFabricante(request.getNomeFabricante());
        if (fabricante == null) {
            throw new Exception("Fabricante não encontrado");
        }
            return new ProdutoEntity(
                    request.getNome(),
                    request.getDescricao(),
                    request.getValor(),
                    request.getCodigoBarra(),
                    fabricante,
                    request.getPeso(),
                    request.getPesoUnidadeMedida());
        }
    }
