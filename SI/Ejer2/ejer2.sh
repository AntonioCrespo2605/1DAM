#!/bin/bash

if [ $# -ge 3 ];
then

	while true 
	do
		echo "elije una opción:"
		echo "[V]Vacío"
		echo "[P]Primeras"
		echo "[D]Doble"
		echo "[M]Mitad"
		echo "[F]Fin"
		read op
		
		case $op in
			M | m)
				mitad=$(($# / 2))
				mitad2=$(($mitad + 1))
				if [  $(expr $# % 2) -eq 0 ]
				then
					eval echo \$$mitad
				fi
				
				eval echo \$$mitad2;;
					
			D | d)
				if [ $1 -eq $2 ]
				then
					echo "$1 y $2 son iguales. No hay números entre ellos"
					break
				elif [ $1 -gt $2 ]
				then
					min=$2
					max=$1
				else
					min=$1
					max=$2
				fi
				for (( i=$min+1 ; i<$max ; i++ ))
				do
					echo $(($i*2))
				done;;
			P | p)
				echo "Escribe un fichero"
				read fic
				[ -f $fic  ] && ( [ -r $fic ] && ( head -n5 $fic ) || echo "no tiene permisos de lectura" ) || echo "no es un fichero";;
			V | v) 
				args=("$@")
				for (( i=0; i<$#; i++ ))
				do
					[ -f ${args[$i]} ] && (cat ejer2.sh >> ${args[$i]}) && echo ${args[$i]} "Es un fichero"  || echo ${args[$i]} "No es un fichero"
				done;
				echo "";;
			f | F) echo "fin"
			exit;;
			*) echo "opción incorrecta"
		esac
	done
else
	echo "Error en la introducción de argumentos"
fi
