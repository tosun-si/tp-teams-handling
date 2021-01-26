package org.tosunsi.tp.teams.handling.api.exception;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String message) {
        super(message);
    }
}
