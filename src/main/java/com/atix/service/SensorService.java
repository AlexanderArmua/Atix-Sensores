package com.atix.service;

import com.atix.sensores.Sensores;

public interface SensorService {

    void registrarSensor(Sensores sensores);

    void procesarEstadisticasTotales();
}
