package com.suse.addons.exceptions;

/**
 * Custom {@link Exception} to indicate problems during add-on registration.
 */
public class RegistrationException extends Exception {

    public RegistrationException() {
        super();
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable cause) {
        super(cause);
    }
}
