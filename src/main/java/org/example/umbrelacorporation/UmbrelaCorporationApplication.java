package org.example.umbrelacorporation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class UmbrelaCorporationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmbrelaCorporationApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Windows
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://localhost:8080");
            } else if (os.contains("mac")) {
                // Mac
                Runtime.getRuntime().exec("open http://localhost:8080");
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux
                Runtime.getRuntime().exec("xdg-open http://localhost:8080");
            } else {
                System.err.println("Sistema operativo no soportado para abrir el navegador.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

