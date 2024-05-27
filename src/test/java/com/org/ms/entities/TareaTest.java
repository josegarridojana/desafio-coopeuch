package com.org.ms.entities;

import com.org.ms.entities.Tarea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TareaTest {

    @Test
    public void tareaObjectConstructWithEmpty() {
        final var tarea = new Tarea();
        tarea.setId(1);
        tarea.setName(null);
        tarea.setDescripcion(null);
        tarea.setCreated(null);
        tarea.setModified(null);
        tarea.setLastLogin(null);
        tarea.setActive(false);
        Assertions.assertEquals(1, tarea.getId());
        Assertions.assertEquals(null, tarea.getName());
        Assertions.assertEquals(null, tarea.getDescripcion());
        Assertions.assertEquals(null, tarea.getCreated());
        Assertions.assertEquals(null, tarea.getModified());
        Assertions.assertEquals(null, tarea.getLastLogin());
        Assertions.assertEquals(false, tarea.isActive());
        Assertions.assertNotNull(tarea.toString());
    }

}
