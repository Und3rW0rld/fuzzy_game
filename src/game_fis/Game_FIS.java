package game_fis;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

/**
 * Clase para manejar el sistema difuso del juego utilizando jFuzzyLogic.
 */
public final class Game_FIS {

    private static final String FILE_NAME = "src/game_fis/game_FCL.fcl";
    private final FIS fis;

    /**
     * Constructor que carga el sistema difuso desde el archivo FCL.
     * @throws IllegalArgumentException si el archivo no se puede cargar.
     */
    public Game_FIS() {
        this.fis = FIS.load(FILE_NAME, true);
        if (fis == null) {
            throw new IllegalArgumentException("No se puede cargar el archivo: '" + FILE_NAME + "'");
        }
    }

    /**
     * Calcula los resultados del sistema difuso en base a las entradas proporcionadas.
     * @param tiempo_reaccion Valor del tiempo de reacción.
     * @param desempeno_jugador Valor del desempeño del jugador.
     * @param tiempo_zona Valor del tiempo de la zona.
     * @return Un arreglo con los valores de las salidas del sistema.
     * @throws IllegalArgumentException si los valores de entrada son inválidos.
     */
    public double[] getResults(float tiempo_reaccion, float desempeno_jugador, float tiempo_zona) {
        // Validar las entradas
        if (tiempo_reaccion < 0 || desempeno_jugador < 0 || tiempo_zona < 0) {
            throw new IllegalArgumentException("Los valores de entrada no pueden ser negativos.");
        }

        // Establecer las entradas del sistema
        fis.setVariable("tiempo_reaccion", tiempo_reaccion);
        fis.setVariable("desempeno_jugador", desempeno_jugador);
        fis.setVariable("tiempo_zona", tiempo_zona);

        // Inicia el funcionamiento del sistema
        fis.evaluate();

        // Recupera los valores de las salidas
        double dificultad = fis.getVariable("dificultad").getLatestDefuzzifiedValue();
        double frecuenciaEventos = fis.getVariable("frecuencia_eventos_dificiles").getLatestDefuzzifiedValue();

        // Muestra los gráficos de las variables de entrada y salida
        JFuzzyChart.get().chart(fis.getFunctionBlock("game"));

        // Devuelve los resultados
        return new double[] { dificultad, frecuenciaEventos };
    }
}
