# Pruebas de búsqueda recursiva

## Validación de límites y valor ausente

- Se compiló `BusquedaRecursivaArreglo.java` con `javac` y se ejecutó su `main`.
- Las comprobaciones internas confirmaron que `buscar(ARREGLO, 4)` devuelve `2` y `buscar(ARREGLO, 10)` devuelve `0`.
- La búsqueda de `99`, que no está en `[10, 25, 4, 8, 30]`, recorrió el arreglo completo y devolvió `-1`; no ocurrió `ArrayIndexOutOfBoundsException`.
- La condición `indice >= arreglo.length` en el método auxiliar detiene la recursión antes de acceder a una posición fuera del arreglo.

**Resultado:** compilación y ejecución exitosas; no se requirieron cambios en el algoritmo.