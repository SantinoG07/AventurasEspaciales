# Aventuras Espaciales

Proyecto integrador desarrollado en Java.

El game propone una aventura espacial donde el jugador controla a un explorador que viaja por diferentes planetas, recolecta recursos, enfrenta peligros del espacio y completa misiones para reparar una estación espacial dañada.

El objetivo principal es completar todas las misiones antes de que la nave sea destruida.

---

## Características principales

* Creación de jugador con nombre personalizado.
* Elección entre distintas naves espaciales.
* Sistema de energía para el jugador.
* Sistema de vida y reparación para la nave.
* Viajes a distintos planetas.
* Recolección aleatoria de recursos mediante minería.
* Entidades.Bodega de carga con capacidad limitada.
* Venta de recursos para obtener créditos.
* Misiones de entrega de recursos.
* Hazards espaciales durante los viajes.
* Condiciones de victoria, derrota y salida voluntaria.
* Validación de datos ingresados por consola.

---

## Naves disponibles

| Entidades.Ship     | Velocidad | Capacidad de carga | Vida inicial |
| -------- | --------: | -----------------: | -----------: |
| Fénix    |      Alta |       50 toneladas |         100% |
| Eclipse  |     Media |      100 toneladas |         100% |
| Galaxian |      Baja |      150 toneladas |         100% |

La velocity de la nave influye en la probabilidad de sufrir peligros espaciales durante los viajes.

---

## Estado inicial del jugador

Al comenzar el game, el jugador tendrá:

* Energía: `100%`
* Créditos espaciales: `0`
* Una nave elegida por el usuario
* Vida de la nave: `100%`
* Entidades.Bodega vacía

Antes de mostrar el menú, el programa informa el estado actual del jugador y de su nave.

---

## Menú principal

Desde la base espacial, el jugador puede realizar las siguientes acciones:

1. Viajar a un planeta.
2. Ver la bodega de carga.
3. Vender recursos.
4. Ver misiones disponibles.
5. Entregar recursos para una misión.
6. Reparar nave.
7. Descansar.
8. Salir del game.

---

## Planetas disponibles

El jugador puede viajar a tres tipos de planetas:

### Entidades.Planet rocoso

| Recurso           | Probabilidad |
| ----------------- | -----------: |
| Mineral común     |          60% |
| Cristal           |          25% |
| Núcleo energético |          15% |

### Entidades.Planet gaseoso

| Recurso | Probabilidad |
| ------- | -----------: |
| Gas     |          60% |
| Plasma  |          25% |
| Cristal |          15% |

### Entidades.Planet volcánico

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

Cada vez que el jugador mina:

* Se consume entre `10%` y `25%` de energía.
* Se genera un recursos según las probabilidades del planeta actual.
* Si no hay energía suficiente, no se puede minar.
* Si el recursos no entra en la bodega, se pierde, pero la energía utilizada no se recupera.

---

## Entidades.Bodega de carga

La nave cuenta con una bodega para almacenar los recursos recolectados.

La bodega utiliza un `ArrayList` y permite:

* Almacenar recursos.
* Mostrar los recursos disponibles.
* Calcular el peso total ocupado.
* Verificar si un recursos entra según la capacidad máxima.
* Retirar recursos al venderlos o entregarlos en una misión.

Si no hay recursos almacenados, el programa informa que la bodega está vacía.

---

## Venta de recursos

Desde la base espacial, el jugador puede vender:

* Un recursos específico.
* Todos los recursos almacenados.

Al vender un recursos:

1. Se elimina de la bodega.
2. El jugador recibe créditos según el valor del recursos.

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

Una misión solo puede completarse si todos los recursos requeridos se encuentran en la bodega.

Al completar una misión:

* Se retiran los recursos entregados.
* Se suman los créditos de recompensa.
* La misión queda marcada como completada.
* No puede completarse nuevamente.

---

## Hazards espaciales

Cada vez que el jugador viaja a un planeta, existe la posibilidad de sufrir un peligro espacial.

| Velocidad de la nave | Probabilidad de peligro |
| -------------------- | ----------------------: |
| Alta                 |                     20% |
| Media                |                     40% |
| Baja                 |                     60% |

Los peligros posibles son:

### Pirata espacial

* Produce entre `3%` y `15%` de daño.
* Si la nave tiene velocity alta, el daño se duplica.

### Renegado

* Produce entre `3%` y `15%` de daño.
* Si la nave tiene velocity baja, el daño se duplica.

### Tormenta cósmica

* Produce entre `5%` y `20%` de daño.
* Afecta a cualquier nave por igual.

Si la vida de la nave llega a `0%`, el jugador pierde la partida.

---

## Reparación y descanso

### Reparar nave

* Cada tramo de `10%` de vida cuesta `25 créditos espaciales`.
* No se puede reparar por encima del `100%`.
* Si el jugador no tiene créditos suficientes, no podrá reparar la nave.

### Descansar

* Recupera la energía del jugador al `100%`.

---

## Condiciones de finalización

### Victoria

El jugador gana al completar las tres misiones de entrega.

### Derrota

El jugador pierde si la vida de la nave llega a `0%`.

### Salida voluntaria

El jugador puede finalizar la partida desde el menú principal.

Al terminar el game se muestra un resumen con:

* Nombre del jugador.
* Entidades.Ship utilizada.
* Créditos obtenidos.
* Misiones completadas.
* Recursos restantes en la bodega.
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
* Uso de `ArrayList` para la bodega de carga.
* Validación de datos ingresados por el usuario.
* Control de límites de energía, vida y carga.
* Clase de utilidad con métodos estáticos para validaciones y números aleatorios.

---

## Estructura sugerida del proyecto

```text
src/
├── Main.java
├── modelo/
│   ├── Entidades.Jugador.java
│   ├── Entidades.Ship.java
│   ├── Fenix.java
│   ├── Eclipse.java
│   ├── Galaxian.java
│   ├── Entidades.Bodega.java
│   ├── Recurso.java
│   ├── Entidades.Mision.java
│   ├── Entidades.Planet.java
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
