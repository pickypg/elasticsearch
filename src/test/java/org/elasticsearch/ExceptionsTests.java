package org.elasticsearch;

import org.elasticsearch.test.ElasticsearchTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

/**
 * Tests {@link Exceptions}.
 */
public class ExceptionsTests extends ElasticsearchTestCase {
    /**
     * Enables checking of expected exceptions including message.
     */
    @Rule
    public final ExpectedException expected = ExpectedException.none();

    /**
     * The message expected in exceptions.
     */
    private final String message = "message";
    /**
     * Message to indicate an unexpected test failure.
     */
    private final String unexpected = "Not expected";

    /**
     * {@code null} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNull_null_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNull(null, message);
    }

    /**
     * Non-{@code null} should not trigger anything.
     */
    @Test
    public void ifNull_valid() {
        Exceptions.ifNull(new Object(), unexpected);
    }

    /**
     * {@code null} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifEmpty_null_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifEmpty(null, message);
    }

    /**
     * {@link String#isEmpty() Empty} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifEmpty_empty_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifEmpty("", message);
    }

    /**
     * Non-{@code null} and not {@link String#isEmpty() Empty} should not
     * trigger anything.
     */
    @Test
    public void ifEmpty_valid() {
        Exceptions.ifEmpty(" ", unexpected);
        Exceptions.ifEmpty("\n", unexpected);
        Exceptions.ifEmpty("\t", unexpected);
        Exceptions.ifEmpty("\r", unexpected);
        Exceptions.ifEmpty("not empty", unexpected);
    }

    /**
     * {@code null} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNotHasText_null_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNotHasText(null, message);
    }

    /**
     * {@link String#isEmpty() Empty} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNotHasText_empty_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNotHasText("", message);
    }

    /**
     * Blank {@link String}s should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNotHasText_blank_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNotHasText(" \t \r \n ", message);
    }

    /**
     * Non-{@code null}, not {@link String#isEmpty() Empty} and not blank should
     * not trigger anything.
     */
    @Test
    public void ifNotHasText_valid() {
        Exceptions.ifEmpty("not blank", unexpected);
        Exceptions.ifEmpty(" not  blank ", unexpected);
    }

    /**
     * {@code null} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNegativeNumber_null_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNegative((Double)null, message);
    }

    /**
     * A negative number should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNegativeNumber_negative_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNegative(-Double.MIN_VALUE, message);
    }

    /**
     * Non-negative numbers should not trigger anything.
     */
    @Test
    public void ifNegativeNumber_valid() {
        Exceptions.ifNegative(0, unexpected);
        Exceptions.ifNegative(0L, unexpected);
        Exceptions.ifNegative(0f, unexpected);
        Exceptions.ifNegative(0d, unexpected);
        Exceptions.ifNegative(1234, unexpected);
    }

    /**
     * Non-negative numbers should not trigger anything.
     */
    @Test
    public void ifNegativeNumber_valid_random() {
        Exceptions.ifNegative(randomIntBetween(0, Integer.MAX_VALUE),
                              unexpected);
    }

    /**
     * {@code null} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNegativeBigDecimal_null_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        Exceptions.ifNegative((BigDecimal)null, message);
    }

    /**
     * A negative {@link BigDecimal} should trigger an {@link
     * ElasticsearchIllegalArgumentException}.
     */
    @Test
    public void ifNegativeBigDecimal_negative_throwsException() {
        expected.expect(ElasticsearchIllegalArgumentException.class);
        expected.expectMessage(message);

        BigDecimal smallNegative = BigDecimal.valueOf(-Double.MIN_VALUE);

        Exceptions.ifNegative(smallNegative, message);
    }

    /**
     * Non-negative {@link BigDecimal}s should not trigger anything.
     */
    @Test
    public void ifNegativeBigDecimal_valid() {
        Exceptions.ifNegative(BigDecimal.ZERO, unexpected);
        Exceptions.ifNegative(BigDecimal.ONE, unexpected);
        Exceptions.ifNegative(BigDecimal.TEN, unexpected);
    }

    /**
     * Non-negative numbers should not trigger anything.
     */
    @Test
    public void ifNegativeBigDecimal_valid_random() {
        Exceptions.ifNegative(randomIntBetween(0, Integer.MAX_VALUE),
                unexpected);
    }


}
