package br.ufsm.csi.gpsmanager.model.localizacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ufsm.csi.gpsmanager.model.dispositivo.Dispositivo;

import java.sql.Timestamp;

@Entity
@Table(name = "localizacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Localizacao {
    @Id
    private Timestamp timestamp;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;

    @Override
    public String toString() {
        return "Localizacao{" +
                "dispositivo=" + dispositivo +
                ", timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}