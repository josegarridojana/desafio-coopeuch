/*
 * @(#)FeignConfig.java
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
package com.org.ms.config;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfig.
 *
 * @author Jose.
 * @version 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    /** messageConverters. */
    private final ObjectFactory<HttpMessageConverters> messageConverters;

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Bean Para Llamada x-www-form-urlencode.
     *
     * @return {@link Encoder}
     */
    @Bean
    public Encoder feignFormEncoder() {
        return new FormEncoder(new SpringEncoder(this.messageConverters));
    }
}
