package model;

/**
 * Descripción: Clase que representa a un usuario del juego Battleship.
 * Esta clase almacena la información del jugador, gestiona su tablero de juego
 * y mantiene un registro de sus victorias.
 */
public class User {
    
    private String name;
    private int winCount;
    private Board board;
    private int isHuman; // 1 for computer, 0 for human
    
    /**
     * Descripción: Constructor de la clase User.
     * Pre: Ninguna.
     * Pos: Se inicializa un nuevo usuario con el nombre, tipo y ID especificados.
     * @param name Nombre del usuario.
     * @param isHuman Indica si el usuario es humano (0) o computadora (1).
     * @param userId Identificador único del usuario.
     */
    public User(String name, int isHuman, String userId) {
        this.name = name;
        this.winCount = 0;
        this.board = null;
        this.isHuman = isHuman;
    }
    
    // User profile management methods
    /**
     * Descripción: Obtiene el nombre del usuario.
     * Pre: Ninguna.
     * Pos: Se retorna el nombre del usuario.
     * @return Nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Descripción: Obtiene el número de victorias del usuario.
     * Pre: Ninguna.
     * Pos: Se retorna el contador de victorias del usuario.
     * @return Número de victorias del usuario.
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * Descripción: Verifica si el usuario es humano o computadora.
     * Pre: Ninguna.
     * Pos: Se retorna el valor que indica si el usuario es humano o computadora.
     * @return 0 si es humano, 1 si es computadora.
     */
    public int isHuman() {
        return isHuman;
    }
    
    /**
     * Descripción: Establece el contador de victorias del usuario.
     * Pre: Ninguna.
     * Pos: Se actualiza el contador de victorias al valor especificado.
     * @param winCount Nuevo valor para el contador de victorias.
     * @return Mensaje confirmando la actualización del contador.
     */
    public String setWinCount(int winCount) {
        this.winCount = winCount;
        return "Se ha actualizado el contador de victorias a: " + winCount;
    }

    /**
     * Descripción: Incrementa el contador de victorias del usuario en 1.
     * Pre: Ninguna.
     * Pos: Se incrementa el contador de victorias en 1.
     */
    public void incrementWinCount() {
        this.winCount++;
    }
    
    // Board management methods
    /**
     * Descripción: Inicializa el tablero del usuario.
     * Pre: Ninguna.
     * Pos: Se crea un nuevo tablero con el número de barcos especificado.
     * @param numShips Número de barcos que tendrá el tablero.
     */
    public void initializeBoard(int numShips) {
        this.board = new Board(numShips);
    }
    
    /**
     * Descripción: Obtiene el tablero del usuario.
     * Pre: Ninguna.
     * Pos: Se retorna el tablero del usuario o null si no está inicializado.
     * @return Tablero del usuario o null si no está inicializado.
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Descripción: Verifica si el tablero del usuario ha sido inicializado.
     * Pre: Ninguna.
     * Pos: Se retorna true si el tablero está inicializado, false en caso contrario.
     * @return true si el tablero está inicializado, false en caso contrario.
     */
    public boolean hasInitializedBoard() {
        return board != null;
    }
    
    // Game interaction methods
    /**
     * Descripción: Añade un barco al tablero del usuario.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se añade el barco al tablero si la posición es válida.
     * @param name Nombre del barco.
     * @param size Tamaño del barco.
     * @param orientation Orientación del barco (0: horizontal, 1: vertical).
     * @param startX Coordenada X inicial del barco.
     * @param startY Coordenada Y inicial del barco.
     * @return Mensaje indicando si el barco se añadió correctamente o el error ocurrido.
     */
    public String addShip(String name, int size, int orientation, int startX, int startY) {
        if (!hasInitializedBoard()) {
            return "El tablero no ha sido inicializado";
        }
        return board.addShip(name, size, orientation, startX, startY);
    }
    
    /**
     * Descripción: Procesa un ataque en el tablero del usuario.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se actualiza el estado del tablero según el resultado del ataque.
     * @param x Coordenada X del ataque.
     * @param y Coordenada Y del ataque.
     * @return Mensaje indicando el resultado del ataque.
     */
    public String processAttack(int x, int y) {
        if (!hasInitializedBoard()) {
            return "El tablero no ha sido inicializado";
        }
        return board.processAttack(x, y);
    }
    
    /**
     * Descripción: Verifica si una coordenada está ocupada en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna true si la coordenada está ocupada, false en caso contrario.
     * @param x Coordenada X a verificar.
     * @param y Coordenada Y a verificar.
     * @return true si la coordenada está ocupada, false en caso contrario.
     */
    public boolean isCoordinateOccupied(int x, int y) {
        if (!hasInitializedBoard()) {
            return false;
        }
        return board.isCoordinateOccupied(x, y);
    }
    
    /**
     * Descripción: Verifica si la posición para un barco está ocupada en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna true si la posición está ocupada o es inválida, false si está disponible.
     * @param size Tamaño del barco.
     * @param orientation Orientación del barco (0: horizontal, 1: vertical).
     * @param startX Coordenada X inicial del barco.
     * @param startY Coordenada Y inicial del barco.
     * @return true si la posición está ocupada o es inválida, false si está disponible.
     */
    public boolean isShipPositionOccupied(int size, int orientation, int startX, int startY) {
        if (!hasInitializedBoard()) {
            return false;
        }
        return board.isShipPositionOccupied(size, orientation, startX, startY);
    }
    
    /**
     * Descripción: Verifica si el usuario ha perdido todos sus barcos.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna true si todos los barcos han sido hundidos, false en caso contrario.
     * @return true si todos los barcos han sido hundidos, false en caso contrario.
     */
    public boolean hasLost() {
        if (!hasInitializedBoard()) {
            return false;
        }
        return board.allShipsSunk();
    }
    
    /**
     * Descripción: Busca un barco por su nombre en el tablero del usuario.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el barco si se encuentra, null en caso contrario.
     * @param name Nombre del barco a buscar.
     * @return Barco encontrado o null si no existe o el tablero no está inicializado.
     */
    public Ship searchShip(String name) {
        if (!hasInitializedBoard()) {
            return null;
        }
        return board.searchShip(name);
    }

    /**
     * Descripción: Genera una representación en texto del usuario.
     * Pre: Ninguna.
     * Pos: Se retorna una cadena con la información básica del usuario.
     * @return Cadena con el nombre, contador de victorias y tipo de usuario.
     */
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", winCount=" + winCount +
                ", isHuman=" + isHuman +
                '}';
    }
}
