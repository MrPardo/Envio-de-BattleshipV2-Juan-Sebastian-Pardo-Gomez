package model;

/**
 * Descripción: Clase que representa un barco en el juego Battleship.
 * Esta clase almacena la información del barco, sus coordenadas en el tablero,
 * su estado actual y gestiona los impactos recibidos.
 */
public class Ship {

    private String name;
    private int shipLenght;
    private Orientation orientation;
    private Coordinate[] shipCoords;
    private ShipState state;
    private int[] hitCount;

    /**
     * Descripción: Constructor de la clase Ship.
     * Pre: Ninguna.
     * Pos: Se inicializa un nuevo barco con el nombre, tamaño, orientación y posición especificados.
     * @param name Nombre del barco.
     * @param shipLength Longitud del barco.
     * @param orientationValue Valor de orientación (0: horizontal, 1: vertical).
     * @param startX Coordenada X inicial del barco.
     * @param startY Coordenada Y inicial del barco.
     */
    public Ship (String name, int shipLength, int orientationValue, int startX, int startY){
        this.name = name;
        this.shipLenght = shipLength;
        this.orientation = intToOrientation(orientationValue);
        this.state = ShipState.OK;
        this.hitCount = new int[shipLength];
        
        // Generar todas las coordenadas del barco basadas en las coordenadas iniciales
        this.shipCoords = new Coordinate[shipLength];
        generateShipCoordinates(startX, startY);
    }

    /**
     * Descripción: Convierte un valor entero a un valor de la enumeración Orientation.
     * Pre: Ninguna.
     * Pos: Se retorna el valor de orientación correspondiente.
     * @param orientationValue Valor entero de orientación (0: horizontal, 1: vertical).
     * @return Valor de la enumeración Orientation correspondiente.
     */
    public Orientation intToOrientation(int orientationValue) {
        if (orientationValue == 1) {
            return Orientation.VERTICAL;
        } else {
            return Orientation.HORIZONTAL;
        }
    }
    
    /**
     * Descripción: Convierte un valor entero a un valor de la enumeración ShipState.
     * Pre: Ninguna.
     * Pos: Se retorna el valor de estado correspondiente.
     * @param stateValue Valor entero de estado (1: OK, 2: HIT, 3: SUNK).
     * @return Valor de la enumeración ShipState correspondiente.
     */
    public ShipState intToShipState(int stateValue){
        switch (stateValue) {
            case 1:
                return ShipState.OK;
            case 2:
                return ShipState.HIT;
            case 3:
                return ShipState.SUNK;
            default:
                return ShipState.OK; // Valor por defecto
        }
    }

    /**
     * Descripción: Obtiene el nombre del barco.
     * Pre: Ninguna.
     * Pos: Se retorna el nombre del barco.
     * @return Nombre del barco.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Descripción: Obtiene la longitud del barco.
     * Pre: Ninguna.
     * Pos: Se retorna la longitud del barco.
     * @return Longitud del barco.
     */
    public int getShipLenght() {
        return shipLenght;
    }
    
    /**
     * Descripción: Obtiene la orientación del barco.
     * Pre: Ninguna.
     * Pos: Se retorna la orientación del barco.
     * @return Orientación del barco (HORIZONTAL o VERTICAL).
     */
    public Orientation getOrientation() {
        return orientation;
    }
    
    /**
     * Descripción: Obtiene las coordenadas que ocupa el barco.
     * Pre: Ninguna.
     * Pos: Se retorna un arreglo con las coordenadas del barco.
     * @return Arreglo de coordenadas que ocupa el barco.
     */
    public Coordinate[] getShipCoords() {
        return shipCoords;
    }
    
    /**
     * Descripción: Obtiene el estado actual del barco.
     * Pre: Ninguna.
     * Pos: Se retorna el estado actual del barco.
     * @return Estado actual del barco (OK, HIT o SUNK).
     */
    public ShipState getState() {
        return state;
    }

    /**
     * Descripción: Obtiene el registro de impactos del barco.
     * Pre: Ninguna.
     * Pos: Se retorna un arreglo con el registro de impactos.
     * @return Arreglo que indica qué partes del barco han sido impactadas.
     */
    public int[] getHitCount() {
        return hitCount;
    }

    /**
     * Descripción: Genera las coordenadas que ocupa el barco basadas en su posición inicial.
     * Pre: Los atributos shipLenght, orientation y shipCoords deben estar inicializados.
     * Pos: Se generan todas las coordenadas que ocupa el barco.
     * @param startX Coordenada X inicial del barco.
     * @param startY Coordenada Y inicial del barco.
     */
    private void generateShipCoordinates(int startX, int startY) {
        for (int i = 0; i < shipLenght; i++) {
            if (orientation == Orientation.VERTICAL) {
                shipCoords[i] = new Coordinate(startX, startY + i);
            } else { // HORIZONTAL
                shipCoords[i] = new Coordinate(startX + i, startY);
            }
        }
    }
    
    /**
     * Descripción: Verifica si una coordenada pertenece al barco.
     * Pre: El barco debe estar inicializado con sus coordenadas.
     * Pos: Se retorna true si la coordenada pertenece al barco, false en caso contrario.
     * @param x Coordenada X a verificar.
     * @param y Coordenada Y a verificar.
     * @return true si la coordenada pertenece al barco, false en caso contrario.
     */
    public boolean containsCoordinate(int x, int y) {
        for (int i = 0; i < shipCoords.length; i++) {
            if (shipCoords[i].coordEquals(x, y)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Descripción: Registra un impacto en el barco en la coordenada especificada.
     * Pre: El barco debe estar inicializado con sus coordenadas.
     * Pos: Se actualiza el estado del barco y el registro de impactos.
     * @param x Coordenada X del impacto.
     * @param y Coordenada Y del impacto.
     * @return true si el impacto fue registrado, false si la coordenada no pertenece al barco o ya fue impactada.
     */
    public boolean hit(int x, int y) {
        if (containsCoordinate(x, y)) {
            // Encontrar qué posición del barco fue impactada
            for (int i = 0; i < shipCoords.length; i++) {
                if (shipCoords[i].coordEquals(x, y)) {
                    // Verificar si esta posición ya fue impactada
                    if (hitCount[i] == 1) {
                        return false; // La posición ya fue impactada
                    }
                    
                    // Marcar la posición como impactada
                    hitCount[i] = 1;
                    
                    // Verificar si el barco ha sido hundido
                    int totalHits = 0;
                    for (int j = 0; j < hitCount.length; j++) {
                        totalHits += hitCount[j];
                    }
                    
                    if (totalHits >= shipLenght) {
                        state = ShipState.SUNK;
                    } else {
                        state = ShipState.HIT;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Descripción: Verifica si el barco ha sido hundido.
     * Pre: El barco debe estar inicializado.
     * Pos: Se retorna true si el barco está hundido, false en caso contrario.
     * @return true si el barco está hundido, false en caso contrario.
     */
    public boolean isSunk() {
        return state == ShipState.SUNK;
    }
    
    /**
     * Descripción: Genera una representación en texto del barco.
     * Pre: El barco debe estar inicializado.
     * Pos: Se retorna una cadena con la información del barco.
     * @return Cadena con la información del barco (nombre, longitud, orientación, estado, impactos y coordenadas).
     */
    @Override
    public String toString() {
        String msg = "Barco: " + name + " | Longitud: " + shipLenght + 
                     " | Orientación: " + orientation + 
                     " | Estado: " + state + 
                     " | Impactos: " + hitCount;

        for (int i = 0; i < shipCoords.length; i++) {
            msg += " | Coordenada " + (i + 1) + ": " + shipCoords[i];
        }
        return msg;
    }
}