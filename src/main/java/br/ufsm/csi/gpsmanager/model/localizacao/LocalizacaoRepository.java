package br.ufsm.csi.gpsmanager.model.localizacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Timestamp> {
    //TODO: definir métodos personalizados
}
