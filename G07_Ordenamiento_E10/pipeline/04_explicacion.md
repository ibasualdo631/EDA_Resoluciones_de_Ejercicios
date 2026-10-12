# Explicación breve

Cuando ordenamos primitivos como `int`, `double` o `char`, la comparación es directa: Java compara sus valores numéricos o lexicográficos sin necesidad de indicar qué parte del dato importa.

Cuando ordenamos objetos, en cambio, la comparación ya no se hace sobre el objeto completo, sino sobre un atributo específico que elegimos como criterio. Por ejemplo, si ordenamos personas por su puntaje, el algoritmo compara `persona.getPuntaje()` en lugar de comparar las referencias de memoria o toda la instancia.

Esto cambia la lógica porque:

- con primitivos, el orden natural está definido por el valor mismo;
- con objetos, necesitamos un comparador o una clave de ordenación;
- si dos objetos tienen el mismo atributo, el algoritmo debe decidir si conserva el orden original o si considera otros campos para desempatar.

En Java, esto se refleja con `Comparator.comparing(...)` o con la interfaz `Comparable`, donde se define qué atributo determina el orden. Así, el algoritmo ordena según la propiedad relevante y no por el objeto en sí.

En resumen: ordenar primitivos es comparar valores directos; ordenar objetos es comparar una clave del objeto, y esa clave define el criterio de ordenación y el comportamiento ante empates.
