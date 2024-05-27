/*
 * @(#)UsuarioService.java
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
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * UsuarioService.
 *
 * @author Jose.
 * @version 1.0.0
 */
public interface TareaService {

    List<Tarea> getAllTareas();

    List<Tarea> getAllTareasByName(String nombre);

    Tarea getTareaById(long id);

    Tarea createTarea(Tarea tarea);

    Tarea updateTarea(long id, Tarea usuario);

    ResponseEntity<HttpStatus> deleteTarea(long id);

    ResponseEntity<HttpStatus> deleteAllTareas();

    List<Tarea> findByIsActive();
}
