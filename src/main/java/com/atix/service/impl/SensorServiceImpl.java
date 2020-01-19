package com.atix.service.impl;

import com.atix.sensores.Sensores;
import com.atix.service.SensorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.logging.Logger;

@Service
public class SensorServiceImpl implements SensorService {

    private final static Logger LOGGER = Logger.getLogger(SensorServiceImpl.class.getName());
    private static final Queue<Sensores> todosLosSensores = new LinkedList<>();

    @Value("${constanteS}")
    private Double constanteS;

    @Value("${constanteM}")
    private Double constanteM;

    @Override
    public void registrarSensor(Sensores sensores) {
        todosLosSensores.add(sensores);
    }

    @Override
    public void procesarEstadisticasTotales() {

        // Guardamos el tamanio antes para que si mientras se esta agregando datos nuevos no los tome.
        final int sizeQueue = todosLosSensores.size();
        for (int i = 0; i < sizeQueue; i++) {
            final DoubleSummaryStatistics estadisticas = todosLosSensores.remove().getEstadisticas();
            LOGGER.info("Datos de sensores procesados: " + estadisticas);

            if (estadisticas.getMax() - estadisticas.getMin() > constanteS) {
                LOGGER.warning("La diferencia entre el maximo: " + estadisticas.getMax() + " y el minimo: " + estadisticas.getMin() + " supera " + constanteS);
            }
            if (estadisticas.getAverage() > constanteM) {
                LOGGER.warning("El promedio: " + estadisticas.getAverage() + " supera " + constanteM);
            }
        }

        LOGGER.info("Todos los datos se procesaron");
    }


}
