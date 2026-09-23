# Matriz de casos de prueba

## 1. Objetivo
Validar que el método `encontrarMinimo(int[] numeros)` devuelva correctamente el menor valor del arreglo, sin romper el requisito de complejidad lineal `O(n)`, y que maneje correctamente entradas inválidas.

## 2. Alcance
Se evaluarán casos de:
- arreglo válido con distintos ordenamientos
- números positivos, negativos y repetidos
- entradas límite
- validación de errores para `null` y arreglo vacío

## 3. Matriz de pruebas

| ID | Tipo de prueba | Entrada | Resultado esperado | Observación |
|---|---|---|---|---|
| TC-01 | Caso base | `[42]` | `42` | Arreglo con un único elemento. |
| TC-02 | Caso normal | `[1, 2, 3, 4, 5]` | `1` | Mínimo en el primer elemento. |
| TC-03 | Caso normal | `[9, 8, 7, 6, 5]` | `5` | Arreglo descendente. |
| TC-04 | Caso normal | `[5, -2, 8, 0, 3]` | `-2` | Mezcla de positivos, negativos y cero. |
| TC-05 | Caso normal | `[-3, -9, -1, -7]` | `-9` | Todos los valores son negativos. |
| TC-06 | Caso normal | `[4, 4, 4, 4]` | `4` | Valores repetidos. |
| TC-07 | Caso normal | `[10, 20, 30, 2]` | `2` | Mínimo en la última posición. |
| TC-08 | Caso normal | `[1, 50, 40, 90]` | `1` | Mínimo en la primera posición. |
| TC-09 | Caso límite | `[]` | `IllegalArgumentException` | El arreglo no debe estar vacío. |
| TC-10 | Caso límite | `null` | `IllegalArgumentException` | El arreglo no debe ser nulo. |

## 4. Resultado esperado del algoritmo
El método `encontrarMinimo` debe:
1. Validar que el arreglo no sea `null`.
2. Validar que el arreglo no esté vacío.
3. Recorrer cada elemento del arreglo una sola vez.
4. Devolver el menor valor encontrado.

## 5. Criterio de aceptación
Se considera correcta la implementación si:
- cumple todos los casos válidos del cuadro anterior,
- lanza `IllegalArgumentException` para `null` y arreglos vacíos,
- mantiene una complejidad temporal de `O(n)`.

## 6. Evidencia de validación
Se ejecutó la clase Java con un caso de prueba válido y el resultado obtenido fue:

```java
El valor mínimo es: 3
```

Esto confirma que el algoritmo identifica correctamente el menor elemento del arreglo de prueba.
