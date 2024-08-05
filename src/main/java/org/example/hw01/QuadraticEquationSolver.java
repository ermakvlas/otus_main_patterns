package org.example.hw01;

import org.example.hw01.validation.QuadraticEquationValidator;
import org.example.hw01.validation.QuadraticEquationValidatorImpl;

/**
 * Необходимо реализовать операцию нахождения квадратного уравнения.
 * Предположим, что эта операция описывается следующей функцией c поправкой на конкретный язык программирования.
 * В ООП языках эта функция реализуется в виде метода класса.
 * solve(double a, double b, double c): double[]
 * здесь a, b, c - коэффициенты квадратного уравнения, функция возвращает список корней квадратного уравнения.
 * ax^2 + bx + c = 0
 */
public class QuadraticEquationSolver {

    private final QuadraticEquationValidator validator;

    public QuadraticEquationSolver() {
        this.validator = new QuadraticEquationValidatorImpl();
    }

    public double[] solve(double a, double b, double c) {
        validator.validateQuadraticEquationCoefficients(a, b, c);

        double discriminant = calculateDiscriminant(a, b, c);

        if (isNoRoots(discriminant)) {
            return new double[0];
        } else if (isSingleRoot(discriminant)) {
            return new double[]{-b / (2 * a)};
        } else {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            return new double[]{
                    (-b + sqrtDiscriminant) / (2 * a),
                    (-b - sqrtDiscriminant) / (2 * a)
            };
        }
    }

    private double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    private boolean isSingleRoot(double discriminant) {
        return validator.isZero(discriminant);
    }

    private boolean isNoRoots(double discriminant) {
        return validator.isNegative(discriminant);
    }
}
