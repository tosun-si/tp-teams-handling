package org.tosunsi.tp.teams.handling.api.controller.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.tosunsi.tp.teams.handling.api.exception.TeamInvalidException;
import org.tosunsi.tp.teams.handling.api.exception.TeamNotFoundException;

@ControllerAdvice
public class TeamExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TeamInvalidException.class)
    protected ResponseEntity<Object> handleTeamInvalidException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Team invalid";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = TeamNotFoundException.class)
    protected ResponseEntity<Object> handleTeamNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Team not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}