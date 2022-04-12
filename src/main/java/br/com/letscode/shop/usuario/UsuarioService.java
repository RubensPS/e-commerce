package br.com.letscode.shop.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponse criar(UsuarioRequest request) {
        UsuarioEntity entity = request.toEntity();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        UsuarioResponse response = new UsuarioResponse(usuarioRepository.save(entity));
        return response;
    }

    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream().map(usuarioEntity -> new UsuarioResponse(usuarioEntity)).collect(Collectors.toList());
    }

    public UsuarioResponse consultarUsuarioPorId(Long id) {
        UsuarioResponse response = new UsuarioResponse(usuarioRepository.findById(id).orElseThrow());
        return response;
    }

    public void excluirUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponse consultarUsuarioPorNomeUsuario(String nomeUsuario) {
        UsuarioResponse response = new UsuarioResponse(usuarioRepository.findByNomeUsuario(nomeUsuario));
        return response;
    }

    public UsuarioResponse alterarDadosUsuario(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow();
        UsuarioEntity usuarioAlterado = aplicarPatchUsuario(patch, usuarioEntity);
        usuarioAlterado.setDataAtualizacao(ZonedDateTime.now());
        return new UsuarioResponse(usuarioRepository.save(usuarioAlterado));
    }
    public UsuarioEntity aplicarPatchUsuario(JsonPatch patch, UsuarioEntity usuarioEntity) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(Json.mapper().convertValue(usuarioEntity, JsonNode.class));
        return Json.mapper().treeToValue(patched, UsuarioEntity.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UsuarioEntity usuario = usuarioRepository.findByNomeUsuario(username);
       if (usuario == null) {
           throw new UsernameNotFoundException("Usuário não encontrado na base de dados");
       }
       Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       authorities.add(new SimpleGrantedAuthority(usuario.getFuncao()));
       return new User(usuario.getNomeUsuario(), usuario.getPassword(), authorities);
    }
}
