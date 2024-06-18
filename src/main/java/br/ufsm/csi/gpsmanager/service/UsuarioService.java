package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.infra.exceptions.UsuarioJaCadastradoException;
import br.ufsm.csi.gpsmanager.model.Usuario;
import br.ufsm.csi.gpsmanager.model.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;


    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void cadastrarUsuario(Usuario usuario) {
        if (repository.findByLogin(usuario.getLogin()) != null) {
            throw new UsuarioJaCadastradoException(usuario.getLogin());
        }
        //todo: Criptografar a senha do usuário ANTES de salvar (verificar ao ativar o Spring Security)
        //usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
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

    public Usuario buscarUsuarioPorIdUsuario(Long id_usuario) {
        return repository.findUsuarioById_usuario(id_usuario);
    }
}
