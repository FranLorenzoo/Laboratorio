# Ejercicio 2 · a) Acceso `protected` dentro del mismo paquete

## Código analizado

```java
package griego;

class Alpha {
    protected int x;

    protected void otroMetodoA() {
        System.out.println("Un método protegido");
    }
}
```

```java
package griego;

class Gamma {
    void unMetodoG() {
        Alpha a = new Alpha();
        a.x = 10;            // ✔ válido
        a.otroMetodoA();     // ✔ válido
    }
}
```

## Respuesta

**Sí, el acceso de la clase `Gamma` es válido.** Las dos sentencias compilan sin
error, a pesar de que `Gamma` **no** es subclase de `Alpha`.

## Justificación

El error habitual es leer `protected` como "solo accesible desde subclases". En
Java el modificador otorga en realidad **dos** niveles de visibilidad al mismo
tiempo:

1. Acceso desde **cualquier clase del mismo paquete**, sea o no subclase
   (exactamente igual que el acceso por defecto o *package-private*).
2. Acceso desde **subclases ubicadas en otro paquete**, con la restricción
   adicional que se analiza en el inciso [e](e.md).

Acá alcanza con la **primera** condición: `Alpha` y `Gamma` están declaradas las
dos en el paquete `griego`, así que `Gamma` ve los miembros `protected` de
`Alpha` como si fueran públicos. La herencia no juega ningún papel en este caso.

### Sobre la declaración de las clases

Ninguna de las dos clases es `public` (tienen acceso de paquete). Eso tampoco es
un problema: `Gamma` está en `griego` y por lo tanto puede **nombrar** el tipo
`Alpha` e instanciarlo con `new Alpha()`. El constructor por defecto que genera
el compilador toma el mismo nivel de acceso que la clase, o sea acceso de
paquete, y `Gamma` cumple esa condición.

Conviene distinguir siempre los dos chequeos que hace el compilador:

- ¿Es visible **el tipo**? → lo decide el modificador de la clase.
- ¿Es visible **el miembro**? → lo decide el modificador de cada campo/método.

En este inciso ambos chequeos pasan por la misma razón: todo ocurre dentro de
`griego`.

## Tabla de referencia — modificadores de acceso

| Modificador     | Misma clase | Mismo paquete | Subclase en otro paquete | Otro paquete (no subclase) |
| --------------- | :---------: | :-----------: | :----------------------: | :------------------------: |
| `private`       |      ✔      |       ✘       |            ✘             |             ✘              |
| *(por defecto)* |      ✔      |       ✔       |            ✘             |             ✘              |
| `protected`     |      ✔      |       ✔       |      ✔ (ver [e](e.md))   |             ✘              |
| `public`        |      ✔      |       ✔       |            ✔             |             ✔              |

La columna que resuelve este inciso es **"Mismo paquete"**, donde `protected`
tiene un ✔.
