package gui;

import game_fis.Game_FIS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

/**
 * Clase Coso
 * Esta clase representa la interfaz gráfica para el proyecto "VIDEOGAMES_GOD PROJECT".
 * Permite a los usuarios ingresar valores relacionados con un sistema difuso
 * y calcula los resultados de dificultad y frecuencia de eventos difíciles.
 */
public class Coso {
    private JPanel contentPane;           // Contenedor principal
    private JLabel title;                // Título principal
    private JLabel imageLabel;           // Imagen decorativa
    private JTextField textField1;       // Campo de texto para tiempo_reaccion
    private JTextField textField2;       // Campo de texto para desempeno_jugador
    private JTextField textField3;       // Campo de texto para tiempo_zona
    private JLabel dificultadLabel;      // Etiqueta para mostrar la dificultad calculada
    private JLabel frecuenciaLabel;      // Etiqueta para mostrar la frecuencia calculada

    /**
     * Constructor de la clase Coso.
     * Configura la interfaz gráfica y sus componentes.
     */
    public Coso() {
        // Configuración del contenedor principal
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setLayout(new BorderLayout(50, 50));
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Título principal
        title = new JLabel("VIDEOGAMES_GOD PROJECT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("FiraCode Nerd Font", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Imagen decorativa
        ImageIcon originalImageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/lapoderosa.jpg")));
        Image scaledImage = originalImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Configuración de las etiquetas e inputs
        Font inputFont = new Font("FiraCode Nerd Font", Font.PLAIN, 14);

        JLabel label1 = new JLabel("Tiempo_reaccion en segundos (0.5, 5):");
        label1.setForeground(Color.WHITE);
        textField1 = createPaddedTextField(inputFont, "Tiempo de reacción");

        JLabel label2 = new JLabel("Desempeño_jugador (0, 100):");
        label2.setForeground(Color.WHITE);
        textField2 = createPaddedTextField(inputFont, "Desempeño del jugador");

        JLabel label3 = new JLabel("Tiempo_zona en minutos (0, 10):");
        label3.setForeground(Color.WHITE);
        textField3 = createPaddedTextField(inputFont, "Tiempo por zona jugada");

        // Botón para calcular resultados
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setFont(inputFont);
        calcularButton.setBackground(Color.ORANGE);
        calcularButton.setForeground(Color.WHITE);
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResultados();
            }
        });

        // Etiquetas para mostrar resultados
        dificultadLabel = new JLabel("Dificultad: ");
        dificultadLabel.setForeground(Color.WHITE);
        dificultadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        frecuenciaLabel = new JLabel("Frecuencia Eventos Difíciles: ");
        frecuenciaLabel.setForeground(Color.WHITE);
        frecuenciaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(Color.BLACK);
        inputPanel.add(label1);
        inputPanel.add(textField1);
        inputPanel.add(label2);
        inputPanel.add(textField2);
        inputPanel.add(label3);
        inputPanel.add(textField3);
        inputPanel.add(new JLabel());
        inputPanel.add(calcularButton);

        // Panel para mostrar resultados
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.setBackground(Color.BLACK);
        outputPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen interno (padding)
        outputPanel.add(Box.createVerticalGlue()); // Espaciador superior
        outputPanel.add(dificultadLabel);
        outputPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciador entre etiquetas
        outputPanel.add(frecuenciaLabel);
        outputPanel.add(Box.createVerticalGlue()); // Espaciador inferior

        // Añadir paneles al contenedor principal
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(inputPanel, BorderLayout.CENTER);
        contentPane.add(outputPanel, BorderLayout.SOUTH);

        // Panel para la imagen
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.BLACK);
        imagePanel.add(imageLabel);
        contentPane.add(imagePanel, BorderLayout.WEST);
    }

    /**
     * Crea un JTextField con padding y un placeholder.
     *
     * @param font       Fuente para el JTextField.
     * @param placeholder Texto de ayuda dentro del JTextField.
     * @return JTextField configurado.
     */
    private JTextField createPaddedTextField(Font font, String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding: top, left, bottom, right
        ));
        setPlaceholder(textField, placeholder);
        return textField;
    }

    /**
     * Establece un placeholder en un JTextField.
     *
     * @param textField  Campo de texto.
     * @param placeholder Placeholder a mostrar.
     */
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * Calcula los resultados del sistema difuso basados en las entradas proporcionadas.
     */
    private void calcularResultados() {
        try {
            double tiempoReaccion = Double.parseDouble(textField1.getText());
            double desempenoJugador = Double.parseDouble(textField2.getText());
            double tiempoZona = Double.parseDouble(textField3.getText());

            // Validación de rangos
            if (tiempoReaccion < 0.5 || tiempoReaccion > 5) {
                throw new IllegalArgumentException("Tiempo de reacción debe estar entre 0.5 y 5");
            }
            if (desempenoJugador < 0 || desempenoJugador > 100) {
                throw new IllegalArgumentException("Desempeño del jugador debe estar entre 0 y 100");
            }
            if (tiempoZona < 0 || tiempoZona > 10) {
                throw new IllegalArgumentException("Tiempo por zona debe estar entre 0 y 10");
            }

            // Calcular resultados
            Game_FIS gameFis = new Game_FIS();
            double[] resultados = gameFis.getResults((float) tiempoReaccion, (float) desempenoJugador, (float) tiempoZona);

            // Mostrar resultados
            dificultadLabel.setText("Dificultad: " + String.format("%.2f", resultados[0]));
            frecuenciaLabel.setText("Frecuencia Eventos Difíciles: " + String.format("%.2f", resultados[1]));
        } catch (NumberFormatException e) {
            dificultadLabel.setText("Dificultad: Error - Entrada no válida");
            frecuenciaLabel.setText("Frecuencia Eventos Difíciles: Error - Entrada no válida");
        } catch (IllegalArgumentException e) {
            dificultadLabel.setText("Dificultad: " + e.getMessage());
            frecuenciaLabel.setText("Frecuencia Eventos Difíciles: " + e.getMessage());
        } catch (Exception e) {
            dificultadLabel.setText("Dificultad: Error inesperado");
            frecuenciaLabel.setText("Frecuencia Eventos Difíciles: Error inesperado");
        }
    }

    /**
     * Método principal para inicializar la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Coso");
            Coso coso = new Coso();
            frame.setContentPane(coso.contentPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 550);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false); // Deshabilitar el cambio de tamaño de la ventana
            frame.setVisible(true);
        });
    }
}
