# Pruebas y auditoría — Cola de impresión

## Alcance

Se auditó `ColaImpresion.java` para verificar que la cola mantenga el orden FIFO y que las operaciones sobre una cola vacía no accedan a elementos inexistentes ni dejen el estado inconsistente.

## Casos ejecutados

| Caso | Resultado |
|---|---|
| Cola recién creada: vacía y con cantidad cero | Aprobado |
| Consultar el frente en una cola vacía lanza `NoSuchElementException` | Aprobado |
| Procesar en una cola vacía lanza `NoSuchElementException` | Aprobado |
| Las operaciones vacías no modifican el estado | Aprobado |
| Encolar A, B y C; consultar y procesar en ese orden | Aprobado: A, B, C |
| Consultar el frente no retira el documento ni modifica la cantidad | Aprobado |
| Al procesar todos los elementos, frente y cantidad quedan vacíos/cero | Aprobado |
| Encolar y procesar un nuevo documento después de vaciar la cola | Aprobado |
| Encolar `null` se rechaza y no altera el estado | Aprobado |
| Compilación de `ColaImpresion.java` con `javac` | Aprobado |

## Resultado de auditoría

No se detectaron defectos en el orden de atención ni en el manejo de cola vacía. `procesarSiguiente()` actualiza correctamente el frente y, al retirar el último documento, también restablece el final; el contador se mantiene consistente. `verFrente()` falla explícitamente en vacío y no altera la cola. No fue necesario modificar el código fuente.

Las pruebas se ejecutaron con un harness temporal fuera del repositorio; los archivos temporales se eliminaron al finalizar.