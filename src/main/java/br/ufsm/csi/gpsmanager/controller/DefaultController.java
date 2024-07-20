package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.situacao.Situacao;
import br.ufsm.csi.gpsmanager.service.DispositivoService;
import br.ufsm.csi.gpsmanager.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class DefaultController {
    private final HttpSession session;
    private final UsuarioService usuarioService;
    private final DispositivoService dispositivoService;


    @GetMapping({"/home", "/"})
    public String home(Model model) {
        Object idUsuarioObj = session.getAttribute("id_usuario");

        if (idUsuarioObj == null) {
            return "index";
        }

        Long idUsuario = Long.valueOf(idUsuarioObj.toString());
        List<Situacao> situacoes = usuarioService.buscarUsuario(idUsuario).getSituacoes();


        model.addAttribute("situacoes", situacoes);
        model.addAttribute("dispositivos", dispositivoService.listarDispositivos());

        return "home";
    }
}
