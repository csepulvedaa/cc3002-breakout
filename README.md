# cc3002-breakout Tarea 3
Tarea 3 Breakout
En este readme se mostrara lo necesario para entender este reposiorio.
Se utilizó el codigo base propuesto por el auxiliar del curso Juan Pablo Silva

# Correr la Aplicacion
Para correr la aplicacion Breakout, basta con ejecutar el main de la clase BasicAPP, el cual lanza la interfaz gráfica del juego, al ejecutar se verá algo como esto
ss1
El juego comienza sin ningún nivel configurado, lo cual sólo permite mover la Barra alrededor de los bordes de la pantalla, para comenzar a jugar se presiona la tecla N.
Esta Acción settea un nuevo nivel para el juego con parámetros por defecto y lo muestra en la pantalla (llamando al metodo newlevel dentro de la clase BasicApp), seteando un nuevo nivel con 20 bricks, 0,5 de prob de ser Glassbrick, 0.2 de prob de ser MetalBrick y una seed al azar usando la funcion System.currentTimeMillis().
Cada vez que se presiona esta tecla, se genera un nuevo nivel con los parametros dados, agregandolo a la lista de niveles jugables.
ss2
Una vez que se setea el nivel y se muestran los bricks en pantalla es posible lanzar la bolita con la tecla espacio y comenzar a jugar.
## Elementos en pantalla
+Barra, se muestra en la parte inferior de la pantalla y permanece dentro de los screenbounds,Una vez que se pierde o se gana el juego la barra no es movible
+Bolas hay sólo una bola en todo momento en la pantalla, si la bola se pierde, se disminute el contador de bolas y la bola se posiciona en el centro de la barra
+Brick corresponden a los bloques dentro del nivel,cada brick tiene una textura y sonido distinto según el tipo de brick implementado en la lógica de la tarea2
+Informacion La información se muestra en un panel al costado del juego, para ver la información del juego se debe presionar la tecla TABULACIÓN.
Esta información muestra el puntaje a obtener y el puntaje acumulado entre niveles 
## Acciones minimas implementadas.
+Mover Barra: La barra se mueve en 2 direcciones en forma horizontal dentro de los ScreenBounds.
+Nueva Bola: El juego automáticamente crea una nueva bola si hay bolas disponibles en el centro de la barra, juan pablo publico en el foro  [podía mover la barra y la bolita juntos](https://www.u-cursos.cl/ingenieria/2018/2/CC3002/1/foro/o/23327201) así que también lo agregue en el readme, al presionar la tecla ESPACIO la bolita sale disparada.
+Nuevo Nivel: Ya se explicó en el funcionamiento de la aplicación como funciona esta acción.



 



# Implementación de Level
Cada nivel se implementó en la clase GameLevel como una lista enlazada, cada nivel contiene al nivel que lo sigue
La clase gamelevel implementa la interfaz Level 
(Ver Javadoc de Intefaz Level y Clase Gamelevel para ver los metodos)
# Implementación de Game
La clase game contiene los metodos del controlador del juego, escencialmente los mismos que el Facade de nuestro proyecto más los necesarios para implementar patrones de diseño
# Interfaz Gamecontroller de Game como Visitor
La interfaz gamecontroller implementada por la clase game contiene los métodos para agregar los respectivos puntos según el tipo de brick que se haya destruido
## Game Como Observer/Visitor y Brick como Observable/Visitable
Al momento de crear y agregar los bricks a nuestra lista de niveles, observamos cada brick con nuestra clase game para notificar los cambios
Los cambios que notifica cada brick van en el método hit de la clase AbstractBrick, cada tipo de brick hereda este método, si un brick se destruye manda un mensaje Update a game.
El metodo Update en la clase Game castea el objeto a tipo brick, al momento de hacer el cast llama al método accept(this) que acepta a game como su visitante.
Cada brick sabe que hacer con su visitante,
### EJ:Si el tipo de brick que recibe el visitante es tipo glass, va al método accept(this) sobreescrito en la clase GlassBrick donde llama al método addGlassPoints(this) del propio visitante, que hereda de la Interfaz Gamecontroller.

Si A un brick de tipo glass lo visita un game, a ese game se le agregan 50 puntos a su marcador.
Si a un brick de tipo Wooden lo visita un game, a ese game se le agregan 200 puntos a su marcador.
Si a un brick de tipo Metal lo visita un game, a ese game se le agrega una Bola.
# Testing
Se agregaron test unitarios para GameLevel,Bricks junto con el test BigTest agrega el resto de coverage de lineas necesario para cumplir mas del 95% de lineas por paquete 
# Funcionalidad y Como correr el programa
Para correr este programa es necesario usar un computador , el programa se ejecuta desde un IDE, agregando el plugin JUNIT 4 teniendo Java version 8 instalado.
Se debe correr el test BIGTEST, hay test unitarios para mostrar un coverage del 90% de lineas por paquete.
Los test muestran la lógica detrás de la implementación del juego de arcade Breakout, testeando distintos esccenarios como la creación de niveles, bricks y la interacción entre las clases.




