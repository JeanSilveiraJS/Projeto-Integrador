package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import br.ufsm.csi.gpsmanager.service.LocalizacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/localizacao")
public class LocalizacaoController {

    private final LocalizacaoService service;


    public LocalizacaoController(LocalizacaoService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Localizacao> setLocalizacao(@RequestBody Localizacao localizacao){
        Localizacao novaLocalizacao = service.salvar(localizacao);

        System.out.println(novaLocalizacao.toString());

        return ResponseEntity.ok(novaLocalizacao);
    }
}
