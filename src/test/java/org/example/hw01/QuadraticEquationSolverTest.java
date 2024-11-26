package org.example.hw01;

import org.junit.jupiter.api.Test;

import static org.example.hw01.validation.validators.DoubleValidator.EPSILON;
import static org.junit.jupiter.api.Assertions.*;

class QuadraticEquationSolverTest {

    /**
     * 3. Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
     */
    @Test
    void test_3_NoRealRoots() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        double[] roots = solver.solve(1, 0, 1);
        assertEquals(0, roots.length);
    }

    /**
     * 4. Написать минимальную реализацию функции solve, которая удовлетворяет данному тесту.
     */
    @Test
    void test_4_NoRealRootsWithMinimumRealization() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver() {
            @Override
            public double[] solve(double a, double b, double c) {
                double discriminant = b * b - 4 * a * c;
                if (discriminant <= -EPSILON) {
                    return new double[0];
                }
                return new double[0];
            }
        };
        double[] roots = solver.solve(1, 0, 1);
        assertEquals(0, roots.length);
    }

    /**
     * 5. Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
     */
    @Test
    void test_5_TwoDistinctRoots() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        double[] roots = solver.solve(1, 0, -1);
        assertEquals(2, roots.length);
        assertTrue(roots[0] == 1 || roots[0] == -1);
        assertTrue(roots[1] == 1 || roots[1] == -1);
    }

    /**
     * 6. Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.5.
     */
    @Test
    void test_6_TwoDistinctRootsWithMinimumRealization() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver() {
            @Override
            public double[] solve(double a, double b, double c) {
                double discriminant = b * b - 4 * a * c;
                if (discriminant > EPSILON) {
                    double sqrtDiscriminant = Math.sqrt(discriminant);
                    double root1 = (-b + sqrtDiscriminant) / (2 * a);
                    double root2 = (-b - sqrtDiscriminant) / (2 * a);
                    return new double[]{root1, root2};
                }
                return new double[0];
            }
        };
        double[] roots = solver.solve(1, 0, -1);
        assertEquals(2, roots.length);
        assertTrue(roots[0] == 1 || roots[0] == -1);
        assertTrue(roots[1] == 1 || roots[1] == -1);
    }

    /**
     * 7. Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1).
     */
    @Test
    void test_7_OneRoot() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        double[] roots = solver.solve(1, 2, 1);
        assertEquals(1, roots.length);
        assertEquals(-1, roots[0], EPSILON);
    }

    /**
     * 8. Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.7.
     */
    @Test
    void test_8_OneRootWithMinimumRealization() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver() {
            @Override
            public double[] solve(double a, double b, double c) {
                double discriminant = b * b - 4 * a * c;
                if (Math.abs(discriminant) <= EPSILON) {
                    double root = -b / (2 * a);
                    return new double[]{root};
                }
                return new double[0];
            }
        };
        double[] roots = solver.solve(1, 2, 1);
        assertEquals(1, roots.length);
        assertEquals(-1, roots[0], EPSILON);
    }

    /**
     * 9. Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
     * Примечание. Учесть, что a имеет тип double и сравнивать с 0 через == нельзя.
     */
    @Test
    void test_9_CoefficientACannotBeZero() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        assertThrows(IllegalArgumentException.class, () -> solver.solve(0, 2, 1));
    }

    /**
     * 10. Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.9.
     */
    @Test
    void test_10_CoefficientACannotBeZeroMinimumRealization() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver() {
            @Override
            public double[] solve(double a, double b, double c) {
                if (Math.abs(a) < EPSILON) {
                    throw new IllegalArgumentException("Coefficient 'a' cannot be zero.");
                }

                return new double[0];
            }
        };
        assertThrows(IllegalArgumentException.class, () -> solver.solve(0, 2, 1));
    }

    /**
     * 11. С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства, подобрать такие коэффициенты
     * квадратного уравнения для случая одного корня кратности два, чтобы дискриминант был отличный от нуля,
     * но меньше заданного эпсилон. Эти коэффициенты должны заменить коэффициенты в тесте из п. 7.
     */
    @Test
    void test_11_OneRoot() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        double epsilon = 1e-10;
        double[] roots = solver.solve(1, 2, 1 - epsilon);
        assertEquals(1, roots.length);
        assertEquals(-1, roots[0], EPSILON);
    }

    /**
     * 13. Посмотреть какие еще значения могут принимать числа типа double, кроме числовых и написать тест
     * с их использованием на все коэффициенты. solve должен выбрасывать исключение.
     */
    @Test
    void test_13_NaNValues() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        assertThrows(IllegalArgumentException.class, () -> solver.solve(Double.NaN, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, Double.NaN, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, 2, Double.NaN));
    }

    /**
     * 13. Посмотреть какие еще значения могут принимать числа типа double, кроме числовых и написать тест
     * с их использованием на все коэффициенты. solve должен выбрасывать исключение.
     */
    @Test
    void test_13_InfinityValues() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        assertThrows(IllegalArgumentException.class, () -> solver.solve(Double.POSITIVE_INFINITY, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, Double.POSITIVE_INFINITY, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, 2, Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(Double.NEGATIVE_INFINITY, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, Double.NEGATIVE_INFINITY, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, 2, Double.NEGATIVE_INFINITY));
    }

    /**
     * 14. Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.13.
     */
    @Test
    void test_14_NaNInfinityValuesMinimumRealization() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver() {
            @Override
            public double[] solve(double a, double b, double c) {
                if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c) || Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c)) {
                    throw new IllegalArgumentException("Coefficients must be finite numbers.");
                }

                return new double[0];
            }
        };
        assertThrows(IllegalArgumentException.class, () -> solver.solve(Double.NaN, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, Double.NaN, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, 2, Double.NaN));

        assertThrows(IllegalArgumentException.class, () -> solver.solve(Double.POSITIVE_INFINITY, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, Double.POSITIVE_INFINITY, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, 2, Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(Double.NEGATIVE_INFINITY, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, Double.NEGATIVE_INFINITY, 1));
        assertThrows(IllegalArgumentException.class, () -> solver.solve(1, 2, Double.NEGATIVE_INFINITY));
    }
}