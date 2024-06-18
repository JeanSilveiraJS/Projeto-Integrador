package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.Usuario;
import br.ufsm.csi.gpsmanager.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;


    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/cadastro")
    public String cadastrarUsuario() {
        return "cadastro";
    }

    @PostMapping
    public void cadastrarUsuario(@ModelAttribute Usuario usuario) {
        service.cadastrarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return service.buscarUsuario(id);
    }

    //todo: Editar usuário
    //todo: Deletar usuário
}
