# Plan técnico — Ejercicio 8: sumar todos los elementos de una matriz

## Objetivo de implementación

Implementar en Java una solución que recorra una matriz de enteros por filas y columnas, devuelva la suma total junto con los contadores de operaciones y documente su complejidad.

## Organización y tipos

- Crear `SumaMatriz.java` con la clase `SumaMatriz` y el método público estático:
  ```java
  public static ResultadoSuma sumar(int[][] matriz)
  ```
- Crear `ResultadoSuma.java` con un DTO inmutable que exponga:
  - `long suma`
  - `long lecturas`
  - `long sumas`
- Proveer constructor y accesores claros (por ejemplo, `getSuma()`, `getLecturas()` y `getSumas()`). No acoplar el algoritmo a la salida por consola: quien invoque el método decide cómo presentar los datos.
- Si el proyecto ya define un paquete, ubicar las clases allí y mantenerlas en archivos separados con el paquete correspondiente. Si no existe, usar el paquete por defecto para ajustarse a la estructura del ejercicio.

## Algoritmo y secuencia

1. Al inicio de `sumar`, validar la referencia:
   - Si `matriz == null` o `matriz.length == 0`, lanzar `IllegalArgumentException` con un mensaje que identifique el argumento inválido.
   - Una matriz con una o más filas vacías sí es válida; producir suma, lecturas y sumas acumuladas iguales a cero cuando no contenga elementos.
2. Inicializar en cero la suma y los dos contadores, todos de tipo `long`.
3. Recorrer las filas con el ciclo externo (`i`) y las columnas de cada fila usando `matriz[i].length` en el ciclo interno (`j`). Así se admiten filas de diferentes longitudes.
4. En cada iteración, leer la celda una sola vez en una variable local `int valor = matriz[i][j]`, incrementar `lecturas` en uno, agregar `valor` a `suma` y luego incrementar `sumas` en uno.
5. Crear y devolver un `ResultadoSuma` con los tres valores. No imprimir dentro del método.

## Convención de conteo

- Cada evaluación efectiva de `matriz[i][j]` cuenta como una lectura. Guardar la celda en una variable local evita accesos duplicados.
- Cada ejecución de la adición `suma += valor` cuenta como una suma acumulada, incluida la adición del primer elemento a cero.
- No sumar al contador las condiciones o avances de los ciclos, accesos a `.length`, inicializaciones ni construcción del DTO.
- Para `N` celdas, los resultados esperados son `lecturas == N` y `sumas == N`.

## Complejidad a documentar

- Sea `R` la cantidad de filas y `N` la cantidad total de celdas. La implementación recorre cada fila y cada celda una sola vez: tiempo `O(R + N)` y espacio auxiliar `O(1)`.
- Para una matriz no vacía con `N` elementos, esto se expresa como peor caso `O(N)` y mejor caso `Ω(N)`: aun en el caso más favorable se inspeccionan todos los elementos.
- Para que las cotas también describan matrices con filas vacías, tomar el tamaño de entrada como `N_total = R + N` (filas más celdas). Entonces el recorrido es `Θ(N_total)`, que puede expresarse como peor caso `O(N_total)` y mejor caso `Ω(N_total)`. Aclarar esta convención; `Ω(N)` no describe la cantidad de celdas si `N` se define solo como número de celdas y la matriz puede tener cero celdas.
- El espacio es `O(1)`: solo se mantienen acumuladores, contadores, índices y una variable local, independientemente del tamaño de la matriz.

## Instrucciones directas para el Desarrollador

1. Implementá las clases `SumaMatriz` y `ResultadoSuma` respetando los tipos y la firma indicados.
2. Hacé que `ResultadoSuma` sea inmutable y que permita recuperar la suma y ambos contadores.
3. Implementá la validación de `null` y cero filas lanzando `IllegalArgumentException`; no trates estos casos como matrices vacías válidas.
4. Aceptá filas vacías dentro de una matriz con filas y evitá asumir una matriz rectangular.
5. Usá dos ciclos anidados y leé cada elemento una sola vez en una variable local antes de sumarlo.
6. Devolvé el resultado y los contadores; no uses impresión por consola desde `sumar`.
7. Añadí o actualizá las pruebas para verificar:
   - una matriz rectangular con positivos y negativos;
   - una sola celda;
   - `null` y una matriz de cero filas, que deben lanzar `IllegalArgumentException`;
   - una matriz con filas vacías, incluida una matriz cuyas filas estén todas vacías, que debe retornar ceros;
   - filas de diferentes longitudes y los conteos exactos `N` lecturas y `N` sumas.
8. En la explicación final, describí el recorrido, la definición de los contadores, las excepciones/casos borde y las cotas temporales y espaciales con la definición de `N` claramente indicada.