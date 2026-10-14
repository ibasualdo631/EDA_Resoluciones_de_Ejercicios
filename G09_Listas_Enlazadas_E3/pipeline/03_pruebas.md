# Pruebas — Ejercicio 3: Obtener por posición

## Alcance de la auditoría

Se auditó `ListaEnlazadaSimple.obtener(int posicion)` junto con las pruebas ejecutables de su `main`. El método valida `posicion < 0 || posicion >= size` antes de leer `head` o avanzar por los nodos y lanza exactamente `IndexOutOfBoundsException`. Por lo tanto, los índices inválidos no se convierten en valores centinela ni dependen de que el recorrido llegue a una referencia nula.

## Casos ejecutados

| Caso | Estado de la lista | Entrada | Resultado esperado | Resultado |
|---|---|---:|---|---|
| Primera posición | `[5, 10, 20, 30, 40, 50]` | `0` | Devuelve `5` | Pasó |
| Posición intermedia | `[5, 10, 20, 30, 40, 50]` | `3` | Devuelve `30` | Pasó |
| Última posición válida | `[5, 10, 20, 30, 40, 50]` | `size - 1` (`5`) | Devuelve `50` | Pasó |
| Índice negativo | Lista con seis elementos | `-1` | Lanza `IndexOutOfBoundsException` | Pasó |
| Índice igual al tamaño | Lista con seis elementos | `size` (`6`) | Lanza `IndexOutOfBoundsException` | Pasó |
| Índice superior al tamaño | Lista con seis elementos | `size + 1` (`7`) | Lanza `IndexOutOfBoundsException` | Pasó |
| Índice después del único elemento | `[7]` | `1` | Lanza `IndexOutOfBoundsException` | Pasó |
| Índice cero en lista vacía | `[]` | `0` | Lanza `IndexOutOfBoundsException` | Pasó |
| Índice negativo en lista vacía | `[]` | `-1` | Lanza `IndexOutOfBoundsException` | Pasó |

Las pruebas de entrada inválida fallan con `AssertionError` si el método retorna sin lanzar la excepción requerida. También comprueban que el tamaño y los datos de los extremos permanezcan iguales tras la excepción.

## Ejecución

Compilación y ejecución de la clase:

```powershell
javac -encoding UTF-8 -d $env:TEMP\g09-listas-enlazadas-e3-audit .\ListaEnlazadaSimple.java
java -ea -cp $env:TEMP\g09-listas-enlazadas-e3-audit ListaEnlazadaSimple
```

**Resultado:** compilación exitosa; todos los casos terminaron correctamente. La salida confirmó `IndexOutOfBoundsException` para cada índice inválido y mostró `Todas las pruebas finalizaron correctamente.`

## Conclusión

La implementación satisface el contrato estricto de límites: rechaza posiciones negativas y toda posición igual o superior a `size`, valida antes del recorrido y también maneja correctamente esos límites en listas vacías y unitarias. No se observaron fallos en la auditoría.