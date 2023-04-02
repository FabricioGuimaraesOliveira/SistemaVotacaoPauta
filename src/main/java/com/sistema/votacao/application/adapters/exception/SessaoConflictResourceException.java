package com.sistema.votacao.application.adapters.exception;

public class SessaoConflictResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SessaoConflictResourceException(String msg) {
        super(msg);
    }
}