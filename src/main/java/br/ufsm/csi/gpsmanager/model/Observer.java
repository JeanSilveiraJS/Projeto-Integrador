package br.ufsm.csi.gpsmanager.model;

import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;

public interface Observer {
    void update(Localizacao localizacao);
}
