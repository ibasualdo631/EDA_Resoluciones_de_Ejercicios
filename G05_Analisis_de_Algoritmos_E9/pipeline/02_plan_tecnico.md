# Plan técnico: búsqueda del mayor elemento de una matriz

## 1. Objetivo

Implementar en Java la clase `ElementoMayorMatriz`, responsable de recorrer una
matriz de enteros y devolver su valor máximo. La solución debe ser correcta
para matrices rectangulares e irregulares, no modificar la entrada y explicitar
el comportamiento ante entradas sin elementos.

## 2. Contrato de la solución

### Clase y método

```java
public class ElementoMayorMatriz {
    public static int buscarMayor(int[][] matriz) {
        // Implementación
    }
}
```

El método será `static` porque no necesita conservar estado entre llamadas.
Recibe una matriz `int[][]` y devuelve un `int` que necesariamente debe estar
presente en la matriz.

### Precondición y excepciones

- Si `matriz` es `null`, lanzar `IllegalArgumentException` con un mensaje claro.
- Si la matriz no contiene ningún entero —porque no tiene filas, todas sus
  filas están vacías o sus filas son `null`—, lanzar
  `IllegalArgumentException`.
- Las filas `null` se tratarán como filas vacías y se omitirán. Esta decisión
  permite trabajar con matrices irregulares parcialmente inicializadas. Si
  después de omitirlas no se encuentra ningún valor, se aplica la excepción
  por ausencia de elementos.
- No capturar ni ocultar excepciones: el método debe reportar directamente la
  entrada inválida mediante `IllegalArgumentException`.

### Garantías

- Si termina normalmente, devuelve el mayor de todos los elementos.
- No ordena ni modifica la matriz recibida.
- Admite valores negativos, incluido `Integer.MIN_VALUE`, y no usa cero como
  valor inicial asumido para el máximo.

## 3. Organización y algoritmo

La solución se concentra en una clase y un método; no requiere estructuras de
datos auxiliares ni dependencias externas.

1. Validar que la referencia de matriz no sea `null`.
2. Inicializar una variable `encontrado` en `false`. La variable `mayor` puede
   inicializarse con cualquier `int`, por ejemplo `0`, pero su valor solo se
   usará después de asignarle el primer elemento real.
3. Recorrer las filas mediante un ciclo externo con índice `i`, desde `0` hasta
   `matriz.length - 1`.
4. Guardar o consultar la fila actual. Si es `null`, continuar con la siguiente
   fila.
5. Recorrer las columnas de esa fila mediante un ciclo interno con índice `j`,
   desde `0` hasta `matriz[i].length - 1`. Usar la longitud de la fila actual,
   no la de la primera fila, para admitir matrices irregulares.
6. Para cada valor:
   - Si todavía no se encontró un elemento, asignarlo a `mayor` y marcar
     `encontrado = true`.
   - En caso contrario, si el valor es mayor que `mayor`, actualizar `mayor`.
7. Al terminar ambos ciclos, si `encontrado` sigue en `false`, lanzar
   `IllegalArgumentException`, porque no existe un máximo que devolver.
8. De lo contrario, devolver `mayor`.

### Pseudocódigo

```text
buscarMayor(matriz):
    si matriz es null:
        lanzar IllegalArgumentException

    encontrado = false
    para i desde 0 hasta matriz.length - 1:
        si matriz[i] es null:
            continuar
        para j desde 0 hasta matriz[i].length - 1:
            valor = matriz[i][j]
            si no encontrado o valor > mayor:
                mayor = valor
                encontrado = true

    si no encontrado:
        lanzar IllegalArgumentException
    devolver mayor
```

## 4. Justificación del recorrido completo

El algoritmo debe inspeccionar todos los elementos en el caso general. Si
dejara un elemento sin revisar, ese valor podría ser mayor que el máximo
observado hasta ese momento. Sin información adicional sobre el orden o el
contenido de la matriz, no es posible descartar esa posibilidad. Por ello, la
solución compara cada elemento y no termina anticipadamente al hallar un valor
grande.

## 5. Complejidad asintótica

Sean `F` la cantidad de filas y `E` la cantidad total de elementos
pertenecientes a filas no nulas.

| Caso | Tiempo | Motivo |
|---|---|---|
| Mejor | `Θ(F + E)` | Se recorren todas las filas y todos sus elementos, incluso si el primer valor ya es el máximo. |
| Promedio | `Θ(F + E)` | La cantidad de iteraciones no depende de la distribución de los valores; todos los elementos se comparan. |
| Peor | `Θ(F + E)` | Se recorren todas las filas y todos los elementos antes de devolver el resultado. |

Para una matriz rectangular no vacía con `F` filas y `C` columnas, `E = F * C`,
por lo que el tiempo es `Θ(F * C)`. Las entradas inválidas también requieren
como máximo recorrer las filas para confirmar que no hay elementos; una matriz
`null` se rechaza en tiempo constante.

El espacio auxiliar es `Θ(1)`: se usan únicamente índices y unas pocas
variables escalares, sin crear copias ni colecciones.

## 6. Plan de pruebas

Probar el contrato y los casos representativos siguientes:

| Entrada | Resultado esperado |
|---|---|
| `{{1, 8, 3}, {2, 5, 4}}` | Devuelve `8`. |
| `{{-9, -2}, {-7, -4}}` | Devuelve `-2`, validando que no se inicialice el máximo a cero. |
| `{{Integer.MIN_VALUE}}` | Devuelve `Integer.MIN_VALUE`. |
| `{{1, 20}, {}, {3, 4, 5}}` | Devuelve `20`; ignora la fila vacía y procesa filas irregulares. |
| `{{1, 2}, null, {-3}}` | Devuelve `2`; omite la fila nula. |
| Matriz con cero filas (`new int[0][]`) | Lanza `IllegalArgumentException`. |
| Matriz con una o más filas vacías | Lanza `IllegalArgumentException` si no hay otros elementos. |
| Matriz con filas `null` únicamente | Lanza `IllegalArgumentException`. |
| Referencia `null` | Lanza `IllegalArgumentException`. |
| Matriz con máximo en la primera, intermedia o última posición | Devuelve ese máximo en todos los casos. |

En los casos válidos, verificar también que la matriz conserve sus valores y
dimensiones tras la llamada. Para las excepciones, comprobar el tipo; el texto
exacto del mensaje no necesita ser parte de la prueba, salvo que el proyecto
establezca esa convención.

## 7. Instrucciones para el desarrollador

1. Crear `ElementoMayorMatriz` en el paquete y la ubicación que utilice el
   proyecto; si no hay paquete existente, dejar la clase en el paquete por
   defecto para respetar la estructura actual.
2. Implementar exactamente el contrato `public static int buscarMayor(int[][] matriz)`.
3. Rechazar la matriz `null` con `IllegalArgumentException` antes de acceder a
   sus filas.
4. Usar ciclos anidados `i` y `j`; el ciclo interior debe usar la longitud de
   la fila actual. Omitir filas `null` y vacías.
5. Inicializar el máximo desde el primer entero encontrado usando una marca
   booleana; no asumir que los valores son positivos.
6. Si no se visitó ningún entero, lanzar `IllegalArgumentException`.
7. Mantener la implementación de una sola pasada, sin ordenar ni modificar los
   datos.
8. Agregar pruebas unitarias para los casos de la sección anterior siguiendo
   el framework y las convenciones de pruebas ya presentes en el proyecto.
9. Ejecutar las pruebas pertinentes y documentar en la explicación final el
   recorrido por filas/columnas, la razón de recorrer todos los elementos y
   las cotas temporales y espaciales de la sección 5.