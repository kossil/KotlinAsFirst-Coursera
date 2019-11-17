@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double = if (n <= 1) {
    1.0
} else {
    n * factorial(n - 1)
}


/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var t = n / 10
    while (Math.abs(t) >= 1) {
        count++
        t /= 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if (n in 1..2) 1 else subFib(3, n, 1, 1)


private fun subFib(i: Int, n: Int, first: Int, second: Int): Int = if (i == n) {
    first + second
} else {
    subFib(i + 1, n, second, first + second)
}


/**
 * fun fib(n: Int): Int {
if (n == 1) {
return 1
}
if (n == 2) {
return 1
}
var res = 0
var first = 1
var second = 1
for (i in 3..n) {
res = first + second
first = second
second = res

}
return res
}
 */


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = if (m < n) n else m
    val max = if (m < n) n else m
    while (k <= m * n) {
        if (k % m == 0 && k % n == 0) return k
        k += max
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var d = 2
    while (n % d != 0) {
        d++
    }
    return d
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var d = n - 1
    while (n % d != 0) {
        d--
    }
    return d
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m < 2 || n < 2) return false
    for (d in 2..(if (m > n) n else m)) {
        if (m % d == 0 && n % d == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (k in m..n) {
        if (isIntSqrt(k)) return true
    }
    return false
}

private fun isIntSqrt(k: Int): Boolean {
    val t = sqrt(k.toDouble())
    return t - t.toInt() == 0.0
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var k = 0
    var xNew = x
    while (xNew != 1) {
        if (xNew % 2 == 0) {
            xNew /= 2
        } else {
            xNew = 3 * xNew + 1
        }
        k++
    }
    return k
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var k = 1.0
    var chlen = pow(x, k) / factorial(k.toInt())
    var sum = 0.0
    var d = 1
    while (abs(chlen) >= abs(eps)) {
        sum += chlen
        k += 2.0
        d *= -1
        chlen = (pow(x, k) / factorial(k.toInt())) * d
        if (chlen == Double.POSITIVE_INFINITY) return 0.0
    }
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var k = 0.0
    var chlen = pow(x, k) / factorial(k.toInt())
    var sum = 0.0
    var d = 1
    while (abs(chlen) >= abs(eps)) {
        sum += chlen
        k += 2.0
        d *= -1
        chlen = (pow(x, k) / factorial(k.toInt())) * d
        if (chlen == Double.POSITIVE_INFINITY) return 1.0
    }
    return sum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    val digitCount = digitNumber(n)
    var k = 1
    var rez = 0.0
    while (k <= digitCount) {
        rez += digitFromNumber(n, k) * pow(10.0, digitCount.toDouble() - k)
        k++
    }
    return rez.toInt()
}

fun digitFromNumber(n: Int, k: Int): Int = ((n % pow(10.0, k.toDouble())) / pow(10.0, k.toDouble() - 1)).toInt()

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
