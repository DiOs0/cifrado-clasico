package cifrados;

import java.awt.Point;

public class PlayFair {
    private static char[][] matriz = new char[5][5];

    private static void generarMatriz(String clave) {
        String limpiaClave = clave.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        String alfabeto = limpiaClave + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        StringBuilder finalKey = new StringBuilder();
        for (char c : alfabeto.toCharArray()) {
            if (finalKey.indexOf(String.valueOf(c)) == -1) {
                finalKey.append(c);
            }
        }
        // Llenar matriz 5x5
        for (int i = 0; i < 25; i++) {
            matriz[i / 5][i % 5] = finalKey.charAt(i);
        }
    }

    private static Point buscar(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matriz[i][j] == c) return new Point(i, j);
            }
        }
        return null;
    }

    public static String procesar(String texto, String clave, boolean cifrar) {
        if (clave.replaceAll("[^A-Z]", "").isEmpty()) clave = "KEYWORD";

        generarMatriz(clave);
        String preparado = texto.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");

        if (preparado.isEmpty()) return "";

        StringBuilder sb = new StringBuilder(preparado);

        // Reglas de formación de pares
        if (cifrar) {
            for (int i = 0; i < sb.length() - 1; i += 2) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    sb.insert(i + 1, 'X'); // Insertar X si hay letras iguales seguidas
                }
            }
            if (sb.length() % 2 != 0) {
                sb.append('X');
            }
        }

        StringBuilder res = new StringBuilder();
        int mod = cifrar ? 1 : 4;

        for (int i = 0; i < sb.length(); i += 2) {
            Point p1 = buscar(sb.charAt(i));
            Point p2 = buscar(sb.charAt(i + 1));

            if (p1.x == p2.x) {
                res.append(matriz[p1.x][(p1.y + mod) % 5]).append(matriz[p2.x][(p2.y + mod) % 5]);
            } else if (p1.y == p2.y) {
                res.append(matriz[(p1.x + mod) % 5][p1.y]).append(matriz[(p2.x + mod) % 5][p2.y]);
            } else {
                res.append(matriz[p1.x][p2.y]).append(matriz[p2.x][p1.y]);
            }
        }
        return res.toString();
    }
}