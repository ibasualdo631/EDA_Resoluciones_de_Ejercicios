# Plan técnico — Ejercicio 6: comparar dos vectores

## 1. Objetivo de implementación

Desarrollar en Java una operación que indique si dos arreglos de enteros representan vectores iguales. La igualdad es posicional: ambos deben tener la misma longitud y el mismo valor en cada índice. La comparación debe terminar apenas se detecte una diferencia.

## 2. Diseño propuesto

Implementar una función estática, sin estado ni efectos secundarios:

```java
public static boolean compararVectores(int[] vectorA, int[] vectorB)
```

- **Entradas:** dos referencias a arreglos `int[]`, que pueden ser `null`.
- **Salida:** `true` si son iguales según las reglas de este plan; `false` en caso contrario.
- **Responsabilidad:** rechazar referencias nulas, validar longitudes y comparar los elementos en orden.
- **Estructuras auxiliares:** ninguna. Usar un índice entero para recorrer los arreglos; no copiar, ordenar ni transformar los datos.

Implementar la clase `CompararVectores` en `CompararVectores.java`, en el directorio raíz del ejercicio. No agregar dependencias ni abstraer una operación que solo necesita un recorrido lineal.

## 3. Reglas de comportamiento

Aplicar estas decisiones explícitamente y en este orden:

1. Si cualquiera de las referencias es `null` (incluidas ambas), lanzar `IllegalArgumentException`.
2. Si las longitudes son diferentes, retornar `false` sin acceder a los elementos.
3. Recorrer los índices desde `0` hasta `length - 1`. Si los elementos de un índice difieren, retornar `false` inmediatamente.
4. Si el recorrido termina sin diferencias, retornar `true`. Esto incluye el caso de dos arreglos vacíos.

Estas reglas hacen que dos vectores vacíos sean iguales y que un arreglo vacío y otro no vacío sean distintos. No usar `==` entre arreglos para determinar igualdad de contenido: compara identidad de referencias, no los valores almacenados.

## 4. Secuencia lógica / pseudocódigo

```text
compararVectores(vectorA, vectorB):
    si vectorA es null o vectorB es null:
        lanzar IllegalArgumentException
    si longitud(vectorA) != longitud(vectorB):
        retornar false

    para i desde 0 hasta longitud(vectorA) - 1:
        si vectorA[i] != vectorB[i]:
            retornar false

    retornar true
```

Mantener el retorno `false` dentro del ciclo para garantizar la terminación anticipada. No recorrer los elementos restantes tras encontrar una diferencia.

## 5. Manejo de excepciones y entradas límite

- **Lanzar `IllegalArgumentException` si algún argumento es `null`:** comprobar ambas referencias antes de consultar `length`, evitando que se produzca una `NullPointerException`.
- **No lanzar excepciones propias por longitudes distintas:** son una condición normal de desigualdad, no un error de uso.
- **Acceso seguro:** después de comprobar que ambas referencias no son `null` y tienen igual longitud, cada índice del ciclo está en el rango válido de ambos arreglos.
- **No capturar excepciones de forma amplia:** no se espera ninguna excepción para las entradas admitidas y un `catch` general ocultaría defectos de implementación.

## 6. Complejidad asintótica

Para arreglos de igual longitud, sea `n` su longitud. Las comprobaciones de referencias y longitudes son constantes.

| Escenario | Comparaciones de elementos | Tiempo |
|---|---:|---:|
| Mejor caso: referencias `null`, longitudes diferentes, arreglos vacíos o diferencia en el índice 0 | 0 o 1 | `O(1)` |
| Peor caso: vectores iguales o diferencia únicamente en el último índice | `n` | `O(n)` |
| Promedio condicionado a vectores distintos, con la primera diferencia equiprobable entre los `n` índices | `(n + 1) / 2` | `Θ(n)` |

El caso promedio debe presentarse con su hipótesis explícita: para vectores distintos de igual longitud, la primera posición diferente se distribuye uniformemente. Si los datos reales tienen otra distribución, el número promedio de comparaciones puede cambiar. El espacio adicional es `O(1)`.

## 7. Pruebas requeridas

Validar el resultado para, como mínimo, estos casos:

| Caso | Entrada (A, B) | Resultado esperado |
|---|---|---|
| A es nulo | `(null, [1])` | `IllegalArgumentException` |
| B es nulo | `([1], null)` | `IllegalArgumentException` |
| Ambas referencias nulas | `(null, null)` | `IllegalArgumentException` |
| Ambos vacíos | `([], [])` | `true` |
| Solo uno vacío | `([], [1])` | `false` |
| Longitudes distintas | `([1], [1, 2])` | `false` |
| Iguales con varios elementos | `([1, 2, 3], [1, 2, 3])` | `true` |
| Diferencia al inicio | `([1, 2], [9, 2])` | `false` |
| Diferencia intermedia | `([1, 2, 3], [1, 9, 3])` | `false` |
| Diferencia al final | `([1, 2, 3], [1, 2, 9])` | `false` |

Si el proyecto ya cuenta con un framework de pruebas, seguir su convención existente. En caso contrario, no introducir una dependencia solo para estos casos: usar el mecanismo de pruebas ya provisto por el ejercicio.

## 8. Criterios de aceptación

- La firma `compararVectores(int[], int[])` retorna un booleano.
- Si cualquiera de sus argumentos es `null`, lanza `IllegalArgumentException`.
- Todos los casos de la tabla producen el resultado esperado.
- No hay acceso a arreglos antes de resolver las referencias `null`.
- La longitud se compara antes de recorrer elementos.
- La primera diferencia provoca retorno inmediato.
- La explicación informa mejor, peor y caso promedio con su supuesto, y espacio adicional.
- La implementación no modifica los arreglos ni agrega estructuras de tamaño proporcional a la entrada.