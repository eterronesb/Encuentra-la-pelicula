Este proyecto es un juego de consola para adivinar el titulo de una pelicula con el juego del ahorcado.
El programa selecciona un titulo aleatoriamente de un archivo que se crea al cargarlo, que contiene las peliculas.
Los jugadores tienen 10 intentos para adivinar de que pelicula se trata.

Caracteristicas
-Carga las peliculas desde un archivo binario que se genera automaticamente si no existe.
-Los jugadores pueden elegir entre adivinar una letra, adivinar el titulo completo o salir del juego.
-Contiene un sistema de puntaje:
  - +10 puntos por cada letra correcta.
  - -10 puntos por cada letra incorrecta.
  - +20 puntos por adivinar correctamente el titulo completo.
  - -20 puntos por fallar al adivinar el titulo completo.
-Continuamente se muestra el progreso actual, las letras incorrectas, los intentos restantes y el puntaje.

 Personalizacion
 Puedes modificar el contenido del archivo movies.dat, editando el metodo createMoviesFiles() en la clase Game. Cambia o añade titulos.
