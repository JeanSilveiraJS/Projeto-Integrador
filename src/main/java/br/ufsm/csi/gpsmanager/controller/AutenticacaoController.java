package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.Usuario;
import br.ufsm.csi.gpsmanager.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {
    private final UsuarioService service;
    private final HttpSession session;


    public AutenticacaoController(UsuarioService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }


    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "index";
    }

    @PostMapping
    public String efetuarLogin(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        Usuario usuario = service.autenticarUsuario(login, senha);
        if (usuario != null) {
            session.setAttribute("situacoes", usuario.getSituacoes());
            session.setAttribute("id_usuario", usuario.getId_usuario());
            return "redirect:/home";
        } else {
            return "login";
        }
    }
}
