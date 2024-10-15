package org.example.umbrelacorporation.servicios;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ServicioSegundo {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startDataProcessing() {
        scheduler.scheduleAtFixedRate(() -> {
            // Aquí procesas los datos o realizas cálculos en segundo plano
            System.out.println("Procesando datos en segundo plano...");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
