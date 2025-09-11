import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Clase Tablero: administra la matriz de fichas

public class Tablero
{
    private int dimension;
    private Ficha[][] casillas;

    public Tablero(int dimension)
    {
        this.dimension = dimension;
        this.casillas = new Ficha[dimension][dimension];
        inicializar();
    }

    // Coloca pares de símbolos de forma aleatoria en el tablero
    public void inicializar()
    {
        int totalFichas = dimension * dimension;
        List<String> simbolos = new ArrayList<>();

        String[] baseSimbolos = {"😀","🐱","🍎","⚽","🚗","🌟","🎵","📚","🔥","❄️","🍀","🎲","🎮","❤️","☕","🌈","🐶","🦋","🍕","🎁"};

        // Se necesitan dimension*dimension/2 pares
        for (int i = 0; i < totalFichas / 2; i++)
        {
            simbolos.add(baseSimbolos[i]);
            simbolos.add(baseSimbolos[i]);
        }

        Random random = new Random();
        for (int i = 0; i < simbolos.size(); i++)
        {
            int j = random.nextInt(simbolos.size());
            String temp = simbolos.get(i);
            simbolos.set(i, simbolos.get(j));
            simbolos.set(j, temp);
        }

        // Colocar los símbolos en la matriz
        int index = 0;
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                casillas[i][j] = new Ficha(simbolos.get(index));
                index++;
            }
        }
    }

    public Ficha getFicha(int fila, int columna)
    {
        return casillas[fila][columna];
    }

    // Verifica si todas las fichas ya están emparejadas
    public boolean todasEmparejadas()
    {
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if(!casillas[i][j].estaEmparejada())
                {
                    return false;
                }
            }
        }

        return true;
    }

    public int getDimension()
    {
        return dimension;
    }

}