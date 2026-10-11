# Análisis — Ejercicio 4: Detectar elementos duplicados en un vector

## 1. Objetivo

Definir los requisitos para implementar en Java dos métodos que determinen si un vector de enteros contiene al menos un valor repetido. Las soluciones deben devolver el mismo resultado, pero utilizar estrategias con costos distintos:

- **Fuerza bruta:** comparar pares de elementos con ciclos anidados y sin estructura auxiliar.
- **HashSet:** recorrer el vector una vez y aprovechar que un conjunto no admite valores repetidos.

El ejercicio evalúa tanto la corrección como el análisis del compromiso entre tiempo de procesamiento y memoria auxiliar.

## 2. Requisitos funcionales

1. Recibir un vector `int[]` y devolver `true` si existe algún valor que aparece al menos dos veces; devolver `false` en caso contrario.
2. Implementar ambas estrategias en métodos públicos estáticos:
   - `tieneDuplicadosFuerzaBruta(int[] vector)`
   - `tieneDuplicadosHashSet(int[] vector)`
3. Detener el procesamiento tan pronto como se encuentre una coincidencia.
4. No modificar el vector recibido.
5. Si el vector es vacío o tiene un solo elemento, devolver `false`.
6. Si la referencia es `null`, lanzar `IllegalArgumentException`, según el contrato definido para la etapa de planificación.
7. Ambas implementaciones deben producir el mismo booleano para toda entrada válida.

El alcance es detectar **si existe** un duplicado. No se requiere enumerar todos los duplicados ni devolver cuál fue el primero.

## 3. Estrategias requeridas

### A. Fuerza bruta: ciclos anidados

Para cada índice `i` desde `0` hasta `n - 2`, comparar `vector[i]` con cada elemento posterior, con `j` desde `i + 1` hasta `n - 1`. Si `vector[i] == vector[j]`, devolver `true` inmediatamente. Si se terminan las comparaciones, devolver `false`.

La estrategia usa el propio vector como entrada de solo lectura y no necesita una colección auxiliar. Su espacio auxiliar es **O(1)**. En el caso sin duplicados realiza exactamente `n(n - 1) / 2` comparaciones, por lo que su tiempo es **Θ(n²)**. Su mejor caso es **Θ(1)** cuando los dos primeros elementos son iguales y el método corta en la primera comparación.

### B. HashSet: tabla hash

Recorrer los elementos una sola vez y agregarlos a un `HashSet<Integer>`. Si `set.add(valor)` devuelve `false`, ese valor ya estaba presente: devolver `true`. Si termina el recorrido sin colisiones, devolver `false`.

Con una distribución de hashes adecuada, las operaciones de inserción y búsqueda son **O(1) promedio**, dando **O(n) promedio** para el recorrido. El conjunto mantiene hasta `n` valores, por lo que el espacio auxiliar es **O(n)**. Si no hay duplicados, se visitan todos los elementos; si el duplicado se detecta pronto, se corta antes y el uso efectivo de la estructura depende del prefijo recorrido.

El análisis O(n) es promedio, no una garantía universal independiente de las colisiones. Para los enteros del ejercicio, el contrato esperado es el comportamiento promedio de `HashSet`; no debe presentarse como un peor caso estricto sin condiciones.

## 4. Trade-off de ingeniería: tiempo frente a espacio

| Estrategia | Tiempo peor caso esperado en el ejercicio | Mejor caso con corte temprano | Espacio auxiliar |
|---|---:|---:|---:|
| Fuerza bruta | Θ(n²) | Θ(1) | O(1) |
| HashSet | O(n) promedio; vector sin duplicados recorre todo | Θ(1) para un duplicado detectado al comienzo | O(n) |

La fuerza bruta ahorra RAM a costa de efectuar potencialmente muchas comparaciones. HashSet reduce el trabajo de búsqueda al mantener información de los valores ya vistos, lo que requiere memoria adicional proporcional al número de elementos insertados.

Para `HashSet<Integer>`, `int` se convierte a `Integer` (autoboxing). La colección mantiene referencias y estructuras internas de buckets; pueden existir objetos `Integer` además de esa estructura, aunque detalles como el uso de valores cacheados dependen del valor y de la implementación. Estos costos constantes no cambian la complejidad espacial asintótica O(n).

## 5. Casos límite y comportamiento esperado

| Entrada / condición | Fuerza bruta | HashSet | Consideración |
|---|---|---|---|
| Vector vacío | `false` | `false` | No hay pares que comparar ni elementos que insertar. |
| Un solo elemento | `false` | `false` | No puede repetirse un valor dentro de un único elemento. |
| `null` | Lanza `IllegalArgumentException` | Lanza `IllegalArgumentException` | Validar antes de acceder a `length` o iterar. |
| Sin duplicados | `false` | `false` | Peor caso temporal para ambas estrategias: se inspecciona toda la entrada; fuerza bruta hace Θ(n²) comparaciones y HashSet realiza n inserciones promedio. |
| Duplicado en las posiciones 0 y 1 | `true` | `true` | Mejor caso Θ(1) con corte anticipado para ambas. |
| Duplicado en los extremos, posiciones 0 y n−1 | `true` | `true` | Fuerza bruta llega a comparar el primer elemento con el último; HashSet recorre hasta el último valor. |
| Más de un valor duplicado | `true` | `true` | Basta con detectar una colisión; no se exige seguir buscando ni identificar todos los valores repetidos. |
| Valores negativos o mezcla de positivos y negativos | Según igualdad exacta | Según igualdad exacta | El signo no altera el criterio; deben compararse los valores enteros. |

La validación de longitud `<= 1` es una optimización de frontera y evita trabajo innecesario. No reemplaza la validación de `null`, que debe ocurrir primero.

## 6. Preguntas y decisiones para la planificación

- **¿Qué hacer con `null`?** Decidido por el prompt de planificación: lanzar `IllegalArgumentException` en ambos métodos. No se considera equivalente a un vector vacío.
- **¿Se debe retornar el valor duplicado?** No. Se menciona como posible sobrecarga opcional en el prompt del planificador, pero queda fuera del requisito obligatorio para mantener un contrato simple y comparable.
- **¿Se permiten modificaciones del vector?** No se necesitan para ninguna estrategia; tratarlo como entrada de solo lectura evita efectos secundarios.
- **¿Qué significa “peor caso” para HashSet?** Para el escenario pedido, un vector sin duplicados fuerza el recorrido completo y cuesta O(n) promedio bajo la hipótesis de distribución de hash adecuada. Conviene distinguirlo del peor caso teórico bajo colisiones adversas.
- **¿Cómo probar que hay corte anticipado?** La matriz de resultados demuestra paridad y cubre duplicados tempranos/tardíos. El corte en sí se confirma revisando la implementación (retorno al encontrar colisión); no se debe inferir solo del resultado booleano.

## 7. Borrador del Prompt Técnico Inicial para el Planificador

> Actúa como Planificador / Software Architect para el Ejercicio 4: detectar si un vector de enteros contiene duplicados. Diseña una clase Java (por ejemplo, `DetectorDuplicados`) con los métodos públicos estáticos `tieneDuplicadosFuerzaBruta(int[] vector)` y `tieneDuplicadosHashSet(int[] vector)`, ambos con retorno `boolean`.
>
> Define contratos iguales para ambos métodos: si `vector == null`, lanzar `IllegalArgumentException`; si `vector.length <= 1`, retornar `false` inmediatamente. No modificar el vector. La primera estrategia debe comparar cada par `(i, j)` con `j` comenzando en `i + 1`, usando memoria auxiliar O(1). La segunda debe recorrer el vector una sola vez con `HashSet<Integer>` y detectar repetidos cuando `set.add(valor)` retorne `false`, usando O(n) de espacio auxiliar.
>
> Exige retorno inmediato al detectar la primera coincidencia. Incluye una matriz formal de complejidad: fuerza bruta Θ(n²) en el caso sin duplicados y Θ(1) en el mejor caso temprano, con O(1) de espacio; HashSet O(n) de tiempo promedio bajo una distribución de hash adecuada, Θ(1) en el mejor caso temprano y O(n) de espacio auxiliar. Distingue el caso sin duplicados (recorrido completo) del mejor caso, y no presentes el O(n) promedio de HashSet como una garantía incondicional de peor caso.
>
> Considera vectores vacío y unitario, referencia `null`, duplicado en las primeras posiciones, duplicado en los extremos, vector sin duplicados, múltiples duplicados y valores negativos. El objetivo es informar si existe algún duplicado; no es necesario devolver los valores duplicados. Devuelve el diseño técnico, los contratos, el pseudoflujo y la matriz de complejidad para que el agente desarrollador implemente la solución.

## 8. Bloque de cabecera para la entrega

```text
Ejercicio 4 — Detectar elementos duplicados en un vector
Lenguaje: Java
Objetivo: implementar dos métodos equivalentes para determinar si existe al menos un duplicado.
Estrategias: fuerza bruta con ciclos anidados (O(1) espacio auxiliar) y HashSet (O(n) espacio auxiliar).
Contrato: null lanza IllegalArgumentException; vector vacío o unitario retorna false; no se modifica la entrada.
Optimización: corte inmediato al encontrar la primera coincidencia.
Análisis: fuerza bruta Θ(n²) en el peor caso y Θ(1) en el mejor; HashSet O(n) promedio bajo distribución adecuada y O(n) espacio.
Prompt inicial para el planificador: [incluir el borrador de la sección 7].
Ajustes posteriores: [registrar aquí cambios de alcance o decisiones del planificador].
```