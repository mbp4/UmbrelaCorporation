package org.example.umbrelacorporation.controlador;

import org.example.umbrelacorporation.servicios.ServicioDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorDataset {

    @Autowired
    private ServicioDataset dataService;

    @GetMapping("/data")
    public List<Double> getData() {
        return dataService.getData();
    }

    @GetMapping("/start")
    public void startData() {
        dataService.startDataGeneration();
    }

    @GetMapping("/stop")
    public void stopData() {
        dataService.stopDataGeneration();
    }
}


