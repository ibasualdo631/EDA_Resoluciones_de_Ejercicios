# Pruebas — Ejercicio 4: Inserción en posición específica

## Ejecución

- **Archivo probado:** `ListaEnlazadaSimple.java`
- **Compilación:** `javac -d <directorio-temporal> ListaEnlazadaSimple.java`
- **Ejecución:** `java -cp <directorio-temporal> ListaEnlazadaSimple`
- **Resultado:** compilación y ejecución exitosas; el programa terminó con código de salida `0`.

## Casos ejecutados

| Caso | Entrada / acción | Resultado esperado | Resultado |
|---|---|---|---|
| Inserción al inicio de lista no vacía | Insertar `5` en posición `0` en `[10, 20, 30]` | `[5, 10, 20, 30]`, tamaño `4` | Aprobado |
| Inserción en el medio | Insertar `15` en posición `2` | `[5, 10, 15, 20, 30]`, tamaño `5` | Aprobado |
| Inserción al final | Insertar `40` en posición igual al tamaño actual (`5`) | `[5, 10, 15, 20, 30, 40]`, tamaño `6` | Aprobado |
| Índice negativo | Insertar en posición `-1` | Lanza `IndexOutOfBoundsException`; contenido y tamaño permanecen iguales | Aprobado |
| Índice mayor al tamaño | Insertar en posición `size + 1` (`7`) | Lanza `IndexOutOfBoundsException`; contenido y tamaño permanecen iguales | Aprobado |
| Lista vacía | Insertar `7` en posición `0` | `[7]`, tamaño `1` | Aprobado |

## Verificaciones realizadas

- Cada inserción válida aumenta `size` exactamente una vez y coincide con la cantidad de nodos recorridos.
- Los rechazos se producen antes de modificar los enlaces y dejan intactos el tamaño y la secuencia.
- Los recorridos coinciden exactamente con los valores esperados y terminan en `null`, detectando elementos omitidos, extras o ciclos en los casos probados.
- En inserciones después de la cabeza, se conserva el orden de reconexión: primero `nuevo.siguiente = actual.siguiente`; después `actual.siguiente = nuevo`.

## Salida observada

```text
Insercion al inicio: correcto
Insercion en el medio: correcto
Insercion al final: correcto
Indice negativo: correcto
Indice mayor al tamano: correcto
Insercion en lista vacia: correcto
Lista final: 5 -> 10 -> 15 -> 20 -> 30 -> 40
Todas las pruebas finalizaron correctamente.
```

## Alcance

Estas pruebas se ejecutaron desde el `main` incluido en la clase; no se utilizó un framework de pruebas externo.
