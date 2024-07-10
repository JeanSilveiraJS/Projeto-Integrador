package br.ufsm.csi.gpsmanager.model.usuario;

import br.ufsm.csi.gpsmanager.model.situacao.Situacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    @NotNull(message = "Informe o nome do usuário")
    private String nome;
    @NotNull(message = "Informe um login para o usuário")
    @Column(unique = true)
    private String login;
    @NotNull(message = "Informe uma senha")
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Situacao> situacoes;


    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
