package com.navent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase de configuracion
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.navent")
public class PedidosSystemConfig {
}
