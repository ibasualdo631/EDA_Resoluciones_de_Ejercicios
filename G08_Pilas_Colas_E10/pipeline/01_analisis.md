# Análisis — Ejercicio 10: Cola circular

## Objetivo

Implementar una cola circular de capacidad fija utilizando un arreglo. Al quitar elementos del frente, sus posiciones deben poder reutilizarse al insertar nuevos elementos, evitando desplazar el contenido del arreglo.

## Requerimientos funcionales

- Insertar elementos al final lógico de la cola.
- Quitar elementos desde el frente lógico de la cola, respetando el orden FIFO (primero en entrar, primero en salir).
- Consultar el elemento del frente sin quitarlo, si la interfaz del ejercicio contempla esa operación.
- Detectar y comunicar los estados lleno y vacío antes de realizar operaciones que no sean válidas.
- Reutilizar las posiciones liberadas mediante avance circular de los índices.

## Representación propuesta

Usar un arreglo de capacidad fija `N`, un índice `front` que apunta al primer elemento válido, un índice `rear` que apunta a la próxima posición disponible para insertar y un contador `count` de elementos almacenados. Se requiere `N > 0`.

El contador permite distinguir los estados sin ambigüedad:

- Vacía: `count == 0`.
- Llena: `count == N`.

No conviene decidir ambos estados únicamente comparando `front` y `rear`: con esta convención pueden coincidir tanto cuando no hay elementos como cuando se han ocupado todas las posiciones. Mantener `count` resuelve esa ambigüedad.

## Avance cíclico con módulo

Después de usar una posición, se avanza el índice con:

```text
índice = (índice + 1) % N
```

El operador `%` calcula el resto de la división. Como los índices válidos van de `0` a `N - 1`, al avanzar desde `N - 1` se obtiene `(N) % N == 0`: el puntero vuelve al inicio del arreglo. Así se reutilizan los espacios que quedaron libres sin mover los demás elementos.

Con la representación propuesta:

- Insertar: si `count == N`, informar que está llena; en otro caso, guardar el elemento en `arreglo[rear]`, actualizar `rear = (rear + 1) % N` e incrementar `count`.
- Quitar: si `count == 0`, informar que está vacía; en otro caso, obtener el elemento de `arreglo[front]`, actualizar `front = (front + 1) % N` y disminuir `count`.
- Consultar el frente: si está vacía, informar que no hay elemento; de lo contrario, devolver `arreglo[front]` sin cambiar índices ni contador.

Por ejemplo, con `N = 5`, si `rear` vale `4`, luego de insertar pasa a `(4 + 1) % 5 = 0`. La inserción siguiente puede usar la posición inicial si quedó libre por una extracción anterior.

## Casos límite y validaciones

- Rechazar o manejar explícitamente una capacidad no positiva, según las convenciones del proyecto.
- Intentar quitar o consultar el frente estando vacía.
- Intentar insertar estando llena.
- Insertar hasta llenar, quitar uno o más elementos y volver a insertar para comprobar que se reciclan las posiciones iniciales.
- Probar el avance de ambos índices desde `N - 1` hasta `0`.
- Comprobar que una cola con un solo elemento pasa correctamente a vacía al quitarlo y vuelve a funcionar al insertar otro.
- Verificar que las operaciones mantienen el orden FIFO tanto antes como después de que los índices den la vuelta.

## Supuestos para el plan

- La capacidad es fija durante la vida de la cola.
- La estructura almacena elementos del tipo y con la interfaz que ya use el proyecto; no se debe inventar otro lenguaje ni cambiar convenciones existentes.
- Los errores por cola llena/vacía deben seguir el mecanismo de salida o manejo de errores usado en el proyecto (por ejemplo, mensajes o valores de retorno), a definir al inspeccionar el código.
- El enunciado no especifica mensajes exactos ni una operación de impresión; conservar la interfaz existente o proponerlos en el plan solo si el ejercicio los requiere.

## Prompt inicial para el planificador

> Analiza el Ejercicio 10 e inspecciona primero el proyecto para identificar el lenguaje, la estructura de archivos, las convenciones y la interfaz que ya se espera. Diseña un plan técnico para implementar una cola circular de capacidad fija usando un arreglo, sin desplazar elementos y reutilizando las posiciones liberadas al inicio.
>
> Usa `front` para señalar el primer elemento válido, `rear` para señalar la próxima posición disponible para insertar y un contador `count` para distinguir sin ambigüedad los estados. La cola está vacía cuando `count == 0` y llena cuando `count == capacidad`. Explica en el plan que cada índice avanza con `(índice + 1) % capacidad`; ilustra cómo el módulo hace que el índice pase de `capacidad - 1` a `0`.
>
> Incluye el flujo de inserción, extracción y consulta del frente si corresponde a la interfaz existente; indica cómo manejar inserciones en una cola llena y extracciones/consultas en una vacía, siguiendo las convenciones del proyecto. Considera capacidad no positiva, cola de un elemento, llenado, vaciado, vuelta de ambos índices y reutilización efectiva de espacios. Propón pruebas que demuestren FIFO y el comportamiento circular. No escribas todavía la implementación: entrega pasos concretos, archivos que habría que modificar y criterios de aceptación, respetando el alcance del enunciado y sin agregar funcionalidades no solicitadas.