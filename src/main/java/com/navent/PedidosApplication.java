package com.navent;

import com.navent.utils.BumexMemcached;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PedidosApplication {

    public static void main(String[] args) {

        SpringApplication.run(PedidosApplication.class, args);

    }

    @PostConstruct
    public void postInit() {

        // Creo la instancia de cache
        BumexMemcached.getInstance();

    }

}
