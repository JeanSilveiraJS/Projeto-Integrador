package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.Situacao;
import br.ufsm.csi.gpsmanager.model.Usuario;
import br.ufsm.csi.gpsmanager.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DefaultController {
    private final HttpSession session;
    private final UsuarioService usuarioService;

    public DefaultController(HttpSession session, UsuarioService usuarioService) {
        this.session = session;
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/home", "/"})
    public String home(Model model) {
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("situacoes", session.getAttribute("situacoes"));
            return "home";
        } else {
            return "index";
        }
    }
}
