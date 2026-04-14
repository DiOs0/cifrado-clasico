package cifrados;
import java.util.Arrays;


public class RailFence {
    public static String cifrar(String texto, int rails) {
        if (rails <= 1) return texto;
        char[][] matrix = new char[rails][texto.length()];
        for (char[] row : matrix) Arrays.fill(row, '\n');

        int row = 0;
        boolean down = false;
        for (int i = 0; i < texto.length(); i++) {
            matrix[row][i] = texto.charAt(i);
            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (char[] r : matrix) {
            for (char c : r) if (c != '\n') res.append(c);
        }
        return res.toString();
    }

    public static String descifrar(String texto, int rails) {
        if (rails <= 1) return texto;
        char[][] matrix = new char[rails][texto.length()];
        for (char[] r : matrix) Arrays.fill(r, '\n');

        int row = 0;
        boolean down = false;
        for (int i = 0; i < texto.length(); i++) {
            matrix[row][i] = '*';
            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < texto.length(); j++) {
                if (matrix[i][j] == '*' && index < texto.length()) {
                    matrix[i][j] = texto.charAt(index++);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        row = 0; down = false;
        for (int i = 0; i < texto.length(); i++) {
            res.append(matrix[row][i]);
            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }
        return res.toString();
    }
}
