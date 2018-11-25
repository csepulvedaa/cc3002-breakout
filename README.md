# cc3002-breakout
Tarea2 Breakout
# Implementación de Bricks
Cada Tipo de Brick extiende la clase AbstractBrick, la cual tiene definido el comportamiento de cada uno de los bricks
(Ver Javadoc de cada clase brick para ver metodos)
# Implementación de Level
Cada nivel se implementó en la clase GameLevel como una lista enlazada, cada nivel contiene al nivel que lo sigue
La clase gamelevel implementa la interfaz Level 
(Ver Javadoc de Intefaz Level y Clase Gamelevel para ver los metodos)
# Implementación de Game
La clase game contiene los metodos del controlador del juego, escencialmente los mismos que el Facade de nuestro proyecto más los necesarios para implementar patrones de diseño
# Interfaz Gamecontroller de Game como Visitor
La interfaz gamecontroller de la clase game contiene los métodos para agregar los respectivos puntos según el tipo de brick que se haya destruido
## Game Como Observer/Visitor y Brick como Observable/Visitable
Al momento de crear y agregar los bricks a nuestra lista de niveles, observamos cada brick con nuestra clase game para notificar los cambios
Los cambios que notifica cada brick van en el método hit de la clase AbstractBrick, cada tipo de brick hereda este método, si un brick se destruye manda un mensaje Update a game.
El metodo Update en la clase Game castea el objeto a tipo brick, al momento de hacer el cast llama al método accept(this) que acepta a game como su visitante.
Cada brick sabe que hacer con su visitante,
### EJ:Si el tipo de brick que recibe el visitante es tipo glass, va al método accepsobreescrito en la clase GlassBrick donde llama al método addGlassPoints(this) del propio visitante, que hereda de la Interfaz Gamecontroller.

Si A un brick de tipo glass lo visita un game, a ese game se le agregan 50 puntos a su marcador.
Si a un brick de tipo Wooden lo visita un game, a ese game se le agregan 200 puntos a su marcador.
Si a un brick de tipo Metal lo visita un game, a ese game se le agrega una Bola.
# Funcionalidad y Como correr el programa
Para correr este programa es necesario usar un computador , el programa se ejecuta desde un IDE, agregando el plugin JUNIT 4 teniendo Java version 8 instalado.
Se debe correr el test BIGTEST, hay test unitarios para mostrar un coverage del 90% de lineas por paquete.
Los test muestran la lógica detrás de la implementación del juego de arcade Breakout, testeando distintos esccenarios como la creación de niveles, bricks y la interacción entre las clases.


