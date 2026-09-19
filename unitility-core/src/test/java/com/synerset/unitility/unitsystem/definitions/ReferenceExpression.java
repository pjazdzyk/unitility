package com.synerset.unitility.unitsystem.definitions;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Evaluates a golden-table reference expression, such as {@code "1055.05585262 / 3600 / 0.3048 / 0.3048"}, in
 * {@link BigDecimal} at {@link MathContext#DECIMAL128}.
 * <p>
 * It exists so that every golden row can be written as the published numbers themselves, typed from the source,
 * never as a Unitility constant. The golden table must stay independent of {@link UnitDefinitions}: if it used
 * those names it would only test that the library agrees with itself.
 * <p>
 * Grammar: {@code expr := term (('+'|'-') term)*}, {@code term := factor (('*'|'/') factor)*},
 * {@code factor := '-' factor | number | 'pi' | 'sqrt(' expr ')' | '(' expr ')'}. {@code pi} is taken from
 * {@link Math#PI} (π to double precision, about 1.2e-16 relative), which is ample for a comparison at 1e-12 and is
 * not a measured value.
 */
final class ReferenceExpression {

    static final MathContext MC = MathContext.DECIMAL128;

    private final String text;
    private int pos;

    private ReferenceExpression(String text) {
        this.text = text;
    }

    static BigDecimal evaluate(String expression) {
        ReferenceExpression parser = new ReferenceExpression(expression);
        BigDecimal result = parser.expr();
        parser.skipSpaces();
        if (parser.pos != parser.text.length()) {
            throw new IllegalArgumentException("Unexpected input at " + parser.pos + " in: " + expression);
        }
        return result;
    }

    private BigDecimal expr() {
        BigDecimal value = term();
        while (true) {
            skipSpaces();
            if (accept('+')) {
                value = value.add(term(), MC);
            } else if (accept('-')) {
                value = value.subtract(term(), MC);
            } else {
                return value;
            }
        }
    }

    private BigDecimal term() {
        BigDecimal value = factor();
        while (true) {
            skipSpaces();
            if (accept('*')) {
                value = value.multiply(factor(), MC);
            } else if (accept('/')) {
                value = value.divide(factor(), MC);
            } else {
                return value;
            }
        }
    }

    private BigDecimal factor() {
        skipSpaces();
        if (accept('-')) {
            return factor().negate();
        }
        if (accept('(')) {
            BigDecimal inner = expr();
            expect(')');
            return inner;
        }
        if (text.startsWith("sqrt(", pos)) {
            pos += 5;
            BigDecimal inner = expr();
            expect(')');
            return inner.sqrt(MC);
        }
        if (text.startsWith("pi", pos)) {
            pos += 2;
            return new BigDecimal(Math.PI);
        }
        int start = pos;
        while (pos < text.length() && (Character.isDigit(text.charAt(pos)) || text.charAt(pos) == '.'
                || text.charAt(pos) == 'E' || text.charAt(pos) == 'e'
                || ((text.charAt(pos) == '-' || text.charAt(pos) == '+') && pos > start
                && (text.charAt(pos - 1) == 'E' || text.charAt(pos - 1) == 'e')))) {
            pos++;
        }
        if (start == pos) {
            throw new IllegalArgumentException("Number expected at " + pos + " in: " + text);
        }
        return new BigDecimal(text.substring(start, pos));
    }

    private void skipSpaces() {
        while (pos < text.length() && text.charAt(pos) == ' ') {
            pos++;
        }
    }

    private boolean accept(char c) {
        skipSpaces();
        if (pos < text.length() && text.charAt(pos) == c) {
            pos++;
            return true;
        }
        return false;
    }

    private void expect(char c) {
        if (!accept(c)) {
            throw new IllegalArgumentException("'" + c + "' expected at " + pos + " in: " + text);
        }
    }

}
