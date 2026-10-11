# Análisis del ejercicio 5: Condicionales con if, else if y else

## 1. Objetivo
Crear un programa en Java que solicite al usuario una nota numérica y determine el estado del alumno según la calificación ingresada. El programa debe informar si el alumno desaprobó, aprobó o promocionó.

## 2. Contexto del problema
El ejercicio está orientado a practicar el uso de estructuras condicionales:
- if
- else if
- else
- operadores relacionales
- condiciones lógicas
- validación de rangos

La intención es evaluar cómo se toma una decisión a partir de un valor numérico y cómo se encadenan varias condiciones para cubrir todos los casos posibles.

## 3. Requisitos funcionales
1. Solicitar al usuario una nota numérica.
2. Validar que la nota ingresada esté dentro de un rango válido.
3. Evaluar la nota con condiciones encadenadas.
4. Mostrar un mensaje indicando el estado del alumno.
5. Cubrir todos los posibles valores del rango sin dejar casos sin resolver.

## 4. Reglas de negocio / lógica esperada
Como el ejercicio habla de tres estados posibles (desaprobó, aprobó y promocionó), se asume una escala de evaluación con tres intervalos. Un criterio típico es:
- 0 a 3: desaprobó
- 4 a 6: aprobó
- 7 a 10: promocionó

Esto puede implementarse con una cadena de condicionantes:
- Si la nota es menor a 4, entonces el alumno desaprobó.
- Else if la nota está entre 4 y 6, entonces aprobó.
- Else if la nota está entre 7 y 10, entonces promocionó.
- Else: la nota no es válida o está fuera del rango permitido.

## 5. Consideraciones de entrada y validación
La nota debe ser un valor numérico. Se recomienda:
- leerla como double o float si se quiere permitir decimales,
- o como int si la escala es entera.
- validar que el valor esté dentro del rango definido (por ejemplo, 0 a 10).

Si la nota está fuera de rango, el programa debe indicar que la entrada es inválida, sin intentar clasificarla como aprobación o reprobación.

## 6. Casos límite
Se deben contemplar situaciones como:
- nota negativa,
- nota 0,
- nota máxima permitida,
- nota decimal (si se permite),
- nota fuera de rango.

Ejemplos de casos relevantes:
- 3 → desaprobó
- 4 → aprobó
- 6 → aprobó
- 7 → promocionó
- 10 → promocionó
- -1 o 11 → nota inválida

## 7. Estructura lógica de solución
El problema se resuelve con una estructura condicional encadenada:
1. Leer la nota.
2. Verificar rango válido.
3. Elegir una de las siguientes opciones:
   - desaprobó
   - aprobó
   - promocionó
4. Mostrar el resultado correspondiente.

## 8. Objetivo pedagógico
Este ejercicio busca que el estudiante comprenda:
- la diferencia entre if, else if y else,
- cómo anidar condiciones para cubrir varios escenarios,
- cómo usar comparaciones como <, >=, <=, etc.,
- la importancia de validar datos antes de tomar decisiones.

## 9. Conclusión
El problema es un ejercicio clásico de lógica condicional en Java: a partir de una nota ingresada, el programa debe decidir el estado del alumno usando una serie de condiciones ordenadas y excluyentes. La clave está en definir correctamente el rango de la nota y encadenar bien las decisiones para que cada valor se asigne a exactamente una categoría.
