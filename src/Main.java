import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import cifrados.*;

import static cifrados.PlayFair.*;

public class Main extends JFrame {
    private JTextArea txtEntrada, txtSalida;
    private JTextField txtClave;
    private JComboBox<String> comboMetodos;
    private final String HINT_TEXTO = "Escriba el mensaje aquí...";
    private final String HINT_CLAVE = "Clave...";

    public Main() {
        setTitle("Criptografía Clásica - Computación");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Opciones
        JPanel panelNorte = new JPanel();
        comboMetodos = new JComboBox<>(new String[]{"Cesar", "Atbash", "Vigenere", "Railfence", "Playfair"});
        txtClave = new JTextField(15);
        configurarHint(txtClave, HINT_CLAVE);

        panelNorte.add(new JLabel("Cifrado:"));
        panelNorte.add(comboMetodos);
        panelNorte.add(new JLabel("Clave:"));
        panelNorte.add(txtClave);
        add(panelNorte, BorderLayout.NORTH);

        // Textos
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 0));
        txtEntrada = new JTextArea();
        configurarHintArea(txtEntrada, HINT_TEXTO);

        txtSalida = new JTextArea();
        txtSalida.setEditable(false);
        txtSalida.setBackground(new Color(240, 240, 240));

        panelCentro.add(new JScrollPane(txtEntrada));
        panelCentro.add(new JScrollPane(txtSalida));
        add(panelCentro, BorderLayout.CENTER);



        // Botones
        JPanel panelSur = new JPanel();
        JButton btnCifrar = new JButton("Cifrar");
        JButton btnDescifrar = new JButton("Descifrar");
        panelSur.add(btnCifrar);
        panelSur.add(btnDescifrar);
        add(panelSur, BorderLayout.SOUTH);

        // Eventos
        btnCifrar.addActionListener(e -> ejecutar(true));
        btnDescifrar.addActionListener(e -> ejecutar(false));
    }

    private void ejecutar(boolean cifrar) {
        String metodo = (String) comboMetodos.getSelectedItem();
        String texto = txtEntrada.getText();
        String clave = txtClave.getText();

        if (texto.equals(HINT_TEXTO) || texto.isEmpty()) return;

        try {
            String resultado = "";
            switch (metodo) {
                case "Cesar":
                    int c = Integer.parseInt(clave);
                    resultado = cifrar ? Cesar.cifrar(texto, c) : Cesar.descifrar(texto, c);
                    break;
                case "Atbash":
                    resultado = Atbash.ejecutar(texto);
                    break;
                case "Vigenere":
                    resultado = Vigenere.procesar(texto, clave, cifrar);
                    break;
                case "Railfence":
                    int r = Integer.parseInt(clave);
                    resultado = cifrar ? RailFence.cifrar(texto, r) : RailFence.descifrar(texto, r);
                    break;
                case "Playfair":
                    resultado = procesar(texto, clave, cifrar);
                    break;
            }
            txtSalida.setText(resultado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: Verifique que la clave sea correcta para el método.");
        }
    }

    private void configurarHint(JTextField campo, String hint) {
        campo.setText(hint);
        campo.setForeground(Color.GRAY);
        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(hint)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(hint);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void configurarHintArea(JTextArea area, String hint) {
        area.setText(hint);
        area.setForeground(Color.GRAY);
        area.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (area.getText().equals(hint)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    area.setText(hint);
                    area.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}