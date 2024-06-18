package br.ufsm.csi.gpsmanager.model;

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
    private String nome;
    private String descricao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_fim;
    @OneToMany(mappedBy = "situacao")
    private List<Agente> agentes;
}
