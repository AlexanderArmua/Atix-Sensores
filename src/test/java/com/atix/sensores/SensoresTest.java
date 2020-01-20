package com.atix.sensores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.DoubleSummaryStatistics;

import static org.junit.jupiter.api.Assertions.*;

class SensoresTest {
    Sensores sensores;

    @BeforeEach
    void setSensor() {
        sensores = new Sensores();
        sensores.addRegistro(4d);
        sensores.addRegistro(5d);
        sensores.addRegistro(1d);
        sensores.addRegistro(3d);
    }

    @Test
    void getEstadisticas() {
        DoubleSummaryStatistics statistics = sensores.getEstadisticas();

        assertEquals(4, statistics.getCount(), "Cantidad de Sensores debe ser 4");
        assertEquals(5, statistics.getMax(), "El maximo debe ser 5");
        assertEquals(1, statistics.getMin(), "El minimo debe ser 1");
        assertEquals(3.25d, statistics.getAverage(), "El promedio debe ser 3.25");
    }

}