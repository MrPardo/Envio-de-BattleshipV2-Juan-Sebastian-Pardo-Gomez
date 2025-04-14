package model;
import java.util.ArrayList;

/**
 * Descripción: Clase que representa el tablero de juego de Battleship.
 * Esta clase gestiona la matriz del tablero, los barcos colocados en él,
 * y procesa los ataques realizados durante el juego.
 */
public class Board {
    
    private int numShips;
    private int[][] board = new int[10][10];
    private boolean winner;
    private ArrayList<Ship> ships = new ArrayList<>();

    /**
     * Descripción: Constructor de la clase Board.
     * Pre: Ninguna.
     * Pos: Se inicializa un nuevo tablero de 10x10 con el número máximo de barcos especificado.
     * @param numShips Número máximo de barcos que puede contener el tablero.
     */
    public Board(int numShips){
        this.winner = false;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                this.board[i][j] = 0;
            }
        }
        this.numShips = numShips;
    }
    
    /**
     * Descripción: Añade un barco al tablero en la posición especificada.
     * Pre: El tablero debe estar inicializado y no haber alcanzado el máximo de barcos.
     * Pos: Si la posición es válida, se coloca el barco en el tablero y se añade a la lista de barcos.
     * @param name Nombre del barco.
     * @param size Tamaño del barco.
     * @param orientation Orientación del barco (0: horizontal, 1: vertical).
     * @param startX Coordenada X inicial del barco.
     * @param startY Coordenada Y inicial del barco.
     * @return Mensaje indicando si el barco se colocó correctamente o el error ocurrido.
     */
    public String addShip(String name, int size, int orientation, int startX, int startY) {
        // Verificar si ya tenemos el máximo de barcos
        if (ships.size() >= numShips) {
            return "Ya se han colocado todos los barcos";
        }
        
        // Verificar que todas las coordenadas sean válidas
        for (int i = 0; i < size; i++) {
            int x = startX;
            int y = startY;
            
            if (orientation == 0) { // Horizontal
                x += i;
            } else { // Vertical
                y += i;
            }
            
            // Verificar que la coordenada sea válida
            if (x < 0 || x >= 10 || y < 0 || y >= 10 || board[x][y] != 0) {
                return "Coordenadas inválidas";
            }
        }
        
        // Si todas las coordenadas son válidas, crear el barco usando el constructor completo
        Ship ship = new Ship(name, size, orientation, startX, startY);
        
        // Colocar el barco en el tablero
        Coordinate[] coords = ship.getShipCoords();
        for (Coordinate coord : coords) {
            int x = coord.getX();
            int y = coord.getY();
            board[x][y] = 1;
        }
        
        // Añadir el barco a la lista
        ships.add(ship);
        return "Barco colocado exitosamente";
    }
    
    /**
     * Descripción: Procesa un ataque en las coordenadas especificadas.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se actualiza el estado del tablero según el resultado del ataque.
     * @param x Coordenada X del ataque.
     * @param y Coordenada Y del ataque.
     * @return Mensaje indicando el resultado del ataque (Agua, Tocado, Hundido, etc.).
     */
    public String processAttack(int x, int y) {
        // Verificar coordenadas válidas
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            return "Coordenadas inválidas";
        }
        
        // Verificar el estado de la casilla
        if (board[x][y] == 0) {
            // No modificamos el tablero cuando es agua
            return "Agua";
        } else if (board[x][y] == 1) {
            board[x][y] = 2; // Marcar como tocado
            
            // Buscar qué barco fue impactado
            for (Ship ship : ships) {
                if (ship.containsCoordinate(x, y)) {
                    ship.hit(x, y);
                    if (ship.isSunk()) {
                        markShipAsSunk(ship);
                        return "¡Hundido! " + ship.getName();
                    }
                    return "¡Tocado!";
                }
            }
            return "¡Tocado!";
        } else if (board[x][y] == 2 || board[x][y] == 3) {
            return "Esta posición ya fue atacada";
        }
        
        return "Error al procesar el ataque";
    }
    
    /**
     * Descripción: Marca un barco como hundido en el tablero.
     * Pre: El barco debe existir y estar en el tablero.
     * Pos: Se actualizan todas las celdas ocupadas por el barco a estado "hundido" (3).
     * @param ship Barco a marcar como hundido.
     */
    private void markShipAsSunk(Ship ship) {
        Coordinate[] coords = ship.getShipCoords();
        for (Coordinate coord : coords) {
            int x = coord.getX();
            int y = coord.getY();
            if (x >= 0 && x < 10 && y >= 0 && y < 10) {
                board[x][y] = 3; // Marcar como hundido
            }
        }
    }
    
    /**
     * Descripción: Verifica si todos los barcos en el tablero están hundidos.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna true si todos los barcos están hundidos, false en caso contrario.
     * @return true si todos los barcos están hundidos, false en caso contrario o si no hay barcos.
     */
    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return !ships.isEmpty();
    }
    
    /**
     * Descripción: Obtiene el número máximo de barcos que puede contener el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el número máximo de barcos.
     * @return Número máximo de barcos.
     */
    public int getNumShips() {
        return numShips;
    }
    
    /**
     * Descripción: Obtiene el número de barcos actualmente colocados en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el número de barcos colocados.
     * @return Número de barcos colocados.
     */
    public int getShipsPlaced() {
        return ships.size();
    }
    
    /**
     * Descripción: Verifica si hay un ganador en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el estado del ganador.
     * @return true si hay un ganador, false en caso contrario.
     */
    public boolean getWinner() {
        return winner;
    }
    
    /**
     * Descripción: Establece el estado del ganador en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se actualiza el estado del ganador.
     * @param winner Nuevo estado del ganador.
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    
    /**
     * Descripción: Busca un barco por su nombre en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el barco si se encuentra, null en caso contrario.
     * @param name Nombre del barco a buscar.
     * @return Barco encontrado o null si no existe.
     */
    public Ship searchShip(String name) {
        for (Ship ship : ships) {
            if (ship.getName().equalsIgnoreCase(name)) { //El equalsIgnoreCase() es para que no se distinga entre mayúsculas y minúsculas y igualmente se encuentre el barco que se busca
                return ship;
            }
        }
        return null;
    }
    
    /**
     * Descripción: Obtiene un barco por su índice en la lista de barcos.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el barco en el índice especificado o null si el índice es inválido.
     * @param index Índice del barco a obtener.
     * @return Barco en el índice especificado o null si el índice es inválido.
     */
    public Ship getShip(int index) {
        if (index >= 0 && index < ships.size()) {
            return ships.get(index);
        }
        return null;
    }
    
    /**
     * Descripción: Verifica si una coordenada específica está ocupada en el tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna true si la coordenada está ocupada o fuera del tablero, false si está libre.
     * @param x Coordenada X a verificar.
     * @param y Coordenada Y a verificar.
     * @return true si la coordenada está ocupada o fuera del tablero, false si está libre.
     */
    public boolean isCoordinateOccupied(int x, int y) {
        // Verificar que las coordenadas estén dentro del tablero
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            return true; // Coordenadas fuera del tablero se consideran ocupadas
        }
        
        // Verificar si la casilla está ocupada (valor diferente de 0)
        return board[x][y] != 0;
    }
    
    /**
     * Descripción: Verifica si un barco puede ser colocado en las coordenadas especificadas.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna true si alguna coordenada está ocupada o fuera del tablero, false si todas están libres.
     * @param size Tamaño del barco.
     * @param orientation Orientación del barco (0: horizontal, 1: vertical).
     * @param startX Coordenada X inicial.
     * @param startY Coordenada Y inicial.
     * @return true si alguna coordenada está ocupada o fuera del tablero, false si todas están libres.
     */
    public boolean isShipPositionOccupied(int size, int orientation, int startX, int startY) {
        // Verificar todas las coordenadas que ocupará el barco
        for (int i = 0; i < size; i++) {
            int x = startX;
            int y = startY;
            
            if (orientation == 0) { // Horizontal
                x += i;
            } else { // Vertical
                y += i;
            }
            
            // Verificar que la coordenada sea válida y no esté ocupada
            if (x < 0 || x >= 10 || y < 0 || y >= 10 || board[x][y] != 0) {
                return true; // Posición ocupada o fuera del tablero
            }
        }
        
        return false; // Todas las posiciones están libres
    }
    
    /**
     * Descripción: Obtiene una copia del estado actual del tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna una matriz con el estado actual del tablero.
     * @return Matriz 10x10 con el estado actual del tablero.
     */
    public int[][] getBoardState() {
        // Crear una copia del tablero para no exponer el arreglo interno
        int[][] boardCopy = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }
    
    /**
     * Descripción: Obtiene el estado de una celda específica del tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se retorna el estado de la celda especificada.
     * @param x Coordenada X de la celda.
     * @param y Coordenada Y de la celda.
     * @return Estado de la celda (0: agua, 1: barco, 2: tocado, 3: hundido) o -1 si está fuera de límites.
     */
    public int getCellState(int x, int y) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            return board[x][y];
        }
        return -1; // Fuera de límites
    }
    
    /**
     * Descripción: Establece el estado de una celda específica del tablero.
     * Pre: El tablero debe estar inicializado.
     * Pos: Se actualiza el estado de la celda especificada si está dentro de los límites.
     * @param x Coordenada X de la celda.
     * @param y Coordenada Y de la celda.
     * @param state Nuevo estado para la celda (0: agua, 1: barco, 2: tocado, 3: hundido).
     */
    public void setCellState(int x, int y, int state) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            board[x][y] = state;
        }
    }
}
