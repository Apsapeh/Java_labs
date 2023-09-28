import java.util.Arrays;
import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        // Пункт 1
        int [] int_arr = new int[12];
        for (int i = 0; i < 12; ++i)
            int_arr[i] = 2 + 2 * (11 - i);

        // Пункт 2
        float [] float_arr = new float[11];
        for (int i = 0; i < 11; ++i)
            float_arr[i] = (float)Math.random() * 26 - 12;

        int [][] a = {{3}, {3}};
        // Пункт 3
        float [][] result = new float[12][];
        for (int i = 0; i < 12; ++i) {
            result[i] = new float[11];
            for (int j = 0; j < 11; ++j) {
                float x = float_arr[j];

                if (int_arr[i] == 18)
                    result[i][j] = (float) log(pow(pow(E, x), sin(tan(x))));
                else if (Arrays.asList(2, 6, 10, 16, 20, 24).contains(int_arr[i]))
                    result[i][j] = (float) pow(Math.pow(Math.E, Math.tan(x)), Math.sin(Math.atan((x+1) / 26)));
                else
                    result[i][j] = (float) log(Math.acos(0.5 * (x+1)/26));
            }

            for (int ae : int_arr) {

            }

        }

        for (int i = 0; i < 12; ++i) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < 11; ++j)
                line.append(String.format("%8.3f", result[i][j]));
            System.out.println(line);
        }
    }
}