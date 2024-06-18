package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.model.Situacao;
import br.ufsm.csi.gpsmanager.model.SituacaoRepository;
import br.ufsm.csi.gpsmanager.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituacaoService {
    private final SituacaoRepository repository;
    private final HttpSession httpSession;


    public SituacaoService(SituacaoRepository repository, HttpSession httpSession){
        this.repository = repository;
        this.httpSession = httpSession;
    }


    public void cadastrar(Situacao situacao){
        situacao.setUsuario((Usuario) httpSession.getAttribute("usuario"));
        this.repository.save(situacao);
    }
    public List<Situacao> findAllSituacoes(){
        return this.repository.findAll();
    }
/*
    public List<Situacao> findByUsuario(Long id){
        return this.repository.findByUsuario(id);
    }*/
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
