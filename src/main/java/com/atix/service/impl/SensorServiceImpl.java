package com.atix.service.impl;

import com.atix.sensores.Sensores;
import com.atix.service.SensorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

@Service
public class SensorServiceImpl implements SensorService {

    private final static Logger LOGGER = Logger.getLogger(SensorServiceImpl.class.getName());
    private static final Queue<Sensores> todosLosSensores = new ConcurrentLinkedQueue<>();

    @Value("${constanteS}")
    private Double constanteS;

    @Value("${constanteM}")
    private Double constanteM;

    @Override
    public void registrarSensor(Sensores sensores) {
        LOGGER.info("Datos de sensores recibido: " + sensores);
        todosLosSensores.add(sensores);
    }

    @Override
    public void procesarEstadisticasTotales() {
        // Guardamos el tamanio antes para que si mientras se esta agregando datos nuevos no los tome.
        final int sizeQueue = todosLosSensores.size();

        for (int i = 0; i < sizeQueue; i++) {
            final DoubleSummaryStatistics estadisticas = getEstadisticas();
            analizarErrores(estadisticas);
        }

        LOGGER.info("Todos los datos se procesaron");
    }

    private DoubleSummaryStatistics getEstadisticas() {
        return todosLosSensores.remove().getEstadisticas();
    }

    @Override
    public void analizarErrores(DoubleSummaryStatistics estadisticas) {
        LOGGER.info("Datos de sensores procesados: " + estadisticas);

        if (hayErrorEntreMaxYMin(estadisticas, constanteS)) {
            LOGGER.warning("La diferencia entre el maximo: " + estadisticas.getMax() + " y el minimo: " + estadisticas.getMin() + " supera " + constanteS);
        }
        if (hayErrorEnPromedio(estadisticas, constanteM)) {
            LOGGER.warning("El promedio: " + estadisticas.getAverage() + " supera " + constanteM);
        }
    }

    @Override
    public boolean hayErrorEntreMaxYMin(final DoubleSummaryStatistics estadisticas, final Double constanteS) {
        return estadisticas.getMax() - estadisticas.getMin() > constanteS;
    }

    @Override
    public boolean hayErrorEnPromedio(final DoubleSummaryStatistics estadisticas, final Double constanteM) {
        return estadisticas.getAverage() > constanteM;
    }
}
