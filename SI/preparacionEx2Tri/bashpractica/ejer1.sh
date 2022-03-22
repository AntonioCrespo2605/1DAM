#!bin/bash
while true 
	do
		echo "elije una opción:"
		echo "[Uno]Sumar argumentos múltiplos de 3"
		echo "[Dos]Ficheros con permisos de escritura"
		echo "[Tres]Ocurrencias del nombre del script"
		echo "[Fin]Fin"
		read op
		
		case $op in
			Uno | uno | U)
				suma=0
				args=($@)
				for (( i=0 ; i<$# ; i++ ))
				do
					if [ $(expr ${args[$i]} % 3 ) -eq 0 ]
					then
						suma=$(expr $suma + ${args[$i]} )
					fi
				done
				echo "la suma es $suma";;	
			Dos | dos | D)
				echo "Escribe el nombre de un fichero"
				read fic
				[ -w $fic ] && echo "Es un fichero y tiene permisos de escritura" || echo "no existe ese fichero o no tiene permisos de escritura";;
			Tres | tres | T)
				veces=0
				until [ $# -eq 0 ]
				do
					if [ $1 = $0 ]
					then
						veces=$(expr $veces + 1)
					fi
					shift
				done
				echo "el nombre del script coincide $veces veces con los argumentos instroducidos ";;
			Fin | fin | F) 
			echo "fin del programa"
			exit;;
			*) echo "opción incorrecta"
		esac
	done
	
