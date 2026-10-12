import java.util.Arrays;

public class IntegradorOrdenamientos {
    private final int[] arregloOriginal;
    private final int[] copiaBurbuja;
    private final int[] copiaSeleccion;
    private final int[] copiaInsercion;
    private final Metricas metricasBurbuja = new Metricas();
    private final Metricas metricasSeleccion = new Metricas();
    private final Metricas metricasInsercion = new Metricas();
    private boolean comparacionEjecutada;

    public IntegradorOrdenamientos(int[] arregloInicial) {
        if (arregloInicial == null) {
            throw new IllegalArgumentException("El arreglo inicial no puede ser null.");
        }

        arregloOriginal = arregloInicial.clone();
        copiaBurbuja = arregloOriginal.clone();
        copiaSeleccion = arregloOriginal.clone();
        copiaInsercion = arregloOriginal.clone();
    }

    public void ejecutarComparacion() {
        if (comparacionEjecutada) {
            throw new IllegalStateException("La comparación ya fue ejecutada.");
        }
        comparacionEjecutada = true;

        ordenarBurbuja(copiaBurbuja, metricasBurbuja);
        ordenarSeleccion(copiaSeleccion, metricasSeleccion);
        ordenarInsercion(copiaInsercion, metricasInsercion);

        if (!Arrays.equals(copiaBurbuja, copiaSeleccion)
                || !Arrays.equals(copiaBurbuja, copiaInsercion)) {
            throw new IllegalStateException("Los algoritmos produjeron resultados distintos.");
        }

        mostrarResultado("Bubble Sort", copiaBurbuja);
        mostrarResultado("Selection Sort", copiaSeleccion);
        mostrarResultado("Insertion Sort", copiaInsercion);
        mostrarTablaComparativa();
    }

    private void ordenarBurbuja(int[] arreglo, Metricas metricas) {
        for (int limite = arreglo.length - 1; limite > 0; limite--) {
            boolean huboIntercambio = false;
            for (int indice = 0; indice < limite; indice++) {
                metricas.registrarComparacion();
                if (arreglo[indice] > arreglo[indice + 1]) {
                    int temporal = arreglo[indice];
                    arreglo[indice] = arreglo[indice + 1];
                    arreglo[indice + 1] = temporal;
                    metricas.registrarMovimiento();
                    huboIntercambio = true;
                }
            }
            if (!huboIntercambio) {
                break;
            }
        }
    }

    private void ordenarSeleccion(int[] arreglo, Metricas metricas) {
        for (int indice = 0; indice < arreglo.length - 1; indice++) {
            int minimo = indice;
            for (int candidato = indice + 1; candidato < arreglo.length; candidato++) {
                metricas.registrarComparacion();
                if (arreglo[candidato] < arreglo[minimo]) {
                    minimo = candidato;
                }
            }

            if (minimo != indice) {
                int temporal = arreglo[indice];
                arreglo[indice] = arreglo[minimo];
                arreglo[minimo] = temporal;
                metricas.registrarMovimiento();
            }
        }
    }

    private void ordenarInsercion(int[] arreglo, Metricas metricas) {
        for (int indice = 1; indice < arreglo.length; indice++) {
            int clave = arreglo[indice];
            int anterior = indice - 1;

            while (anterior >= 0) {
                metricas.registrarComparacion();
                if (arreglo[anterior] > clave) {
                    arreglo[anterior + 1] = arreglo[anterior];
                    metricas.registrarMovimiento();
                    anterior--;
                } else {
                    break;
                }
            }
            arreglo[anterior + 1] = clave;
        }
    }

    private void mostrarResultado(String metodo, int[] arreglo) {
        System.out.println(metodo + ": " + Arrays.toString(arreglo));
    }

    private void mostrarTablaComparativa() {
        System.out.println("\nComparacion de metricas");
        System.out.println("+----------------+----------------+---------------------+");
        System.out.printf("| %-14s | %-14s | %-19s |%n",
                "Metodo", "Comparaciones", "Movimientos fisicos");
        System.out.println("+----------------+----------------+---------------------+");
        imprimirFila("Bubble Sort", metricasBurbuja);
        imprimirFila("Selection Sort", metricasSeleccion);
        imprimirFila("Insertion Sort", metricasInsercion);
        System.out.println("+----------------+----------------+---------------------+");
        System.out.println("Movimiento fisico: intercambio real en Bubble/Selection; "
                + "desplazamiento a la derecha en Insertion.");
    }

    private void imprimirFila(String metodo, Metricas metricas) {
        System.out.printf("| %-14s | %-14d | %-19d |%n",
                metodo, metricas.getComparaciones(), metricas.getMovimientos());
    }

    public static void main(String[] args) {
        int[] datos = {64, 34, 25, 12, 22, 11, 90, 25};
        IntegradorOrdenamientos integrador = new IntegradorOrdenamientos(datos);
        integrador.ejecutarComparacion();
    }

    private static class Metricas {
        private long comparaciones;
        private long movimientos;

        private void registrarComparacion() {
            comparaciones++;
        }

        private void registrarMovimiento() {
            movimientos++;
        }

        private long getComparaciones() {
            return comparaciones;
        }

        private long getMovimientos() {
            return movimientos;
        }
    }
}
