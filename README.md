# Aventuras Espaciales

Proyecto integrador desarrollado en Java.

El game propone una aventura espacial donde el player controla a un explorador que viaja por diferentes planetas, recolecta resources, enfrenta peligros del espacio y completa misiones para reparar una estación espacial dañada.

El objetivo principal es completar todas las misiones antes de que la ship sea destruida.

---

## Características principales

* Creación de player con nombre personalizado.
* Elección entre distintas naves espaciales.
* Sistema de energía para el player.
* Sistema de vida y reparación para la ship.
* Viajes a distintos planetas.
* Recolección aleatoria de resources mediante minería.
* Entities.CargoHold de carga con capacidad limitada.
* Venta de resources para obtener créditos.
* Misiones de entrega de resources.
* Hazards espaciales durante los viajes.
* Condiciones de victoria, derrota y salida voluntaria.
* Validación de datos ingresados por consola.

---

## Naves disponibles

| Entities.Ship     | Velocidad | Capacidad de carga | Vida inicial |
| -------- | --------: | -----------------: | -----------: |
| Fénix    |      Alta |       50 toneladas |         100% |
| Eclipse  |     Media |      100 toneladas |         100% |
| Galaxian |      Baja |      150 toneladas |         100% |

La velocity de la ship influye en la probabilidad de sufrir peligros espaciales durante los viajes.

---

## Estado inicial del player

Al comenzar el game, el player tendrá:

* Energía: `100%`
* Créditos espaciales: `0`
* Una ship elegida por el usuario
* Vida de la ship: `100%`
* Entities.CargoHold vacía

Antes de mostrar el menú, el programa informa el estado actual del player y de su ship.

---

## Menú principal

Desde la base espacial, el player puede realizar las siguientes acciones:

1. Viajar a un planeta.
2. Ver la cargoHold de carga.
3. Vender resources.
4. Ver misiones disponibles.
5. Entregar resources para una misión.
6. Reparar ship.
7. Descansar.
8. Salir del game.

---

## Planetas disponibles

El player puede viajar a tres tipos de planetas:

### Entities.Planet rocoso

| Recurso           | Probabilidad |
| ----------------- | -----------: |
| Mineral común     |          60% |
| Cristal           |          25% |
| Núcleo energético |          15% |

### Entities.Planet gaseoso

| Recurso | Probabilidad |
| ------- | -----------: |
| Gas     |          60% |
| Plasma  |          25% |
| Cristal |          15% |

### Entities.Planet volcánico

| Recurso           | Probabilidad |
| ----------------- | -----------: |
| Lava              |          50% |
| Obsidiana         |          30% |
| Núcleo energético |          20% |

---

## Recursos

| Recurso           |         Peso | Valor de venta |
| ----------------- | -----------: | -------------: |
| Mineral común     | 10 toneladas |    10 créditos |
| Gas               | 20 toneladas |    15 créditos |
| Lava              | 30 toneladas |    20 créditos |
| Cristal           | 15 toneladas |    35 créditos |
| Plasma            | 25 toneladas |    45 créditos |
| Obsidiana         | 25 toneladas |    50 créditos |
| Núcleo energético | 40 toneladas |    80 créditos |

Cada vez que el player mina:

* Se consume entre `10%` y `25%` de energía.
* Se genera un resource según las probabilidades del planeta actual.
* Si no hay energía suficiente, no se puede minar.
* Si el resource no entra en la cargoHold, se pierde, pero la energía utilizada no se recupera.

---

## Entities.CargoHold de carga

La ship cuenta con una cargoHold para almacenar los resources recolectados.

La cargoHold utiliza un `ArrayList` y permite:

* Almacenar resources.
* Mostrar los resources disponibles.
* Calcular el peso total ocupado.
* Verificar si un resource entra según la capacidad máxima.
* Retirar resources al venderlos o entregarlos en una misión.

Si no hay resources almacenados, el programa informa que la cargoHold está vacía.

---

## Venta de resources

Desde la base espacial, el player puede vender:

* Un resource específico.
* Todos los resources almacenados.

Al vender un resource:

1. Se elimina de la cargoHold.
2. El player recibe créditos según el valor del resource.

---

## Misiones disponibles

### Misión 1: Reparación del casco exterior

**Recursos requeridos:**

* 3 minerales comunes
* 1 cristal

**Recompensa:** `120 créditos espaciales`

---

### Misión 2: Estabilización del reactor

**Recursos requeridos:**

* 2 gases
* 1 plasma

**Recompensa:** `170 créditos espaciales`

---

### Misión 3: Núcleo de energía principal

**Recursos requeridos:**

* 1 núcleo energético
* 2 obsidianas

**Recompensa:** `250 créditos espaciales`

Una misión solo puede completarse si todos los resources requeridos se encuentran en la cargoHold.

Al completar una misión:

* Se retiran los resources entregados.
* Se suman los créditos de recompensa.
* La misión queda marcada como completada.
* No puede completarse nuevamente.

---

## Hazards espaciales

Cada vez que el player viaja a un planeta, existe la posibilidad de sufrir un peligro espacial.

| Velocidad de la ship | Probabilidad de peligro |
| -------------------- | ----------------------: |
| Alta                 |                     20% |
| Media                |                     40% |
| Baja                 |                     60% |

Los peligros posibles son:

### Pirata espacial

* Produce entre `3%` y `15%` de daño.
* Si la ship tiene velocity alta, el daño se duplica.

### Renegado

* Produce entre `3%` y `15%` de daño.
* Si la ship tiene velocity baja, el daño se duplica.

### Tormenta cósmica

* Produce entre `5%` y `20%` de daño.
* Afecta a cualquier ship por igual.

Si la vida de la ship llega a `0%`, el player pierde la partida.

---

## Reparación y descanso

### Reparar ship

* Cada tramo de `10%` de vida cuesta `25 créditos espaciales`.
* No se puede reparar por encima del `100%`.
* Si el player no tiene créditos suficientes, no podrá reparar la ship.

### Descansar

* Recupera la energía del player al `100%`.

---

## Condiciones de finalización

### Victoria

El player gana al completar las tres misiones de entrega.

### Derrota

El player pierde si la vida de la ship llega a `0%`.

### Salida voluntaria

El player puede finalizar la partida desde el menú principal.

Al terminar el game se muestra un resumen con:

* Nombre del player.
* Entities.Ship utilizada.
* Créditos obtenidos.
* Misiones completadas.
* Recursos restantes en la cargoHold.
* Resultado final de la partida.

---

## Requisitos técnicos aplicados

* Programación orientada a objetos.
* Clases con responsabilidades claras.
* Encapsulamiento mediante `private`, `protected` y `public`.
* Uso de composición.
* Uso de herencia.
* Uso de polimorfismo.
* Uso de `enum` para representar la velocity de las naves.
* Uso de `ArrayList` para la cargoHold de carga.
* Validación de datos ingresados por el usuario.
* Control de límites de energía, vida y carga.
* Clase de utilidad con métodos estáticos para validaciones y números aleatorios.

---

## Estructura sugerida del proyecto

```text
src/
├── Main.java
├── modelo/
│   ├── Entities.Player.java
│   ├── Entities.Ship.java
│   ├── Fenix.java
│   ├── Eclipse.java
│   ├── Galaxian.java
│   ├── Entities.CargoHold.java
│   ├── Recurso.java
│   ├── Entities.Mision.java
│   ├── Entities.Planet.java
│   ├── PeligroEspacial.java
│   └── Velocidad.java
├── servicio/
│   ├── Game.java
│   └── GeneradorEventos.java
└── util/
    └── Utilidades.java
```

---

## Ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/SantinoG07/AventurasEspaciales.git
```

2. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Java.

3. Ejecutar la clase principal:

```text
Main.java
```

---

## Autor

**Santino Gennuso**
