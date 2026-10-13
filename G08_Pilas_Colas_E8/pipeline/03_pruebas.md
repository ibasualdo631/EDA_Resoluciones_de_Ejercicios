# Informe de pruebas — Cola de impresión

## Alcance

Auditoría de [ColaImpresion.java](../ColaImpresion.java) respecto del orden FIFO de los documentos y el manejo de una cola vacía, según [01_analisis.md](./01_analisis.md).

## Casos ejecutados

| Caso | Resultado |
|---|---|
| Una cola recién creada informa que está vacía. | Correcto |
| Procesar una cola vacía devuelve `Optional.empty()` y no cambia su estado. | Correcto |
| Encolar `Informe (10 páginas)`, `Apuntes (1 página)` y `TP (5 páginas)` y procesarlos conserva el orden de llegada, sin priorizar por páginas. | Correcto |
| Procesar después de retirar todos los documentos sigue devolviendo vacío y no altera el estado. | Correcto |
| Encolar y procesar un documento después de vaciar la cola funciona; los punteros de frente y final quedan consistentes. | Correcto |
| Intentar encolar `null` produce `IllegalArgumentException`. | Correcto |
| Ejecutar el `main` muestra los tres documentos procesados en orden y comunica que no quedan documentos pendientes. | Correcto |

## Verificación

Se compiló `ColaImpresion.java` junto con un arnés temporal de aserciones y se ejecutaron tanto el arnés como el `main`. Todas las aserciones pasaron. Los archivos temporales de prueba se eliminaron al finalizar.

## Conclusión

No se observaron alteraciones del orden FIFO ni fallos al procesar una cola vacía o reutilizarla tras vaciarla. No fue necesario modificar el código fuente.