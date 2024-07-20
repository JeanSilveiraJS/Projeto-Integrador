package br.ufsm.csi.gpsmanager.service;

import br.ufsm.csi.gpsmanager.model.agente.AgenteDTO;
import br.ufsm.csi.gpsmanager.model.agente.Agente;
import br.ufsm.csi.gpsmanager.model.agente.AgenteRepository;
import br.ufsm.csi.gpsmanager.model.dispositivo.Dispositivo;
import br.ufsm.csi.gpsmanager.model.localizacao.Localizacao;
import br.ufsm.csi.gpsmanager.model.situacao.Situacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenteService {
    private final AgenteRepository repository;
    private final DispositivoService dispositivoService;
    private final LocalizacaoService localizacaoService;
    private final SituacaoService situacaoService;

    public Agente findAgente(Long idAgente) {
        Agente agente = this.repository.findById(idAgente).orElse(null);
        Dispositivo dispositivo = agente.getDispositivo();

        List<Localizacao> localizacoes = localizacaoService.buscarPorDispositivo(dispositivo.getIdDispositivo());
        dispositivo.setLocalizacoes(localizacoes);

        return agente;
    }

    public Agente cadastrar(Agente agente) {
        return repository.save(agente);
    }

    public Agente cadastrarDTO(AgenteDTO agenteDTO) {
        Agente agente = new Agente();
        Situacao situacao = new Situacao();
        Dispositivo dispositivo = new Dispositivo();

        situacao = situacaoService.findSituacao(agenteDTO.getId_situacao());
        dispositivo = dispositivoService.buscarPorId(agenteDTO.getId_dispositivo());

        agente.setSituacao(situacao);
        agente.setNome(agenteDTO.getNome());
        agente.setDescricao(agenteDTO.getDescricao());
        agente.setTipo(agenteDTO.getTipo());
        agente.setDispositivo(dispositivo);

        return this.cadastrar(agente);
    }

    public void excluir(Long idAgente) {
        repository.deleteById(idAgente);
    }
}
