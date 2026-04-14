import javax.swing.*;
import java.awt.*;
import cifrados.*;

public class Main extends JFrame {
    private JTextArea txtEntrada, txtSalida;
    private JComboBox<String> comboMetodos;
    private JTextField txtClave;

    public Main() {
        setTitle("Criptografía Clásica - Computación");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Opciones
        JPanel panelNorte = new JPanel();
        comboMetodos = new JComboBox<>(new String[]{"Cesar", "Atbash", "Vigenere", "Railfence", "Playfair"});
        txtClave = new JTextField(10);
        panelNorte.add(new JLabel("Cifrado:"));
        panelNorte.add(comboMetodos);
        panelNorte.add(new JLabel("Clave:"));
        panelNorte.add(txtClave);
        add(panelNorte, BorderLayout.NORTH);

        // Textos
        JPanel panelCentro = new JPanel(new GridLayout(1, 2));
        txtEntrada = new JTextArea("");
        txtSalida = new JTextArea("");
        txtSalida.setEditable(false);
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

        btnCifrar.addActionListener(e -> procesar(true));
        btnDescifrar.addActionListener(e -> procesar(false));
    }

    private void procesar(boolean cifrar) {
        String metodo = (String) comboMetodos.getSelectedItem();
        String texto = txtEntrada.getText();
        String clave = txtClave.getText();
        String resultado = "";

        try {
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
                    if(cifrar) resultado = RailFence.cifrar(texto, Integer.parseInt(clave));
                    else resultado = "Descifrado RailFence no implementado";
                    break;
                case "Playfair":
                    resultado = PlayFair.procesar(texto, clave, cifrar);
                    break;
            }
            txtSalida.setText(resultado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: Revisa la clave (algunas deben ser números)");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
