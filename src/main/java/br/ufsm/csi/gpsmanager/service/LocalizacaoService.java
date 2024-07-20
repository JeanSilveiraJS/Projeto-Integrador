package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import br.ufsm.csi.gpsmanager.model.localizacao.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {
    LocalizacaoRepository repository;


    @Autowired
    public LocalizacaoService(LocalizacaoRepository repository){
        this.repository = repository;
    }

    public List<Localizacao> buscarPorDispositivo(Long idDispositivo){
        if (this.repository == null){
            System.out.println("repository é nulo");
            return null;
        }
        List<Localizacao> localizacoes = this.repository.findAllByDispositivo_IdDispositivo(idDispositivo);
        for (Localizacao localizacao : localizacoes) {
            localizacao.setDispositivo(null);
        }
        return localizacoes;
    }

    public Localizacao salvar(Localizacao localizacao){
        //TODO: Adicionar verificação de duplicidade
        Localizacao localizacaoSalva = this.repository.save(localizacao);

        if (localizacaoSalva.getDispositivo() != null){
            localizacaoSalva.getDispositivo().notifyObservers(localizacaoSalva);
        }

        return localizacaoSalva;
    }
}
