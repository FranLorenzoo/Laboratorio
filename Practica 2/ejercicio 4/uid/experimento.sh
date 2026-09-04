#!/bin/bash
# Experimento de serialVersionUID.
#
#   v1 : UID = 1L, campos {codigo, cantidad}          <- se serializa con esta
#   v2 : UID = 1L, se agrega 'precio'                 -> compatible
#   v3 : UID = 2L, mismos campos que v1               -> incompatible
#   v4 : sin UID declarado, se agrega 'precio'        -> incompatible
#
# Uso: ./experimento.sh
cd "$(dirname "$0")" || exit 1
rm -rf out pieza.ser && mkdir -p out

echo "=============================================================="
echo "PASO 1 - Serializar con v1 (UID = 1L)"
echo "=============================================================="
javac -d out v1/Pieza.java Escribir.java && java -cp out Escribir
echo

for v in v2 v3 v4; do
    case $v in
        v2) desc="MISMO UID (1L), campo 'precio' agregado" ;;
        v3) desc="UID CAMBIADO a 2L, misma estructura" ;;
        v4) desc="SIN UID declarado, campo 'precio' agregado" ;;
    esac
    echo "=============================================================="
    echo "PASO 2 - Leer con $v : $desc"
    echo "=============================================================="
    rm -rf out/*.class
    javac -d out "$v/Pieza.java" Leer.java && java -cp out Leer
    echo
done

rm -rf out
