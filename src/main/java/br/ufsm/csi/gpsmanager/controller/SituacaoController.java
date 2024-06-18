package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.Situacao;
import br.ufsm.csi.gpsmanager.model.Usuario;
import br.ufsm.csi.gpsmanager.service.SituacaoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/situacao")
public class SituacaoController {
    private final SituacaoService service;


    public SituacaoController(SituacaoService service) {
        this.service = service;
    }


    @GetMapping("/cadastrar")
    public String cadastrarSituacao() {
        return "situacao/cadastrarSituacao";
    }

    @PostMapping("/cadastrar")
    public String cadastrarSituacao(@ModelAttribute Situacao situacao) {
        this.service.cadastrar(situacao);
        return "redirect:/home";
    }
}
