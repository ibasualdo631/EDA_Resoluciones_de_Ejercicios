# Análisis — Ejercicio 5: Cola de impresión

## Alcance y material disponible

Este análisis toma como consigna principal el título indicado por el usuario: **“Cola de impresión”**. La imagen disponible en `Ejercicio85.png` no corresponde a esa consigna: describe implementar una pila genérica `Pila<T>` con arreglos. Por eso, los requerimientos que siguen son una propuesta inicial basada en el título y en el comportamiento típico de una cola de impresión; deben contrastarse con el enunciado correcto antes de fijar detalles de implementación.

## Problema que resuelve

Representar los trabajos que esperan para ser impresos y determinar cuál debe atenderse a continuación. La estructura debe conservar el orden de llegada y permitir agregar trabajos nuevos al final y retirar el más antiguo desde el frente.

## Por qué FIFO modela correctamente la cola

FIFO (*First In, First Out*, primero en entrar, primero en salir) refleja el flujo básico de un dispositivo de impresión compartido: los trabajos aceptados se incorporan al final de la fila, mientras que la impresora toma el trabajo pendiente más antiguo desde el frente. Como el dispositivo procesa un trabajo a la vez, este orden ofrece un criterio predecible y justo de atención: un trabajo nuevo no adelanta a los que ya estaban esperando.

Una pila aplicaría LIFO (*Last In, First Out*): el trabajo agregado más recientemente saldría primero y podría postergar indefinidamente los anteriores bajo una llegada continua. Eso no representa una fila ordinaria de impresión.

FIFO es el modelo de disciplina, no una afirmación de que todo sistema operativo real sea estrictamente FIFO: los sistemas de impresión pueden permitir prioridades, cancelaciones, reintentos o varias impresoras. Esas políticas quedan fuera del alcance inicial salvo que el enunciado las pida.

## Requerimientos funcionales propuestos

1. Mantener una colección ordenada de trabajos pendientes.
2. Encolar un trabajo al final, conservando su información relevante (por ejemplo, nombre/identificador del documento y, si el enunciado lo requiere, usuario o cantidad de páginas).
3. Consultar cuál es el siguiente trabajo sin retirarlo, si la interfaz solicitada incluye esa operación.
4. Desencolar y devolver el trabajo más antiguo para simular que la impresora lo toma.
5. Informar si la cola está vacía y, si corresponde, mostrar su contenido en orden FIFO.
6. Mantener consistente el estado de la cola luego de cada operación.

## Reglas y casos límite

- Si se encolan A, B y C, el orden de atención debe ser A, B, C.
- Encolar no debe reemplazar ni reordenar trabajos ya pendientes.
- Consultar o retirar el siguiente trabajo con la cola vacía debe manejarse según el contrato esperado; no se debe acceder a un elemento inexistente.
- Luego de retirar todos los trabajos, la cola vuelve al estado vacío y puede recibir nuevos trabajos.
- Definir, conforme al lenguaje y al enunciado, cómo validar un trabajo inválido o vacío.
- No asumir prioridades, concurrencia, persistencia, cancelación ni simulación de tiempos sin respaldo en la consigna.

## Aspectos por confirmar con el enunciado correcto

- Lenguaje, estructura subyacente y operaciones exigidas.
- Qué campos componen un trabajo de impresión.
- Si la cola tiene capacidad fija o puede crecer.
- Qué resultado/error se espera al consultar o desencolar una cola vacía.
- Si se debe imprimir/mostrar la cola, simular la impresión o solo implementar la estructura.
- Si la evaluación exige pruebas, interfaz interactiva o una clase genérica.

## Prompt inicial para el planificador

> Planificá la resolución del **Ejercicio 5: Cola de impresión**. El objetivo es representar trabajos pendientes de una impresora compartida mediante una cola con disciplina FIFO (*First In, First Out*): cada trabajo nuevo se agrega al final y el siguiente que se atiende se retira del frente, de modo que los trabajos se procesen en orden de llegada. Explicá brevemente por qué este comportamiento modela una cola de hardware mejor que una pila LIFO.
>
> Antes de definir la implementación, inspeccioná el repositorio y el enunciado disponible para identificar el lenguaje, las convenciones del proyecto y las operaciones/campos realmente requeridos. La imagen actualmente disponible parece describir una pila genérica `Pila<T>`, no una cola de impresión; no la tomes como fuente de requisitos para este ejercicio y señalá la discrepancia si no aparece otra consigna. No inventes capacidad, interfaz, datos del trabajo ni manejo de errores: si el enunciado no los determina, registralos como decisiones pendientes o proponé alternativas explícitas.
>
> Entregá un plan técnico concreto y acotado que enumere los cambios necesarios, la estructura y operaciones propuestas (encolar, desencolar, consultar frente y/o verificar vacío solo si aplican), el manejo seguro de cola vacía y pruebas que demuestren el orden FIFO (por ejemplo, encolar A, B y C y comprobar que salen A, B y C), además de los casos vacía y reinicio tras vaciarse. Conservá el estilo y las interfaces ya usadas por el proyecto; no agregues funcionalidades de prioridades, concurrencia o persistencia salvo que el enunciado las exija.