# Reporte de Testing y Telemetría: Ejercicio 7 - Peor Caso de Quicksort

### I. Matriz de Casos de Prueba (Benchmarking & Destructive Testing)
Se audita la degradación del rendimiento contrastando arreglos de idéntico tamaño ($n = 7$) pero con distinta distribución inicial (entropía). El objetivo es estresar la Pila de Ejecución (*Call Stack*) y verificar la explosión de llamadas recursivas teóricas.

| ID | Escenario de Estrés (Entropía) | Datos de Entrada ($n=7$) | Rendimiento Teórico | Llamadas Recursivas Físicas | Estado de Telemetría |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **01** | Borde Hostil: Arreglo ya ordenado | `[1, 2, 3, 4, 5, 6, 7]` | Peor Caso $O(n^2)$ | `13` (Máxima profundidad) |  **Degradación Crítica** |
| **02** | Borde Hostil: Orden Inverso | `[7, 6, 5, 4, 3, 2, 1]` | Peor Caso $O(n^2)$ | `13` (Máxima profundidad) | **Degradación Crítica** |
| **03** | Flujo Normal: Desordenado/Balanceado | `[4, 1, 6, 2, 7, 3, 5]` | Caso Promedio $\Theta(n \log n)$ | `11` (o menos, partición simétrica) | **Óptimo** |

*(Nota de QA: En un $n=7$ la diferencia de llamadas parece pequeña (13 vs 11), pero a nivel asintótico, si $n=1000$, el caso desordenado requiere $\approx 1000$ llamadas, mientras que el caso ordenado detona el Call Stack con $\approx 2000$ llamadas lineales y un tiempo de CPU cuadrático O(n²))*.

---

### II. Código del Test Runner (Harness Automatizado)
Este arnés de pruebas exige agregar un método `public static int getContadorLlamadas()` en la clase `QuickSortPeorCaso` para poder auditar la métrica desde afuera sin romper la encapsulación.

```java
public class QuickSortTestRunner {
    
    public static void main(String[] args) {
        System.out.println("=== AUDITORÍA DE RENDIMIENTO: QUICKSORT PIVOTE FIJO ===");
        
        // 1. Prueba de Flujo Normal (Arreglo Desordenado)
        int[] arrDesordenado = {4, 1, 6, 2, 7, 3, 5};
        QuickSortPeorCaso.quicksort(arrDesordenado);
        int llamadasOptimas = QuickSortPeorCaso.getContadorLlamadas();
        System.out.println("[INFO] Arreglo desordenado (Caso Promedio) -> Llamadas: " + llamadasOptimas);
        
        // 2. Prueba Destructiva (Arreglo Ya Ordenado)
        int[] arrOrdenado = {1, 2, 3, 4, 5, 6, 7};
        QuickSortPeorCaso.quicksort(arrOrdenado);
        int llamadasPeorCaso = QuickSortPeorCaso.getContadorLlamadas();
        System.out.println("[WARN] Arreglo ordenado (Peor Caso O(n²)) -> Llamadas: " + llamadasPeorCaso);
        
        // 3. Aserción de Calidad
        if (llamadasPeorCaso <= llamadasOptimas) {
            throw new AssertionError(
                "FALLA DE TELEMETRÍA: El peor caso debería registrar estrictamente " +
                "más llamadas recursivas que el caso promedio. Revisa el contador."
            );
        }
        
        System.out.println("\nCERTIFICACIÓN: Degradación de rendimiento demostrada con éxito.");
    }
}