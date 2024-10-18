package org.example.umbrelacorporation.servicios;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ServicioDataset {
    private ScheduledExecutorService scheduler;
    private final List<Double> data = new ArrayList<>();
    private boolean isGenerating = false;
    private int lastIndex = 0;

    public List<Double> getData() {
        return data;
    }

    public void startDataGeneration() {
        if (!isGenerating) {
            if (scheduler == null || scheduler.isShutdown()) {
                scheduler = Executors.newScheduledThreadPool(1);
            }
            scheduler.scheduleAtFixedRate(this::generateData, 0, 1, TimeUnit.SECONDS);
            isGenerating = true;
        }
    }

    public void stopDataGeneration() {
        if (isGenerating) {
            scheduler.shutdown();
            isGenerating = false;
        }
    }

    private void generateData() {
        // Simulate data generation
        data.add(Math.random() * 100);
        lastIndex++;
    }
}
