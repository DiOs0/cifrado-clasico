package cifrados;


public class Atbash {
    public static String ejecutar(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                resultado.append((char) ('z' - (c - 'a')));
            } else if (c >= 'A' && c <= 'Z') {
                resultado.append((char) ('Z' - (c - 'A')));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
