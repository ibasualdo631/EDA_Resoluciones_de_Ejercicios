# Pruebas — Sistema de turnos

## Objetivo

Verificar que la atención respeta estrictamente el orden de llegada (FIFO) y
que intentar atender una cola vacía produce un error explícito sin corromper
el estado. También se comprueba que la cola puede volver a usarse después de
quedar vacía.

## Casos de prueba

| ID | Caso | Procedimiento | Resultado esperado |
|---|---|---|---|
| P01 | Orden FIFO con llegadas intercaladas | Registrar Ana y Bruno; atender; registrar Carla; atender dos veces más. | Se atienden Ana, Bruno y Carla, en ese orden; Carla no adelanta a Bruno aunque llegó después de la primera atención. |
| P02 | Intento de atención sin turnos | En un sistema nuevo, invocar `atenderSiguiente()`. | Lanza `NoSuchElementException` con el mensaje «No se puede atender: no hay clientes esperando.»; no devuelve `null`. |
| P03 | Estado tras atender una cola vacía | Después de P02, consultar `estaVacio()` y `obtenerCantidadPendiente()`. | El sistema sigue vacío y la cantidad permanece en 0. |
| P04 | Consulta del frente vacío | En un sistema nuevo, invocar `consultarSiguiente()`. | Lanza `NoSuchElementException` y mantiene la cola vacía. |
| P05 | Drenado y reutilización | Registrar y atender todos los turnos; luego registrar Diego y atenderlo. | La cola vuelve a quedar vacía tras el drenado; Diego puede agregarse y atenderse correctamente después. |
| P06 | Listado FIFO y aislado | Con Bruno y Carla pendientes, obtener `listarPendientes()` y modificar esa lista. | La copia enumera Bruno antes que Carla; modificarla no cambia los elementos ni la cantidad de la cola. |
| P07 | Simulación incluida | Ejecutar el `main` de `SistemaTurnos`. | Se muestran Ana, Bruno, Carla y Diego en orden de llegada/atención, y la cantidad final pendiente es 0. |

## Verificación ejecutada

Se compiló `SistemaTurnos.java` junto con un arnés temporal de aserciones usando
`javac -Xlint:all`; se ejecutaron P01–P06 y luego la simulación de P07. Todas
las aserciones pasaron, ambas operaciones sobre la cola vacía lanzaron la
excepción prevista y el proceso terminó con código 0. El arnés temporal no se
conservó en el repositorio.

## Contrato de cola vacía

`atenderSiguiente()` no oculta el error con un valor nulo ni modifica la cola:
lanza `NoSuchElementException`. El llamador puede manejar esa excepción; la
simulación incluida evita el caso consultando `estaVacio()` antes de cada
atención. La excepción y su mensaje quedan cubiertos por P02.