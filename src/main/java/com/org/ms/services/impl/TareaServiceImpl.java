/*
 * @(#)TareaServiceImpl.java
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
package com.org.ms.services.impl;

import com.org.ms.entities.Tarea;
import com.org.ms.enums.EnumError;
import com.org.ms.exceptions.SimpleException;
import com.org.ms.repositories.TareaRepository;
import com.org.ms.services.TareaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * TareaServiceImpl.
 *
 * @author Jose.
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    /** usuarioRepository. */
    private final TareaRepository tareaRepository;

    @Override
    public List<Tarea> getAllTareas() {
        try {
            return this.tareaRepository.findAll();
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(), ex);
        }
    }

    @Override
    public List<Tarea> getAllTareasByName(final String name) {
        try {
            final List<Tarea> usuarios = new ArrayList<>();
            this.tareaRepository.findByNameContaining(name).forEach(usuarios::add);
            return usuarios;
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST
                .value(), ex);
        }
    }

    @Override
    public Tarea getTareaById(final long id) {
        try {
            final var pruebaData = this.tareaRepository.findById(id);
            if (!pruebaData.isPresent()) {
                throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value());
            }
            return pruebaData.get();
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(),
                ex);
        }

    }

    @Override
    public Tarea createTarea(final Tarea tarea) {
        try {
            this.validateNameAndDescripcion(tarea);
            final var findUserEmail = this.tareaRepository.findByDescripcion(tarea.getDescripcion());
            if (findUserEmail.isPresent()) {
                throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value());
            }
            final var saveTarea = new Tarea();
            saveTarea.setName(tarea.getName());
            saveTarea.setDescripcion(tarea.getDescripcion());
            saveTarea.setActive(true);
            saveTarea.setCreated(new Date());
            saveTarea.setModified(new Date());
            saveTarea.setLastLogin(new Date());
            return this.tareaRepository
                .save(saveTarea);
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(), ex);
        }
    }

    @Override
    public Tarea updateTarea(final long id, final Tarea usuario) {
        try {

            final var usuarioData = this.tareaRepository.findById(id);
            if (!usuarioData.isPresent()) {
                throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value());
            }
            final var saveUsuario = usuarioData.get();
            saveUsuario.setName(usuario.getName());
            saveUsuario.setDescripcion(usuario.getDescripcion());
            saveUsuario.setActive(usuario.isActive());
            saveUsuario.setModified(new Date());
            return this.tareaRepository.save(saveUsuario);
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(), ex);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTarea(final long id) {
        try {
            this.tareaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(), ex);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAllTareas() {
        try {
            this.tareaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(), ex);
        }
    }

    @Override
    public List<Tarea> findByIsActive() {
        try {
            return this.tareaRepository.findByIsActive(true);
        } catch (final Exception ex) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value(), ex);
        }
    }

    private void validateNameAndDescripcion(final Tarea tarea) {
        if (tarea.getName() == null && tarea.getDescripcion() == null) {
            throw new SimpleException(EnumError.DEFAULT, HttpStatus.BAD_REQUEST.value());
        }
    }

}
