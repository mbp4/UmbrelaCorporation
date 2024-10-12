package org.example.umbrelacorporation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class UmbrelaCorporationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmbrelaCorporationApplication.class, args);
        openBrowser("http://localhost:8080/");
    }

    private static void openBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                System.err.println("Sistema operativo no soportado para abrir el navegador.");
            }
        } catch (IOException e) {
            System.err.println("Error al abrir el navegador: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


