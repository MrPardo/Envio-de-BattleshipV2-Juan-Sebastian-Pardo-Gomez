package ui;
import java.util.Scanner;
import model.Controller;


/*
*Analisis: Battleship V2 es una evolución del juego original, pasando de una dimensión a un tablero 
*bidimensional de 10x10 (Por medio de una matrix). Esta versión ofrece una experiencia más amplia, permitiendo 
*a los jugadores colocar barcos en diferentes orientaciones y posiciones en un espacio 2D, con 2 nuevos atributos para
*los barcos, x & y pero tambien su orientación.
*
*El juego consta de dos jugadores: un humano (0) y una máquina (1 computadora). Se han implementado diferentes 
*métodos para gestionar el registro de jugadores, la creación de tableros, la colocación de barcos, 
*los ataques y la determinación del ganador.
*
*menu():
*   Este método muestra el menú principal con las opciones disponibles y gestiona la navegación del usuario. 
*   Ofrece opciones para jugar en modo estándar, modo personalizado, ver estadísticas o salir del juego. 
*   El método utiliza un bucle do-while para mantener el menú activo hasta que el usuario elija salir.
*
*playStandar():
*   Implementa el modo estándar del juego con barcos predefinidos. Registra al jugador, crea un tablero 
*   y solicita al usuario que coloque sus barcos en posiciones válidas. Los barcos tienen tamaños y 
*   orientaciones predefinidas. Una vez colocados los barcos del jugador, el método coloca automáticamente 
*   los barcos de la computadora y comienza el juego.
*
*playCustom():
*   Ofrece un modo personalizado donde el jugador puede definir el número de barcos (entre 1 y 10), 
*   así como el nombre, tamaño y orientación de cada uno. Este modo brinda mayor flexibilidad y permite 
*   experiencias de juego más variadas. Al igual que en el modo estándar, los barcos de la computadora 
*   se colocan automáticamente después.
*
*startGame():
*   Gestiona la dinámica del juego por turnos. Primero, el jugador humano realiza un ataque seleccionando 
*   coordenadas X e Y. Luego, la computadora realiza su ataque con coordenadas generadas aleatoriamente. 
*   Después de cada ataque, se verifica si algún jugador ha perdido todos sus barcos para determinar el ganador.
*
*displayBoard() y displayEnemyBoard():
*   Estos métodos muestran el estado actual de los tableros. displayBoard() muestra el tablero completo 
*   con todos los elementos, mientras que displayEnemyBoard() oculta los barcos no atacados del oponente, 
*   mostrando solo agua y barcos atacados.
*
*placeComputerShips() y placeComputerShipsStandard():
*   Colocan automáticamente los barcos de la computadora en el tablero, ya sea siguiendo el patrón estándar 
*   o adaptándose al modo personalizado. Utilizan generación aleatoria de coordenadas y verifican que las 
*   posiciones sean válidas antes de colocar los barcos.
*
*getComputerAttackCoordinates():
*   Genera coordenadas aleatorias para los ataques de la computadora, asegurándose de que no se repitan 
*   ataques en posiciones ya atacadas anteriormente.
*
*Ejemplo de secuencia de juego:
*   1. El usuario inicia el programa y selecciona la opción 1 (modo estándar) en el menú.
*   2. Se registra el nombre del jugador mediante registerPlayer().
*   3. Se crea un tablero de juego con controller.createBoard(6).
*   4. El jugador coloca sus 6 barcos estándar uno por uno, indicando las coordenadas para cada uno.
*   5. La computadora coloca automáticamente sus barcos con placeComputerShipsStandard().
*   6. Comienza el juego con startGame(), alternando turnos entre el jugador y la computadora.
*   7. El jugador ataca seleccionando coordenadas, y se muestra el resultado con displayEnemyBoard().
*   8. La computadora ataca con coordenadas generadas por getComputerAttackCoordinates().
*   9. Se verifica después de cada ataque si algún jugador ha perdido todos sus barcos.
*   10. Cuando un jugador gana, se incrementa su contador de victorias y se vuelve al menú principal.
*   11. El usuario puede volver al menú principal para jugar otro modo o ver las estadísticas o salir.
*Mejoras Respecto a V1:
*   1. Tablero Bidimensional: Se ha pasado de un tablero unidimensional a uno 2D de 10x10, ofreciendo más estrategia.
*   2. Orientación de Barcos: Los barcos pueden colocarse horizontal o verticalmente.
*   3. Modos de Juego: Se incluyen dos modos (estándar y personalizado) para mayor variedad.
*   4. Interfaz Mejorada: Se proporciona más información visual sobre la colocación de barcos y el estado del juego.
*   5. Sistema de Coordenadas Intuitivo: Se utiliza un sistema de coordenadas 1-10 para la interacción con el usuario.
*   6. Manejo de Errores: Se implementa validación de entrada para evitar errores y mejorar la experiencia del usuario.
*   7. Estadísticas de Juego: Se registran y muestran las victorias de cada jugador.
*/

public class Executable {

    private Scanner input;
    private Controller controller;

    /**
     * Descripción: Método principal que inicia la aplicación.
     * Pre: Ninguna.
     * Pos: Se crea una instancia de Executable y se muestra el menú principal.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Executable exe = new Executable();
        exe.menu();
    }

    /**
     * Descripción: Constructor de la clase Executable.
     * Pre: Ninguna.
     * Pos: Se inicializa una nueva instancia de Executable y se llama al método initializer().
     */
    public Executable(){
        initializer();
    }

    /**
     * Descripción: Inicializa los componentes necesarios para la ejecución del programa.
     * Pre: Ninguna.
     * Pos: Se inicializa el scanner para entrada de usuario y el controlador del juego.
     */
    public void initializer() {
        input = new Scanner(System.in);
        controller = new Controller(); //
    }

    /**
     * Descripción: Muestra el menú principal y gestiona la navegación del usuario.
     * Pre: Los atributos input y controller deben estar inicializados.
     * Pos: Se ejecuta la opción seleccionada por el usuario hasta que elija salir.
     */
    public void menu() {
        int option = 0;
        do {
            System.out.println("Bienvenido a la simulación de Batalla Naval.");
            System.out.println("Te presentamos las siguientes opciones, ingresa:");
            System.out.println("1. Para jugar modo estandar");
            System.out.println("2. Para jugar modo personalizado");
            System.out.println("3. Para conocer cuántas veces ha ganado el jugador y cuántas veces la máquina");
            System.out.println("0. Para salir del programa");
            System.out.print("> ");
            option = input.nextInt();
            switch (option) {
                case 1:
                    playStandar();
                    break;
                case 2:
                    playCustom();
                    break;
                case 3:
                    showStats();
                    break;
                case 0:
                    System.out.println("Muchas gracias por jugar.");
                    break;
                default:
                    System.out.println("Opción inválida, intente con números del 0 al 3.");
            }
        } while (option != 0);     
    }

    /**
     * Descripción: Implementa el modo estándar del juego con barcos predefinidos.
     * Pre: Los atributos input y controller deben estar inicializados.
     * Pos: Se registra al jugador, se crea un tablero, se colocan los barcos y comienza el juego.
     */
    public void playStandar() {
        registerPlayer();
        controller.createBoard(6);
        
        System.out.println("A continuación, se te pedirá que coloques tus barcos en el tablero.");
        System.out.println("Los barcos no deben solaparse y deben ajustarse a las reglas de orientación.");
        System.out.println("Indica las coordenadas X, Y para cada barco. Recuerda que el tablero es de 10x10.");
        
        // Definir los barcos estándar
        String[] shipNames = {"Lancha", "Barco Médico", "Barco de Provisiones", "Barco de Munición", "Buque de Guerra", "Portaaviones"};
        int[] shipSizes = {1, 2, 3, 3, 4, 5};
        int[] shipOrientations = {0, 1, 0, 1, 0, 1}; // Orientaciones predefinidas
        String[] orientationText = {"horizontal", "vertical"};
        
        // Para cada barco
        for (int i = 0; i < shipNames.length; i++) {
            int startX, startY;
            int orientation = shipOrientations[i];
            boolean shipPlaced = false;
            
            System.out.println("Coloca tu " + shipNames[i] + " (" + shipSizes[i] + " casilla" + (shipSizes[i] > 1 ? "s" : "") + 
                              (shipSizes[i] > 1 ? ", " + orientationText[orientation] : "") + "): ");
            
            // Intentar colocar el barco hasta que se coloque correctamente
            while (!shipPlaced) {
                // Obtener coordenadas válidas
                startX = getValidCoordinate("X", shipNames[i], 1, 10) - 1; // Ajustar para índice base 0
                startY = getValidCoordinate("Y", shipNames[i], 1, 10) - 1; // Ajustar para índice base 0
                
                // Para barcos de más de 1 casilla, mostrar mensaje de colocación
                if (shipSizes[i] > 1) {
                    System.out.print("El " + shipNames[i] + " se colocará " + orientationText[orientation] + " en las coordenadas ");
                    for (int j = 0; j < shipSizes[i]; j++) {
                        System.out.print("(" + (startX + 1 + (orientation == 0 ? j : 0)) + ", " + 
                                        (startY + 1 + (orientation == 1 ? j : 0)) + ")");
                        if (j < shipSizes[i] - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println(".");
                }
                
                // Verificar si la posición es válida para el barco
                if (controller.verifyShipPosition(shipSizes[i], orientation, startX, startY, 0)) {
                    System.out.println("Posición no válida. Inténtelo de nuevo.");
                } else {
                    // Colocar el barco
                    String result = controller.placeShip(shipNames[i].toLowerCase(), shipSizes[i], orientation, startX, startY, 0);
                    
                    // Verificar si el barco se colocó correctamente
                    if (result.equals("Barco colocado exitosamente")) {
                        shipPlaced = true;
                        // Mostrar el tablero actualizado
                        System.out.println("Estado actual del tablero:");
                        displayBoard(0);
                    } else {
                        System.out.println("Error al colocar el barco: " + result);
                    }
                }
            }
        }
        
        // Colocar barcos para la computadora automáticamente
        placeComputerShipsStandard();
        
        // Mostrar el tablero final
        System.out.println("Finalmente la disposición de tu tablero con tus barcos queda así:");
        displayBoard(0);
        
        // Iniciar el juego
        startGame();
    }
    
    /**
     * Descripción: Obtiene una coordenada válida del usuario con manejo de errores.
     * Pre: El atributo input debe estar inicializado.
     * Pos: Se retorna una coordenada válida dentro del rango especificado.
     * @param axis Nombre del eje (X o Y).
     * @param shipName Nombre del barco o "ataque" si es una coordenada de ataque.
     * @param min Valor mínimo permitido para la coordenada.
     * @param max Valor máximo permitido para la coordenada.
     * @return Coordenada válida dentro del rango especificado.
     */
    private int getValidCoordinate(String axis, String shipName, int min, int max) {
        int coordinate = -1;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.println("Ingresa la coordenada " + axis + " " + 
                                  "del " + shipName + " (" + min + "-" + max + "): ");
                System.out.print("> ");
                coordinate = input.nextInt();
                
                if (coordinate < min || coordinate > max) {
                    System.out.println("Error: La coordenada debe estar entre " + min + " y " + max + ".");
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Error: Debes ingresar un número entero.");
                input.nextLine(); // Limpiar el buffer
            }
        }
        
        return coordinate;
    }

    /**
     * Descripción: Gestiona la dinámica del juego por turnos hasta que haya un ganador.
     * Pre: Los tableros deben estar creados y los barcos colocados para ambos jugadores.
     * Pos: Se determina un ganador, se incrementa su contador de victorias y se vuelve al menú principal.
     */
    public void startGame() {
        System.out.println("¡Muy bien! ¡Ahora vamos a jugar!");
        boolean gameOver = false;
        
        while (!gameOver) {
            // Turno del jugador
            int attackX = getValidCoordinate("X", "ataque", 1, 10) - 1; // Ajustar para índice base 0
            int attackY = getValidCoordinate("Y", "ataque", 1, 10) - 1; // Ajustar para índice base 0
            
            System.out.println("Atacarás el punto exacto de fila " + (attackY + 1) + ", columna " + (attackX + 1) + ".");
            
            // Procesar ataque del jugador
            String attackResult = controller.processAttack(attackX, attackY, 1); // Atacar al jugador 1 (computadora)
            System.out.println(attackResult);
            
            // Mostrar tablero rival
            System.out.println("Tablero rival:");
            displayEnemyBoard(1);
            
            // Verificar si el jugador ganó
            if (controller.hasPlayerLost(1)) {
                System.out.println("¡Felicidades! ¡Has ganado la partida!");
                controller.incrementPlayerWins(0);
                gameOver = true;
                break;
            }
            
            // En el método startGame(), reemplazar la generación aleatoria de coordenadas de la computadora
            
            // Turno de la computadora
            int[] computerCoords = getComputerAttackCoordinates();
            int computerX = computerCoords[0];
            int computerY = computerCoords[1];
            
            System.out.println("El jugador máquina realiza la jugada X: " + (computerX + 1) + ", Y: " + (computerY + 1));
            
            // Procesar ataque de la computadora
            String computerAttackResult = controller.processAttack(computerX, computerY, 0);
            if (computerAttackResult.contains("dado")) {
                System.out.println("¡Te han dado!");
            } else {
                System.out.println(computerAttackResult);
            }
            
            // Mostrar tablero del jugador
            System.out.println("Tablero del jugador:");
            displayBoard(0);
            
            // Verificar si la computadora ganó
            if (controller.hasPlayerLost(0)) {
                System.out.println("¡Has perdido la partida!");
                controller.incrementPlayerWins(1);
                gameOver = true;
            }
        }
    }
    
    /**
     * Descripción: Muestra las estadísticas de victorias de ambos jugadores.
     * Pre: El controlador debe estar inicializado.
     * Pos: Se muestran por pantalla las victorias del jugador humano y de la máquina.
     */
    public void showStats() {
        System.out.println("Estadísticas de juego:");
        System.out.println("Victorias del jugador: " + controller.getPlayerWins(0));
        System.out.println("Victorias de la máquina: " + controller.getPlayerWins(1));
    }

    /**
     * Descripción: Muestra el tablero completo del jugador especificado.
     * Pre: El tablero del jugador debe estar creado.
     * Pos: Se muestra por pantalla el estado actual del tablero.
     * @param playerIndex Índice del jugador (0: humano, 1: computadora).
     */
    public void displayBoard(int playerIndex) {
        int[][] boardState = controller.getBoardState(playerIndex);
        
        for (int y = 0; y < 10; y++) {
            System.out.print(" ");
            for (int x = 0; x < 10; x++) {
                System.out.print(boardState[x][y] + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Descripción: Muestra el tablero enemigo ocultando los barcos no atacados.
     * Pre: El tablero del jugador debe estar creado.
     * Pos: Se muestra por pantalla el tablero enemigo con los barcos no atacados ocultos.
     * @param playerIndex Índice del jugador (0: humano, 1: computadora).
     */
    public void displayEnemyBoard(int playerIndex) {
        int[][] boardState = controller.getBoardState(playerIndex);
        
        for (int y = 0; y < 10; y++) {
            System.out.print(" ");
            for (int x = 0; x < 10; x++) {
                // Solo mostrar agua (0) y barcos atacados (2 o 3)
                if (boardState[x][y] == 1) {
                    System.out.print("0 "); // Ocultar barcos no atacados
                } else {
                    System.out.print(boardState[x][y] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Descripción: Registra a los jugadores para la partida.
     * Pre: Los atributos input y controller deben estar inicializados.
     * Pos: Se registran el jugador humano y la computadora en el sistema.
     */
    public void registerPlayer() {
        input.nextLine(); // Clear buffer
        System.out.println("Registro de jugador");
        System.out.println("Ingrese su nombre:");
        String playerName = input.nextLine();
        
        // Registrar jugador humano (índice 0)
        controller.registerUser(playerName, 0, 0);
        
        // Registrar jugador computadora (índice 1)
        controller.registerUser("Computadora", 1, 1);
        
        System.out.println("¡Bienvenido " + playerName + "! Jugarás contra la Computadora.");
    }

    /**
     * Descripción: Implementa el modo personalizado del juego.
     * Pre: Los atributos input y controller deben estar inicializados.
     * Pos: Se registra al jugador, se crea un tablero con el número de barcos especificado,
     *      se colocan los barcos personalizados y comienza el juego.
     */
    public void playCustom() {
        registerPlayer();
        
        // Solicitar el número de barcos
        System.out.println("Modo personalizado");
        System.out.println("Ingrese el número de barcos que desea utilizar (1-10):");
        System.out.print("> ");
        int numShips = input.nextInt();
        
        // Validar el número de barcos
        if (numShips < 1) {
            numShips = 1;
            System.out.println("Número mínimo de barcos establecido a 1.");
        } else if (numShips > 10) {
            numShips = 10;
            System.out.println("Número máximo de barcos establecido a 10.");
        }
        
        controller.createBoard(numShips);
        
        // Arreglos para almacenar la información de los barcos
        String[] shipNames = new String[numShips];
        int[] shipSizes = new int[numShips];
        int[] shipOrientations = new int[numShips];
        String[] orientationText = {"horizontal", "vertical"};
        
        // Solicitar información para cada barco
        for (int i = 0; i < numShips; i++) {
            System.out.println("Barco " + (i + 1) + ":");
            
            // Nombre del barco
            System.out.println("Ingrese el nombre del barco:");
            System.out.print("> ");
            input.nextLine(); // Limpiar buffer
            shipNames[i] = input.nextLine();
            
            // Tamaño del barco
            do {
                System.out.println("Ingrese el tamaño del barco (1-5):");
                System.out.print("> ");
                shipSizes[i] = input.nextInt();
            } while (shipSizes[i] < 1 || shipSizes[i] > 5);
            
            // Orientación del barco
            do {
                System.out.println("Ingrese la orientación del barco (0: horizontal, 1: vertical):");
                System.out.print("> ");
                shipOrientations[i] = input.nextInt();
            } while (shipOrientations[i] != 0 && shipOrientations[i] != 1);
            
            // Colocar el barco
            int startX, startY;
            boolean shipPlaced = false;
            
            System.out.println("Coloca tu " + shipNames[i] + " (" + shipSizes[i] + " casilla" + (shipSizes[i] > 1 ? "s" : "") + 
                              (shipSizes[i] > 1 ? ", " + orientationText[shipOrientations[i]] : "") + "): ");
            
            // Intentar colocar el barco hasta que se coloque correctamente
            while (!shipPlaced) {
                // Obtener coordenadas válidas
                do {
                    System.out.println("Ingresa la coordenada X " + 
                                      (shipSizes[i] > 1 ? "de la primera casilla " : "") + 
                                      "del " + shipNames[i] + " (1-10): ");
                    System.out.print("> ");
                    startX = input.nextInt() - 1; // Ajustar para índice base 0
                } while (startX < 0 || startX > 9);
                
                do {
                    System.out.println("Ingresa la coordenada Y " + 
                                      (shipSizes[i] > 1 ? "de la primera casilla " : "") + 
                                      "del " + shipNames[i] + " (1-10): ");
                    System.out.print("> ");
                    startY = input.nextInt() - 1; // Ajustar para índice base 0
                } while (startY < 0 || startY > 9);
                
                // Para barcos de más de 1 casilla, mostrar mensaje de colocación
                if (shipSizes[i] > 1) {
                    System.out.print("El " + shipNames[i] + " se colocará " + orientationText[shipOrientations[i]] + " en las coordenadas ");
                    for (int j = 0; j < shipSizes[i]; j++) {
                        System.out.print("(" + (startX + 1 + (shipOrientations[i] == 0 ? j : 0)) + ", " + 
                                        (startY + 1 + (shipOrientations[i] == 1 ? j : 0)) + ")");
                        if (j < shipSizes[i] - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println(".");
                }
                
                // Verificar si la posición es válida para el barco
                if (controller.verifyShipPosition(shipSizes[i], shipOrientations[i], startX, startY, 0)) {
                    System.out.println("Posición no válida. Inténtelo de nuevo.");
                } else {
                    // Colocar el barco
                    String result = controller.placeShip(shipNames[i].toLowerCase(), shipSizes[i], shipOrientations[i], startX, startY, 0);
                    
                    // Verificar si el barco se colocó correctamente
                    if (result.equals("Barco colocado exitosamente")) {
                        shipPlaced = true;
                        // Mostrar el tablero actualizado
                        System.out.println("Estado actual del tablero:");
                        displayBoard(0);
                    } else {
                        System.out.println("Error al colocar el barco: " + result);
                    }
                }
            }
        }
        
        // Colocar barcos para la computadora automáticamente
        placeComputerShips(numShips, shipSizes);
        
        // Mostrar el tablero final
        System.out.println("Finalmente la disposición de tu tablero con tus barcos queda así:");
        displayBoard(0);
        
        // Iniciar el juego
        startGame();
    }
    
    /**
     * Descripción: Coloca barcos para la computadora en modo personalizado.
     * Pre: El tablero de la computadora debe estar creado.
     * Pos: Se colocan barcos para la computadora con tamaños similares a los del jugador.
     * @param numShips Número de barcos a colocar.
     * @param shipSizes Arreglo con los tamaños de los barcos del jugador.
     */
    private void placeComputerShips(int numShips, int[] shipSizes) {
        System.out.println("Colocando barcos para la computadora...");
        
        String[] computerShipNames = {"lancha", "barco", "submarino", "destructor", "acorazado", 
                                     "crucero", "portaaviones", "fragata", "corbeta", "buque"};
        
        for (int i = 0; i < numShips; i++) {
            boolean shipPlaced = false;
            int size = (i < shipSizes.length) ? shipSizes[i] : (int)(Math.random() * 5) + 1;
            int orientation = (int)(Math.random() * 2); // 0: horizontal, 1: vertical
            
            while (!shipPlaced) {
                int startX = (int)(Math.random() * 10);
                int startY = (int)(Math.random() * 10);
                
                // Verificar si la posición es válida
                if (!controller.verifyShipPosition(size, orientation, startX, startY, 1)) {
                    // Colocar el barco
                    String name = (i < computerShipNames.length) ? computerShipNames[i] : "barco" + i;
                    String result = controller.placeShip(name, size, orientation, startX, startY, 1);
                    
                    if (result.equals("Barco colocado exitosamente")) {
                        shipPlaced = true;
                    }
                }
            }
        }
        
        System.out.println("Barcos de la computadora colocados exitosamente.");
    }
    
    /**
     * Descripción: Coloca barcos para la computadora en modo estándar.
     * Pre: El tablero de la computadora debe estar creado.
     * Pos: Se colocan los barcos estándar para la computadora.
     */
    private void placeComputerShipsStandard() {
        System.out.println("Colocando barcos para la computadora...");
        
        // Definir los barcos estándar para la computadora
        String[] shipNames = {"lancha", "barco médico", "barco de provisiones", "barco de munición", "buque de guerra", "portaaviones"};
        int[] shipSizes = {1, 2, 3, 3, 4, 5};
        int[] shipOrientations = {0, 1, 0, 1, 0, 1}; // Orientaciones predefinidas
        
        for (int i = 0; i < shipNames.length; i++) {
            boolean shipPlaced = false;
            int orientation = shipOrientations[i];
            
            while (!shipPlaced) {
                int startX = (int)(Math.random() * 10);
                int startY = (int)(Math.random() * 10);
                
                // Verificar si la posición es válida
                if (!controller.verifyShipPosition(shipSizes[i], orientation, startX, startY, 1)) {
                    // Colocar el barco
                    String result = controller.placeShip(shipNames[i], shipSizes[i], orientation, startX, startY, 1);
                    
                    if (result.equals("Barco colocado exitosamente")) {
                        shipPlaced = true;
                    }
                }
            }
        }
        
        System.out.println("Barcos de la computadora colocados exitosamente.");
    }

    /**
     * Descripción: Genera coordenadas aleatorias para los ataques de la computadora.
     * Pre: El tablero del jugador humano debe estar creado.
     * Pos: Se generan coordenadas aleatorias que no han sido atacadas previamente.
     * @return Arreglo de dos enteros con las coordenadas X e Y del ataque.
     */
    private int[] getComputerAttackCoordinates() {
        int[] coords = new int[2];
        boolean validAttack = false;
        
        while (!validAttack) {
            coords[0] = (int)(Math.random() * 10); // X
            coords[1] = (int)(Math.random() * 10); // Y
            
            // Verificar si esta coordenada ya ha sido atacada
            int cellState = controller.getCellState(coords[0], coords[1], 0);
            
            // Si la celda es agua (0) o barco (1), es un ataque válido
            if (cellState == 0 || cellState == 1) {
                validAttack = true;
            }
        }
        
        return coords;
    }
}