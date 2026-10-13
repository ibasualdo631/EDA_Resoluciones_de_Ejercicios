# Pruebas — Ejercicio 2: Simulador de torre de platos

## Alcance

Verificar los requisitos funcionales y los casos límite definidos en [01_analisis.md](./01_analisis.md) y [02_plan_tecnico.md](./02_plan_tecnico.md): comportamiento LIFO, consulta sin extracción, capacidad, validación de platos y excepciones en estado vacío.

## Casos de prueba

| ID | Caso | Pasos | Resultado esperado |
|---|---|---|---|
| TP-01 | Estado inicial | Crear `new TorrePlatos(3)` y consultar `estaVacia()` y `cantidadPlatos()`. | Está vacía y la cantidad es `0`. |
| TP-02 | Apilado y orden LIFO | Apilar `"A"`, `"B"` y `"C"`; desapilar tres veces. | Se reciben `"C"`, `"B"` y `"A"`, en ese orden. |
| TP-03 | Consulta no destructiva | Apilar `"A"` y `"B"`; consultar dos veces y luego desapilar. | Las consultas devuelven `"B"`; la cantidad no cambia y el desapilado devuelve `"B"`. |
| TP-04 | Capacidad máxima | Crear torre de capacidad `1`, apilar `"A"` e intentar apilar `"B"`. | El segundo apilado lanza `IllegalStateException`; `"A"` sigue arriba y la cantidad sigue siendo `1`. |
| TP-05 | Desapilar torre vacía | Crear una torre vacía e invocar `desapilar()`. | Lanza `NoSuchElementException` sin alterar el estado. |
| TP-06 | Consultar torre vacía | Crear una torre vacía e invocar `consultarSuperior()`. | Lanza `NoSuchElementException` sin alterar el estado. |
| TP-07 | Continuidad tras underflow | Capturar por separado las excepciones de TP-05 y TP-06 y ejecutar una instrucción después de cada captura. | Se reciben las excepciones esperadas y el programa continúa, sin terminación abrupta. |
| TP-08 | Capacidad inválida | Construir torres con capacidad `0` y `-1`. | Cada constructor lanza `IllegalArgumentException`. |
| TP-09 | Plato inválido | En una torre con espacio, intentar apilar `null` y un objeto que no sea `String` ni `Number`. | Cada operación lanza `IllegalArgumentException` y no cambia la cantidad ni el tope. |
| TP-10 | Plato numérico | Apilar el número `7`, consultarlo y desapilarlo. | La consulta y el desapilado devuelven `7`; al final la torre está vacía. |

## Ejecución y resultados

Se compiló y ejecutó la clase con:

```text
javac TorrePlatos.java
java TorrePlatos
```

| ID | Estado | Evidencia |
|---|---|---|
| TP-02 | **Aprobado** | El `main` apiló tres platos y la salida confirmó el orden de retiro: postre, llano, hondo. |
| TP-03 | **Aprobado** | La consulta inicial mostró el plato de postre y el primer retiro devolvió ese mismo plato. |
| TP-05 | **Aprobado** | Al intentar desapilar tras vaciar la torre se capturó `NoSuchElementException` con el mensaje de underflow. |
| TP-06 | **Aprobado** | Al consultar la torre vacía se capturó `NoSuchElementException` con el mensaje de underflow. |
| TP-07 | **Aprobado** | Después de capturar ambas excepciones, el `main` imprimió el mensaje de continuación; la ejecución terminó con código `0`. |
| TP-01, TP-04, TP-08, TP-09, TP-10 | **Pendientes** | Están definidos como pruebas de aceptación, pero no forman parte de la demostración ejecutada por el `main`. |

**Resultado global:** aprobado para los flujos ejecutados (LIFO, consulta superior, underflow y continuidad). Los casos marcados como pendientes requieren ejecución adicional para considerar validada toda la matriz.