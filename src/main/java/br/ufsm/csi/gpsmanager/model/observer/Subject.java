package br.ufsm.csi.gpsmanager.model.observer;

import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Localizacao localizacao);
}
