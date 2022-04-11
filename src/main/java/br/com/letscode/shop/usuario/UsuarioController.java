package br.com.letscode.shop.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioEntity> salvarNovoUsuario(@RequestBody UsuarioRequest request) {
        UsuarioEntity entity = usuarioService.criar(request);
        return new ResponseEntity(entity, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioEntity>> listarTodosUsuarios() {
        List<UsuarioEntity> entities = usuarioService.listarTodos();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<UsuarioEntity> consultarUsuario(@PathVariable Long id) {
        UsuarioEntity entity = usuarioService.consultarUsuarioPorId(id);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuarioPorId(id);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<UsuarioResponse> alterarDadosUsuario(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            UsuarioResponse usuarioResponse = usuarioService.alterarDadosUsuario(id, patch);
            return ResponseEntity.ok(usuarioResponse);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/consultarNome/{nomeUsuario}")
    public ResponseEntity<UsuarioEntity> consultarUsuario(@PathVariable String nomeUsuario) {
        UsuarioEntity entity = usuarioService.consultarUsuarioPorNomeUsuario(nomeUsuario);
        return ResponseEntity.ok(entity);
    }

}