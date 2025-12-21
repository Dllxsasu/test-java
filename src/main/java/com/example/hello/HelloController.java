package com.example.hello;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.function.Supplier;

@RestController
public class HelloController {
    private final CircuitBreaker circuitBreaker;

    // 1. Inyectamos el Registry (la fábrica de Circuit Breakers)
    public HelloController(CircuitBreakerRegistry registry) {
        // 2. Obtenemos la instancia configurada en el application.properties
        this.circuitBreaker = registry.circuitBreaker("miServicioLento");
    }


    @GetMapping("/hello")
    public String hello() {
        return "Holasdaa Muasdasdsndo desde Kube22rnetes3";
    }

    @GetMapping("/api/lento-manual")
    public String endpointManual() {
        // 3. Definimos la lógica peligrosa (Supplier)
        Supplier<String> logicaPeligrosa = () -> {
            try {
                Thread.sleep(5000); // Simula lentitud
                return "🐢 Respuesta lenta original";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // 4. "Decoramos" la lógica con el Circuit Breaker
        Supplier<String> logicaProtegida = CircuitBreaker.decorateSupplier(circuitBreaker, logicaPeligrosa);

        // 5. Ejecutamos intentando capturar errores (Pattern Match)
        try {
            return logicaProtegida.get();
        } catch (Exception e) {
            // Si el Circuit Breaker está abierto o hay timeout, cae aquí
            return fallbackManual(e);
        }
    }

    public String fallbackManual(Throwable t) {
        return "🚀 ¡Fallback Manual activado! (Sin anotaciones)";
    }
}
