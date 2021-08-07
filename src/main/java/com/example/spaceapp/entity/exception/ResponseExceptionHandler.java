package com.example.spaceapp.entity.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MasterNotExistsException.class)
    public String handleMasterNotExistsException() {
        return "errors/master-not-exists";
    }

    @ExceptionHandler(PlanetNotExistsException.class)
    public String handlePlanetNotExistsException() {
        return "errors/planet-not-exists";
    }

    @ExceptionHandler(PlanetAlreadyTakenException.class)
    public String handlePlanetAlreadyTakenException() {
        return "errors/planet-already-taken";
    }
}
