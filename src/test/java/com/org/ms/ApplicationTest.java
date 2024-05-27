/*
 * @(#)ApplicationTest.java
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
package com.org.ms;

import com.org.ms.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ApplicationTest.
 *
 * @author Jose.
 * @version 1.0.0
 */
public class ApplicationTest {

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Valida que el contexto spring se carga correctamente.
     */
    @Test
    void testLoadContext() {
        Application.main(new String[] {});
        Assertions.assertTrue(true);
    }
}
