/*
 * @(#)TareaFixture.java
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
package com.org.ms.fixture;

import com.org.ms.entities.Tarea;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * TareaFixture.
 *
 * @author Jose.
 * @version 1.0.0
 */
public class TareaFixture {

    /**
     * Genera un Objeto de usuarios para probar service.
     *
     * @return {@link Tarea}
     */
    public static Tarea getTarea() {
        final var tarea = new Tarea();
        tarea.setName("Juan Rodriguez");
        tarea.setDescripcion("hunter288");
        tarea.setActive(true);
        tarea.setCreated(new Date());
        tarea.setModified(new Date());
        tarea.setLastLogin(new Date());
        return tarea;
    }

    /**
     * Genera un Objeto Optional de usuarios para probar service.
     *
     * @return {@link Tarea}
     */
    public static Optional<Tarea> getTareaOptional() {
        final var tarea = new Tarea();
        tarea.setName("Juan Rodriguez");
        tarea.setDescripcion("hunter288");
        tarea.setActive(true);
        tarea.setCreated(new Date());
        tarea.setModified(new Date());
        tarea.setLastLogin(new Date());
        return Optional.of(tarea);
    }

    /**
     * Genera un Objeto de usuarios para probar service de solicitud.
     *
     * @return {@link Tarea}
     */
    public static Tarea getTareaRequest() {
        final var tarea = new Tarea();
        tarea.setName("Juan Rodriguez");
        tarea.setDescripcion("hunter288");
        return tarea;
    }

    /**
     * Genera una lista de usuarios para probar service.
     *
     * @return {@link Tarea}
     */
    public static List<Tarea> getTareas() {
        final List<Tarea> tareas = new ArrayList<>();
        final var tarea = new Tarea();
        tarea.setName("Juan Rodriguez");
        tarea.setDescripcion("hunter288");
        tarea.setActive(true);
        tarea.setCreated(new Date());
        tarea.setModified(new Date());
        tarea.setLastLogin(new Date());
        tareas.add(tarea);
        return tareas;
    }

}
