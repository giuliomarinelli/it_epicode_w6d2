package it.epicode.w6d2.exceptions;

import lombok.AllArgsConstructor;


public class AutoreIdNotFoundException extends Exception {
    public AutoreIdNotFoundException() {
        super();
    }
    public AutoreIdNotFoundException(String msg) {
        super(msg);
    }
}
