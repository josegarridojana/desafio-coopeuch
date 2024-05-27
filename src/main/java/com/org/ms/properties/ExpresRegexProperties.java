/*
 * @(#)ExpresRegexProperties.java
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
package com.org.ms.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ExpresRegexProperties.
 *
 * @author Jose.
 * @version 1.0.0
 */
@Getter
@Setter
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "desafio.azurian.expres.regex")
public class ExpresRegexProperties {

    /** email. */
    private String email;
    /** password. */
    private String password;

}
