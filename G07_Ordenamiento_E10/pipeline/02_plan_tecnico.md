# Plan técnico

## 1. Modelo de datos

```java
public class Jugador {
    private String nombre;
    private int puntaje;

    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() { return nombre; }
    public int getPuntaje() { return puntaje; }
}
```

## 2. Estrategia de ordenamiento

Usar un ordenamiento estable con complejidad O(n log n), por ejemplo `Collections.sort` o `List.sort` con `Comparator` en Java. La clave será ordenar por `puntaje` en forma descendente:

```java
List<Jugador> jugadores = new ArrayList<>();

jugadores.sort(Comparator.comparingInt(Jugador::getPuntaje).reversed());
```

Esto usa una estrategia estable (TimSort en Java), por lo que si dos jugadores tienen el mismo puntaje, el orden original se conserva.

## 3. Manejo de empates

Si `puntaje == puntaje`, no se debe alterar el orden relativo de los jugadores. Es decir, el comparador solo considera el puntaje; cuando son iguales, la ordenación no hace intercambio adicional, y la estabilidad del algoritmo resuelve el empate de manera segura.

## 4. Plan breve

1. Crear la clase `Jugador` con `nombre` y `puntaje`.
2. Guardar los jugadores en una lista.
3. Ordenar la lista con un comparador descendente por puntaje.
4. Mostrar el ranking final.
5. Verificar empates: mantener el orden original entre jugadores con igual puntaje.
