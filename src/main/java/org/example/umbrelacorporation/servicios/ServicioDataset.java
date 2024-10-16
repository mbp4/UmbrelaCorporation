package org.example.umbrelacorporation.servicios;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ServicioDataset {

    private List<Double> data = new ArrayList<>();
    private ScheduledExecutorService scheduler;
    private boolean running = false;
    private List<Double> dataset = new ArrayList<>(); // Almacena los valores del CSV

    public ServicioDataset() {
        loadDataset(); // Cargar los datos del CSV al iniciar el servicio
    }

    // Método para cargar el dataset desde un archivo CSV
    private void loadDataset() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/dataset/breast-cancer-wisconsin.data"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Parsear el valor de la segunda columna como el dato relevante
                if (!line[1].equals("?")) { // Asegurarse de que no sea un valor desconocido
                    double value = Double.parseDouble(line[1]); // Cambiar el índice si necesitas otro valor
                    dataset.add(value);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    // Método para iniciar la generación de datos periódicamente
    public void startDataGeneration() {
        if (!running) {
            running = true;
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                if (!dataset.isEmpty()) {
                    // Obtener el primer valor del dataset y removerlo
                    double newValue = dataset.remove(0);
                    data.add(newValue);
                    if (data.size() > 50) { // Limitar los datos a 50 puntos
                        data.remove(0);
                    }
                    System.out.println("Nuevo dato generado: " + newValue);
                } else {
                    System.out.println("No hay más datos en el dataset.");
                    stopDataGeneration(); // Detener si no hay más datos
                }
            }, 0, 1, TimeUnit.SECONDS); // Generar datos cada segundo
        }
    }

    // Método para detener la generación de datos
    public void stopDataGeneration() {
        running = false;
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
            System.out.println("Generación de datos detenida.");
        }
    }

    // Método para obtener los datos generados
    public List<Double> getData() {
        return new ArrayList<>(data); // Devuelve una copia de los datos
    }
}
