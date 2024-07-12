package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.situacao.Situacao;
import br.ufsm.csi.gpsmanager.model.situacao.SituacaoUpdater;
import br.ufsm.csi.gpsmanager.service.SituacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "situacao/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarSituacao(@ModelAttribute Situacao situacao) {
        this.service.cadastrar(situacao);
        return "redirect:/gerenciar/{situacao.getId()}";
    }

    @GetMapping("/gerenciar/{id}")
    public String gerenciarSituacao(@PathVariable Long id, Model model) {
        //Situacao situacao = this.service.findSituacao(id);
        //model.addAttribute("situacao", situacao);
        return "situacao/gerenciar";
    }
}
