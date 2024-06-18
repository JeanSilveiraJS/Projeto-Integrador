package br.ufsm.csi.gpsmanager.infra.exceptions;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String login) {
        super("Usuário com login " + login + " já cadastrado.");
    }
}