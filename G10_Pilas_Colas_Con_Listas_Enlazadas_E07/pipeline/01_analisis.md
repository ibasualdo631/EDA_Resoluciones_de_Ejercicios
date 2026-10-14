# Análisis — Verificador de paréntesis balanceados

## Objetivo

Implementar un programa que reciba una expresión y determine si sus paréntesis `(` y `)` están balanceados. La validación debe apoyarse en una pila: las aperturas se guardan y cada cierre debe corresponderse con la apertura más reciente que aún no se cerró.

## Alcance

- Validar el balance y el orden de los paréntesis redondos.
- No evaluar la expresión ni comprobar que su sintaxis aritmética sea correcta. Por ejemplo, el ejercicio no pide validar operadores, operandos ni espacios.
- Los demás caracteres de la expresión no afectan el balance; solo se inspeccionan `(` y `)`.

## Reglas de validación

1. Al leer un paréntesis de apertura `(`, agregarlo a la pila.
2. Al leer un paréntesis de cierre `)`:
   - Si la pila está vacía, el cierre no tiene una apertura previa disponible y la expresión es inválida.
   - Si la pila no está vacía, retirar de la pila la apertura más reciente; ese par queda emparejado.
3. Al terminar de recorrer la expresión:
   - Si la pila está vacía y no hubo un cierre inválido, todos los paréntesis quedaron emparejados y la expresión es válida.
   - Si aún quedan aperturas en la pila, faltan cierres y la expresión es inválida.
4. Los pares deben estar correctamente anidados. La pila garantiza este orden LIFO (último en entrar, primero en salir).

## Casos de error

| Caso | Ejemplo | Motivo |
|---|---|---|
| Cierre sin apertura disponible | `)(` o `())` | Se encuentra `)` cuando la pila está vacía. |
| Apertura sin cierre correspondiente | `(2 + 3` | Al finalizar, queda al menos una apertura en la pila. |
| Orden/anidamiento incorrecto | `())(` | El cierre excedente aparece antes de que termine el recorrido. |

Con el alfabeto de delimitadores solicitado (solo `(` y `)`), no existe un error de tipo de pareja —como cruzar `[` con `)`— porque los corchetes y llaves no forman parte de la consigna.

## Casos de referencia del enunciado

- Válidas: `(2 + 3) * (5 - 1)` y `((a + b) * c)`.
- Inválidas: `(2 + 3` y `())(`.

## Casos límite y criterios pendientes

- Una expresión sin paréntesis resulta balanceada según este criterio, porque no deja aperturas pendientes ni presenta cierres sin pareja.
- La cadena vacía también resulta balanceada si se valida exclusivamente el equilibrio de paréntesis. El enunciado no establece si debe rechazarse por no contener una expresión; esa sería una validación adicional, fuera del alcance indicado.
- El enunciado no solicita mensajes de error detallados, posiciones de caracteres ni recuperación tras detectar un error. Basta con informar si la expresión está balanceada.

## Requisitos no funcionales

- Recorrer la expresión una sola vez: tiempo `O(n)`.
- En el peor caso, almacenar todas las aperturas: espacio `O(n)`.