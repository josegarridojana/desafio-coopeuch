/*
 * @(#)DesafioUtils.java
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
package com.org.ms.utils;

import java.util.regex.Pattern;

/**
 * DesafioUtils.
 *
 * @author Jose.
 * @version 1.0.0
 */
public final class DesafioUtils {

    // -------------------------------------------------------------------
    // -- Constructor ----------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Constructor.
     */
    private DesafioUtils() {
        throw new UnsupportedOperationException("Prohibido Instanciar esta clase");
    }

    /**
     * Valida email segun una expresiones regulares.
     *
     * @param email {@link String} email del cliente
     * @param regex {@link String} expresion regular
     * @return {@link Boolean}
     */
    public static boolean validateEmail(final String email, final String regex) {
        final var pattern = Pattern.compile(regex);
        final var matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    /**
     * Valida password segun una expresiones regulares.
     * Mínimo ocho caracteres, al menos una letra y un número
     *
     * @param password {@link String} password del cliente
     * @param regex {@link String} expresion regular
     * @return {@link Boolean}
     */
    public static boolean validatePassword(final String password, final String regex) {
        final var pattern = Pattern.compile(regex);
        final var matcher = pattern.matcher(password);
        if (password.isEmpty() || password == "" || !matcher.matches()) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

}
