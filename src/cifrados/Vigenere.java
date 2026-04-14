package cifrados;

public class Vigenere {
    public static String procesar(String texto, String clave, boolean cifrar) {
        StringBuilder res = new StringBuilder();
        clave = clave.toUpperCase();

        for (int i = 0, j = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int modo = cifrar ? 1 : -1;
                int desplazamiento = clave.charAt(j) - 'A';
                char procesado = (char) ((c - base + modo * desplazamiento + 26) % 26 + base);

                res.append(procesado);
                j = (j + 1) % clave.length();
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
