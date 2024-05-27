/*
 * @(#)JsonProxyFeignClient.java
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
package com.org.ms.aop;

import com.org.ms.config.FeignConfig;
import com.org.ms.enums.EnumError;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;

/**
 * JsonProxyFeignClient.
 *
 * @author Jose.
 * @version 1.0.0, 23-02-2024
 */

@FeignClient(configuration = FeignConfig.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonProxyFeignClient {

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Alias de la anotacion FeignClient para el nombre del servicio.
     *
     * @return el nombre del servicio
     */
    @AliasFor(annotation = FeignClient.class)
    String name();

    /**
     * Alias para la url del servicio.
     *
     * @return la url del servicio
     */
    @AliasFor(annotation = FeignClient.class)
    String url();

    /**
     * Codigo de error que retornará el servicio BFF al ocurrir una excepcion no controlada al llamar al motor interno.
     * Se usa por defecto el codigo de error generico {@link InternalCodesError#DEFAULT}
     *
     * @return el codigo de error a retornar por el BFF.
     */
    EnumError error() default EnumError.DEFAULT;

    /**
     * Codigo http que retornara el servicio BFF al ocurrir una excepcion no controlada al llamar al servicio Feign.
     *
     * @return el codigo status http.
     */
    HttpStatus status() default HttpStatus.BAD_REQUEST;
}
