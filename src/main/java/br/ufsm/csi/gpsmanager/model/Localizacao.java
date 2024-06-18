package br.ufsm.csi.gpsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;
    private double latitude;
    private double longitude;
}
