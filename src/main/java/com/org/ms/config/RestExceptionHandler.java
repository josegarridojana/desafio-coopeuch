/*
 * @(#)RestExceptionHandler.java
 *
 * Copyright (c) (Chile). All rights reserved.
 *
 * All rights to this product are owned by and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package com.org.ms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.ms.enums.CommonError;
import com.org.ms.exceptions.SimpleException;
import lombok.NoArgsConstructor;
//import org.slf4j.CleanLogger;
//import org.slf4j.CleanLoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * RestExceptionHandler.
 *
 * @author Jose.
 * @version 1.0.0, 19-05-2024
 */
@NoArgsConstructor
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /** SIMPLE_EXCEPTION. */
    private static final String SIMPLE_EXCEPTION = "cl.bch.cloud.exception.SimpleException";
    /** LOGGER. */
    // private static final CleanLogger LOGGER_HANDLER = CleanLoggerFactory.getLogger(RestExceptionHandler.class);
    /** mapper. */
    private final ObjectMapper mapper = new ObjectMapper();

    // -------------------------------------------------------------------
    // -- Métodos Sobrescritos -------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status,
        final WebRequest request) {
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.INVALID_ARGS), headers, status,
            request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        final HttpRequestMethodNotSupportedException ex,
        final HttpHeaders headers,
        final HttpStatus status,
        final WebRequest request) {
        // RestExceptionHandler.LOGGER_HANDLER.warn("Method not supported: " + ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.NOT_ALLOWED), headers, status,
            request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
        final ServletRequestBindingException ex,
        final HttpHeaders headers,
        final HttpStatus status,
        final WebRequest request) {
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.NOT_ALLOWED), headers, status, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        final HttpMessageNotReadableException ex,
        final HttpHeaders headers, final HttpStatus status,
        final WebRequest request) {
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.INVALID_BODY), headers, status,
            request);
    }

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Manejo de errores controlados: Tipo de todas la excepciones controladas de la aplicacion.
     *
     * @param ex {@link SimpleException} excepcion a controlar
     * @param webRequest {@link WebRequest} info del request
     * @return {@link ResponseEntity} respuesta en formato JSON de la aplicacion.
     */
    @ExceptionHandler(SimpleException.class)
    public ResponseEntity<Object> handleSimpleException(final SimpleException ex, final WebRequest webRequest) {
        // RestExceptionHandler.LOGGER_HANDLER.warn("Hubo una excepcion controlada. Estado. {}, Codigo: {}, Mensaje: {}
        // ",
        // ex.getStatus(), ex.getCode(), ex.getMessage(), ex);
        return this.doHandleSimpleException(ex, webRequest);
    }

    /**
     * Manejo de las SimpleException
     *
     * @param ex {@link SimpleException} excepcion a controlar
     * @param webRequest webRequest {@link WebRequest} info del request
     * @return {@link ResponseEntity} respuesta en formato JSON de la aplicacion.
     */
    private ResponseEntity<Object> doHandleSimpleException(final SimpleException ex, final WebRequest webRequest) {
        if (ex.getCause() != null) {
            // RestClientResponseException
            if (RestExceptionHandler.isCauseType(ex, RestClientResponseException.class)) {
                final var cause = (RestClientResponseException) ex.getCause();
                if (this.isResponseFromOurApi(cause)) {
                    return this.handleExceptionInternal(ex, cause.getResponseBodyAsString(),
                        cause.getResponseHeaders(), HttpStatus.valueOf(cause.getRawStatusCode()), webRequest);
                }
            } else if (RestExceptionHandler.isCauseType(ex, SimpleException.class)) {
                return this.handleSimpleException((SimpleException) ex.getCause(), webRequest);
            }
        }
        return this.handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus()), webRequest);
    }

    /**
     * Manejo de errores de resiliencia.
     *
     * @param ex {@link SimpleException} excepcion a controlar
     * @param webRequest {@link WebRequest} info del request
     * @return {@link ResponseEntity} respuesta en formato JSON de la aplicacion.
     */
    /*
     * @ExceptionHandler(ResilienceException.class)
     * public ResponseEntity<Object> handleResilienceException(final ResilienceException ex, final WebRequest
     * webRequest) {
     * RestExceptionHandler.LOGGER_HANDLER.warn(
     * "Hubo una excepcion controlada de resiliencia. Estado. {}, Codigo: {}, Mensaje: {}, Intentos: {} ",
     * ex.getStatus(), ex.getCode(), ex.getMessage(), ex.getAttemptsNumber(), ex);
     * return this.doHandleSimpleException(ex, webRequest);
     * }
     */

    /**
     * Manejo de errores NO controlados: de respuesta de la comunicacion REST a
     * otros backends.
     *
     * @param ex excepcion a controlar
     * @param webRequest info del request
     * @return {@link ResponseEntity} respuesta en formato JSON de la aplicacion.
     */
    @ExceptionHandler({ RestClientResponseException.class })
    public ResponseEntity<Object> handleRestClientResponseException(final RestClientResponseException ex,
        final WebRequest webRequest) {
        if (this.isResponseFromOurApi(ex)) {
            return this.handleExceptionInternal(ex, ex.getResponseBodyAsString(), ex.getResponseHeaders(),
                HttpStatus.valueOf(ex.getRawStatusCode()), webRequest);
        }
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.REST_CLIENT), ex.getResponseHeaders(),
            HttpStatus.valueOf(ex.getRawStatusCode()), webRequest);
    }

    /**
     * Manejo de errores NO controlados: Exception General.
     *
     * @param ex excepcion a controlar
     * @param webRequest info del request
     * @return {@link ResponseEntity} respuesta en formato JSON de la aplicacion.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(final Exception ex, final WebRequest webRequest) {
        // RestExceptionHandler.LOGGER_HANDLER.error("Excepcion NO manejada de la aplicacion.", ex);
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.DEFAULT), new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    // -------------------------------------------------------------------
    // -- Métodos Protected ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Manejo de errores NO controlados: Tipo de argumento en un controller no
     * correspondiente.
     *
     * @param ex excepcion a controlar
     * @param request info del request
     * @return {@link ResponseEntity} respuesta en formato JSON de la aplicacion.
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    protected ResponseEntity<Object> handleInvalidRequest(final MethodArgumentTypeMismatchException ex,
        final WebRequest request) {
        return this.handleExceptionInternal(ex, new SimpleException(CommonError.INVALID_ARGS), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
    }

    // -------------------------------------------------------------------
    // -- Métodos Privados -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Valida si la exception es arrojada desde alguno de los rest de la aplicacion.
     *
     * @param ex Exception
     * @return <code>true</code> si es una respuesta de nuestro API,
     *         <code>false</code> en otra condicion.
     */
    private boolean isResponseFromOurApi(final RestClientResponseException ex) {
        try {
            final var tree = this.mapper.readTree(ex.getResponseBodyAsByteArray());
            return null != tree.get("code") && null != tree.get("message") && null != tree.get("source")
                && RestExceptionHandler.SIMPLE_EXCEPTION.equals(tree.get("source").asText());
        } catch (final Exception e) {
            // RestExceptionHandler.LOGGER_HANDLER.warn("External Exception.", e);
            // No es de los enviados por el backend que se envie el simple exception
            return false;
        }
    }

    /**
     * Valida si la exception causa pertenece a un tipo en particular.
     *
     * @param e excepcion
     * @param type si es un tipo de esas excepciones.
     * @return <code>true</code> si la excepcion es del <code>type</code>,
     *         <code>false</code> en otra condicion.
     */
    private static boolean isCauseType(final Exception e, final Class<?> type) {
        return type.isAssignableFrom(e.getCause().getClass());
    }
}
