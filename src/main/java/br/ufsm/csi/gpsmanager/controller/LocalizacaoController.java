package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.Localizacao;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocalizacaoController {

    @PutMapping("/{id}")
    public void setLocalizacao(@RequestBody Localizacao localizacao){
        System.out.println("Localizacao recebida: " + localizacao.toString());
    }
}
