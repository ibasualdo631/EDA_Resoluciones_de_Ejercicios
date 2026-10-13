# Pruebas — Desperdicio de espacio en cola simple

## Ejecución

Se compiló `DesperdicioCola.java` y se ejecutó su método `main`.

## Casos verificados

| Caso | Resultado esperado | Resultado |
|---|---|---|
| Mostrar el estado luego de cada operación | Imprimir operación, resultado, los cinco índices y las cinco celdas, además de `front` y `rear`. | Aprobado |
| Desencolar `A` y `B` | Los índices `0` y `1` quedan vacíos; `front = 2` y `rear = 3`. | Aprobado |
| Encolar `E` | `E` ocupa el índice `4`; el arreglo queda `_ _ C D E`. | Aprobado |
| Intentar encolar `F` | Rechazar la inserción con `rear = 4`; identificar explícitamente el falso Overflow y las celdas libres `0` y `1`; conservar el estado del arreglo. | Aprobado |

## Evidencia de la traza final

La salida identifica el intento como `encolar(F)` y anuncia `FALSO OVERFLOW` en el mismo mensaje de rechazo. Inmediatamente debajo imprime el estado resultante:

```text
Índice:  0   1   2   3   4
Arreglo: _   _   C   D   E
front = 2 (índice 2), rear = 4 (índice 4)
Celdas vacías: 2 (índices 0, 1)
```

Esto permite ubicar el instante del rechazo y comprobar que el arreglo no está lleno: quedan libres los índices `0` y `1`, pero ambos preceden a `front`.