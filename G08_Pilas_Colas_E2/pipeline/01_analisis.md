# Análisis — Ejercicio 2: Simulador de torre de platos

## Objetivo

Definir el comportamiento de un programa que simule una torre de platos y permita agregar un plato, retirar el plato superior y consultar qué plato está arriba. El ejercicio busca aplicar una estructura de datos LIFO (Last In, First Out: último en entrar, primero en salir).

## Justificación de la estructura

La torre se comporta como una pila porque los platos se agregan y se manipulan por un único extremo: la parte superior. Al colocar un plato nuevo, este queda encima de los anteriores y los tapa. Por eso, para retirar un plato sin desarmar la torre, necesariamente se quita primero el que se colocó más recientemente.

Ejemplo: al agregar `A`, luego `B` y finalmente `C`, la torre queda (de abajo hacia arriba) `A`, `B`, `C`. El plato superior es `C`; al retirarlo, queda `B` arriba y es el siguiente que puede retirarse. El orden de retiro es `C`, `B`, `A`.

Una cola no representa esta regla: una cola aplica FIFO (First In, First Out) y retiraría primero `A`, el elemento más antiguo. Eso no corresponde a una torre física de platos, ya que para acceder al plato de abajo habría que mover primero los que están encima. En consecuencia, la estructura adecuada es una pila con operaciones equivalentes a insertar arriba (*push*), consultar el tope (*peek*) y retirar el tope (*pop*).

## Requerimientos funcionales

1. Representar cada plato mediante un número o un nombre.
2. Agregar un plato a la parte superior de la torre.
3. Consultar el plato que está en la parte superior sin retirarlo.
4. Retirar el plato superior.
5. Mantener el orden LIFO entre las operaciones.

## Reglas y casos límite

- Consultar o retirar cuando la torre está vacía debe producir una respuesta controlada e informativa; no existe un plato superior que pueda consultarse o retirarse.
- Retirar un plato debe actualizar el tope para que pase a ser el plato colocado inmediatamente antes.
- Consultar el tope no debe modificar la torre.
- No se especifican en la consigna un límite de capacidad, una interfaz, un lenguaje de programación ni restricciones sobre el formato de nombres o números. El planificador debe respetar la tecnología ya usada en el repositorio; si no existe una, debe indicar el supuesto de implementación en vez de atribuirlo a la consigna.

## Criterios de aceptación

- Tras agregar `A`, `B` y `C`, la consulta informa `C`.
- Una consulta no cambia el contenido ni el orden de la torre.
- El primer retiro devuelve `C`; el siguiente, `B`; el último, `A`.
- Después del último retiro, consultar o retirar vuelve a informar que la torre está vacía, sin provocar un error no controlado.
- La explicación del diseño distingue explícitamente LIFO de FIFO y justifica por qué una cola no modela el acceso físico a los platos.

## Prompt inicial para el planificador

> Analiza la consigna «Ejercicio 2: Simulador de torre de platos» y prepara un plan técnico para implementarla en el lenguaje y con las convenciones que ya utilice el repositorio. El programa debe representar cada plato con un número o un nombre y permitir agregar un plato arriba, consultar el plato superior sin modificar la torre y retirar el plato superior. Modela la torre como una pila LIFO: si se agregan A, B y C en ese orden, C debe consultarse y retirarse primero, luego B y finalmente A. Explica por qué una pila corresponde al acceso físico a la torre y por qué una cola FIFO no es apropiada. Incluye el manejo explícito de consultas y retiros en una torre vacía, los componentes o funciones que habría que modificar o crear, una secuencia breve de pruebas de aceptación y cualquier supuesto necesario sobre la interfaz o el formato de entrada. No implementes código en esta etapa ni supongas requisitos que no aparecen en la consigna.