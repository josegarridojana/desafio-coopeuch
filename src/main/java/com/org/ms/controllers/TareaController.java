/*
 * @(#)TareaController.java
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

import com.org.ms.entities.Tarea;
import com.org.ms.services.TareaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TareaController.
 *
 * @author Jose.
 * @version 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/tareas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TareaController {

    /** usuarioService. */
    @Autowired
    TareaService tareaService;

    @GetMapping
    public List<Tarea> getAllTareas() {
        return this.tareaService.getAllTareas();
    }

    @GetMapping("/por-nombre")
    public List<Tarea> getAllTareasByName(@RequestParam(required = false) final String nombre) {
        return this.tareaService.getAllTareasByName(nombre);
    }

    @GetMapping("/{id}")
    public Tarea getTareaById(@PathVariable("id") final long id) {
        return this.tareaService.getTareaById(id);
    }

    @PostMapping
    public Tarea createTarea(@RequestBody final Tarea tarea) {
        return this.tareaService.createTarea(tarea);
    }

    @PutMapping("/{id}")
    public Tarea updateTarea(@PathVariable("id") final long id,
        @RequestBody final Tarea tarea) {
        return this.tareaService.updateTarea(id, tarea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTarea(@PathVariable("id") final long id) {
        return this.tareaService.deleteTarea(id);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTareas() {
        return this.tareaService.deleteAllTareas();
    }

    @GetMapping("/activos")
    public List<Tarea> findByIsActive() {
        return this.tareaService.findByIsActive();
    }

}
