/*
 * @(#)DesafioConstants.java
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
package com.org.ms.constants;

import java.util.Locale;

/**
 * DesafioConstants.
 *
 * @author Jose.
 * @version 1.0.0
 */
public final class Constants {

    // -------------------------------------------------------------------
    // -- Locale ---------------------------------------------------------
    // -------------------------------------------------------------------
    /** Locale de los mini servicios. */
    public static final Locale LOCALE = new Locale("es", "CL");

    // -------------------------------------------------------------------
    // --EXPRESIONES REGULARES --------------------------------------------------------
    // -------------------------------------------------------------------
    /** REGEXP_VALIDATE_PASSWORD. */
    public static final String REGEXP_VALIDATE_PASSWORD =
        "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    /** REGEXP_VALIDATE_EMAIL. */
    public static final String REGEXP_VALIDATE_EMAIL =
        "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    // -------------------------------------------------------------------
    // --TOKEN --------------------------------------------------------
    // -------------------------------------------------------------------
    /** SECRET. */
    public static final String SECRET = "baeldung";
    /** ISSUER. */
    public static final String ISSUER = "Baeldung";
    /** SUBJECT. */
    public static final String SUBJECT = "Baeldung Details";
    /** DATA_CLAIM. */
    public static final String DATA_CLAIM = "userId";
    /** DATA. */
    public static final String DATA = "1234";
    /** TOKEN_VALIDITY_IN_MILLIS. */
    public static final long TOKEN_VALIDITY_IN_MILLIS = 5000L;

    // -------------------------------------------------------------------
    // -- Constructor ----------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Constructor.
     */
    private Constants() {
        throw new UnsupportedOperationException("Prohibido Instanciar esta clase");
    }

}
