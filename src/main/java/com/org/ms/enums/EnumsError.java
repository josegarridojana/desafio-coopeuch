/*
 * @(#)EnumsError.java
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

import java.io.Serializable;

/**
 * EnumsError.
 *
 * @author Jose.
 * @version 1.0.0, 19-05-2024
 */
public interface EnumsError extends Serializable {

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Obtiene prefijo.
     *
     * @return {@link String}
     */
    String getPrefix();

    /**
     * Obtiene el codigo de error que se muestra al usuario.
     *
     * @return {@link String}
     */
    String getCode();

    /**
     * Obtiene el mensaje de la aplicacion que se muestra al usuario
     *
     * @return {@link String}
     */
    String getMessage();

    /**
     * Obtiene el codigo para log standard <br>
     * <strong>(retro-compatibilidad)</strong>
     *
     * @return {@link String}
     */
    String getStandardLogCode();

    /**
     * Obtiene el mensaje para log standard <br>
     * <strong>(retro-compatibilidad)</strong>
     *
     * @return {@link String}
     */
    String getStandardLogMessage();
}
