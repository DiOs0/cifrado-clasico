package cifrados;


public class Atbash {
    public static String ejecutar(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                resultado.append((char) ('Z' - (c - 'A')));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
