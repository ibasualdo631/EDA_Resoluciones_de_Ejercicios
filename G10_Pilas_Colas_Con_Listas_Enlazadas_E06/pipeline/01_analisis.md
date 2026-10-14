# Análisis: cola de atención bancaria

## Objetivo

Definir un sistema de consola que administre la fila de clientes de un banco. Los clientes se atienden en el mismo orden en que llegan; por eso, la estructura central debe ser una cola FIFO (*First In, First Out*). El sistema debe permitir registrar clientes, atender al siguiente, consultar quién sigue e imprimir la fila actual.

## Alcance

- Administrar una única fila de atención en memoria durante la ejecución del programa.
- Representar cada cliente con su nombre, número de turno y motivo de consulta.
- Ofrecer las operaciones de gestión y consulta indicadas en el enunciado.
- Comunicar cuándo una operación no puede realizarse porque la fila está vacía.

No se especifican persistencia entre ejecuciones, múltiples ventanillas, prioridades, cancelación de turnos, autenticación ni una interfaz gráfica; quedan fuera del alcance.

## Actor

- **Empleado del banco:** utiliza el sistema para registrar a las personas que llegan, consultar la fila y llamar al próximo cliente.

## Datos de cliente

| Dato | Descripción |
|---|---|
| Nombre | Identificación del cliente que solicita atención. |
| Número de turno | Número asociado al cliente al incorporarlo a la fila. |
| Motivo de consulta | Descripción del trámite o consulta por la que solicita atención. |

## Casos de uso

### CU-01 — Agregar un cliente a la fila

- **Actor:** Empleado del banco.
- **Precondición:** El cliente tiene nombre y motivo de consulta disponibles.
- **Flujo principal:**
  1. El empleado ingresa el nombre y el motivo de consulta.
  2. El sistema asigna o registra un número de turno.
  3. El sistema agrega al cliente al final de la fila.
  4. El sistema confirma el turno y conserva el orden de llegada.
- **Resultado:** El cliente queda detrás de quienes ya estaban esperando.
- **Regla relacionada:** El alta no modifica el orden de los clientes existentes.

### CU-02 — Atender al próximo cliente

- **Actor:** Empleado del banco.
- **Precondición:** Hay al menos un cliente en la fila.
- **Flujo principal:**
  1. El empleado solicita atender al próximo cliente.
  2. El sistema identifica al cliente que llegó primero.
  3. El sistema informa sus datos y lo retira del frente de la fila.
- **Alternativa:** Si la fila está vacía, el sistema informa que no hay clientes para atender y no modifica la fila.
- **Resultado:** Se atiende y elimina de la fila únicamente al cliente que estaba primero.

### CU-03 — Consultar quién sigue

- **Actor:** Empleado del banco.
- **Flujo principal:**
  1. El empleado consulta el próximo turno.
  2. Si la fila no está vacía, el sistema muestra los datos del cliente ubicado al frente.
- **Alternativa:** Si la fila está vacía, el sistema informa que no hay un próximo cliente.
- **Resultado:** La consulta no retira ni reordena clientes.

### CU-04 — Imprimir la fila actual

- **Actor:** Empleado del banco.
- **Flujo principal:**
  1. El empleado solicita visualizar la fila.
  2. El sistema muestra a los clientes desde el primero en ser atendido hasta el último en llegar, incluyendo nombre, turno y motivo.
- **Alternativa:** Si no hay clientes, el sistema indica que la fila está vacía.
- **Resultado:** La impresión es de solo lectura; no altera la fila.

## Reglas de negocio

1. La atención sigue estrictamente el orden FIFO: quien llega primero es atendido primero.
2. Los nuevos clientes se incorporan al final; la atención y la consulta del siguiente operan sobre el frente.
3. Consultar e imprimir la fila no cambian su contenido ni su orden.
4. Atender una fila vacía no debe producir un cliente inexistente ni alterar el estado.
5. Los números de turno identifican las solicitudes en la fila y deben asignarse de forma consistente. El enunciado no define si el contador se reinicia en cada ejecución o si existe un formato especial.

## Criterios de aceptación

- Es posible agregar clientes con sus tres datos y verlos al final de la fila.
- El cliente incorporado primero se consulta y atiende antes que los demás.
- Atender elimina solamente al cliente que se encontraba al frente.
- Consultar el próximo cliente e imprimir la fila mantienen intacto el orden.
- Las operaciones de atención, consulta e impresión manejan claramente la fila vacía.
- La explicación del sistema relaciona la elección de una cola con el orden cronológico de llegada.

## Supuestos y aspectos no especificados

- El número de turno se asigna al registrar al cliente; el mecanismo exacto queda por definir en el diseño técnico.
- No se indican validaciones de formato ni restricciones sobre nombres y motivos; se asume que se ingresan valores no vacíos.
- No se define un límite de clientes ni una política de prioridades; todos esperan en la misma fila.