package cifrados;


public class RailFence {
    public static String cifrar(String texto, int clave) {
        char[][] matrix = new char[clave][texto.length()];
        boolean bajando = false;
        int fila = 0;
        for (int i = 0; i < texto.length(); i++) {
            matrix[fila][i] = texto.charAt(i);
            if (fila == 0 || fila == clave - 1) bajando = !bajando;
            fila += bajando ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        for (char[] r : matrix) for (char c : r) if (c != 0) res.append(c);
        return res.toString();
    }
    // El descifrado de RailFence es más complejo, omitido por brevedad o simplificado.
}
