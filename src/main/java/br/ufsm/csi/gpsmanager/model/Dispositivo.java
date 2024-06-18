package br.ufsm.csi.gpsmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "dispositivo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    @Id
    private int id_dispositivo;
    private String nome;
    private String tipo;
    @OneToMany(mappedBy = "dispositivo")
    private List<Localizacao> localizacoes;
}
