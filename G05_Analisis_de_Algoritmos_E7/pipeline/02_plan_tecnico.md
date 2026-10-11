# Plan técnico — Ejercicio 7: invertir un vector

## 1. Objetivo

Implementar en Java dos estrategias para invertir un vector de enteros:

- `invertirVectorInSitu`: invierte el arreglo recibido mediante intercambios, sin reservar otro arreglo de tamaño `n`.
- `InvertirVectorAuxiliar`: crea un arreglo nuevo con los elementos en orden inverso y conserva sin cambios el arreglo recibido.

Ambos métodos reciben `int[]` y devuelven el vector resultante como `int[]`. Si la referencia recibida es `null`, ambos lanzan `IllegalArgumentException` con un mensaje claro. Si el arreglo tiene menos de dos elementos, el método avisa por consola y lo devuelve sin cambios.

## 2. Estructura propuesta

Mantener la implementación sencilla, en el mismo paquete del ejercicio:

| Tipo | Responsabilidad |
|---|---|
| `invertirVectorInSitu` | Implementación que modifica el arreglo original mediante intercambios simétricos. |
| `InvertirVectorAuxiliar` | Implementación que devuelve un arreglo nuevo y no altera el original. |
| `Ejercicio7` | Clase ejecutable con `main` y pruebas de consola para ambos métodos. |

Se conserva el nombre `invertirVectorInSitu` tal como fue solicitado, aunque la convención habitual de Java recomienda iniciar los nombres de clase con mayúscula.

## 3. Contrato y firmas

```java
public final class invertirVectorInSitu {
    public static int[] invertir(int[] vector);
}

public final class InvertirVectorAuxiliar {
    public static int[] invertir(int[] vector);
}
```

### Política de entrada inválida y caso de un elemento

- Si `vector == null`, lanzar `IllegalArgumentException` (por ejemplo, `throw new IllegalArgumentException("El vector no puede ser null")`). La excepción es la respuesta al caso nulo; no se devuelve un resultado normal para esa entrada.
- Si `vector.length == 0`, imprimir un aviso de que el vector está vacío y devolverlo sin cambios.
- Si `vector.length == 1`, imprimir un aviso de que posee un único elemento y devolverlo sin cambios.
- Si `vector.length >= 2`, invertirlo y devolver el vector resultante.

La versión in situ devuelve la misma referencia recibida. La versión auxiliar devuelve una referencia nueva incluso para los casos de longitud menor que dos, preservando el contenido de entrada.

## 4. Algoritmos

### 4.1 Inversión in situ

1. Validar que la referencia no sea `null`.
2. Si la longitud es menor que 2, imprimir el aviso correspondiente y devolver el mismo arreglo sin modificarlo.
3. Inicializar `izquierda = 0` y `derecha = n - 1`.
4. Mientras `izquierda < derecha`, intercambiar `vector[izquierda]` con `vector[derecha]`, incrementar `izquierda` y decrementar `derecha`.
5. Devolver la misma referencia modificada.

No crear copias, subarreglos ni estructuras proporcionales a `n`.

### 4.2 Inversión con arreglo auxiliar

1. Validar que la referencia no sea `null`.
2. Si la longitud es menor que 2, imprimir el aviso correspondiente.
3. Crear un arreglo auxiliar de longitud `n`.
4. Para cada índice `i` desde 0 hasta `n - 1`, asignar `auxiliar[n - 1 - i] = vector[i]`.
5. Devolver el arreglo auxiliar. El original debe permanecer intacto.

La estrategia auxiliar siempre devuelve una referencia independiente, aun para longitud 0 o 1.

## 5. Complejidad

Sea `n` la longitud del vector. El análisis excluye el tamaño del vector de entrada. Las validaciones y la construcción del objeto resultado requieren espacio/tiempo constante, además de lo propio del algoritmo.

| Método | Mejor caso | Promedio | Peor caso | Espacio auxiliar |
|---|---:|---:|---:|---:|
| `invertirVectorInSitu.invertir` | Θ(n) | Θ(n) | Θ(n) | Θ(1) |
| `InvertirVectorAuxiliar.invertir` | Θ(n) | Θ(n) | Θ(n) | Θ(n) |

- **In situ:** procesa `⌊n/2⌋` pares simétricos. Para un `n` fijo, el número de intercambios no depende de los valores ni del orden inicial; por eso mejor, promedio y peor caso son Θ(n). Para `n <= 1`, el retorno temprano cuesta Θ(1). Usa un número fijo de variables y no crea un arreglo proporcional a la entrada: Θ(1) espacio adicional.
- **Auxiliar:** asigna y llena `n` posiciones. Para un `n` fijo debe copiar los elementos independientemente de sus valores, de modo que mejor, promedio y peor caso son Θ(n). El nuevo arreglo ocupa Θ(n) espacio adicional. Para `n <= 1`, el trabajo de control es Θ(1); si se mantiene la semántica de resultado independiente también se crea un arreglo de longitud `n`.

No hay mejor caso asintótico por contenido de los datos: invertir un arreglo general requiere procesar una cantidad proporcional a su longitud. La recomendación por memoria es la estrategia in situ cuando se permite modificar el original.

## 6. Secuencia de implementación

1. Implementar `invertirVectorInSitu` con validación de `null`, avisos para casos borde e intercambios.
2. Implementar `InvertirVectorAuxiliar` con el mismo contrato y copia en orden inverso.
3. Añadir un `main` que pruebe ambos métodos para los casos requeridos y verifique contenido y referencias.
4. Documentar en la explicación la diferencia de memoria, el efecto sobre el arreglo original y el análisis temporal mejor/promedio/peor.

## 7. Pruebas de aceptación

- Vector de longitud par, por ejemplo `[1, 2, 3, 4]`, produce `[4, 3, 2, 1]`.
- Vector de longitud impar, por ejemplo `[1, 2, 3, 4, 5]`, produce `[5, 4, 3, 2, 1]` y conserva el elemento central.
- Vector vacío imprime un aviso, conserva su contenido y no produce error.
- Vector unitario imprime un aviso y conserva su valor.
- Referencia `null` lanza `IllegalArgumentException` con un mensaje no nulo y entendible en ambas implementaciones.
- La versión in situ devuelve la misma referencia recibida y modifica su contenido cuando la longitud es mayor que uno.
- La versión auxiliar devuelve una referencia distinta para entradas no nulas y conserva intacto el arreglo original.
- Ambas implementaciones devuelven el mismo contenido invertido para entradas equivalentes.