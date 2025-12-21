package com.example.hello;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Holasdaa Muasdasdsndo desde Kube22rnetes3";
    }

    @GetMapping("/api/lento")
    @CircuitBreaker(name = "miServicioLento", fallbackMethod = "fallbackHola")
    public String endpointLento() throws InterruptedException {
        // Simulamos que la base de datos tarda 5 segundos en responder
        Thread.sleep(5000);
        return "🐢 Respuesta lenta (Si ves esto, el Circuit Breaker NO funcionó)";
    }

    // Esta es la respuesta de emergencia (Plan B)
    public String fallbackHola(Throwable t) {
        return "🚀 ¡Respuesta Rápida de Emergencia! (El sistema detectó lentitud y te salvó)";
    }
}
