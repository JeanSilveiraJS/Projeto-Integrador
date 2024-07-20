package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.agente.AgenteDTO;
import br.ufsm.csi.gpsmanager.model.agente.Agente;
import br.ufsm.csi.gpsmanager.service.AgenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agente")
@RequiredArgsConstructor
public class AgenteController {
    private final AgenteService service;

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        Agente agente = service.findAgente(id);
        return "redirect:/situacao/gerenciar/" + agente.getSituacao().getId_situacao() + "/agente/" + agente.getId_agente();
    }

    @PostMapping()
    public String cadastrar(@ModelAttribute AgenteDTO agenteDTO) {
        Agente agente = service.cadastrarDTO(agenteDTO);
        return "redirect:/situacao/gerenciar/" + agente.getSituacao().getId_situacao() + "/agente/" + agente.getId_agente();
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        Agente agente = service.findAgente(id);
        long idSituacao = (long) agente.getSituacao().getId_situacao();
        service.excluir(id);
        return "redirect:/situacao/gerenciar/" + idSituacao;
    }
}
