# Pruebas de `PilaEnteros`

Se compiló `PilaEnteros.java` con `javac` y se ejecutó su método `main`. Todos
los casos terminaron correctamente.

| Caso | Preparación y operación | Resultado esperado | Resultado |
|---|---|---|---|
| Underflow al extraer | Pila vacía; ejecutar `pop()` | Lanza `NoSuchElementException` y conserva tamaño 0 | Aprobado |
| Underflow al consultar | Pila vacía; ejecutar `peek()` | Lanza `NoSuchElementException` y conserva tamaño 0 | Aprobado |
| Overflow con capacidad completa | Capacidad 2; insertar 4 y 8, luego intentar insertar 12 | Lanza `IllegalStateException`; conserva tamaño 2 y tope 8 | Aprobado |
| Inserción y extracción LIFO | Insertar 10, 20 y 30; extraer tres veces | Devuelve 30, 20 y 10; la pila termina vacía | Aprobado |
| Capacidad mínima y reutilización | Capacidad 1; insertar y extraer 42 | Se llena y vuelve a vaciarse correctamente | Aprobado |
| Reutilizar espacio liberado | Capacidad 2; insertar 1 y 2, extraer, insertar 3 | Se acepta 3 y se extrae antes que 1 | Aprobado |

**Conclusión:** las guardas de Underflow y Overflow se ejecutan antes de
modificar el estado de la pila.