package org.elasticsearch;

import org.elasticsearch.common.Strings;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

/**
 * {@code Exceptions} exists to provide a mechanism for quickly testing incoming
 * parameters to any function or constructor in order to guarantee their code
 * contract. This also reduces the likelihood of a typo (e.g., {@code !=}
 * becoming {@code ==}, or similar) in such contracts by giving readability.
 * <p />
 * This replaces long-winded code such as
 * <p />
 * <pre>{@code
 * if (value1 == null) {
 *   throw new ElasticsearchIllegalArgumentException("value1 cannot be null");
 * }
 * if (Strings.isNullOrEmpty(value2)) {
 *   throw new ElasticsearchIllegalArgumentException("value2 cannot be empty");
 * }
 *
 * // required
 * this.value1 = value1;
 * this.value2 = value2;
 * // optional
 * this.value3 = value3;
 * }</pre>
 * <p />
 * with shorter code such as
 * <p />
 * <pre>{@code
 * // required
 * this.value1 = Exceptions.ifNull(value1, "value1 cannot be null");
 * this.value2 = Exceptions.ifEmpty(value2, "value2 cannot be empty");
 * // optional
 * this.value3 = value3;
 * }</pre>
 * <p />
 * By using a static import of {@code Exceptions}, the code can be shortened
 * further.
 */
public final class Exceptions {
    /**
     * Throw an {@link ElasticsearchNullPointerException} if the {@code value}
     * is {@code null}.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null}.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null}
     */
    public static <T> T ifNull(T value, String message) {
        if (value == null) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or {@link String#isEmpty() empty}.
     * <p />
     * Note: Being non-empty only indicates that it is at least one character,
     * which could itself be some form of whitespace. Consider using {@link
     * #ifNotHasText} instead.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null} or {@link String#isEmpty() empty}.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or {@link
     *                                               String#isEmpty() empty}
     */
    public static String ifEmpty(String value, String message) {
        if (value == null || value.isEmpty()) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null}, {@link String#isEmpty() empty} or made up
     * entirely of whitespace (blank).
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null}, {@link String#isEmpty() empty} or made up
     *         entirely of whitespace.
     * @throws ElasticsearchIllegalArgumentException if {@code value} does not
     *                                               contain any text ({@code
     *                                               null} or all whitespace)
     */
    public static String ifNotHasText(String value, String message) {
        if ( ! Strings.hasText(value)) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or negative.
     * @param value The value to test
     * @param message The message to use with the exception
     * @param <T> Any implementation of {@link Number}.
     * @return Never {@code null} and always greater than or equal to zero.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or &lt; 0
     */
    public static <T extends Number> T ifNegative(T value, String message) {
        if (value == null || value.doubleValue() < 0) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or negative.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null} and always greater than or equal to {@link
     *         BigDecimal#ZERO}.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or &lt; 0
     */
    public static BigDecimal ifNegative(BigDecimal value, String message) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or negative.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null} and always greater than or equal to {@link
     *         BigInteger#ZERO}.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or &lt; 0
     */
    public static BigInteger ifNegative(BigInteger value, String message) {
        if (value == null || value.compareTo(BigInteger.ZERO) < 0) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or not greater than zero.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null} and always greater than zero.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or &lt;= 0
     */
    public static <T extends Number> T ifNotPositive(T value, String message) {
        if (value == null || value.doubleValue() <= 0) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or not greater than {@link BigDecimal#ZERO}.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null} and always greater than {@link
     *         BigDecimal#ZERO}.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or &lt;= 0
     */
    public static BigDecimal ifNotPositive(BigDecimal value, String message) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or not greater than {@link BigInteger#ZERO}.
     * @param value The value to test
     * @param message The message to use with the exception
     * @return Never {@code null} and always greater than {@link
     *         BigInteger#ZERO}.
     * @throws ElasticsearchIllegalArgumentException if {@code value} is {@code
     *                                               null} or &lt;= 0
     */
    public static BigInteger ifNotPositive(BigInteger value, String message) {
        if (value == null || value.compareTo(BigInteger.ZERO) <= 0) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return value;
    }


    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or {@link Collection#contains} {@code null}.
     * <p />
     * Note: This will loop across the {@code collection} in lieu of using
     * {@link Collection#contains} to avoid the allowed {@link
     * NullPointerException} if the {@code collection} does not allow
     * {@code null}s.
     * <p />
     * With that in mind, if you are always using those types of collections,
     * then you should only use {@link #ifNull(Object, String)}.
     * @param collection The collection to test
     * @param message The message to use with the exception
     * @return Never {@code null}. Never contains {@code null}.
     * @throws ElasticsearchIllegalArgumentException if {@code collection} is
     *                                               {@code null} or contains
     *                                               {@code null}
     */
    public static <V, T extends Collection<V>> T ifHasNull(T collection,
                                                           String message) {
        if (collection == null) {
            throw new ElasticsearchIllegalArgumentException(message);
        }
        else {
            for (Object value : collection) {
                if (value == null) {
                    throw new ElasticsearchIllegalArgumentException(message);
                }
            }
        }

        return collection;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * value} is {@code null} or {@link Collection#contains} {@code null}.
     * <p />
     * Note: If the {@code collection}, {@code T}, does not support {@code null}
     * values within it (e.g., Guava-based collections), then it is free to
     * raise a {@link NullPointerException}. To avoid this behavior, you can
     * use the safer, but likely slower {@link #ifHasNull(Collection, String)}.
     * <p />
     * With that in mind, if you are always using those types of collections,
     * then you should only use {@link #ifNull(Object, String)}.
     * @param collection The collection to test
     * @param message The message to use with the exception
     * @return Never {@code null}. Never contains {@code null}.
     * @throws ElasticsearchIllegalArgumentException if {@code collection} is
     *                                               {@code null} or contains
     *                                               {@code null}
     */
    public static <V, T extends Collection<V>> T ifContainsNull(T collection,
                                                                String message) {
        if (collection == null || collection.contains(null)) {
            throw new ElasticsearchIllegalArgumentException(message);
        }

        return collection;
    }

    /**
     * Throw an {@link ElasticsearchIllegalArgumentException} if the {@code
     * condition} is {@code false} (not {@code true}).
     * @param condition The value to test
     * @param message The message to use with the exception
     * @throws ElasticsearchIllegalArgumentException if {@code condition} is
     *                                               {@code false}
     */
    public static void ifNot(boolean condition, String message) {
        if ( ! condition) {
            throw new ElasticsearchIllegalArgumentException(message);
        }
    }
}
