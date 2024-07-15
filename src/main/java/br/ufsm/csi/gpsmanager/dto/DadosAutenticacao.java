package br.ufsm.csi.gpsmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DadosAutenticacao {
    private String login;
    private String senha;
}
