# Pruebas — Cola de impresión

## Ejecución

Se compiló `ColaImpresion.java` con `javac` y se ejecutó una simulación automatizada del menú de consola. La validación comprobó el orden de las salidas y el mensaje del caso vacío.

## Casos probados

| Caso | Pasos | Resultado esperado | Resultado |
| --- | --- | --- | --- |
| Envíos e impresiones consecutivas | Agregar `alpha.pdf` (2 páginas, Ana), `beta.pdf` (5 páginas, Luis) y `gamma.pdf` (1 página, Marta); luego solicitar tres impresiones consecutivas. | Imprimir alpha, beta y gamma en ese mismo orden, sin perder ni duplicar trabajos. | **Aprobado.** Los tres trabajos se imprimieron en orden FIFO. |
| Impresión sin trabajos pendientes | Después de imprimir los tres trabajos anteriores, solicitar una cuarta impresión. | No producir un trabajo y mostrar que no hay trabajos pendientes. | **Aprobado.** La operación informó que no había trabajos pendientes. |

## Resultado general

Compilación y simulación completadas correctamente. La ejecución confirma el procesamiento FIFO y el manejo del intento de impresión sobre una cola vacía.