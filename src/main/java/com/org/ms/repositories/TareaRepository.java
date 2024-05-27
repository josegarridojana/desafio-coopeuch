/*
 * @(#)TareaRepository.java
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
package com.org.ms.repositories;

import com.org.ms.entities.Tarea;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TareaRepository.
 *
 * @author Jose.
 * @version 1.0.0
 */
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByIsActive(boolean isActive);

    List<Tarea> findByNameContaining(String name);

    Optional<Tarea> findByDescripcion(String descripcion);
}
