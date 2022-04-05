package br.com.letscode.shop.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity criar(UsuarioRequest request) {
        UsuarioEntity entity = request.toEntity();
        return usuarioRepository.save(entity);
    }

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity consultarUsuarioPorId(Long id) {
        return usuarioRepository.findById(Long.toString(id)).orElseThrow();
    }

    public void excluirUsuarioPorId(Long id) {
        usuarioRepository.deleteById(String.valueOf(id));
    }
}
