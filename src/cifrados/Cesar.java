package cifrados;

public class Cesar {
    public static String cifrar(String texto, int clave) {
        StringBuilder res = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                res.append((char) ((c - base + clave) % 26 + base));
            } else res.append(c);
        }
        return res.toString();
    }

    public static String descifrar(String texto, int clave) {
        return cifrar(texto, 26 - (clave % 26));
    }
}
