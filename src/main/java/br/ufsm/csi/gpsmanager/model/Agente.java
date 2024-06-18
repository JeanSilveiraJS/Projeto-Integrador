package br.ufsm.csi.gpsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_agente;
    @ManyToOne
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;
    private String nome;
    private String descricao;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;
}
