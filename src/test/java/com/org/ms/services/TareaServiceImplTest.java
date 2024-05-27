/*
 * @(#)TareaServiceImplTest.java
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
package com.org.ms.services;

import com.org.ms.entities.Tarea;
import com.org.ms.exceptions.SimpleException;
import com.org.ms.fixture.TareaFixture;
import com.org.ms.properties.ExpresRegexProperties;
import com.org.ms.repositories.TareaRepository;
import com.org.ms.services.impl.TareaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * TareaServiceImplTest.
 *
 * @author Jose.
 * @version 1.0.0
 */
public class TareaServiceImplTest {

    /** TareaRepository. */
    @Mock
    private TareaRepository TareaRepository;
    /** expresRegexProperties. */
    @Mock
    private ExpresRegexProperties expresRegexProperties;
    /** TareaService. */
    @InjectMocks
    private TareaServiceImpl TareaService;

    // -------------------------------------------------------------------
    // -- Setup ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Setup.
     *
     * @throws Exception Exception
     */
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    // -------------------------------------------------------------------
    // -- Test -----------------------------------------------------------
    // -------------------------------------------------------------------

    /**
     * Test.
     */
    @Test
    void testGetAllTareas() {
        final var rs = TareaFixture.getTareas();
        Mockito.when(this.expresRegexProperties.getEmail()).thenReturn(
            "^[\\w-\\\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Mockito.when(this.expresRegexProperties.getPassword()).thenReturn(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Mockito.when(this.TareaRepository.findAll())
            .thenReturn(rs);
        Assertions.assertNotNull(this.TareaService.getAllTareas());
    }

    /**
     * Test.
     */
    @Test
    void testCreateTarea() {
        final var rs = TareaFixture.getTarea();
        final var rq = TareaFixture.getTareaRequest();
        final var findUserEmail = TareaFixture.getTareaOptional();
        Mockito.when(this.expresRegexProperties.getEmail()).thenReturn(
            "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Mockito.when(this.expresRegexProperties.getPassword()).thenReturn(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Mockito.when(this.TareaRepository.findByDescripcion("sasa@sas.cl"))
            .thenReturn(findUserEmail);
        Mockito.when(this.TareaRepository.save(ArgumentMatchers.any(Tarea.class)))
            .thenReturn(rs);
        Assertions.assertNotNull(this.TareaService.createTarea(rq));
    }

    /**
     * Test.
     */
    @Test
    void testCreateTareaException() {
        final var rq = TareaFixture.getTareaRequest();
        Mockito.when(this.TareaRepository.save(ArgumentMatchers.any(Tarea.class)))
            .thenThrow(SimpleException.class);
        Assertions.assertThrows(SimpleException.class,
            () -> this.TareaService.createTarea(rq));
    }

    /**
     * Test.
     */
    @Test
    void testCreateTareaEmailException() {
        final var rs = TareaFixture.getTarea();
        final var rq = TareaFixture.getTareaRequest();
        final var findUserEmail = TareaFixture.getTareaOptional();
        Mockito.when(this.expresRegexProperties.getEmail()).thenReturn(
            "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Mockito.when(this.expresRegexProperties.getPassword()).thenReturn(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Mockito.when(this.TareaRepository.findByDescripcion(ArgumentMatchers.anyString()))
            .thenReturn(findUserEmail);
        Mockito.when(this.TareaRepository.save(ArgumentMatchers.any(Tarea.class)))
            .thenReturn(rs);
        Assertions.assertThrows(SimpleException.class,
            () -> this.TareaService.createTarea(rq));
    }

}
