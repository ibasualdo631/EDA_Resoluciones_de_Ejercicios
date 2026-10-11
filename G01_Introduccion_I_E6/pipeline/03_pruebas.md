# Matriz de casos de prueba

## 1. Objetivo

Validar que el programa `MenuOpciones` cumple con la consigna del ejercicio: mostrar un menú de opciones, leer una selección del usuario y responder según la opción elegida utilizando `switch`.

## 2. Alcance

Se probarán los casos principales del flujo del programa:
- opciones válidas del 1 al 4,
- opción inválida fuera del rango,
- entradas no numéricas,
- validación del mensaje mostrado por consola.

## 3. Casos de prueba

| ID | Descripción | Entrada | Resultado esperado | Estado |
|----|-------------|---------|-------------------|--------|
| TC-01 | Opción válida 1 | `1` | Se muestra: `Elegiste la opción 1.` | Correcto |
| TC-02 | Opción válida 2 | `2` | Se muestra: `Elegiste la opción 2.` | Correcto |
| TC-03 | Opción válida 3 | `3` | Se muestra: `Elegiste la opción 3.` | Correcto |
| TC-04 | Opción válida 4 | `4` | Se muestra: `Elegiste la opción 4.` | Correcto |
| TC-05 | Opción inválida menor al rango | `0` | Se muestra: `Opción inválida. Debe elegir un número entre 1 y 4.` | Correcto |
| TC-06 | Opción inválida mayor al rango | `5` | Se muestra: `Opción inválida. Debe elegir un número entre 1 y 4.` | Correcto |
| TC-07 | Entrada negativa | `-1` | Se muestra: `Opción inválida. Debe elegir un número entre 1 y 4.` | Correcto |
| TC-08 | Entrada no numérica | `abc` | Se muestra: `Entrada inválida. Debe ingresar un número.` y luego solicita nuevamente la entrada | Correcto |
| TC-09 | Entrada mixta no válida seguida de válida | `x`, `2` | Primero informa `Entrada inválida...` y luego ejecuta `Elegiste la opción 2.` | Correcto |
| TC-10 | Verificación del menú | sin entrada | El programa imprime las opciones 1, 2, 3 y 4 antes de solicitar la selección | Correcto |

## 4. Resultado de validación

Se verificó que el programa cumple con las condiciones principales del ejercicio:
- presenta el menú
- captura la entrada del usuario
- usa `switch` para decidir la acción
- maneja opciones válidas e inválidas
- evita que la ejecución falle ante entrada no numérica

## 5. Observaciones

La implementación actual incluye una validación adicional para detectar entradas no numéricas, lo cual mejora la robustez del programa sin romper la lógica del ejercicio.

## 6. Conclusión

La matriz de pruebas confirma que el software responde correctamente ante los casos esperados del ejercicio y cumple con la funcionalidad pedida por la consigna.
