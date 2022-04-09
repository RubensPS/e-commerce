package br.com.letscode.shop.fabricante;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FabricanteService {
    private final FabricanteRepository fabricanteRepository;

    @Transactional
    public FabricanteResponse criar(FabricanteRequest fabricanteRequest) throws Exception {
        FabricanteEntity fabricanteEntity = toEntity(fabricanteRequest);
        return new FabricanteResponse(fabricanteRepository.save(fabricanteEntity));
    }

    @Transactional(readOnly = true)
    public FabricanteResponse consultarFabricante(String nomeFabricante) throws Exception {
        FabricanteEntity fabricanteEntity = fabricanteRepository.findByNomeFabricante(nomeFabricante);
        if (fabricanteEntity == null) {
            throw new Exception("Fabricante não encontrado");
        }
        return new FabricanteResponse(fabricanteEntity);

    }

    @Transactional(readOnly = true)
    public List<FabricanteResponse> consultarTodos() {
        List<FabricanteEntity> fabricanteEntities = fabricanteRepository.findAll();
        return fabricanteEntities.stream().map(fabricanteEntity -> new FabricanteResponse(fabricanteEntity)).collect(Collectors.toList());
    }

    @Transactional
    public void excluirFabricante(String nomeFabricante) throws Exception {
        FabricanteEntity fabricanteEntity = fabricanteRepository.findByNomeFabricante(nomeFabricante);
        if (fabricanteEntity == null) {
            throw new Exception("Fabricante não encontrado");
        }
        fabricanteRepository.deleteByNomeFabricante(nomeFabricante);
    }

    public FabricanteEntity toEntity(FabricanteRequest request) throws Exception {
        FabricanteEntity fabricanteEntity = fabricanteRepository.findByNomeFabricante(request.getNomeFabricante());
        if (fabricanteEntity != null) {
            throw new Exception("Fabricante já existe");
        }

        return new FabricanteEntity(request);
    }
}
