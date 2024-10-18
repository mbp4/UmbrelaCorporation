package org.example.umbrelacorporation.controlador;

import org.example.umbrelacorporation.servicios.ServicioDataset;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorDataset {
    private final ServicioDataset servicioDataset;

    public ControladorDataset(ServicioDataset servicioDataset) {
        this.servicioDataset = servicioDataset;
    }

    @GetMapping("/data")
    public List<Double> getData() {
        return servicioDataset.getData();
    }

    @GetMapping("/start")
    public void startData() {
        servicioDataset.startDataGeneration();
    }

    @GetMapping("/stop")
    public void stopData() {
        servicioDataset.stopDataGeneration();
    }
}