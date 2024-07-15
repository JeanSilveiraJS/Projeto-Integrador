package br.ufsm.csi.gpsmanager.service;


import br.ufsm.csi.gpsmanager.model.usuario.Usuario;
import br.ufsm.csi.gpsmanager.model.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {
    private final UsuarioRepository repository;


    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        Usuario usuario = this.repository.findByLogin(login);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }else{
            return User
                    .withUsername(usuario.getLogin())
                    .password(usuario.getSenha())
                    //TODO: Implementar a permissão do usuário (remover o hardcode)
                    .authorities("USER")
                    .build();
        }
    }
}