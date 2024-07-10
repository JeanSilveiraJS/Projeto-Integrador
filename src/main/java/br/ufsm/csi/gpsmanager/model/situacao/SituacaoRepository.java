package br.ufsm.csi.gpsmanager.model.situacao;

import br.ufsm.csi.gpsmanager.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SituacaoRepository extends JpaRepository<Situacao, Long> {
    public Situacao findByNome(String nome);
    List<Situacao> findByUsuario(Usuario usuario);
}
