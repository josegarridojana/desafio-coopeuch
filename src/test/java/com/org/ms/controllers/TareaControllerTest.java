/*
 * @(#)TareaControllerTest.java
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
package com.org.ms.controllers;

import com.org.ms.controllers.TareaController;
import com.org.ms.fixture.TareaFixture;
import com.org.ms.services.TareaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * TareaControllerTests.
 *
 * @author Jose.
 * @version 1.0.0
 */
@ExtendWith(SpringExtension.class)
public class TareaControllerTest {

    /** tareaService. */
    @Mock
    private TareaService tareaService;
    /** controller. */
    @InjectMocks
    private TareaController controller;

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test.
     *
     * @throws Exception {@link Exception}
     */
    @Test
    void testGetAllUsuarios() {
        final var rs = TareaFixture.getTareas();
        Mockito.when(this.tareaService.getAllTareas())
            .thenReturn(rs);
        Assertions.assertNotNull(this.controller.getAllTareas());
    }

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test.
     *
     * @throws Exception {@link Exception}
     */
    @Test
    void testCreateUsuario() {
        final var rs = TareaFixture.getTarea();
        final var rq = TareaFixture.getTareaRequest();
        Mockito.when(this.tareaService.createTarea(ArgumentMatchers.any()))
            .thenReturn(rs);
        Assertions.assertNotNull(this.controller.createTarea(rq));
    }

}
