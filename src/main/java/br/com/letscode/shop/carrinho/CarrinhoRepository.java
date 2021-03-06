package br.com.letscode.shop.carrinho;

import br.com.letscode.shop.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {

    List<CarrinhoEntity> findAllByUsuario (UsuarioEntity userName);
}
