package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import br.ufsm.csi.gpsmanager.model.localizacao.LocalizacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository){
        this.repository = repository;
    }

    public Localizacao salvar(Localizacao localizacao){
        //TODO: Implementar lógica de salvar localização
        // (descomentar linha abaixo, adicionar lógica completa e remover linha seguinte)
        //Localizacao localizacaoSalva = this.repository.save(localizacao);
        Localizacao localizacaoSalva = localizacao;

        if (localizacaoSalva.getDispositivo() != null){
            localizacaoSalva.getDispositivo().notifyObservers(localizacaoSalva);
        }

        return localizacaoSalva;
    }
}
