# Análisis — Ejercicio 9: Cola de impresión

## Objetivo

Implementar una cola de impresión que administre trabajos pendientes y los procese en el orden en que fueron enviados. El sistema representa una impresora que atiende un trabajo por vez.

## Usuario y alcance

El usuario del sistema puede cargar trabajos y consultar o imprimir los trabajos de la cola. El enunciado no especifica una interfaz, persistencia ni una impresora física; el alcance es administrar y mostrar los trabajos en memoria.

## Datos de un trabajo de impresión

Cada trabajo debe contener:

| Atributo | Significado |
| --- | --- |
| Archivo | Nombre del archivo que se enviará a imprimir. |
| Páginas | Cantidad de páginas del trabajo. |
| Usuario | Persona que envió el trabajo. |

Se espera que el nombre del archivo y el usuario no estén vacíos y que la cantidad de páginas sea un entero positivo.

## Operaciones requeridas

1. **Agregar trabajo:** recibir los datos de un trabajo y colocarlo al final de la cola, detrás de los trabajos pendientes.
2. **Imprimir próximo trabajo:** retirar de la cola e imprimir el trabajo ubicado al frente. La operación debe informar cuál trabajo se procesó. Si no hay trabajos, debe comunicar que la cola está vacía.
3. **Consultar próximo archivo:** informar el nombre del archivo del trabajo al frente sin retirarlo ni alterar el orden de la cola. Si no hay trabajos, debe comunicar que no existe un próximo archivo.
4. **Mostrar trabajos pendientes:** listar todos los trabajos en el orden en que serán atendidos. La consulta no debe modificar la cola.

## Regla de procesamiento: FIFO

La impresora debe usar **FIFO** (*First In, First Out*, primero en entrar, primero en salir): el primer trabajo agregado es el primero que se imprime. Esto respeta el orden de llegada y evita que un trabajo más nuevo adelante a otros que ya estaban esperando. La consulta y el listado son operaciones de observación: no cambian ese orden.

## Casos límite y respuestas esperadas

- Imprimir con la cola vacía: no retirar ningún elemento y comunicar que no hay trabajos pendientes.
- Consultar el próximo archivo con la cola vacía: comunicar que no hay un archivo próximo.
- Listar una cola vacía: indicar que no hay trabajos pendientes.
- Tras imprimir el único trabajo, la cola debe quedar vacía.
- Rechazar datos inválidos del trabajo (archivo o usuario vacíos, o páginas no positivas) antes de agregarlo.

## Criterios de aceptación

- Cada trabajo conserva archivo, cantidad de páginas y usuario.
- Los nuevos trabajos se agregan al final.
- La impresión elimina únicamente el trabajo del frente.
- La consulta del próximo archivo y el listado no eliminan ni reordenan trabajos.
- La cola se procesa estrictamente en orden FIFO y las operaciones sobre una cola vacía se manejan sin errores.