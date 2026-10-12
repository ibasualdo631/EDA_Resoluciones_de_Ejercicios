# Pruebas: palíndromo recursivo

## Casos diseñados

Las pruebas ejecutables están en el `main` de `Palindromo.java`. El resultado esperado para cada entrada es:

| Caso | Entrada | Esperado | Qué valida |
|---|---|---:|---|
| Cadena vacía | `""` | `true` | Caso base de longitud cero |
| Un carácter minúsculo | `"a"` | `true` | Caso base de longitud uno |
| Un carácter mayúsculo | `"A"` | `true` | Caso base independiente del uso de mayúsculas |
| Palíndromo en mayúsculas | `"RADAR"` | `true` | Normalización de mayúsculas |
| Palíndromo mixto | `"rAdAr"` | `true` | Comparación sin distinguir mayúsculas |
| Palíndromo recursivo mixto | `"ReCoNoCeR"` | `true` | Normalización conservada en toda la recursión |
| Extremos distintos | `"RaDaX"` | `false` | Corte anticipado en la primera comparación |
| Extremos iguales, interior distinto | `"raXbr"` | `false` | Rechazo después de reducir el problema |
| No palíndromo existente | `"java"` | `false` | Conservación del resultado negativo |
| Cadena de 100 000 caracteres con extremos distintos | 99 999 `"a"` seguidos por `"b"` | `false` | Corte en los extremos sin descender recursivamente por una entrada grande |
| Locale turco predeterminado | `"Ii"` | `true` | Normalización estable usando `Locale.ROOT` |

## Criterios de aceptación

- Todas las comprobaciones deben finalizar sin `AssertionError`.
- Las mayúsculas y minúsculas no deben alterar el resultado.
- Una entrada vacía o de un carácter debe devolver `true`.
- Si los extremos difieren, debe devolver `false` de inmediato, sin llamadas recursivas adicionales.
- La prueba larga con extremos diferentes verifica que el rechazo temprano evita agotar la pila por una recursión innecesaria.

## Ejecución

Desde este directorio:

```powershell
javac -d "$env:TEMP\G06_Recursividad_E9-test" Palindromo.java
java -cp "$env:TEMP\G06_Recursividad_E9-test" Palindromo
```

Resultado: **ejecutado**. La compilación y las once comprobaciones finalizaron correctamente (`[OK]`).