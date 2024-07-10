package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.usuario.Usuario;
import br.ufsm.csi.gpsmanager.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;


    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/cadastrar")
    public String cadastrarUsuario() {
        return "/usuario/cadastrar";
    }

    @PostMapping
    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        service.cadastrarUsuario(usuario);

        return "redirect:/login";
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return service.buscarUsuario(id);
    }

    //todo: Editar usuário
    //todo: Deletar usuário
}
