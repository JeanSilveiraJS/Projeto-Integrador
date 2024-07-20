package br.ufsm.csi.gpsmanager.model.agente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgenteDTO {
    private Long id_situacao;
    private String nome;
    private String descricao;
    private String tipo;
    private Long id_dispositivo;
}
