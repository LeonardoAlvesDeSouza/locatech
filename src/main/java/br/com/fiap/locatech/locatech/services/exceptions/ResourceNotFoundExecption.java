package br.com.fiap.locatech.locatech.services.exceptions;

public class ResourceNotFoundExecption extends RuntimeException {
    public ResourceNotFoundExecption(String message) {
        super(message);
    }
}
