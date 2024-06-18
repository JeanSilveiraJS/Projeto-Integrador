package br.ufsm.csi.gpsmanager.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByLogin(String login);
    public Usuario findUsuarioById_usuario(Long id_usuario);
    public Usuario findByLoginAndSenha(String login, String senha);
}
