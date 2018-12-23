# CC3002-breakout Tarea 3
Tarea 3 Breakout
En este readme se mostrara lo necesario para entender este reposiorio.
Se utilizó el codigo base propuesto por el auxiliar del curso Juan Pablo Silva

# Correr la Aplicacion
Para correr la aplicacion Breakout, basta con ejecutar el main de la clase BasicAPP, el cual lanza la interfaz gráfica del juego, al ejecutar se verá algo como esto

![alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_2.png "Breakout Al Inicio")

El juego comienza sin ningún nivel configurado, lo cual sólo permite mover la Barra alrededor de los bordes de la pantalla, para comenzar a jugar se presiona la tecla N.
Esta Acción settea un nuevo nivel para el juego con parámetros por defecto y lo muestra en la pantalla (llamando al metodo newlevel dentro de la clase BasicApp), seteando un nuevo nivel con 20 bricks, 0,5 de prob de ser Glassbrick, 0.2 de prob de ser MetalBrick y una seed al azar usando la funcion System.currentTimeMillis().

Cada vez que se presiona esta tecla, se genera un nuevo nivel con los parametros dados, agregandolo a la lista de niveles jugables.

![alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_1.png "Breakout Al Iniciar un Nuevo Nivel")

Una vez que se setea el nivel y se muestran los bricks en pantalla es posible lanzar la bolita con la tecla espacio y comenzar a jugar.
## Elementos en pantalla
1. Barra: se muestra en la parte inferior de la pantalla y permanece dentro de los screenbounds,Una vez que se pierde o se gana el juego la barra no es movible
2. Bolas: Hay sólo una bola en todo momento en la pantalla, si la bola se pierde, se disminute el contador de bolas y la bola se posiciona en el centro de la barra
3. Brick: corresponden a los bloques dentro del nivel,cada brick tiene una textura y sonido distinto según el tipo de brick implementado en la lógica de la tarea2
4. Informacion La información se muestra en un panel al costado del juego, para ver la información del juego se debe presionar la tecla TABULACIÓN.
Esta información muestra el puntaje a obtener y el puntaje acumulado entre niveles 
## Acciones minimas implementadas e Interacciones en Pantalla .
1. Barra: 
La barra se mueve en 2 direcciones en forma horizontal dentro de los ScreenBounds, la bola rebota con la barra.
2. Nueva Bola:
El juego automáticamente crea una nueva bola si hay bolas disponibles en el centro de la barra independiente de donde esta se encuentre al hacer la invocacion, juan pablo publico en el foro que [podía mover la barra y la bolita juntos](https://www.u-cursos.cl/ingenieria/2018/2/CC3002/1/foro/o/23327201) así que también lo agregue en el readme, al presionar la tecla ESPACIO la bolita sale disparada.

Cuando la bola cae a la parte inferior de la pantalla se considera una bola perdida y se pierde una bola disponible y esta es retirada del juego para luego posicionar una nueva bola en la pantalla en caso de quedar bolas disponibles.

La bola es disparada al presionar la tecla ESPACIO.

4. Rebotes: La bola rebota al golpear las paredes y no sale del espacio visible de la pantalla, también rebota con la barra y bricks.
Si la bola rebota con la pared inferior se pierde una bola.

5.Nuevo Nivel: Ya se explicó en el funcionamiento de la aplicación como funciona esta acción.

6.Niveles: Los bricks se posicionan dinámicamente contando filas y bloques en tiempo de ejecución , es posible posicionar mas de los bloques por defecto en pantalla ordenados por filas de 10 bricks, los bricks se muestran desordenados dejando un espacio entre la primera fila de bricks y el "techo" de la pantalla.

![alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_3.png "Display Dinámico de Bricks")
 
7. Ayuda Nuevo Nivel: el metodo clearScreen dentro de la Gui limpia la pantalla antes de desplegar nuevos bricks de otro nivel.
8. Perder o ganar el juego: Cuando se pierde el juego (No quedan bolas disponibles) o se gana el juego (Se gana el último nivel jugable) el jugador no puede seguir interactuando con el nivel y se muestra un nuevo mensaje de "Retry".

Ganar juego.

![alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_4.png "Juego Ganado")

Perder juego.

![alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_5.png "Juego Perdido")

Al presionar la tecla R se reinicia el juego y se da la opción de volver a jugar.
# FEATURES IMPLEMENTADAS
Para evitar EXCESIVAS (y digo excesivas ya que en un momento tenía mas de 9 llamadas a estos metodos, tanto para los sonidos al golpe como para las texturas como para desplegarlos en pantalla) a los métodos isWoodenBrick, isMetalBrick, IsGlassbrick dentro de la lógica de los bricks decidí crear un BrickFactory, que me asignaba un tipo METALBRICK, GLASSBRICK, WOODENBRICK, esto para simplificar lineas de codigo y para manejar mejor las colisiones entre la bola y los bricks y lo que sucedía entre ellos.
Así la llamada al método IsWoodenBrick()... etc sólo se hace en la creación de la entidad Brick en el método BrickFactory.

## Mayores:
1. Estado Distinto: Cuando un brick es golpeado este modifica la textura agregada al juego, en el caso del glassbrick es destruido automáticamente


2.-Mecanismo de Testing:Al presionar la tecla "T" permite simular un golpe a un brick con un click del mouse, llamando al método Onhit() del componente lógico del Brick para cada entidad brick, esto permite pasar los niveles clickeando los brick, donde un click se considera un golpe.


Esto no aplica para las colisiones de la bola con los bricks, sólo para testear el pasar niveles clickeando los bricks con el mouse (pregunté a JP en auxiliar y me dijo que esto funcionaba como testing).

## Menores:
1. Sonido al golpe: cuando la bola golpea un brick se reproduce un sonido distinto para cada tipo de brick, cuando un brick se destruye se escucha un sonido especial.


2. Chispas Estrellas: al destruir un brick aparecen particulas que desaparecen rápidamente, debido a lo molesto de las particulas las implementé al destruir el brick no en cada golpe para evitar saturar la pantalla de particulas.


# BUGS ENCONTRADOS:
El panel se solapa al presionar tab, mostrando la información legible la primera vez que se muestra en pantalla, no logré solucionar esto a tiempo para la entrega.
Primera vez que se presiona tab.

![alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_6.png "Informacion Legible")

Segunda vez que se presiona tab.

[alt text](https://raw.githubusercontent.com/csepulvedaa/cc3002-breakout/master/src/main/resources/assets/screenshots/Screenshot_7.png "Informacion Solapada")

# Programado y Ejecutado con:
1. IntelliJ Usando Maven Dependency Management
2. FXGL Library 0.54

# Testing
 Al usar el código proporcionado por el auxiliar no logré crear tests que agreguen 90% de lineas por paquete y mis test no eran compatibles con las clases proporcionadas en este código
# Funcionalidad y como correr el programa
Para correr este programa es necesario usar un computador , el programa se ejecuta desde un IDE, agregando la libreria FXGL mediante maven teniendo Java version 8 instalado.

Se debe correr el main de la clase BasicAPP.

La gui muestra la lógica detrás de la implementación del juego de arcade Breakout.


# Autor
Cristobal Ignacio Sepúlveda Alvarez.

Diciembre 2018.

Todas las Imágenenes y sonídos fueron encontrados en repositorios grátis de internet o creados por mi mismo.






