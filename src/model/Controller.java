package model;

/**
 * Descripción: Clase controladora que gestiona la lógica del juego Battleship.
 * Esta clase actúa como intermediario entre la interfaz de usuario y los modelos de datos,
 * gestionando los usuarios, tableros, barcos y la lógica de juego.
 */
public class Controller {
    
    private User[] users;
    
    /**
     * Descripción: Constructor de la clase Controller.
     * Pre: Ninguna.
     * Pos: Se inicializa un nuevo controlador con espacio para dos usuarios (humano y computadora).
     */
    public Controller() {
        // Inicializamos el arreglo para dos usuarios (humano y computadora)
        users = new User[2];
    }
    
    /**
     * Descripción: Crea los tableros para ambos jugadores.
     * Pre: Los usuarios deben estar registrados.
     * Pos: Se inicializan los tableros para ambos jugadores con el número de barcos especificado.
     * @param numShips Número de barcos que tendrá cada tablero.
     */
    public void createBoard(int numShips) {
        // Verificar que los usuarios estén inicializados
        if (users[0] != null) {
            users[0].initializeBoard(numShips);
        }
        if (users[1] != null) {
            users[1].initializeBoard(numShips);
        }
    }
    
    /**
     * Descripción: Coloca un barco en el tablero de un jugador.
     * Pre: El jugador debe existir y su tablero debe estar inicializado.
     * Pos: Se coloca el barco en el tablero si la posición es válida.
     * @param name Nombre del barco.
     * @param size Tamaño del barco.
     * @param orientation Orientación del barco (0: horizontal, 1: vertical).
     * @param x Coordenada X inicial del barco.
     * @param y Coordenada Y inicial del barco.
     * @param player Índice del jugador (0: humano, 1: computadora).
     * @return Mensaje indicando si el barco se colocó correctamente o el error ocurrido.
     */
    public String placeShip(String name, int size, int orientation, int x, int y, int player) {
        // Verificar que el jugador exista
        if (player < 0 || player >= users.length || users[player] == null) {
            return "Jugador no válido";
        }
        
        // Verificar que el tablero esté inicializado
        if (!users[player].hasInitializedBoard()) {
            return "El tablero no ha sido inicializado";
        }
        
        // Colocar el barco en el tablero del jugador
        return users[player].addShip(name, size, orientation, x, y);
    }
    
    /**
     * Descripción: Verifica si una coordenada está ocupada en el tablero de un jugador.
     * Pre: El jugador debe existir y su tablero debe estar inicializado.
     * Pos: Se retorna true si la coordenada está ocupada, false en caso contrario.
     * @param x Coordenada X a verificar.
     * @param y Coordenada Y a verificar.
     * @param player Índice del jugador (0: humano, 1: computadora).
     * @return true si la coordenada está ocupada, false en caso contrario.
     */
    public boolean verifityCoord(int x, int y, int player) {
        // Verificar que el jugador exista
        if (player < 0 || player >= users.length || users[player] == null) {
            return false;
        }
        
        // Verificar que el tablero esté inicializado
        if (!users[player].hasInitializedBoard()) {
            return false;
        }
        
        // Verificar si la coordenada está ocupada
        return users[player].isCoordinateOccupied(x, y);
    }
    
    /**
     * Descripción: Verifica si un barco puede ser colocado en una posición específica.
     * Pre: El jugador debe existir y su tablero debe estar inicializado.
     * Pos: Se retorna true si la posición está ocupada o es inválida, false si está disponible.
     * @param size Tamaño del barco.
     * @param orientation Orientación del barco (0: horizontal, 1: vertical).
     * @param startX Coordenada X inicial del barco.
     * @param startY Coordenada Y inicial del barco.
     * @param player Índice del jugador (0: humano, 1: computadora).
     * @return true si la posición está ocupada o es inválida, false si está disponible.
     */
    public boolean verifyShipPosition(int size, int orientation, int startX, int startY, int player) {
        // Verificar que el jugador exista
        if (player < 0 || player >= users.length || users[player] == null) {
            return false;
        }
        
        // Verificar que el tablero esté inicializado
        if (!users[player].hasInitializedBoard()) {
            return false;
        }
        
        // Verificar si la posición del barco es válida
        return users[player].isShipPositionOccupied(size, orientation, startX, startY);
    }
    
    /**
     * Descripción: Registra un nuevo jugador en el sistema.
     * Pre: El índice del jugador debe ser válido (0 o 1).
     * Pos: Se crea un nuevo usuario y se inicializa su tablero con 6 barcos por defecto.
     * @param name Nombre del jugador.
     * @param isHuman Indica si el jugador es humano (0) o computadora (1).
     * @param playerIndex Índice del jugador en el arreglo de usuarios.
     */
    public void registerUser(String name, int isHuman, int playerIndex) {
        if (playerIndex >= 0 && playerIndex < users.length) {
            users[playerIndex] = new User(name, isHuman, "user" + playerIndex);
            // Inicializar el tablero con 6 barcos por defecto
            users[playerIndex].initializeBoard(6);
        }
    }
    
    /**
     * Descripción: Procesa un ataque a una coordenada específica del tablero de un jugador.
     * Pre: El jugador objetivo debe existir y su tablero debe estar inicializado.
     * Pos: Se actualiza el estado del tablero según el resultado del ataque y se verifica si el jugador ha perdido.
     * @param x Coordenada X del ataque.
     * @param y Coordenada Y del ataque.
     * @param targetPlayer Índice del jugador objetivo del ataque.
     * @return Mensaje indicando el resultado del ataque.
     */
    public String processAttack(int x, int y, int targetPlayer) {
        // Verificar que el jugador objetivo exista
        if (targetPlayer < 0 || targetPlayer >= users.length || users[targetPlayer] == null) {
            return "Jugador objetivo no válido";
        }
        
        // Verificar que el tablero esté inicializado
        if (!users[targetPlayer].hasInitializedBoard()) {
            return "El tablero del jugador objetivo no ha sido inicializado";
        }
        
        // Procesar el ataque en el tablero del jugador objetivo
        String result = users[targetPlayer].processAttack(x, y);
        
        // Verificar si el jugador ha perdido
        if (users[targetPlayer].hasLost()) {
            // Incrementar el contador de victorias del jugador contrario
            int winnerIndex = (targetPlayer == 0) ? 1 : 0;
            if (users[winnerIndex] != null) {
                users[winnerIndex].incrementWinCount();
            }
        }
        
        return result;
    }
    
    /**
     * Descripción: Obtiene un usuario según su índice.
     * Pre: El índice debe ser válido (0 o 1).
     * Pos: Se retorna el usuario correspondiente al índice o null si no existe.
     * @param index Índice del usuario a obtener.
     * @return Usuario correspondiente al índice o null si no existe.
     */
    public User getUser(int index) {
        if (index >= 0 && index < users.length) {
            return users[index];
        }
        return null;
    }
    
    /**
     * Descripción: Obtiene el estado actual del tablero de un jugador.
     * Pre: El jugador debe existir y su tablero debe estar inicializado.
     * Pos: Se retorna una matriz con el estado actual del tablero.
     * @param playerIndex Índice del jugador.
     * @return Matriz 10x10 con el estado del tablero o una matriz vacía si no existe.
     */
    public int[][] getBoardState(int playerIndex) {
        if (playerIndex >= 0 && playerIndex < users.length && users[playerIndex] != null && users[playerIndex].hasInitializedBoard()) {
            return users[playerIndex].getBoard().getBoardState();
        }
        // Retornar un tablero vacío si no hay tablero inicializado
        return new int[10][10];
    }
    
    /**
     * Descripción: Verifica si un jugador ha perdido todos sus barcos.
     * Pre: El jugador debe existir.
     * Pos: Se retorna true si el jugador ha perdido, false en caso contrario.
     * @param playerIndex Índice del jugador a verificar.
     * @return true si el jugador ha perdido, false en caso contrario.
     */
    public boolean hasPlayerLost(int playerIndex) {
        if (playerIndex >= 0 && playerIndex < users.length && users[playerIndex] != null) {
            return users[playerIndex].hasLost();
        }
        return false;
    }
    
    /**
     * Descripción: Incrementa el contador de victorias de un jugador.
     * Pre: El jugador debe existir.
     * Pos: Se incrementa en 1 el contador de victorias del jugador.
     * @param playerIndex Índice del jugador.
     */
    public void incrementPlayerWins(int playerIndex) {
        if (playerIndex >= 0 && playerIndex < users.length && users[playerIndex] != null) {
            users[playerIndex].incrementWinCount();
        }
    }
    
    /**
     * Descripción: Obtiene el número de victorias de un jugador.
     * Pre: El jugador debe existir.
     * Pos: Se retorna el número de victorias del jugador.
     * @param playerIndex Índice del jugador.
     * @return Número de victorias del jugador o 0 si no existe.
     */
    public int getPlayerWins(int playerIndex) {
        if (playerIndex >= 0 && playerIndex < users.length && users[playerIndex] != null) {
            return users[playerIndex].getWinCount();
        }
        return 0;
    }
    
    /**
     * Descripción: Obtiene el estado de una celda específica del tablero de un jugador.
     * Pre: El jugador debe existir y su tablero debe estar inicializado.
     * Pos: Se retorna el estado de la celda especificada.
     * @param x Coordenada X de la celda.
     * @param y Coordenada Y de la celda.
     * @param playerIndex Índice del jugador.
     * @return Estado de la celda (0: agua, 1: barco, 2: agua atacada, 3: barco atacado) o -1 si hay error.
     */
    public int getCellState(int x, int y, int playerIndex) {
        if (playerIndex >= 0 && playerIndex < users.length && users[playerIndex] != null && users[playerIndex].hasInitializedBoard()) {
            return users[playerIndex].getBoard().getCellState(x, y);
        }
        return -1; // Error o fuera de límites
    }
}