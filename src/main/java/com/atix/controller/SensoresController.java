package com.atix.controller;

import com.atix.sensores.Sensores;
import com.atix.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;

@RestController
public class SensoresController {

    @Autowired
    private SensorService service;

    // En una aplicacion productiva hubiera puesto un token de validacion
    @RequestMapping(value = "/registrarSensores", method = RequestMethod.POST)
    @ResponseBody
    public void registrarSensores(final @RequestBody Sensores sensores) {
        service.registrarSensor(sensores);
    }

//    @RequestMapping(value = "/getEstadisticas", method = RequestMethod.GET)
//    @ResponseBody
//    private void estadisticas() {
//        service.procesarEstadisticasTotales();
//    }
}
