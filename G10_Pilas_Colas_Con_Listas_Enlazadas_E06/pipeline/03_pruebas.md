# Pruebas: cola de atención bancaria

## Resultado general

**Estado: APROBADO.** Se compiló y ejecutó el programa Java con entradas simuladas por consola. Las tres simulaciones finalizaron con código de salida `0` y pasaron las verificaciones indicadas.

## Entorno y ejecución

- **Versión de compilador:** `javac 21.0.10`.
- **Compilación:** `javac -encoding UTF-8 -d <directorio-temporal> SistemaBancario.java`.
- **Ejecución:** `java -cp <directorio-temporal> SistemaBancario`, suministrando las opciones y los datos por entrada estándar.
- **Aplicación:** consola local; no requiere base de datos, servicios externos ni navegador.
- Las clases compiladas se guardaron temporalmente fuera del proyecto y se eliminaron al concluir las pruebas.

## Casos ejecutados

| ID | Flujo simulado | Verificaciones | Resultado |
|---|---|---|---|
| P-01 | Con la fila vacía, consultar quién sigue, atender, imprimir y salir. | Se reciben los mensajes correspondientes a fila vacía en las tres operaciones y el menú permite salir normalmente. | **APROBADO** |
| P-02 | Agregar Ana, Bruno y Carla; consultar y mostrar la fila; atenderlos; consultar la fila ya vacía; agregar y atender a Diego. | La consulta y la impresión muestran primero a Ana; la impresión mantiene el orden Ana-Bruno-Carla; la atención conserva ese orden; la fila vuelve a manejarse después de quedar vacía; Diego recibe turno 4. | **APROBADO** |
| P-03 | Ingresar una opción de menú inválida y luego intentar registrar un cliente con nombre y motivo vacíos antes de proporcionar valores válidos. | Se informa la opción inválida, se rechazan ambos campos vacíos sin crear un turno prematuro, y Marta queda registrada con el primer turno. | **APROBADO** |

## Evidencia de consola

| Ejecución | Resultado observado | Código de salida |
|---|---|---:|
| Compilación Java | Sin errores. | `0` |
| P-01: fila vacía | Consulta, atención e impresión informan que no hay clientes; salida normal. | `0` |
| P-02: flujo FIFO | Turnos atendidos en orden `1, 2, 3`; después de vaciar la fila se agrega y atiende el turno `4`. | `0` |
| P-03: entradas inválidas | La opción desconocida y los campos vacíos se rechazan; luego se registra el cliente. | `0` |

## Observaciones y alcance

- La validación fue una prueba funcional de extremo a extremo mediante entradas simuladas a la aplicación de consola; no se añadió un framework de pruebas al ejercicio.
- La comprobación cubre el uso normal, las transiciones de fila vacía/no vacía y la validación básica de entradas. No prueba persistencia entre ejecuciones, ya que no forma parte del alcance.