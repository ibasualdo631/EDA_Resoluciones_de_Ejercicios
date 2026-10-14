# Pruebas — Historial de navegación

## Validación ejecutada

Se compiló `HistorialNavegacion.java` con `javac -encoding UTF-8` y se ejecutó un arnés temporal de aserciones contra sus operaciones públicas. También se recorrió el menú con entradas de consola. El arnés y sus archivos temporales se eliminaron al finalizar; no se agregó una segunda clase al ejercicio.

## Casos y resultados

| Caso | Secuencia / condición | Resultado esperado | Resultado |
|---|---|---|---|
| Historial inicial vacío | Consultar la página actual | Devuelve `null`; la consola informa que no hay página actual | Aprobado |
| Retroceso sin visitas | Ejecutar `volverAtras()` con la pila vacía | Devuelve `false` y conserva el historial vacío | Aprobado |
| Imprimir historial vacío | Ejecutar `imprimirHistorial()` sin páginas | Informa que está vacío y no falla | Aprobado |
| Visita vacía o en blanco | Llamar `visitar("  ")` | Lanza `IllegalArgumentException`; no agrega una página | Aprobado |
| Una sola página | Visitar A y volver atrás | Devuelve `false`; A continúa como página actual | Aprobado |
| Varias visitas | Visitar A, B y C; consultar actual | C es la página actual | Aprobado |
| Orden impreso | Imprimir después de visitar A, B y C | Muestra C, B, A (tope a fondo) | Aprobado |
| Impresión no destructiva | Consultar después de imprimir A, B y C | C sigue siendo la página actual | Aprobado |
| Retrocesos sucesivos | Con A, B y C, volver atrás repetidamente | C → B → A; el siguiente retroceso devuelve `false` y mantiene A | Aprobado |
| Menú por consola | Intentar retroceder vacío; visitar páginas; consultar, imprimir y volver | Mensajes de límite correctos, orden reciente-a-antigua y terminación normal | Aprobado |

## Resultado

La compilación y todas las aserciones finalizaron correctamente. Los escenarios límite verifican que no se pierde ni cambia el historial al intentar retroceder cuando no existe una página anterior, y que la impresión solo recorre la pila.