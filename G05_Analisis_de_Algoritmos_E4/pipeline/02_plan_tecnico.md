# Plan técnico — Ejercicio 4: Detectar elementos duplicados

## 1. Decisiones de diseño

- Implementar una clase de utilidad `DetectorDuplicados`, declarada `public final`.
- Exponer los dos métodos estáticos especificados en el análisis; ambos responden únicamente si existe al menos un valor duplicado.
- Mantener la entrada inmutable: los métodos solo leen el vector.
- Centralizar la validación de referencia en un método privado para que ambos métodos cumplan el mismo contrato.
- No implementar las sobrecargas opcionales que devuelven el primer elemento duplicado: no forman parte del alcance obligatorio y añadirían una segunda semántica que el ejercicio no necesita.
- Si el proyecto no define paquete Java, mantener la clase en el paquete por defecto; si se establece un paquete al crear el proyecto, usarlo de forma consistente con la estructura existente.

## 2. Estructura propuesta

```text
DetectorDuplicados
├── private DetectorDuplicados()
├── private static void validarVector(int[] vector)
├── public static boolean tieneDuplicadosFuerzaBruta(int[] vector)
├── public static boolean tieneDuplicadosHashSet(int[] vector)
└── public static void main(String[] args)          // banco de pruebas de la entrega
```

El constructor privado evita instanciar una clase cuyos miembros son estáticos. `validarVector` lanza `IllegalArgumentException` cuando recibe `null`. Los métodos públicos invocan esa validación antes de acceder a `length`; a continuación, ambos retornan `false` cuando `length <= 1`.

## 3. Contratos de métodos

### `tieneDuplicadosFuerzaBruta`

```java
public static boolean tieneDuplicadosFuerzaBruta(int[] vector)
```

- **Precondición:** la referencia debe ser distinta de `null`; de lo contrario, lanza `IllegalArgumentException`.
- **Resultado:** `true` si dos posiciones distintas contienen el mismo entero; `false` si no existe tal par.
- **Frontera:** vector vacío o unitario retorna `false`.
- **Efectos:** no modifica el vector ni mantiene colecciones auxiliares.
- **Algoritmo:** bucle externo `i = 0; i < n - 1; i++`; bucle interno `j = i + 1; j < n; j++`; si `vector[i] == vector[j]`, retornar `true`; tras completar ambos bucles, retornar `false`.
- **Corte anticipado:** el `return true` al encontrar la primera igualdad termina inmediatamente ambos bucles.

### `tieneDuplicadosHashSet`

```java
public static boolean tieneDuplicadosHashSet(int[] vector)
```

- **Precondición, resultado de frontera y efectos:** idénticos al método de fuerza bruta.
- **Algoritmo:** crear `HashSet<Integer>` y recorrer el vector una sola vez con `for-each`. Para cada valor, llamar a `vistos.add(valor)`; si devuelve `false`, retornar `true`; si el ciclo finaliza, retornar `false`.
- **Corte anticipado:** el retorno ocurre en la primera inserción fallida.
- **Dependencia:** importar `java.util.HashSet` (sin dependencias externas).

## 4. Secuencia lógica

1. Recibir el vector.
2. Validar `vector != null`; si no se cumple, lanzar `IllegalArgumentException`.
3. Si `vector.length <= 1`, retornar `false`.
4. Ejecutar la estrategia propia del método:
   - Fuerza bruta compara únicamente pares con `j > i`.
   - HashSet conserva los valores visitados e interpreta `add(valor) == false` como duplicado.
5. Retornar `true` al primer duplicado; retornar `false` si se agotan las comparaciones o el recorrido.

## 5. Matriz de complejidad asintótica

`n` representa la longitud del vector. El mejor caso Θ(1) supone que el duplicado aparece en las dos primeras posiciones, tras validar la entrada.

| Estrategia | Caso sin duplicados / recorrido completo | Mejor caso con corte anticipado | Espacio auxiliar |
|---|---|---|---|
| Fuerza bruta | Θ(n²): compara `n(n − 1) / 2` pares | Θ(1) (también expresable como Ω(1)) | O(1) |
| HashSet | O(n) promedio, suponiendo inserción O(1) promedio por baja colisión | Θ(1) (también expresable como Ω(1)) | O(n) |

**Precisión sobre el término “peor caso”:** no describir HashSet como O(n) de peor caso incondicional. El vector sin duplicados obliga a recorrer todos sus `n` elementos, pero el costo O(n) es promedio bajo la hipótesis de baja colisión. En distribuciones adversas, las operaciones del conjunto pueden costar más; por ello, la garantía presentada en esta matriz es explícitamente promedio.

El costo O(1) de espacio de fuerza bruta cuenta el espacio auxiliar del algoritmo, no el vector recibido. Para HashSet, el espacio auxiliar crece con los valores insertados; el autoboxing de `int` a `Integer` y la tabla interna no cambian el orden O(n).

## 6. Organización del banco de pruebas

El `main` ejecutará los dos métodos con las mismas entradas y comprobará que ambos produzcan el resultado esperado y coincidan entre sí. Incluir como mínimo:

| Caso | Ejemplo | Esperado |
|---|---|---:|
| Duplicado adyacente al inicio | `{4, 4, 2}` | `true` |
| Duplicado en los extremos | `{7, 3, 5, 7}` | `true` |
| Sin duplicados | `{1, 2, 3, 4}` | `false` |
| Un elemento | `{9}` | `false` |
| Vacío | `{}` | `false` |
| Negativos y positivos con duplicado | `{-3, 10, 5, -3}` | `true` |
| Múltiples duplicados | `{1, 2, 1, 2}` | `true` |

Comprobar también `null` para ambos métodos, verificando que lanzan `IllegalArgumentException`. Para que el banco de pruebas siempre se ejecute sin opciones especiales de JVM, preferir comprobaciones explícitas que informen el caso fallido en lugar de depender únicamente de `assert`, que puede estar deshabilitado.

## 7. Hyper-Prompt definitivo para el Agente Desarrollador

> Actúa como Desarrollador Senior Java e implementa el Ejercicio 4, “Detectar elementos duplicados en un vector”, respetando el diseño y los contratos siguientes. Crea una clase de utilidad `public final DetectorDuplicados` (adapta el paquete solo si el proyecto ya tiene uno), con constructor privado y estos dos métodos públicos estáticos:
>
> ```java
> public static boolean tieneDuplicadosFuerzaBruta(int[] vector)
> public static boolean tieneDuplicadosHashSet(int[] vector)
> ```
>
> Ambos métodos deben lanzar `IllegalArgumentException` si `vector == null`, antes de cualquier acceso a `length` o iteración. Después de validar, deben retornar inmediatamente `false` para `vector.length <= 1`. No modificar la entrada. Ambos deben contestar la misma pregunta booleana: si existe al menos un valor que aparece en dos posiciones distintas.
>
> En `tieneDuplicadosFuerzaBruta`, implementa dos ciclos `for`: el externo con `i` desde `0` hasta `n - 2`, el interno con `j` desde `i + 1` hasta `n - 1`. Compara `vector[i]` con `vector[j]`; ante igualdad, retorna `true` de inmediato. Si termina el recorrido sin coincidencias, retorna `false`. No uses estructuras auxiliares para esta estrategia.
>
> En `tieneDuplicadosHashSet`, usa `java.util.HashSet<Integer>` y un único ciclo `for-each`. Si `vistos.add(valor)` devuelve `false`, retorna `true` de inmediato. Si concluye el recorrido, retorna `false`.
>
> Añade comentarios técnicos breves y explícitos junto a las estrategias para justificar el trade-off: fuerza bruta realiza Θ(n²) comparaciones en el caso sin duplicados y usa O(1) de espacio auxiliar; HashSet tiene O(n) de tiempo promedio bajo la hipótesis de baja colisión y usa O(n) de espacio auxiliar. Aclara que el O(n) de HashSet es promedio, no una garantía incondicional ante colisiones adversas. Menciona que el vector no se modifica. No añadas comentarios que repitan literalmente cada instrucción.
>
> Incluye un `main` con comprobaciones explícitas para los dos métodos sobre los mismos casos: duplicado adyacente al inicio, duplicado en los extremos, vector sin duplicados, vector vacío, vector unitario, valores negativos y positivos con repetición, y múltiples duplicados. Verifica resultados esperados y paridad entre métodos. Verifica también que ambos métodos rechacen `null` con `IllegalArgumentException`. Las comprobaciones deben ejecutarse aunque no se active `-ea`.
>
> No implementes sobrecargas que devuelvan el valor duplicado: el alcance es exclusivamente el booleano. Mantén el código compilable y conciso, sin dependencias externas. Entrega el archivo Java completo e informa brevemente cómo compilarlo y ejecutar el banco de pruebas.