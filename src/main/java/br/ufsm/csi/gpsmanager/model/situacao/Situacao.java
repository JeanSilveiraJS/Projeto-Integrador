package br.ufsm.csi.gpsmanager.model.situacao;

import br.ufsm.csi.gpsmanager.model.agente.Agente;
import br.ufsm.csi.gpsmanager.model.dispositivo.Dispositivo;
import br.ufsm.csi.gpsmanager.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "situacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Situacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_situacao;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private String nome; //TODO: Alterar BD - remover unique (criar constraint id_usuario, nome)
    private String descricao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_fim;
    @OneToMany(mappedBy = "situacao")
    private List<Agente> agentes;

    public List<Agente> getAgentes() {
        for (Agente agente : agentes) {
            agente.setSituacao(this);
            agente.setDispositivo(agente.getDispositivo());
        }
        return agentes;
    }


    public void registrarObserversParaDispositivos(SituacaoUpdater updater) {
        for (Agente agente : agentes) {
            Dispositivo dispositivo = agente.getDispositivo();
            if (dispositivo != null) {
                dispositivo.registerObserver(updater);
            }
        }
    }
}
