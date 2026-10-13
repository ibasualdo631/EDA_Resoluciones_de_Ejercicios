# Plan técnico — Sistema de turnos para atención

## 1. Decisión de diseño

Modelar la espera con una cola enlazada FIFO. El sistema genera un `Turno` para cada llegada; cada turno contiene un número correlativo y una `Persona`. La cola almacena turnos, no nombres sueltos, de modo que la información de identificación viaja junto con el elemento que se encola, consulta y atiende.

El análisis no fija lenguaje ni interfaz. Por eso, las firmas siguientes expresan contratos y tipos conceptuales, no una sintaxis dependiente de un lenguaje. No se agrega una interfaz gráfica ni persistencia.

## 2. Tipos y responsabilidades

### `Persona`

Representa los datos de la persona que solicita atención.

- `nombre: Cadena`
- `Persona(nombre: Cadena) -> Persona`: construye una persona válida. Rechaza `null`, una cadena vacía o un nombre compuesto solo por espacios. Puede normalizar los espacios exteriores antes de almacenar el nombre.

No se usa el nombre como identificador único: varias personas pueden compartirlo.

### `Turno`

Asocia una persona con su posición identificable en el sistema.

- `numero: EnteroPositivo`
- `persona: Persona`
- `Turno(numero: EnteroPositivo, persona: Persona) -> Turno`: requiere un número positivo y una persona no nula.

El número se asigna al registrar una llegada y es correlativo dentro de una ejecución del sistema. Si el tipo numérico elegido pudiera desbordarse, el registro debe fallar explícitamente sin modificar la cola.

### `Nodo<Turno>`

Estructura interna de la cola enlazada:

- `valor: Turno`
- `siguiente: Nodo<Turno> | Nulo`

Cada nodo contiene un turno y una referencia al siguiente nodo. No se expone fuera de la implementación de la cola.

### `ColaTurnos`

Implementa la estructura FIFO con referencias al frente y al final:

- `frente: Nodo<Turno> | Nulo`
- `final: Nodo<Turno> | Nulo`
- `cantidad: EnteroNoNegativo`

Invariantes:

1. `cantidad == 0` si y solo si `frente == Nulo` y `final == Nulo`.
2. Si `cantidad > 0`, tanto `frente` como `final` son no nulos.
3. `final.siguiente == Nulo`.
4. Los nodos se recorren desde el más antiguo al más reciente; insertar no altera ese orden.

## 3. Firmas y contratos

Las operaciones de cola son:

```text
encolar(turno: Turno) -> Vacio
desencolar() -> Opcional<Turno>
consultarFrente() -> Opcional<Turno>
estaVacia() -> Booleano
tamano() -> EnteroNoNegativo
```

- `encolar` rechaza un turno nulo con error de argumento inválido. En una cola vacía, asigna el nuevo nodo tanto a `frente` como a `final`; en caso contrario, enlaza el nodo después de `final` y actualiza `final`. Incrementa `cantidad` una sola vez.
- `desencolar` devuelve `Opcional.Vacio` sin cambiar nada si no hay elementos. Si hay elementos, guarda el turno del frente, avanza `frente` y decrementa `cantidad`. Si se quitó el último elemento, también establece `final = Nulo`. Devuelve el turno retirado.
- `consultarFrente` devuelve `Opcional.Vacio` si no hay elementos; en caso contrario devuelve el turno de `frente` sin modificar referencias ni cantidad.
- `estaVacia` y `tamano` consultan el estado sin mutarlo. `estaVacia` debe ser consistente con `cantidad == 0`.

El sistema que coordina numeración y cola expone:

```text
registrarLlegada(nombre: Cadena) -> Resultado<Turno, ErrorValidacion>
atenderSiguiente() -> Opcional<Turno>
consultarSiguiente() -> Opcional<Turno>
hayPersonasEsperando() -> Booleano
```

- `registrarLlegada` valida el nombre, crea `Persona`, asigna el próximo número disponible, crea el `Turno` y lo encola. Devuelve el turno generado. Ante entrada inválida o imposibilidad de asignar el número, devuelve un error explícito y deja intacto el estado de la cola y el contador.
- `atenderSiguiente` delega en `desencolar`. El turno devuelto permite informar qué persona fue atendida; un resultado vacío indica que no había nadie.
- `consultarSiguiente` delega en `consultarFrente`. Un resultado vacío indica que no hay próximo turno y nunca retira elementos.
- `hayPersonasEsperando` delega en `estaVacia` o consulta su negación; no mantiene un estado duplicado.

La presentación (si se implementa) interpreta los resultados vacíos y errores para mostrar mensajes claros, por ejemplo, “No hay personas para atender” o “No hay turnos en espera”. La lógica de cola no imprime directamente ni inventa turnos.

## 4. Organización y secuencia

Separar las responsabilidades en tres componentes:

1. **Modelo:** `Persona` y `Turno`, con sus validaciones de construcción.
2. **Estructura:** `Nodo<Turno>` y `ColaTurnos`, responsables exclusivamente del orden y las operaciones FIFO.
3. **Coordinación:** sistema de turnos, responsable de validar entradas de llegada, numerar turnos y delegar en la cola.

Secuencia de llegada:

1. Recibir el nombre.
2. Validarlo y construir `Persona`.
3. Comprobar que hay un número de turno disponible.
4. Crear `Turno` con ese número y la persona.
5. Encolar el turno y, solo si el alta se completó, avanzar el próximo número.
6. Devolver el turno registrado.

Secuencia de atención: llamar a `desencolar`; si devuelve un turno, informar su número y persona; si devuelve vacío, informar que no hay personas en espera.

Secuencia de consulta: llamar a `consultarFrente`; informar el turno si existe o que la cola está vacía. No llamar a `desencolar` durante una consulta.

## 5. Casos de borde y validaciones

- Nombre nulo, vacío o compuesto solo por espacios: rechazar la llegada y no consumir un número.
- Turno o persona nulos al construir/encolar: rechazar de forma explícita; la estructura conserva su estado.
- Atender con la cola vacía: devolver resultado vacío, sin decrementar tamaño ni alterar referencias.
- Consultar con la cola vacía: devolver resultado vacío, sin mutar la cola.
- Encolar en una cola vacía: frente y final apuntan al mismo nodo.
- Atender el único turno: frente y final vuelven a nulo y el tamaño queda en cero.
- Encolar después de vaciar la cola: el nuevo nodo vuelve a ser frente y final.
- Nombres repetidos: permitidos; el número de turno distingue las solicitudes.
- Agotamiento del rango numérico: informar error y no cambiar cola ni contador.

## 6. Verificación prevista

Probar como mínimo:

1. Cola nueva: vacía, tamaño cero, consulta y atención vacías.
2. Registrar A, B y C: números correlativos y consulta devuelve A.
3. Consultar repetidamente: sigue devolviendo A y el tamaño no cambia.
4. Atender sucesivamente: devuelve A, B y C en ese orden.
5. Tras atender C: queda vacía; una atención adicional no falla ni altera el estado.
6. Registrar un nuevo turno tras vaciarla: puede consultarse y atenderse correctamente.
7. Rechazar nombre inválido y confirmar que no cambia el tamaño ni se saltea un número.
8. Permitir dos personas con igual nombre y distinguirlas por sus números de turno.