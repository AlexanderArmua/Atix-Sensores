package com.atix.scheduling;

import com.atix.service.SensorService;
import com.atix.service.impl.SensorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProcesarSensores {
    private final static Logger LOGGER = Logger.getLogger(SensorServiceImpl.class.getName());

    @Autowired
    private SensorService sensorService;

    @Async
    @Scheduled(cron="${cronProcesarSensores}")
    public void asyncScheduledProcesarSensores() {
        LOGGER.info("Inicio proceso temporal procesar todos los datos de los sensores");
        sensorService.procesarEstadisticasTotales();
    }
}
