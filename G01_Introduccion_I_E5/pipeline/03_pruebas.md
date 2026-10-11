# Matriz de pruebas: ejercicio 5

## 1. Objetivo
Validar que el programa clasifica correctamente una nota numérica según estas reglas:
- nota < 4 → desaprobado
- 4 <= nota <= 6 → aprobado
- nota > 6 y <= 10 → promocionado
- fuera del rango [0, 10] → inválida

## 2. Alcance
Se prueban casos válidos e inválidos, incluyendo valores límite y condiciones de frontera, para asegurar que la lógica de `if`, `else if` y `else` sea correcta.

## 3. Matriz de casos de prueba

| ID | Entrada | Resultado esperado | Observación |
|----|---------|-------------------|------------|
| TC-01 | 0 | desaprobado | límite inferior del rango válido |
| TC-02 | 3 | desaprobado | valor menor a 4 |
| TC-03 | 4 | aprobado | límite inferior de aprobado |
| TC-04 | 6 | aprobado | límite superior de aprobado |
| TC-05 | 7 | promocionado | límite inferior de promocionado |
| TC-06 | 10 | promocionado | límite superior del rango válido |
| TC-07 | -1 | inválida | fuera del rango por debajo |
| TC-08 | 11 | inválida | fuera del rango por arriba |
| TC-09 | 4.5 | aprobado | número decimal dentro del rango |
| TC-10 | 6.9 | promocionado | decimal en rango de promocionado |
| TC-11 | 3.99 | desaprobado | decimal justo antes del umbral |
| TC-12 | 7.01 | promocionado | decimal por encima del umbral |

## 4. Casos de validación de lógica

### 4.1. Frontera de aprobado
- 3.99 → desaprobado
- 4.00 → aprobado

### 4.2. Frontera de promocionado
- 6.99 → aprobado
- 7.00 → promocionado

## 5. Resultado esperado general
El programa debe:
1. leer una nota,
2. validar que esté en el rango entre 0 y 10,
3. si es válida, mostrar el estado correspondiente,
4. si no es válida, mostrar un mensaje de error.

## 6. Conclusión
La matriz cubre casos normales, límites y valores fuera de rango. Esto permite verificar que la estructura condicional cumple con la especificación del ejercicio y evita errores en los bordes del rango.
