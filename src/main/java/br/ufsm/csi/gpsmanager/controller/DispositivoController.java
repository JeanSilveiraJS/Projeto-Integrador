package br.ufsm.csi.gpsmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoController {
    @GetMapping("/cadastrar")
    public String cadastrarDispositivo() {
        return "dispositivo/cadastrar";
    }
}
