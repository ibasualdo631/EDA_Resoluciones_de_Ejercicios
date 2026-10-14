# Pruebas — Pila enlazada de enteros

## Alcance

Se validó `PilaEnteros` contra los requerimientos y casos límite de `pipeline/01_analisis.md`. La suite ejecutable `PruebasPilaEnteros.java` no necesita bibliotecas de terceros: compila con el JDK y usa aserciones propias que detienen la ejecución si alguna condición falla.

## Casos diseñados y resultados

| ID | Caso | Resultado esperado | Resultado |
|---|---|---|---|
| P01 | Consultar estado y buscar en una pila recién creada. | `isEmpty()` es `true`; `contains` devuelve `false`. | **Aprobado** |
| P02 | Ejecutar `pop()` con la pila vacía. | Lanza `IllegalStateException` y conserva el estado vacío. | **Aprobado** |
| P03 | Ejecutar `peek()` con la pila vacía. | Lanza `IllegalStateException`. | **Aprobado** |
| P04 | Apilar un único entero, consultar y desapilarlo. | `peek()` devuelve el valor sin quitarlo; `pop()` lo devuelve y deja la pila vacía. | **Aprobado** |
| P05 | Apilar varios valores, incluyendo negativo, cero, duplicados y los extremos `Integer.MIN_VALUE`/`Integer.MAX_VALUE`. | Todos los valores se conservan y `pop()` los devuelve en orden LIFO. | **Aprobado** |
| P06 | Buscar valores ubicados en el tope, en el interior, en la base y uno ausente. | Presente devuelve `true`; ausente devuelve `false`; búsqueda no altera el tope ni el orden. | **Aprobado** |
| P07 | Imprimir una pila vacía. | Produce exactamente `Pila vacia` seguido del fin de línea. | **Aprobado** |
| P08 | Imprimir una pila con tres elementos. | Produce `Tope -> 1 -> 2 -> 3 -> Base` seguido del fin de línea, sin modificarla. | **Aprobado** |
| P09 | Apilar después de desapilar el último elemento. | La pila vuelve a funcionar y devuelve el nuevo valor correctamente. | **Aprobado** |
| P10 | Llamar a `imprimir(null)`. | Lanza `NullPointerException` explícitamente en lugar de ocultar el argumento inválido. | **Aprobado** |

## Ejecución

Desde la carpeta del ejercicio, con JDK 21.0.10:

```text
javac -d out PilaEnteros.java PruebasPilaEnteros.java
java -cp out PruebasPilaEnteros
```

Salida observada:

```text
Pruebas superadas: 29
```

**Resultado general: aprobado; 29 comprobaciones superadas, 0 fallidas.** La compilación y la suite concluyeron con código de salida 0.

## Observaciones

- Los casos vacíos, transición entre vacío/no vacío, orden LIFO, valores enteros, búsqueda y salida no destructiva están cubiertos por pruebas ejecutables.
- La complejidad no se infiere de una medición de tiempo: se verifica en el algoritmo. `push` crea y enlaza un nodo en `head`, y `pop` avanza `head` una vez, sin recorrer la lista; ambos son O(1). `contains` e impresión recorren como máximo todos los nodos, O(n).
- La suite se ejecuta como programa Java independiente, ya que el proyecto no contiene configuración de JUnit u otro framework de pruebas.