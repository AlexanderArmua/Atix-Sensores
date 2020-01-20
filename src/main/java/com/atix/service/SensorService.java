package com.atix.service;

import com.atix.sensores.Sensores;

import java.util.DoubleSummaryStatistics;

public interface SensorService {

    void registrarSensor(Sensores sensores);

    void procesarEstadisticasTotales();

    void analizarErrores(DoubleSummaryStatistics estadisticas);

    boolean hayErrorEntreMaxYMin(DoubleSummaryStatistics estadisticas, Double constante);

    boolean hayErrorEnPromedio(DoubleSummaryStatistics estadisticas, Double constante);
}
