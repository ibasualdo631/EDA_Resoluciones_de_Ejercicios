# Análisis — Ejercicio 7: invertir un vector

## Objetivo

Definir un prompt para solicitar una implementación en Java de dos algoritmos que inviertan un vector (arreglo): uno mediante un arreglo auxiliar y otro modificando el arreglo original **in situ**. La solución debe comparar sus ventajas, desventajas y complejidades temporal y espacial, e indicar los casos borde.

## Alcance y supuestos

- Se entiende por vector un arreglo de Java de longitud fija, por ejemplo `int[]`.
- Invertir significa que el elemento de índice `i` pasa a la posición `n - 1 - i`; no se ordenan los valores.
- Ambas alternativas deben producir el mismo orden inverso.
- La versión con auxiliar construye y devuelve un arreglo nuevo, dejando intacto el original.
- La versión in situ modifica el arreglo recibido y utiliza espacio auxiliar constante.
- El enunciado no especifica el tipo de elementos ni una firma de método. El prompt de implementación debe pedir que se elija y mantenga una interfaz coherente en ambas versiones.

## Requisitos funcionales

1. Implementar dos métodos en Java:
   - **Con arreglo auxiliar:** copiar los elementos en orden inverso a un nuevo arreglo y devolverlo.
   - **In situ:** intercambiar los elementos de los extremos hacia el centro, modificando el arreglo recibido.
2. Aclarar el efecto de cada método sobre el arreglo original.
3. Considerar arreglo vacío, arreglo de un elemento y referencia nula.
4. Explicar ventajas y desventajas de ambas opciones.
5. Analizar complejidad temporal (mejor, peor y promedio) y espacial.

## Casos borde y decisiones pendientes

| Caso | Resultado esperado |
|---|---|
| Arreglo vacío (`length == 0`) | Debe permanecer vacío; no se deben realizar accesos a elementos. |
| Un solo elemento | El resultado conserva el mismo único elemento; no hacen falta copias entre posiciones ni intercambios. |
| Referencia `null` | El enunciado pide evaluarla, pero no define el comportamiento. Se recomienda acordar y documentar una política común para ambos métodos: rechazarla explícitamente o propagar `null`. No debe confundirse `null` con un arreglo vacío. |
| Longitud par | En la versión in situ, se intercambia cada pareja simétrica hasta que los índices se cruzan. |
| Longitud impar | En la versión in situ, el elemento central permanece en su posición. |

**Recomendación para cerrar la ambigüedad de `null`:** rechazarlo de forma explícita (por ejemplo, con `NullPointerException` mediante `Objects.requireNonNull`) y aplicar el mismo contrato a los dos métodos. Si el ejercicio espera otro comportamiento, debe indicarse en el prompt.

## Análisis de complejidad

Sea `n` la cantidad de elementos del arreglo. Las complejidades describen el crecimiento para `n`; el costo de validaciones y de longitudes 0 o 1 es constante.

| Estrategia | Mejor caso | Caso promedio | Peor caso | Espacio auxiliar |
|---|---:|---:|---:|---:|
| Arreglo auxiliar | Θ(n) | Θ(n) | Θ(n) | Θ(n) |
| In situ | Θ(n) | Θ(n) | Θ(n) | Θ(1) |

El mejor, promedio y peor caso tienen el mismo orden para cada estrategia: el trabajo depende de la longitud, no de los valores ni de su disposición. Por ello, no existe un caso de entrada más favorable según el contenido del vector. Para `n = 0` o `n = 1`, el trabajo es Θ(1).

- **Auxiliar:** recorre los `n` elementos para copiarlos en orden inverso; reserva un segundo arreglo de `n` posiciones, por lo que requiere Θ(n) espacio adicional.
- **In situ:** realiza `⌊n/2⌋` intercambios, con tiempo Θ(n), y usa una cantidad fija de variables auxiliares, por lo que requiere Θ(1) espacio adicional. La memoria ocupada por el propio arreglo no se cuenta como espacio auxiliar.

## Comparación

- **Arreglo auxiliar:** conserva el arreglo de entrada y entrega un resultado independiente; es útil cuando no se debe alterar el original. Su desventaja es la reserva de Θ(n) memoria y el mayor consumo de asignaciones.
- **In situ:** minimiza el espacio adicional y es la opción más eficiente en memoria; modifica el arreglo original, lo cual puede ser una desventaja si otros consumidores necesitan el orden anterior.

Si se busca la opción más eficiente en espacio y se permite modificar el arreglo, la inversión in situ es la recomendada. Ninguna de las dos mejora el orden temporal Θ(n), porque para invertir un arreglo general hay que procesar proporcionalmente sus elementos.

## Criterios de aceptación del prompt de implementación

- Se presentan dos implementaciones Java diferenciadas y correctas.
- La versión auxiliar no modifica el arreglo recibido y devuelve el arreglo invertido.
- La versión in situ modifica el arreglo recibido con intercambios desde ambos extremos, sin crear otro arreglo de tamaño `n`.
- Se define y aplica de forma coherente la política para `null`.
- Se explican los resultados para arreglo vacío, de un elemento, y para longitudes pares e impares.
- Se comparan ventajas y desventajas y se informan las complejidades temporales y espaciales, distinguiendo mejor, promedio y peor caso.