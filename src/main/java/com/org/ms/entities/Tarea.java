/*
 * @(#)Tarea.java
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
package com.org.ms.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Tarea.
 *
 * @author Jose.
 * @version 1.0.0
 */
@Entity
@Table(name = "tareas")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "isactive")
    private boolean isActive;
    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;
    @UpdateTimestamp
    @Column(name = "modified", nullable = true, updatable = true)
    private Date modified;
    @UpdateTimestamp
    @Column(name = "last_login", nullable = false, updatable = false)
    private Date lastLogin;

}
