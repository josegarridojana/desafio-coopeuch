package com.org.ms;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@NoArgsConstructor
@EnableAsync
@SpringBootApplication
@ConfigurationProperties
@ComponentScan(basePackages = "com.org")
@PropertySource("classpath:desafio-coopeuch-1.0.properties")
@EnableFeignClients(basePackages = "com.org.ms.restclients")
public class Application {

    // -------------------------------------------------------------------
    // -- Locale ---------------------------------------------------------
    // -------------------------------------------------------------------
    /** Locale de los mini servicios. */
    public static final Locale LOCALE = new Locale("es", "CL");
    /** inspectBeansAtStartup. */
    @Value("${startup.beans.inspect: false}")
    private boolean inspectBeansAtStartup;
    /** timezoneDefault. */
    @Value("${timezone.default}")
    private String timezoneDefault;
    /** formatDateDefault. */
    @Value("${date.pattern.default}")
    private String formatDateDefault;

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * A bean that executes at application start up and retrieves all the beans that
     * were created either by the app or were automatically added by Spring Boot
     *
     * @param ctx {@link ApplicationContext} The application context just
     *            initialized
     * @return {@link CommandLineRunner} A CommandLineRunner executed
     */
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {
            final var format = new SimpleDateFormat(this.formatDateDefault, Application.LOCALE);
            Application.log.info(String.format("Context started up at: %s", format.format(ctx.getStartupDate())));
            if (this.inspectBeansAtStartup) {
                Application.log.info(String.format("Property 'startup.beans.inspect' is set as %s. " +
                    "Let's inspect the beans provided by Spring Boot:",
                    this.inspectBeansAtStartup));
                final var beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (final String beanName : beanNames) {
                    Application.log.info("  " + beanName);
                }
            } else {
                Application.log.info(String.format(
                    "Property 'startup.beans.inspect' is set as %s. Let's jump the beans inspection.",
                    this.inspectBeansAtStartup));
            }
        };
    }

    /**
     * Main routine - It starts the application context.
     *
     * @param args {@link String}
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * TimeZone por defecto.
     */
    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(this.timezoneDefault));
    }

}
