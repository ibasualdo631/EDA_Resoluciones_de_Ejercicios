# Pruebas y auditoría: mínimo de un vector

## 1. Alcance

Se audita `MinimoVector.encontrarMinimo(int[])`. Se verifica el valor devuelto, el comportamiento ante entradas inválidas y que el algoritmo no modifique el arreglo recibido. Las pruebas automatizadas se encuentran en `MinimoVectorTestRunner.java` y no requieren bibliotecas externas.

## 2. Matriz de casos de prueba

| ID | Escenario | Entrada | Resultado esperado |
|---|---|---|---|
| TC-01 | Elementos variados, arbitrarios y desordenados | `{12, -3, 25, 0, -11, 8}` | `-11` |
| TC-02 | Un único elemento | `{42}` | `42`; termina sin acceder a índices posteriores |
| TC-03 | Valores negativos | `{-5, -20, -1}` | `-20` |
| TC-04 | Mínimo duplicado | `{4, -8, 2, -8, 10}` | `-8` |
| TC-05 | Vector ordenado ascendentemente | `{-7, -2, 0, 3, 9}` | `-7` |
| TC-06 | Vector ordenado descendentemente | `{9, 3, 0, -2, -7}` | `-7` |
| TC-07 | Mínimo en primera posición | `{-10, 3, 0, 8}` | `-10` |
| TC-08 | Mínimo en posición intermedia | `{6, 2, -4, 5, 8}` | `-4` |
| TC-09 | Mínimo en última posición | `{5, 2, 9, -4}` | `-4` |
| TC-10 | Límite inferior de `int` | `{0, Integer.MIN_VALUE, 7}` | `Integer.MIN_VALUE` |
| TC-11 | Arreglo vacío | `new int[0]` | Lanza `IllegalArgumentException` |
| TC-12 | Referencia nula | `null` | Lanza `IllegalArgumentException` |

En cada caso válido, el TestRunner también compara el arreglo antes y después para confirmar que el método no lo modifica.

## 3. Ejecución automatizada

Desde el directorio del ejercicio, compilar y ejecutar:

```text
javac MinimoVector.java MinimoVectorTestRunner.java
java MinimoVectorTestRunner
```

El runner utiliza aserciones (`AssertionError`) y reporta `OK` por cada caso aprobado. Si un resultado no coincide, si el arreglo cambia o si falta una excepción esperada, la ejecución falla. El resumen final indica el total de pruebas aprobadas.

## 4. Resultado de auditoría

La implementación satisface el contrato evaluado:

- valida `null` y longitud cero antes de acceder a `vector[0]`;
- inicializa el candidato con el primer elemento, evitando fallos con entradas totalmente positivas o negativas;
- recorre el arreglo sin ordenar y devuelve el mínimo, incluidos valores repetidos y `Integer.MIN_VALUE`;
- mantiene intacta la entrada;
- tiene costo temporal `Θ(n)` y espacio auxiliar `O(1)`.

**Resultado de ejecución:** `javac` compiló ambos archivos y el TestRunner finalizó con **12 de 12 pruebas aprobadas**.

**Dictamen:** aprobado para la entrega respecto de los requisitos funcionales y los casos cubiertos por esta matriz.
