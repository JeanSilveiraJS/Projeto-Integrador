package br.ufsm.csi.gpsmanager.model.localizacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Timestamp> {
    //TODO: definir métodos personalizados
    List<Localizacao> findAllByDispositivo_IdDispositivo(Long idDispositivo);
}
