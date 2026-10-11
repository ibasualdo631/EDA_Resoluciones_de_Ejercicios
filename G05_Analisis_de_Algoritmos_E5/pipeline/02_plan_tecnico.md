# Plan técnico — Ejercicio 5: Contar ocurrencias

## Diseño del método

Implementar un método público y estático en Java con esta firma:

```java
public static int contarOcurrencias(int[] vector, int objetivo)
```

- `vector`: arreglo de enteros que se analizará.
- `objetivo`: valor cuya cantidad de apariciones se desea contar.
- Retorno: número de posiciones cuyo valor es igual a `objetivo`.

Ubicar el método en la clase correspondiente al ejercicio, siguiendo la organización existente del proyecto. No agregar dependencias ni estructuras de datos auxiliares.

## Contrato y excepciones

- Si `vector` es `null`, lanzar `IllegalArgumentException` antes de iniciar el recorrido, con un mensaje claro que indique que el vector no puede ser `null`.
- Si `vector` está vacío, devolver `0`.
- Si no hay coincidencias, devolver `0`.
- Contar todas las coincidencias, incluso si son consecutivas o están separadas.

## Instrucciones directas para el Desarrollador

1. Declarar un contador entero inicializado en `0`.
2. Recorrer el arreglo desde el índice `0` hasta `vector.length - 1`.
3. En cada posición, comparar `vector[i]` con `objetivo`; si son iguales, incrementar el contador.
4. Completar siempre el recorrido. No usar `break`, `return` dentro del bucle ni otra salida anticipada ante una coincidencia o una no coincidencia.
5. Devolver el contador una vez terminado el bucle.
6. Mantener el estado adicional en variables escalares; no copiar ni ordenar el arreglo.
7. Agregar pruebas siguiendo el mecanismo de testing ya utilizado por el proyecto.

Pseudocódigo:

```text
contarOcurrencias(vector, objetivo):
    si vector es null:
        lanzar IllegalArgumentException
    contador = 0
    para cada índice i desde 0 hasta vector.length - 1:
        si vector[i] == objetivo:
            contador = contador + 1
    devolver contador
```

## Justificación de complejidad

Sea `n = vector.length`.

- **Peor caso:** `O(n)`. El método realiza una comparación por cada posición.
- **Mejor caso:** `Ω(n)`. También cuando el arreglo está vacío o la primera posición coincide, el método debe completar el recorrido para contar el total. Más precisamente, para todo arreglo válido hace `n` comparaciones, por lo que el tiempo es `Θ(n)` en mejor, promedio y peor caso.
- **Espacio adicional:** `O(1)`. Solo se usa el contador y el índice del recorrido, sin memoria proporcional al tamaño del arreglo.

La verificación de `null` ocurre en tiempo constante y no modifica estas cotas para entradas válidas.

## Pruebas requeridas

Verificar como mínimo:

| Vector | Objetivo | Resultado |
|---|---:|---:|
| `{}` | `4` | `0` |
| `{1, 2, 3}` | `4` | `0` |
| `{4}` | `4` | `1` |
| `{4, 2, 4, 4, 1}` | `4` | `3` |
| `{1, 2, 3}` | `1` | `1` |
| `null` | `4` | Lanza `IllegalArgumentException` |