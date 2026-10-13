# Pruebas — `ColaEnteros`

## Alcance

Revisión de la implementación contra el análisis y el plan técnico, con énfasis en los estados de frontera de `front` y `rear` para una cola lineal no circular.

## Casos y resultados

| Caso | Acción | Estado esperado de `front`, `rear` | Resultado |
|---|---|---|---|
| Inicialización con capacidad 3 | Crear cola | `(0, -1)`, vacía | Aprobado |
| Llenado exacto | Encolar tres valores | `(0, 2)`, llena | Aprobado |
| Desbordamiento | Encolar un cuarto valor | Lanza `IllegalStateException`; sigue `(0, 2)` | Aprobado |
| Primera extracción | Desencolar con tres valores | Devuelve el primero; queda `(1, 2)` | Aprobado |
| Extracciones parciales | Desencolar otro valor | Devuelve el segundo; queda `(2, 2)` | Aprobado |
| Espacio anterior a `front` | Encolar mientras `rear` está al final | Lanza `IllegalStateException`; sigue `(2, 2)` | Aprobado; coincide con la limitación lineal documentada |
| Vaciado | Desencolar el último valor | Devuelve el tercero y restablece `(0, -1)` | Aprobado |
| Subdesbordamiento | Desencolar con la cola vacía | Lanza `IllegalStateException`; sigue `(0, -1)` | Aprobado |
| Reutilización | Encolar y extraer después de vaciar | FIFO correcto; al volver a vaciar, `(0, -1)` | Aprobado |
| Capacidad uno | Encolar, extraer y volver a usar | Se vacía y se reutiliza correctamente | Aprobado |

## Verificación ejecutada

- Compilación de `ColaEnteros.java` con `javac`: aprobada.
- Ejecución de su `main`, que prueba FIFO, cola llena/vacía, reinicio, reutilización y capacidad inválida: aprobada (`Pruebas de ColaEnteros completadas correctamente.`).
- Comprobación adicional de los valores privados exactos de `front` y `rear` mediante reflexión: aprobada (`QA_BOUNDARY_INDICES_PASSED`). También se comprobó que las operaciones fallidas no alteran los índices.

## Conclusión

La implementación conserva los invariantes definidos: vacía equivale a `front = 0`, `rear = -1`; llena se alcanza cuando `rear` llega al último índice; las extracciones parciales solo avanzan `front`; al retirar el último elemento ambos índices se reinician. No se detectaron fallos en los casos límite revisados. La cola no reutiliza huecos anteriores a `front` hasta quedar completamente vacía, comportamiento intencional de la variante lineal.