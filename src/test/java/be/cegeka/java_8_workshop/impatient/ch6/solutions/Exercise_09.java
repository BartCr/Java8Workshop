package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_09 {

    @Test
    public void solution() throws Exception {
        Matrix[] array = new Matrix[10];
        final int[][] f = {{1, 1}, {1, 0}};
        Arrays.parallelSetAll(array, i -> new Matrix(f));
        Arrays.parallelPrefix(array, Matrix::multiply);

        assertThat(array[0].m[0][0]).isEqualTo(1);
        assertThat(array[1].m[0][0]).isEqualTo(2);
        assertThat(array[2].m[0][0]).isEqualTo(3);
        assertThat(array[3].m[0][0]).isEqualTo(5);
        assertThat(array[4].m[0][0]).isEqualTo(8);
        assertThat(array[5].m[0][0]).isEqualTo(13);
        assertThat(array[6].m[0][0]).isEqualTo(21);
        assertThat(array[7].m[0][0]).isEqualTo(34);
        assertThat(array[8].m[0][0]).isEqualTo(55);
        assertThat(array[9].m[0][0]).isEqualTo(89);
    }

    class Matrix {

        int[][] m;

        Matrix(int[][] m) {
            this.m = m;
        }

        Matrix multiply(Matrix other) {
            int x1 = m[0][0] * other.m[0][0] + m[0][1] * other.m[1][0];
            int y1 = m[0][0] * other.m[0][1] + m[0][1] * other.m[1][1];
            int x2 = m[1][0] * other.m[0][0] + m[1][1] * other.m[1][0];
            int y2 = m[1][0] * other.m[0][1] + m[1][1] * other.m[1][1];
            return new Matrix(new int[][]{{x1, y1}, {x2, y2}});
        }
    }
}
