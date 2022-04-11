package br.com.letscode.shop.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioEntity criar(UsuarioRequest request) {
        UsuarioEntity entity = request.toEntity();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return usuarioRepository.save(entity);
    }

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity consultarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public void excluirUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity consultarUsuarioPorNomeUsuario(String nomeUsuario) {
        return usuarioRepository.findByNomeUsuario(nomeUsuario);
    }

    public UsuarioResponse alterarDadosUsuario(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow();
        UsuarioEntity usuarioAlterado = aplicarPatchUsuario(patch, usuarioEntity);
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
