package br.ufsm.csi.gpsmanager.model.localizacao;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LocalizacaoDTO {
    private Timestamp timestamp;
    private double latitude;
    private double longitude;
    private Long id_dispositivo;
}
