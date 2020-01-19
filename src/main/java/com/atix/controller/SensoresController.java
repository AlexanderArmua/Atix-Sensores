package com.atix.controller;

import com.atix.sensores.Sensores;
import com.atix.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.logging.Logger;

import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;

@RestController
public class SensoresController {

    @Autowired
    private SensorService service;

    private static final Logger LOGGER = Logger.getLogger(GLOBAL_LOGGER_NAME);

    // En una aplicacion productiva hubiera puesto un token de validacion
    @RequestMapping(value = "/registrarSensores", method = RequestMethod.POST)
    @ResponseBody
    public void registrarSensores(final @RequestBody Sensores sensores) {
        LOGGER.info("Datos de sensores recibido: " + sensores.getEstadisticas());

        service.registrarSensor(sensores);
    }

    @RequestMapping(value = "/getEstadisticas", method = RequestMethod.GET)
    @ResponseBody
    private void estadisticas() {
            service.procesarEstadisticasTotales();
    }
}
