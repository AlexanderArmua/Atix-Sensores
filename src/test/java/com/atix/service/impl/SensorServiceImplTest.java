package com.atix.service.impl;

import com.atix.sensores.Sensores;
import com.atix.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.DoubleSummaryStatistics;

import static org.junit.jupiter.api.Assertions.*;

class SensorServiceImplTest {

    private SensorServiceImpl service;

    @BeforeEach
    public void before() {
        this.service = new SensorServiceImpl();
    }

    @Test
    void testHayErrorEntreMaxYMin() {
        Sensores sensores = new Sensores();
        sensores.addRegistro(10d);
        sensores.addRegistro(100d);
        sensores.addRegistro(50d);

        DoubleSummaryStatistics statistics = sensores.getEstadisticas();
        boolean errorMaximoYMin = service.hayErrorEntreMaxYMin(statistics, 10d);

        assertEquals(true, errorMaximoYMin, "Debe haber errores entre el maximo y el minimo");
    }

    @Test
    void testHayErrorEnPromedio() {
        Sensores sensores = new Sensores();
        sensores.addRegistro(10d);
        sensores.addRegistro(100d);
        sensores.addRegistro(50d);

        DoubleSummaryStatistics statistics = sensores.getEstadisticas();
        boolean errorEnPromedio = service.hayErrorEnPromedio(statistics, 10d);

        assertEquals(true, errorEnPromedio, "El promedio excede el constante");
    }
}