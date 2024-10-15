package org.example.umbrelacorporation.servicios;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ServicioDataset {

    private List<Double> data = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private boolean running = false; // Controlar si el proceso está en ejecución
    private List<Double> dataset = new ArrayList<>(); // Para almacenar los valores del CSV

    public ServicioDataset() {
        loadDataset(); // Cargar datos del CSV al iniciar el servicio
    }

    private void loadDataset() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/dataset/breast-cancer-wisconsin.data"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Asumimos que el valor que deseas obtener está en la primera columna
                double value = Double.parseDouble(line[0]); // Cambia el índice si el valor está en otra columna
                dataset.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public void startDataGeneration() {
        if (!running) {
            running = true; // Marcar como en ejecución
            scheduler.scheduleAtFixedRate(() -> {
                if (!dataset.isEmpty()) {
                    // Obtenemos un valor del dataset de manera cíclica
                    double newValue = dataset.remove(0); // Obtener el primer valor y eliminarlo
                    data.add(newValue);
                    if (data.size() > 50) { // Limitar los datos a 50 puntos
                        data.remove(0);
                    }
                    System.out.println("Nuevo dato generado: " + newValue);
                } else {
                    System.out.println("No hay más datos en el dataset.");
                    stopDataGeneration(); // Detener si no hay más datos
                }
            }, 0, 1, TimeUnit.SECONDS); // Actualizar cada segundo
        }
    }

    public void stopDataGeneration() {
        running = false; // Marcar como no en ejecución
        scheduler.shutdownNow(); // Detener el scheduler
        System.out.println("Generación de datos detenida.");
    }

    public List<Double> getData() {
        return new ArrayList<>(data); // Devuelve una copia de los datos
    }
}
