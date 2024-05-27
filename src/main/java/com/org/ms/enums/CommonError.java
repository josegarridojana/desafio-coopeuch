/*
 * @(#)CommonError.java
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
package com.org.ms.enums;

import lombok.RequiredArgsConstructor;

/**
 * CommonError.
 *
 * @author Jose.
 * @version 1.0.0, 19-05-2024
 */
@RequiredArgsConstructor
public enum CommonError implements EnumsError {

    /** DEFAULT. */
    DEFAULT("99", "Error generico"),
    /** OK. */
    OK("0", "Operacion exitosa"),
    /** TOKEN. */
    TOKEN("1", "Error al procesar token"),
    /** DECRYPT. */
    DECRYPT("2", "Error al desencriptar objeto"),
    /** ENCRYPT. */
    ENCRYPT("3", "Error al encriptar objeto"),
    /** CHALLENGE. */
    CHALLENGE("4", "Desafio invalido"),
    /** VALIDATE_CHALLENGE. */
    VALIDATE_CHALLENGE("5", "Error al validar el desafio"),
    /** CREDENTIALS. */
    CREDENTIALS("6", "Error al validar credenciales"),
    /** PUSH. */
    PUSH("7", "Error al enviar notificacion push"),
    /** NOT_ALLOWED. */
    NOT_ALLOWED("8", "No permitido"),
    /** INVALID_ARGS. */
    INVALID_ARGS("9", "Argumentos invalidos"),
    /** INVALID_BODY. */
    INVALID_BODY("10", "Cuerpo de llamada invalido"),
    /** REST_CLIENT. */
    REST_CLIENT("11", "Error al consumir servicio REST"),
    /** JWT_NO_HEADER. */
    JWT_NO_HEADER("12", "No existe cabecera JWT"),
    /** JWT_INVALID. */
    JWT_INVALID("13", "Token invalido"),
    /** APP_NO_HEADER. */
    APP_NO_HEADER("14", "No existe cabecera "),
    /** USER_NO_HEADER. */
    USER_NO_HEADER("15", "No existe cabecera "),
    /** MULTICHANNEL_PROPERTY. */
    MULTICHANNEL_PROPERTY("16", "No existe valor para property multicanal requerida."),
    /** JDBC_ERROR. */
    JDBC_ERROR("17", "Error en operacion JDBC"),
    /** Cuando ocurre un error al intentar criptografia. */
    CRYPTO_ERROR("18", "Error en criptografia"),
    /** Cuando falla la verificacion de la firma. */
    CRYPTO_VERIFY("19", "Error al verificar firma"),

    // resiliencia
    /** Cuando la compuerta de circuit breaker esta abierta. */
    CIRCUIT_BREAKER("50", "Error compuerta abierta"),
    /** Cuando el tiempo establecido del timelimiter es superado. */
    TIME_LIMITER("51", "Se supero tiempo limite"),
    /** Cuando no se tiene una respuesta correcta en la cantidad de intentos establecidos en el retry. */
    RETRY("52", "Se supero cantidad maxima de intentos"),
    /** Cuando se superan la cantidad concurrentes establecidos en el bulkhead */
    BULKHEAD("53", "El bulkhead está lleno y no permite más peticiones."),
    /** Cuando se superan la cantidad de llamadas en el tiempo establecidos en el rate limiter */
    RATE_LIMITER("54", "No se permiten más peticiones por rate limiter.");

    /** code. */
    private final String code;
    /** message. */
    private final String message;

    // -------------------------------------------------------------------
    // -- Métodos Sobrescritos -------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String getCode() {
        return this.getPrefix() + this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStandardLogCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStandardLogMessage() {
        return this.message;
    }

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Retorna el {@link CommonError} correspondiente, segun el code de error.
     *
     * @param code code del error
     * @return {@link CommonError} correspondiente o {@link CommonError#DEFAULT}
     */
    public static CommonError getErrorByCode(final String code) {
        for (final CommonError error : CommonError.values()) {
            if (error.getCode().equalsIgnoreCase(code)) {
                return error;
            }
        }
        return DEFAULT;
    }

}
