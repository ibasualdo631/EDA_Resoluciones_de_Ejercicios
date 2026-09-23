# Matriz de casos de prueba

## 1. Alcance

Se validará la clase `BusquedaVectorDesordenado`, que implementa una búsqueda secuencial sobre un vector desordenado. La prueba debe verificar:

- correctitud del algoritmo;
- manejo de casos límite;
- tratamiento de errores de entrada;
- contador de posiciones recorridas;
- comportamiento frente a valores presentes y ausentes.

---

## 2. Casos de prueba

| ID | Descripción | Entrada | Resultado esperado | Observación |
|---|---|---|---|---|
| TC-01 | Buscar un valor en la primera posición | `vector = {9, 4, 7, 2, 6}`; `valorBuscado = 9` | Devuelve `0` y muestra `Posiciones recorridas: 1` | Caso mejor |
| TC-02 | Buscar un valor en una posición intermedia | `vector = {5, 8, 12, 3, 9, 1}`; `valorBuscado = 9` | Devuelve `4` y muestra las posiciones recorridas hasta llegar allí | Caso normal |
| TC-03 | Buscar un valor en la última posición | `vector = {4, 6, 1, 8, 3, 10}`; `valorBuscado = 10` | Devuelve `5` y muestra `Posiciones recorridas: 6` | Caso peor en valor presente |
| TC-04 | Buscar un valor que no existe | `vector = {2, 7, 5, 9, 1}`; `valorBuscado = 8` | Devuelve `-1` y muestra `Posiciones recorridas: 5` | Caso peor |
| TC-05 | Vector con un solo elemento | `vector = {15}`; `valorBuscado = 15` | Devuelve `0` y recorre 1 posición | Caso mínimo válido |
| TC-06 | Vector con números repetidos | `vector = {3, 8, 3, 1, 9}`; `valorBuscado = 3` | Devuelve la primera coincidencia: `0` | Debe devolver el primer índice encontrado |
| TC-07 | El valor buscado aparece varias veces | `vector = {7, 2, 7, 5, 7}`; `valorBuscado = 7` | Devuelve `0` (primer coincidencia) | Verifica la lógica de primera aparición |
| TC-08 | Vector vacío | `vector = {}`; `valorBuscado = 5` | Lanza `IllegalArgumentException` | Caso de error de entrada |
| TC-09 | Vector nulo | `vector = null`; `valorBuscado = 5` | Lanza `IllegalArgumentException` | Caso de error de entrada |
| TC-10 | Búsqueda con valor negativo | `vector = {4, -3, 10, -8}`; `valorBuscado = -3` | Devuelve `1` | Verifica que funcione con enteros negativos |
| TC-11 | Búsqueda con valor cero | `vector = {1, 0, 5, 9}`; `valorBuscado = 0` | Devuelve `1` | Verifica que el cero se trate como valor válido |
| TC-12 | Vector descendente | `vector = {9, 8, 7, 6, 5}`; `valorBuscado = 5` | Devuelve `4` | Comprueba que no depende del orden |

---

## 3. Resultado esperado general

La implementación debe cumplir estas reglas:

1. Si el valor se encuentra, devuelve la posición exacta del primer elemento coincidente.
2. Si el valor no existe, devuelve `-1`.
3. No debe asumir que el vector está ordenado.
4. Debe recorrer secuencialmente el arreglo hasta el final si es necesario.
5. Debe manejar entradas inválidas con una excepción clara.
6. El número de posiciones recorridas debe ser consistente con la lógica del algoritmo.

---

## 4. Cobertura de prueba

Se cubren los siguientes tipos de escenarios:

- casos normales;
- casos de éxito temprano;
- casos de éxito tardío;
- caso de no existencia;
- caso mínimo;
- vector con valores duplicados;
- vectores ordenados y desordenados;
- valores negativos y cero;
- entradas nulas y vacías.

---

## 5. Conclusión

La matriz de casos de prueba valida que la búsqueda secuencial funciona correctamente en todos los escenarios relevantes para un arreglo desordenado. Esto garantiza que el algoritmo sea correcto, robusto y conforme con la estrategia elegida.
