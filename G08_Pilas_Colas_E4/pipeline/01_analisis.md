# Análisis — Ejercicio 4: Sistema de turnos

## Observación sobre la consigna recibida

La imagen adjunta no describe un sistema de turnos: su título es “Historial de navegación”, indica apilar URLs y volver con `pop`, y explícitamente pide explicar LIFO. Esa situación corresponde a una **pila**, no a una cola. Este análisis toma como fuente de requisitos el texto del usuario (“Sistema de turnos” y atención FIFO); antes de implementar, conviene reemplazar o confirmar la imagen para evitar resolver el ejercicio equivocado.

## Objetivo

Modelar la espera de personas que solicitan atención. El programa registra cada turno nuevo y permite atender al siguiente, conservando el orden de llegada.

## Justificación de la estructura

Una cola aplica **FIFO** (*First In, First Out*): el primer elemento que entra es el primero que sale. Esto representa la regla habitual de un sistema de turnos sin prioridades: quien llegó antes debe ser atendido antes. Al emitir/registrar los turnos al final de la cola y atender siempre desde el frente, se mantiene el orden justo y predecible.

Una pila aplica **LIFO** (*Last In, First Out*): el elemento agregado más recientemente sale primero. Si se usara una pila, cada persona recién llegada quedaría por delante de quienes esperan desde antes; con llegadas sucesivas, los turnos antiguos podrían postergarse indefinidamente. Por tanto, una pila contradice la política de atención por orden de llegada. La elección no es intercambiable: viene determinada por la regla de negocio.

Esta conclusión presupone que no existen prioridades, citas con horario ni múltiples filas. Si el enunciado real incorpora esas reglas, habrá que modelarlas explícitamente; una prioridad, por ejemplo, ya no sería una cola FIFO simple para todos los usuarios.

## Requerimientos funcionales inferidos

1. Registrar un nuevo turno al final de la espera.
2. Atender y retirar el turno ubicado al frente.
3. Conservar el orden de llegada entre las operaciones.
4. Informar cuando se intenta atender con la cola vacía, sin retirar un elemento inexistente.
5. Permitir consultar los turnos pendientes o indicar que no hay personas esperando, si la interfaz del ejercicio lo requiere.

## Casos límite y decisiones pendientes

- Intentar atender sin turnos pendientes.
- Consultar una cola vacía.
- Definir si el identificador de turno lo ingresa el usuario o lo genera el sistema.
- Definir la representación exacta de las operaciones y sus mensajes.
- Si se admiten datos inválidos o turnos duplicados, precisar cómo validarlos.

## Prompt inicial para el planificador

> Diseña un plan para resolver el Ejercicio 4, “Sistema de turnos”, utilizando una **cola FIFO**. El sistema debe agregar cada nuevo turno al final y atender siempre el turno más antiguo desde el frente. Justifica en el diseño por qué FIFO reproduce la atención por orden de llegada y por qué una pila LIFO sería incorrecta: una pila atendería primero a quienes llegaron últimos y podría postergar indefinidamente a los anteriores. Incluye las operaciones necesarias, el estado que debe mantener la estructura, el flujo de interacción, el manejo de cola vacía y pruebas que verifiquen el orden (por ejemplo, registrar A, B y C y comprobar que se atiendan A, luego B y luego C). No agregues prioridades ni reglas que no estén en la consigna; señala como supuesto que se trata de una única fila sin prioridades y deja identificadas las decisiones de entrada/salida que el enunciado no especifique. Nota: la imagen adjunta parece pertenecer a otro ejercicio (historial de navegación con pila LIFO); no la uses como requisito del sistema de turnos sin confirmación.