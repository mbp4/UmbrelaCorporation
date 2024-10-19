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
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(9);
    private boolean running = false;
    private List<Double> dataset = new ArrayList<>();

    public ServicioDataset() {
        loadDataset();
    }

    private void loadDataset() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/dataset/breast-cancer-wisconsin.data"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                double value = Double.parseDouble(line[1]);
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
            running = true;
            scheduler.scheduleAtFixedRate(() -> {
                if (!dataset.isEmpty()) {
                    double newValue = dataset.remove(0);
                    data.add(newValue);
                    if (data.size() > 50) {
                        data.remove(0);
                    }
                    System.out.println("Nuevo dato generado con valor: " + newValue);
                } else {
                    System.out.println("No hay más datos en el dataset.");
                    stopDataGeneration();
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    public void stopDataGeneration() {
        running = false;
        scheduler.shutdown();
        System.out.println("Generación de datos detenida.");
    }

    public List<Double> getData() {
        return new ArrayList<>(data);
    }
}
