package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.infra.exceptions.UsuarioJaCadastradoException;
import br.ufsm.csi.gpsmanager.model.usuario.Usuario;
import br.ufsm.csi.gpsmanager.model.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;


    @Transactional
    public void cadastrarUsuario(Usuario usuario) {
        if (repository.findByLogin(usuario.getLogin()) != null) {
            throw new UsuarioJaCadastradoException(usuario.getLogin());
        }

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        repository.save(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        return repository.findById(id).get();
    }

    //todo: Implementar a autenticação do usuário com Spring Security
    public Usuario autenticarUsuario(String login, String senha) {
        Usuario usuario = repository.findByLogin(login);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return repository.findByLogin(login);
        } else {
            return null;
        }
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        return repository.findByLogin(login);
    }
}
