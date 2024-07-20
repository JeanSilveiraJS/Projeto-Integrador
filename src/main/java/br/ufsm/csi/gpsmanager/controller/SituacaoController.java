package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.agente.Agente;
import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import br.ufsm.csi.gpsmanager.model.situacao.Situacao;
import br.ufsm.csi.gpsmanager.service.AgenteService;
import br.ufsm.csi.gpsmanager.service.DispositivoService;
import br.ufsm.csi.gpsmanager.service.SituacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/situacao")
@RequiredArgsConstructor
public class SituacaoController {
    private final SituacaoService situacaoService;
    private final DispositivoService dispositivoService;
    private final AgenteService agenteService;

    //TODO: Implementar a injeção de dependência do googleMapsApiKey
    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;


    @GetMapping("/cadastrar")
    public String cadastrarSituacao() {
        return "situacao/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarSituacao(@ModelAttribute Situacao situacao) {
        Situacao situacaoCadastrada = this.situacaoService.cadastrar(situacao);
        return "redirect:/situacao/gerenciar/" + situacaoCadastrada.getId_situacao();
    }

    @GetMapping("/excluir/{id}")
    public String excluirSituacao(@PathVariable Long id) {
        this.situacaoService.excluirSituacao(id);
        return "redirect:/home";
    }

    @GetMapping("/gerenciar/{id}")
    public String gerenciarSituacao(@PathVariable Long id, Model model) {
        Situacao situacao = this.situacaoService.findSituacao(id);

        //model.addAttribute("apiKey", googleMapsApiKey);
        model.addAttribute("situacao", situacao);
        model.addAttribute("agentes", situacao.getAgentes());
        model.addAttribute("dispositivos", dispositivoService.listarDispositivos());

        return "situacao/gerenciar";
    }

    @GetMapping("/gerenciar/{idSituacao}/agente/{idAgente}")
    public String gerenciarAgente(@PathVariable Long idSituacao, @PathVariable Long idAgente, Model model) throws JsonProcessingException {
        Situacao situacao = this.situacaoService.findSituacao(idSituacao);
        Agente agente = this.agenteService.findAgente(idAgente);
        List<Localizacao> localizacoes = agente.getDispositivo().getLocalizacoes();

        ObjectMapper objectMapper = new ObjectMapper();
        String localizacoesJson = objectMapper.writeValueAsString(localizacoes);

        // Substituir aspas duplas em torno dos nomes dos campos por nada
        localizacoesJson = localizacoesJson.replaceAll("\"(\\w+)\":", "$1:");


        model.addAttribute("situacao", situacao);
        model.addAttribute("agente", agente);
        model.addAttribute("dispositivos", dispositivoService.listarDispositivos());
        model.addAttribute("localizacoes", localizacoesJson);

        return "situacao/gerenciar";
    }
}
