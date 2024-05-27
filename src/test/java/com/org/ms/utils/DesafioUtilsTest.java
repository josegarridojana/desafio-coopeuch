/*
 * @(#)DesafioUtilsTest.java
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

import com.org.ms.utils.DesafioUtils;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DesafioUtilsTest.
 *
 * @author Jose.
 * @version 1.0.0
 */
public class DesafioUtilsTest {

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test.
     *
     * @throws Exception {@link Exception}
     */
    @Test
    void testConstructor() throws Exception {
        final var constructor = DesafioUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
