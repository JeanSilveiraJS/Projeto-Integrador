package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.infra.security.TokenServiceJWT;
import br.ufsm.csi.gpsmanager.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class AutenticacaoController {
    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenServiceJWT;
    private final UsuarioService service;
    private final HttpSession session;

    @PostMapping
    public String efetuarLogin(@RequestParam String login, @RequestParam String senha, RedirectAttributes redirectAttributes) {

        try {
            Authentication autenticado = new UsernamePasswordAuthenticationToken(login, senha);
            Authentication at = manager.authenticate(autenticado);

            final User user = (User) at.getPrincipal();
            String token = this.tokenServiceJWT.gerarToken(user);

            session.setAttribute("id_usuario", service.buscarUsuarioPorLogin(login).getId_usuario());

            redirectAttributes.addFlashAttribute("token", token);

            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping
    public String login() {
        return "/usuario/login";
    }


    private record DadosTokenJWT(String token) {
    }
}