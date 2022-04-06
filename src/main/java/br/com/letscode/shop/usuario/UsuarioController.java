package br.com.letscode.shop.usuario;

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

    @PostMapping("/criar")
    public ResponseEntity<UsuarioEntity> salvarNovoUsuario(@RequestBody UsuarioRequest request) {
        UsuarioEntity entity = usuarioService.criar(request);
        return new ResponseEntity(entity, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioEntity>> listarTodosUsuarios() {
        List<UsuarioEntity> entities = usuarioService.listarTodos();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<UsuarioEntity> consultarUsuario(@PathVariable Long id) {
        UsuarioEntity entity = usuarioService.consultarUsuarioPorId(id);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuarioPorId(id);
    }

    @PatchMapping("/alterar")
    public ResponseEntity<UsuarioEntity> alterarUsuario(@RequestBody UsuarioRequest request) {
        //chamar metodo para alteração de usuario. verificar se é possível receber
        // um request de parametros não obrigatorios e alterar o que vier no bd, retornando o usuario com dados dalterados
        return null;
    }

    @GetMapping("/consultarNome/{nomeUsuario}")
    public ResponseEntity<UsuarioEntity> consultarUsuario(@PathVariable String nomeUsuario) {
        UsuarioEntity entity = usuarioService.consultarUsuarioPorNomeUsuario(nomeUsuario);
        return ResponseEntity.ok(entity);
    }

}