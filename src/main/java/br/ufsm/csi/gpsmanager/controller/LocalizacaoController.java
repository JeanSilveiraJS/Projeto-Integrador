package br.ufsm.csi.gpsmanager.controller;

import br.ufsm.csi.gpsmanager.model.localizacao.LocalizacaoDTO;
import br.ufsm.csi.gpsmanager.model.dispositivo.Dispositivo;
import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import br.ufsm.csi.gpsmanager.service.DispositivoService;
import br.ufsm.csi.gpsmanager.service.LocalizacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacao")
@RequiredArgsConstructor
public class LocalizacaoController {
    private final LocalizacaoService service;
    private final DispositivoService dispositivoService;


    @PostMapping
    public ResponseEntity<?> setLocalizacoes(@RequestBody List<LocalizacaoDTO> localizacoesDTO) {
        for (LocalizacaoDTO dto : localizacoesDTO) {
            Localizacao localizacao = new Localizacao();
            localizacao.setTimestamp(dto.getTimestamp());
            localizacao.setLatitude(dto.getLatitude());
            localizacao.setLongitude(dto.getLongitude());

            if (dto.getId_dispositivo() != null) {
                Dispositivo dispositivo = dispositivoService.buscarPorId(dto.getId_dispositivo());
                if (dispositivo != null) {
                    localizacao.setDispositivo(dispositivo);
                } else {
                    return ResponseEntity.badRequest().body("Dispositivo com ID " + dto.getId_dispositivo() + " não encontrado.");
                }
            }

            service.salvar(localizacao);
        }

        return ResponseEntity.ok().body("Todas as localizações foram salvas com sucesso.");
    }
}
