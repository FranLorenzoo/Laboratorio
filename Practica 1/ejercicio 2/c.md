# Ejercicio 2 · c) Acceso por defecto (*package-private*) desde otro paquete

## Código analizado

```java
package griego;

public class Alpha {
    int x;                       // sin modificador → acceso de paquete

    void unMetodoA() {           // sin modificador → acceso de paquete
        System.out.println("Un mét. paquete");
    }
}
```

```java
package romano;

import griego.*;

class Beta {
    void unMetodoB() {
        Alpha a = new Alpha();   // ✔ válido
        a.x = 10;                // ✘ ERROR de compilación
        a.unMetodoA();           // ✘ ERROR de compilación
    }
}
```

## Respuesta

**No, el método `unMetodoB()` no es válido.** Genera dos errores de compilación,
con mensajes del estilo:

```
error: x is not public in Alpha; cannot be accessed from outside package
error: unMetodoA() is not public in Alpha; cannot be accessed from outside package
```

## Justificación

Al quitarles el modificador, `x` y `unMetodoA()` quedan con **acceso por
defecto** (también llamado *package-private* o "acceso de paquete"). Ese nivel
restringe la visibilidad **al paquete donde se los declara**: solo las clases de
`griego` pueden usarlos. `Beta` pertenece a `romano`, así que ambos accesos son
rechazados.

Como se explica en [b](b.md), `import griego.*;` no interviene: únicamente
resuelve el nombre `Alpha`, no otorga permisos.

## La línea que **sí** es válida

Vale la pena separar bien los dos chequeos que hace el compilador:

| Sentencia          | Qué se chequea            | Resultado                             |
| ------------------ | ------------------------- | ------------------------------------- |
| `Alpha a = ...`    | visibilidad del **tipo**  | ✔ `Alpha` sigue siendo `public`        |
| `new Alpha()`      | visibilidad del **constructor** | ✔ el implícito hereda el `public` de la clase |
| `a.x = 10;`        | visibilidad del **campo** | ✘ acceso de paquete                    |
| `a.unMetodoA();`   | visibilidad del **método**| ✘ acceso de paquete                    |

O sea que **la instanciación es perfectamente legal**; lo que falla es el uso de
los miembros. Un error frecuente es responder que "no se puede crear el objeto":
sí se puede.

## Conclusión conceptual

Declarar `public class Alpha` exporta **el tipo**, no su contenido. Cada miembro
lleva su propio modificador y se evalúa por separado. El resultado de este
inciso es una clase que, vista desde fuera de `griego`, se puede nombrar,
instanciar y pasar como parámetro, pero **no ofrece ningún estado ni
comportamiento utilizable**: es una caja opaca.

## Cómo se arreglaría

Cualquiera de estas dos opciones hace compilar a `Beta`:

```java
public int x;                    // acceso total
public void unMetodoA() { ... }
```

o bien, respetando el encapsulamiento, dejar el campo privado y exponer métodos
públicos:

```java
private int x;
public void setX(int x) { this.x = x; }
public int  getX()      { return x; }
public void unMetodoA() { ... }
```

Notar que `protected` **no** alcanzaría en este caso, porque `Beta` no es
subclase de `Alpha` (ver [d](d.md) y [e](e.md)).
