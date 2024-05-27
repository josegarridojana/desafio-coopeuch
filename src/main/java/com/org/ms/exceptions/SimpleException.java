/*
 * @(#)SimpleException.java
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
/**
 *
 */
package com.org.ms.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.ms.enums.CommonError;
import com.org.ms.enums.EnumsError;
import feign.FeignException;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * SimpleException.
 *
 * @author Jose.
 * @version 1.0.0
 */
@JsonIgnoreProperties({
    "cause",
    "stackTrace",
    "localizedMessage",
    "suppressed",
    "status",
    "timestamp",
    "trace",
    "error",
    "path" })
public class SimpleException extends RuntimeException {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1905122041950251207L;
    /** LOGGER. */
    // private static final CleanLogger LOGGER = CleanLoggerFactory.getLogger(SimpleException.class);
    /** HTTP status para esta excepcion. Por defecto INTERNAL ERROR SERVER (500) */
    private final int status;
    /** Codigo de error de cada aplicacion */
    private final String code;
    /** Enum de error de cada aplicacion */
    private final EnumsError errorEnum;
    /** Identificador que el mensaje es de los backend de los motores */
    private final String source = SimpleException.class.getName();

    /**
     * Constructor.
     *
     * @param enumError Enum that provide the code and the message (the detail
     *            message (which is saved for later retrieval by the
     *            {@link #getMessage()} method)).
     */
    public SimpleException(final EnumsError enumError) {
        this(enumError, null);
    }

    /**
     * Constructor.
     *
     * @param enumError Enum that provide the code and the message (the detail
     *            message (which is saved for later retrieval by the
     *            {@link #getMessage()} method)).
     * @param cause the cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). (A <code>null</code> value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public SimpleException(final EnumsError enumError, final Throwable cause) {
        this(enumError, 500, cause);
    }

    /**
     * Constructor principal.
     *
     * @param enumError Enum that provide the code and the message (the detail
     *            message (which is saved for later retrieval by the
     *            {@link #getMessage()} method)).
     * @param httpStatus status http to response to this error
     */
    public SimpleException(final EnumsError enumError, final int httpStatus) {
        this(enumError, httpStatus, null);
    }

    /**
     * Constructor principal.
     *
     * @param enumError Enum that provide the code and the message (the detail
     *            message (which is saved for later retrieval by the
     *            {@link #getMessage()} method)).
     * @param httpStatus status http to response to this error
     * @param cause the cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). (A <code>null</code> value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public SimpleException(final EnumsError enumError, final int httpStatus, final Throwable cause) {
        super(enumError.getMessage(), cause);
        this.errorEnum = enumError;
        this.status = httpStatus;
        this.code = enumError.getCode();
    }

    /**
     * Constructor.
     *
     * @param code {@link String}
     * @param message {@link String}
     * @param httpStatus {@link HttpStatus}
     * @param cause {@link Throwable}
     */
    private SimpleException(final String code, final String message, final int httpStatus, final Throwable cause) {
        super(message, cause);
        this.errorEnum = CommonError.REST_CLIENT;
        this.code = code;
        this.status = httpStatus;
    }

    // -------------------------------------------------------------------
    // -- Métodos Getters ------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Obtiene el campo "status".
     *
     * @return status
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Obtiene el campo "code".
     *
     * @return code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Obtiene el campo "errorEnum".
     *
     * @return errorEnum
     */
    public EnumsError getErrorEnum() {
        return this.errorEnum;
    }

    /**
     * Obtiene el campo "source".
     *
     * @return source
     */
    public String getSource() {
        return this.source;
    }

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Convierte de {@link FeignException} a {@link SimpleException}
     *
     * @param e {@link FeignException}
     * @return {@link SimpleException}
     */
    public static SimpleException fromFeign(final FeignException e) {
        return SimpleException.fromFeign(e, e.status());
    }

    /**
     * Convierte de {@link FeignException} a {@link SimpleException} modificando el HttpStatus.
     *
     * @param e {@link FeignException}
     * @param httpStatus {@link HttpStatus}
     * @return {@link SimpleException}
     */
    public static SimpleException fromFeign(final FeignException e, final int httpStatus) {
        try {
            final var m = (Map<String, Object>) new ObjectMapper().readValue(e.contentUTF8(), Object.class);
            final var code = m.containsKey("internalCode") ? m.get("internalCode").toString() : m.get("code")
                .toString();
            final var message = m.containsKey("description") ? m.get("description").toString() : m.get("message")
                .toString();
            return new SimpleException(code, message, httpStatus, e);
        } catch (final Exception ex) {
            // SimpleException.LOGGER.debug("Error al parsear feign a simpleexception.{}", ex.getMessage(), ex);
            throw new SimpleException(CommonError.REST_CLIENT, HttpStatus.SERVICE_UNAVAILABLE.value(), ex);
        }
    }

}
