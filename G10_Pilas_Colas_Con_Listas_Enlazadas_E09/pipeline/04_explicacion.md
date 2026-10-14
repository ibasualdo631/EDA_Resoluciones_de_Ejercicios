# Explicación didáctica — Cola de impresión

## 1. Qué resuelve el programa

`ColaImpresion` mantiene trabajos pendientes en memoria y ofrece cuatro operaciones: agregar un trabajo, imprimir el siguiente, consultar el nombre del archivo que sigue y listar los trabajos pendientes. Procesa en orden **FIFO** (*First In, First Out*): primero en entrar, primero en salir.

El programa implementa una cola enlazada real en memoria, con nodos conectados mediante referencias. La palabra “imprimir” en este ejercicio significa retirar el trabajo que está al frente y mostrar sus datos en la consola; no se envía el archivo a una impresora física.

## 2. Una clase pública y una clase anidada

El archivo contiene una clase pública, `ColaImpresion`, y dentro de ella declara:

```java
private static final class Nodo
```

`Nodo` es una clase anidada: se declara dentro de otra clase porque solo sirve para implementar internamente esta cola. No hace falta que otros objetos del programa conozcan ni manipulen directamente sus nodos. `private` protege ese detalle y `static` indica que cada nodo no necesita una referencia implícita a una instancia externa de `ColaImpresion`.

Cada nodo contiene los campos del trabajo (`archivo`, `paginas` y `usuario`) y la referencia `siguiente` al nodo que viene después. Los tres datos del trabajo son `final`, así que no se reemplazan una vez creado el nodo; el enlace sí puede cambiar para poder construir y manipular la cadena.

Esta organización mantiene en un solo archivo la estructura y su implementación. `Nodo` sigue siendo una clase Java, pero no es una segunda clase pública ni un archivo separado.

## 3. Cómo se representa la cola enlazada

La cola mantiene dos referencias:

- `frente`: apunta al trabajo más antiguo, que se atenderá primero.
- `ultimo`: apunta al trabajo más nuevo, donde se conectará la siguiente llegada.

Cada nodo enlaza con el siguiente, formando una cadena. Por ejemplo, después de agregar tres trabajos:

```text
frente
  |
  v
[alpha] -> [beta] -> [gamma] -> null
                              ^
                              |
                            ultimo
```

`null` marca el final de la cadena. Cuando la cola está vacía, `frente` es `null`; al mantener correctamente los extremos, `ultimo` también es `null`.

El método `estaVacia()` consulta `frente == null`. Así se centraliza la condición que usan las operaciones antes de actuar sobre el primer elemento.

## 4. Agregar: conectar al final

`agregarTrabajo(archivo, paginas, usuario)` primero comprueba que:

- el nombre del archivo exista y no esté vacío;
- las páginas sean mayores que cero;
- el usuario exista y no esté vacío.

Si un dato no cumple, arroja `IllegalArgumentException`. La interfaz captura esa excepción y explica por qué el trabajo no pudo agregarse. Las cadenas se limpian con `trim()` antes de guardarse.

Luego se crea un nodo. Hay dos casos:

1. **Cola vacía:** el nodo es el primero y el último al mismo tiempo, por eso ambas referencias apuntan a él.
2. **Cola con elementos:** se conecta el nodo después del que señalaba `ultimo`, y `ultimo` se actualiza para señalar el nodo nuevo.

Al guardar `ultimo`, agregar no necesita recorrer toda la cadena: cuesta `O(1)`.

## 5. Imprimir: retirar del frente

`imprimirProximo()` entrega un `Optional<String>`, que representa explícitamente dos posibilidades: hay un trabajo para procesar (`Optional` con valor) o no hay ninguno (`Optional.empty()`).

Cuando sí hay un trabajo:

1. Se guarda temporalmente el nodo actual de `frente`.
2. `frente` avanza a `frente.siguiente`; así el segundo trabajo pasa a ser el próximo.
3. Si no queda otro nodo, `frente` pasa a ser `null` y también se limpia `ultimo`. Este paso es importante: después de retirar el único trabajo, los dos extremos deben indicar cola vacía.
4. Se separa el nodo retirado de la cadena poniendo su enlace en `null`.
5. Se devuelve una descripción con los datos del trabajo para que el menú la muestre.

Retirar desde el frente cuesta `O(1)`. El método que valida la estructura devuelve `Optional.empty()` si estaba vacía; el menú transforma ese caso en el mensaje “No hay trabajos pendientes”.

### Seguimiento de referencias

Si la cola contiene `[alpha] -> [beta] -> null`, inicialmente `frente` señala `alpha`. Al imprimir, `frente` avanza a `beta`; el nodo `alpha` deja de pertenecer a la cola. La siguiente impresión retirará `beta`. Ese cambio de referencia implementa FIFO.

## 6. Consultar y listar sin alterar la cola

### Consultar el próximo archivo

`consultarProximoArchivo()` lee `frente.archivo` si existe un nodo y devuelve el resultado en un `Optional`. No avanza `frente`, así que consultar no consume el trabajo.

### Listar trabajos pendientes

`listarPendientes()` usa una referencia temporal llamada `actual`. Empieza en `frente`, registra la descripción de cada nodo y avanza por `actual.siguiente` hasta llegar a `null`. No cambia los enlaces de la cola.

La lista devuelta es una representación para mostrar: contiene descripciones (`String`), no referencias a los nodos internos. Recorrer `n` nodos cuesta `O(n)`; las consultas de un elemento cuestan `O(1)`.

## 7. Por qué el orden debe ser FIFO

El comentario en el código resume la razón: FIFO preserva el orden de llegada. Un trabajo nuevo no puede adelantar a otro que ya está esperando, lo que evita postergaciones indefinidas y hace previsible cuál será el siguiente trabajo. Por eso, la implementación inserta por el extremo `ultimo` y retira por `frente`; intercambiar esos extremos o buscar el trabajo más corto cambiaría el comportamiento requerido.

## 8. Interacción por consola

El método `main` crea una instancia del spooler y un `Scanner` para leer la entrada. El ciclo `while` muestra el menú repetidamente y el `switch` deriva la selección a la operación correspondiente:

1. **Agregar:** lee archivo, páginas y usuario. `Integer.parseInt` convierte las páginas a entero; si no es un número válido, se informa el error sin cerrar el programa.
2. **Imprimir:** usa `ifPresentOrElse` para mostrar el trabajo retirado o informar que la cola está vacía.
3. **Consultar:** muestra el archivo al frente, si lo hay.
4. **Listar:** enumera los trabajos que devuelve `listarPendientes()`.
0. **Salir:** cambia `continuar` a `false` y termina el ciclo.

El menú es la capa de interacción; los métodos de la cola contienen las reglas de manipulación. Esa separación permite reutilizar las operaciones desde otra interfaz sin copiar la lógica de los nodos.

## 9. Conceptos de Java y estructuras de datos

- **Clases y encapsulamiento:** `ColaImpresion` administra el estado; `Nodo` oculta la representación enlazada.
- **Clases anidadas:** agrupan el nodo privado con la estructura que lo utiliza.
- **Referencias y `null`:** conectan los nodos y representan los extremos de la cadena.
- **Campos `final`:** protegen los datos del trabajo frente a reasignaciones.
- **Excepciones:** `IllegalArgumentException` comunica parámetros inválidos; `NumberFormatException` comunica que las páginas no se pudieron convertir.
- **`Optional`:** hace visible que imprimir o consultar pueden no tener resultado.
- **`List` y `ArrayList`:** reúnen las descripciones para presentar el listado.
- **Ciclo `while`, `switch` y `for`:** controlan respectivamente el menú, sus opciones y la enumeración de trabajos.
- **Complejidad:** agregar e imprimir son `O(1)`; consultar el frente también es `O(1)`; listar es `O(n)`.

## 10. Errores comunes que se evitan

- **No actualizar `ultimo` al retirar el único nodo:** podría dejar una referencia obsoleta y romper la siguiente inserción.
- **Avanzar el frente al consultar:** consumiría accidentalmente el trabajo sin imprimirlo.
- **Insertar al frente en lugar de al final:** invertiría el orden de llegada.
- **Recorrer toda la cola para agregar:** seguiría funcionando, pero sería innecesariamente `O(n)` en vez de `O(1)`.
- **Desreferenciar `frente` cuando es `null`:** ocasionaría un `NullPointerException`; primero se comprueba si la cola está vacía.
- **Aceptar datos inválidos:** se rechazan nombres vacíos y cantidades de páginas no positivas antes de crear el nodo.

## 11. Mejoras posibles

Para una aplicación de producción se podría modelar `TrabajoImpresion` como un tipo propio, añadir pruebas unitarias automatizadas y persistencia para conservar la cola al cerrar el programa. También sería necesario integrar una biblioteca o servicio de impresión real y definir cómo se gestionan fallos de dispositivo. Estas mejoras quedan fuera del alcance actual, que demuestra la estructura FIFO en memoria.