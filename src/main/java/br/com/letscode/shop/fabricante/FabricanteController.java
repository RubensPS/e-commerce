package br.com.letscode.shop.fabricante;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fabricantes")
public final class FabricanteController {

    private final FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<FabricanteResponse> adicionarFabricante(@RequestBody FabricanteRequest fabricanteRequest) {
        try {
            return new ResponseEntity(fabricanteService.criar(fabricanteRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/consultar/{nomeFabricante}")
    public ResponseEntity<FabricanteResponse> consultarFabricante(@PathVariable String nomeFabricante) {
        try{
            FabricanteResponse fabricanteResponse = fabricanteService.consultarFabricante(nomeFabricante);
            return ResponseEntity.ok(fabricanteResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FabricanteResponse>> consultarTodos() throws Exception {
        try{
            List<FabricanteResponse> fabricanteResponses = fabricanteService.consultarTodos();
            return ResponseEntity.ok(fabricanteResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{nomeFabricante}")
    public ResponseEntity<?> excluirFabricante(@PathVariable String nomeFabricante) throws Exception {
        try {
            fabricanteService.excluirFabricante(nomeFabricante);
            return ResponseEntity.ok("Fabricante excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
