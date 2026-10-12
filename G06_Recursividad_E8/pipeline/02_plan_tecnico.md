# Plan técnico — Inversión recursiva de un `String`

## Contrato del método

Implementar el método estático:

```java
public static String invertir(String cadena)
```

Importar `java.util.Objects` para validar el argumento con `requireNonNull`.

- **Entrada:** una referencia a `String`.
- **Salida:** los mismos caracteres en orden inverso, sin modificar espacios,
  signos ni mayúsculas/minúsculas.
- **`null`:** rechazarlo explícitamente al inicio con
  `Objects.requireNonNull(cadena, "cadena no puede ser null")`. Así se obtiene
  un error inmediato y descriptivo, en vez de un `NullPointerException` tardío
  al consultar la longitud. `null` no se interpreta como cadena vacía.
- **Restricción:** resolver la inversión con recursión; no usar ciclos ni una
  utilidad de biblioteca que invierta la cadena.

## Organización y secuencia

Mantener la solución en la clase existente del ejercicio y respetar su estilo.
No crear estructuras auxiliares ni cambiar la interfaz de consola si ya existe.
El método sigue esta secuencia:

1. Validar `cadena` con `Objects.requireNonNull`.
2. Si `cadena.length() <= 1`, devolver `cadena`. Esto cubre `""` y las cadenas
   de un carácter.
3. Guardar el primer carácter con `cadena.charAt(0)` y el resto con
   `cadena.substring(1)`.
4. Devolver `invertir(resto) + primerCaracter`. La llamada procesa una cadena
   un carácter más corta y la concatenación agrega el carácter reservado al
   final al desenrollar la recursión.

La recursión termina porque la longitud disminuye en uno en cada llamada hasta
llegar a cero o uno.

## Estructuras y estado

- Usar únicamente variables locales: `resto` (`String`) y `primerCaracter`
  (`char`).
- No hace falta arreglo, colección ni estado compartido.
- La pila de llamadas conserva los caracteres separados hasta reconstruir la
  respuesta.

## Costo asintótico

Para una entrada de longitud `n >= 1` hay `n` llamadas (y una para `n = 0`),
por lo que la profundidad de recursión y el espacio de pila son **O(n)**.

La reconstrucción `invertir(resto) + primerCaracter` **no cuesta O(1)**:
`String` es inmutable y cada concatenación debe producir una cadena nueva y
copiar sus caracteres. Al retornar se construyen resultados de longitudes
aproximadas `1, 2, ..., n`; la suma es **O(n²)** tiempo y **O(n²)** caracteres
copiados/asignados en total. En las versiones modernas de Java,
`substring(1)` también copia el segmento restante; la suma de esos segmentos
es asimismo **O(n²)**. Por lo tanto, la implementación indicada es **O(n²)**
en tiempo. La pila usa **O(n)** espacio y se asignan **O(n²)** caracteres
temporales en total; el pico de memoria de esas cadenas depende de la retención
de referencias y del recolector, y puede ser **O(n²)** en implementaciones
modernas de Java que copian las subcadenas.

Aunque un auxiliar con `StringBuilder` podría reducir el trabajo de copia, no
se propone aquí porque la consigna pide mostrar la reconstrucción recursiva
mediante concatenación. El costo cuadrático debe considerarse aceptable para
el tamaño didáctico del ejercicio.

## Verificación prevista

Comprobar los casos sugeridos en el análisis: `""` → `""`, `"a"` → `"a"`,
`"sol"` → `"los"`, `"reconocer"` → `"reconocer"` y `"a b"` → `"b a"`.
Agregar además una comprobación de que pasar `null` produce la excepción
descriptiva definida en el contrato.