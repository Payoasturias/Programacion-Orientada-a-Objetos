// Clase JuegoMemoria: controla la lógica del juego

import java.util.InputMismatchException;

public class JuegoMemoria
{
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Vista vista;

    public JuegoMemoria(Jugador jugador1, Jugador jugador2, int dimensionTablero)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1; // Comienza jugador 1
        this.tablero = new Tablero(dimensionTablero);
        this.vista = new Vista();
    }

    // Inicia la partida
    public void iniciarPartida()
    {
        boolean jugando = true;

        while (jugando)
        {
            vista.mostrarTablero(tablero);
            vista.mostrarMensaje("Turno de " + jugadorActual.getNombre());

            try
            {
                // Leer movimientos
                int[] mov1 = vista.leerMovimiento(jugadorActual, tablero.getDimension());
                int[] mov2 = vista.leerMovimiento(jugadorActual, tablero.getDimension());

                //Validar que no sea la misma casilla
                if (mov1[0] == mov2[0] && mov1[1] == mov2[1])
                {
                    vista.mostrarMensaje("⚠️ No puedes seleccionar la misma casilla dos veces.");
                    cambiarTurno();
                }
                
                else
                {
                    boolean acierto = seleccionarCasillas(mov1[0], mov1[1], mov2[0], mov2[1]);

                    if (!acierto)
                    {
                        vista.mostrarMensaje("❌ No hubo coincidencia.");
                        cambiarTurno();
                    }
                    
                    else
                    {
                        vista.mostrarMensaje("✅ ¡Bien hecho! Encontraste un par.");
                    }
                }

                // Verificar si terminó el juego
                if (verificarFinDeJuego())
                {
                    vista.mostrarTablero(tablero);
                    Jugador ganador = obtenerGanador();

                    if (ganador != null)
                    {
                        vista.mostrarResultados(jugador1, jugador2);
                        vista.mostrarMensaje("🏆 Ganador: " + ganador.getNombre());
                    }
                    
                    else
                    {
                        vista.mostrarResultados(jugador1, jugador2);
                        vista.mostrarMensaje("🤝 Empate");
                    }

                    jugando = false;
                }
            }

            catch (IndexOutOfBoundsException e)
            {
               vista.mostrarMensaje("⚠️ Movimiento inválido o Coordenadas fuera del tablero: " + e.getMessage()); 
            }

            catch (InputMismatchException e)
            {
                vista.mostrarMensaje("⚠️ Entrada inválida. Debes ingresar números.");
                vista.limpiarBuffer(); // Limpiar buffer
            }        
        }
    }

    private void cambiarTurno()
    {
        if (jugadorActual == jugador1)
        {
            jugadorActual = jugador2;
        }

        else
        {
            jugadorActual = jugador1;
        }
    }

    public boolean seleccionarCasillas(int f1, int c1, int f2, int c2)
    {
        Ficha ficha1 = tablero.getFicha(f1, c1);
        Ficha ficha2 = tablero.getFicha(f2, c2);

        if(ficha1.estaEmparejada() || ficha2.estaEmparejada())
        {
            vista.mostrarMensaje("⚠️ Una de las fichas ya está emparejada.");
            return false;
        }
        
        ficha1.descubrir();
        ficha2.descubrir();
        
        if (ficha1.getSimbolo().equals(ficha2.getSimbolo()))
        {
            ficha1.marcarEmparejada();
            ficha2.marcarEmparejada();
            jugadorActual.aumentarPuntaje();
            return true;
        }

        else
        {
            ficha1.ocultar();
            ficha2.ocultar();
            return false;
        }
    }

    private boolean verificarFinDeJuego()
    {
        return tablero.todasEmparejadas();
    }

    public Jugador obtenerGanador()
    {
        if (jugador1.getPuntaje() > jugador2.getPuntaje())
        {
            return jugador1;
        }
        
        else if (jugador2.getPuntaje() > jugador1.getPuntaje())
        {
            return jugador2;
        }
        
        else
        {
            return null;
        }
    }
}