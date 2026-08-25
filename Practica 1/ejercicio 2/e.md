# Ejercicio 2 · e) `protected` y herencia entre paquetes

## Código analizado

```java
package griego;

public class Alpha {
    protected int x;

    protected void otroMetodoA() {
        System.out.println("Un método protegido");
    }
}
```

```java
package romano;

import griego.*;

public class Delta extends Alpha {
    void unMetodoD(Alpha a, Delta d) {
        a.x = 10;              // ✘ ERROR de compilación
        d.x = 10;              // ✔ válido
        a.otroMetodoA();       // ✘ ERROR de compilación
        d.otroMetodoA();       // ✔ válido
    }
}
```

## Respuesta

**No, el método no es válido: dos de sus cuatro sentencias no compilan.** Los
accesos a través de la referencia `d` (de tipo `Delta`) son correctos; los
accesos a través de la referencia `a` (de tipo `Alpha`) son rechazados, con
mensajes del estilo:

```
error: x has protected access in Alpha
        a.x = 10;
         ^
error: otroMetodoA() has protected access in Alpha
        a.otroMetodoA();
         ^
```

Es el resultado más contraintuitivo del ejercicio, porque las cuatro líneas
"parecen" hacer lo mismo.

## Justificación

Cuando se accede a un miembro `protected` **desde otro paquete**, no alcanza con
ser subclase. La regla del lenguaje agrega una condición sobre el objeto que
recibe el acceso: la subclase solo puede tocar miembros `protected` de aquellos
objetos **de cuya implementación es responsable**, es decir, objetos de su
propio tipo.

Formalmente: en un acceso de la forma `E.miembro` que ocurre dentro de una clase
`S` (acá `S = Delta`), estando `miembro` declarado `protected` en otro paquete,
se exige que **el tipo de la expresión `E` sea `S` o un subtipo de `S`**.

Aplicando la regla:

| Sentencia         | Tipo de la referencia | ¿Es `Delta` o subtipo de `Delta`?             | Resultado |
| ----------------- | --------------------- | --------------------------------------------- | :-------: |
| `a.x = 10`        | `Alpha`               | No — `Alpha` es **super**tipo de `Delta`      |     ✘     |
| `d.x = 10`        | `Delta`               | Sí                                            |     ✔     |
| `a.otroMetodoA()` | `Alpha`               | No                                            |     ✘     |
| `d.otroMetodoA()` | `Delta`               | Sí                                            |     ✔     |

## El porqué de la regla

`protected` está pensado para que una subclase pueda manipular **su propia
porción heredada de estado**, no para convertirse en una puerta trasera hacia
los internos de cualquier instancia de la superclase.

Si `a.x = 10` fuera legal, bastaría con escribir una subclase trivial en
cualquier paquete del mundo para poder leer y escribir libremente los campos
`protected` de objetos ajenos:

```java
package cualquiera;

public class Colador extends Alpha {
    public static void romper(Alpha victima) {
        victima.x = -999;      // si esto compilara, `protected` no protegería nada
    }
}
```

La restricción cierra ese agujero: `Delta` puede confiar en su propio estado
heredado, pero el encapsulamiento que `griego` definió frente al resto del mundo
se mantiene intacto.

## Detalle clave: decide el tipo **estático**, no el objeto real

La verificación la hace el compilador mirando el **tipo declarado** de la
referencia, sin importar qué objeto llegue en tiempo de ejecución. Aunque se
invoque el método pasando el mismo objeto `Delta` en los dos parámetros:

```java
Delta obj = new Delta();
obj.unMetodoD(obj, obj);      // `a` y `d` apuntan al MISMO objeto Delta
```

`a.x = 10` **sigue sin compilar**, porque `a` está declarada de tipo `Alpha`. El
control de acceso es enteramente de tiempo de compilación.

Como corolario, tampoco sirve intentar un *downcast* implícito ni forzar la
lectura; lo que sí funciona (y es legal, aunque huele mal) es castear
explícitamente cuando uno sabe que el objeto es un `Delta`:

```java
((Delta) a).x = 10;           // ✔ compila; falla en runtime si `a` no es un Delta
```

## Accesos que sí son válidos dentro de `Delta`

Todas estas formas tienen como receptor al propio objeto, cuyo tipo es `Delta`:

```java
public class Delta extends Alpha {
    void ejemplos() {
        x = 10;                  // ✔ receptor implícito: this (tipo Delta)
        this.x = 10;             // ✔
        otroMetodoA();           // ✔
        super.otroMetodoA();     // ✔ acceso explícito a la versión heredada
    }
}
```

## Cómo influye `protected` en la herencia — resumen

1. **Dentro del mismo paquete**, `protected` se comporta como acceso de paquete:
   lo ve cualquier clase vecina, sea o no subclase (inciso [a](a.md)). Si `Delta`
   estuviera declarada en `griego`, **las cuatro líneas serían válidas**.
2. **Fuera del paquete**, solo lo ven las subclases, y únicamente sobre
   referencias de su propio tipo (o de un subtipo).
3. Un miembro `protected` **sí se hereda** entre paquetes, a diferencia del
   acceso por defecto del inciso [d](d.md). Por eso `d.otroMetodoA()` funciona:
   `Delta` heredó ese método y lo posee como miembro propio.
4. Al sobrescribir un método `protected`, se puede **ampliar** su visibilidad a
   `public`, pero nunca reducirla a acceso de paquete o `private`.

## Versión corregida del método

```java
package romano;

import griego.*;

public class Delta extends Alpha {
    void unMetodoD(Alpha a, Delta d) {
        // a.x = 10;            // no se puede: haría falta un setter público en Alpha
        d.x = 10;               // ✔
        // a.otroMetodoA();     // no se puede por la misma razón
        d.otroMetodoA();        // ✔
    }
}
```
