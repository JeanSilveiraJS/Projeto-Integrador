package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.model.situacao.Situacao;
import br.ufsm.csi.gpsmanager.model.situacao.SituacaoRepository;
import br.ufsm.csi.gpsmanager.model.usuario.Usuario;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SituacaoService {
    private final SituacaoRepository repository;
    private final HttpSession httpSession;


    public Situacao cadastrar(Situacao situacao){
        Usuario usuario = (Usuario) this.httpSession.getAttribute("usuario");
        if (usuario == null) {
            return null;
        }
        situacao.setUsuario(usuario);
        situacao.setAgentes(new ArrayList<>());
        return this.repository.save(situacao);
    }

    public void excluirSituacao(Long id){
        this.repository.deleteById(id);
    }

    public List<Situacao> findAllSituacoes(){
        return this.repository.findAll();
    }

    public List<Situacao> getSituacoesByUsuario(Usuario usuario) {
        return this.repository.findByUsuario(usuario);
    }

    public Situacao findSituacao(Long id){
        return this.repository.findById(id).orElse(null);
    }
    public void deletar(Long id){
        this.repository.deleteById(id);
    }
    public void atualizar(Situacao situacao){
        this.repository.save(situacao);
    }
}
