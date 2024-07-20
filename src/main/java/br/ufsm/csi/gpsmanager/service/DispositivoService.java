package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.model.dispositivo.Dispositivo;
import br.ufsm.csi.gpsmanager.model.dispositivo.DispositivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DispositivoService {
    private final DispositivoRepository repository;

    public List<Dispositivo> listarDispositivos() {
        return this.repository.findAll();
    }

    public Dispositivo buscarPorId(Long idDispositivo) {
        return this.repository.findById(idDispositivo).orElse(null);
    }
}
