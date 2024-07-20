package br.ufsm.csi.gpsmanager.model.dispositivo;

import br.ufsm.csi.gpsmanager.model.observer.Observer;
import br.ufsm.csi.gpsmanager.model.observer.Subject;
import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dispositivo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo implements Subject {
    @Id
    private Long idDispositivo;
    private String nome;
    private String tipo;

    @OneToMany(mappedBy = "dispositivo")
    private List<Localizacao> localizacoes = new ArrayList<>();

    private transient List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Localizacao localizacao) {
        for (Observer observer : observers) {
            observer.update(localizacao);
        }
    }

    public void addLocalizacao(Localizacao localizacao){
        if (localizacoes == null) {
            localizacoes = new ArrayList<>();
        }
        localizacao.setDispositivo(this);
        localizacoes.add(localizacao);
        notifyObservers(localizacao);
    }
}