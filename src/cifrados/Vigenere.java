package cifrados;

public class Vigenere {
    public static String procesar(String texto, String clave, boolean cifrar) {
        StringBuilder res = new StringBuilder();
        texto = texto.toUpperCase();
        clave = clave.toUpperCase();
        for (int i = 0, j = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                int modo = cifrar ? 1 : -1;
                res.append((char) ((c - 'A' + modo * (clave.charAt(j) - 'A') + 26) % 26 + 'A'));
                j = (j + 1) % clave.length();
            } else res.append(c);
        }
        return res.toString();
    }
}
