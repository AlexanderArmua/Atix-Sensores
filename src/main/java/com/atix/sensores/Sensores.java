package com.atix.sensores;

import java.util.*;

public class Sensores {
    private List<Double> registros;

    public Sensores() {
        registros = new LinkedList<>();
    }

    public DoubleSummaryStatistics getEstadisticas() {
        final DoubleSummaryStatistics stats = registros.stream().mapToDouble((x) -> x).summaryStatistics();

        return stats;
    }

    public List<Double> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Double> registros) {
        this.registros = registros;
    }

    public void addRegistro(Double registro) {
        registros.add(registro);
    }
}
