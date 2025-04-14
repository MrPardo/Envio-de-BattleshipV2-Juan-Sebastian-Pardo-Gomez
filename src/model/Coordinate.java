package model;

/**
 * Descripción: Clase que representa una coordenada en el tablero de juego.
 * Esta clase almacena las coordenadas X e Y de una posición en el tablero
 * y proporciona métodos para manipular y comparar coordenadas.
 */
public class Coordinate {
    
    private int x;
    private int y;

    /**
     * Descripción: Constructor de la clase Coordinate.
     * Pre: Ninguna.
     * Pos: Se inicializa una nueva coordenada con los valores X e Y especificados.
     * @param x Valor de la coordenada X.
     * @param y Valor de la coordenada Y.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Descripción: Obtiene el valor de la coordenada X.
     * Pre: Ninguna.
     * Pos: Se retorna el valor de la coordenada X.
     * @return Valor de la coordenada X.
     */
    public int getX() {
        return x;
    }

    /**
     * Descripción: Obtiene el valor de la coordenada Y.
     * Pre: Ninguna.
     * Pos: Se retorna el valor de la coordenada Y.
     * @return Valor de la coordenada Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Descripción: Genera una representación en texto de la coordenada.
     * Pre: Ninguna.
     * Pos: Se retorna una cadena con los valores X e Y de la coordenada.
     * @return Cadena con los valores X e Y de la coordenada.
     */
    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Descripción: Establece un nuevo valor para la coordenada X.
     * Pre: Ninguna.
     * Pos: Se actualiza el valor de la coordenada X.
     * @param x Nuevo valor para la coordenada X.
     * @return true indicando que la operación se realizó correctamente.
     */
    public boolean setX(int x) {
        this.x = x;
        return true;
    }

    /**
     * Descripción: Establece un nuevo valor para la coordenada Y.
     * Pre: Ninguna.
     * Pos: Se actualiza el valor de la coordenada Y.
     * @param y Nuevo valor para la coordenada Y.
     * @return true indicando que la operación se realizó correctamente.
     */
    public boolean setY(int y) {
        this.y = y;
        return true;
    }

    /**
     * Descripción: Incrementa los valores de las coordenadas X e Y.
     * Pre: Ninguna.
     * Pos: Se incrementan los valores de las coordenadas X e Y según los valores especificados.
     * @param x Valor a incrementar en la coordenada X.
     * @param y Valor a incrementar en la coordenada Y.
     */
    public void addCordinate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Descripción: Compara si esta coordenada coincide con los valores X e Y especificados.
     * Pre: Ninguna.
     * Pos: Se retorna true si los valores coinciden, false en caso contrario.
     * @param x Valor X a comparar.
     * @param y Valor Y a comparar.
     * @return true si los valores coinciden, false en caso contrario.
     */
    public boolean coordEquals(int x, int y) {
        if (this.x == x && this.y == y) {
            return true;
        }
        return false;
    }
}