/*
 * @(#)Error.java
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
 * Error.
 *
 * @author Jose.
 * @version 1.0.0, 23-02-2024
 */

@RequiredArgsConstructor
public enum EnumError implements EnumsError {

    /** DEFAULT. */
    DEFAULT("99", "Error Generico"),
    /** ERROR_ARTICLE. */
    ERROR_ARTICLE("1", "Error articulos");

    /** code. */
    private final String code;
    /** message. */
    private final String message;

    /**
     * // -------------------------------------------------------------------
     * // -- Métodos Públicos -----------------------------------------------
     * // -------------------------------------------------------------------
     * /**
     * Obtiene enum de Error segun parametro.
     *
     * @param code {@link String}
     * @return {@link CodigosCanjeError}
     */
    public static EnumError getErrorByCode(final String code) {
        for (final EnumError error : EnumError.values()) {
            if (error.getCode().equalsIgnoreCase(code)) {
                return error;
            }
        }
        return DEFAULT;
    }

    /**
     * Obtiene el campo "code".
     *
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Obtiene el campo "message".
     *
     * @return message
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
        return "BFF";
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

}
