@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
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
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var digitCounter = 0
    var digit = n
    do {
        digit /= 10
        digitCounter += 1
    } while (abs(digit) > 0)
    return (digitCounter)
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    /*Был вариант сделать рекурсией, но это занимает слишком много ресурсов компьютера.
    Так итоговое время теста составило 59 секунд.
    Код:
    fun fib(n: Int): Int = return if (n == 1 || n == 2) 1 else fib(n - 1) + fib(n - 2)*/
    var previousNumber = 1
    var presentNumber = 1
    var t: Int
    return if (n == 1 || n == 2) 1
    else {
        for (i in 3..n) {
            t = presentNumber
            presentNumber += previousNumber
            previousNumber = t
        }
        return (presentNumber)
    }
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    /*Находим НОД, используя алгоритм Евклида:*/
    var numberA = max(m, n)
    var numberB = min(m, n)
    var gcd = 1
    var temp = 1
    return when {
        numberA % numberB == 0 -> numberA //случай, когда числа равны или большее число кратно меньшему
        numberA == 1 || numberB == 1 -> m * n //случай, когда одно из чисел или оба числа единицы
        else -> {
            while (temp != 0) {
                gcd = temp
                temp = numberA % numberB
                numberA = numberB
                numberB = temp
            }
            (m / gcd * n)
        }
    }
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var result = n
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            result = i
            break
        }
    }
    return (result)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = if (minDivisor(n) == 1) 1 else n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    /*Находим НОД, используя алгоритм Евклида:*/
    var numberA = max(m, n)
    var numberB = min(m, n)
    var gcd = 1
    var temp = 1
    when {
        numberA % numberB == 0 -> gcd = numberB //случай, когда числа равны или большее число кратно меньшему
        else -> {
            while (temp != 0) {
                gcd = temp
                temp = numberA % numberB
                numberA = numberB
                numberB = temp
            }
        }
    }
    /*Числа будут взаимно простыми, если их наибольший общий делитель равен единице*/
    return gcd == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = sqrt(n.toDouble()) >= ceil(sqrt(m.toDouble()))

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
    var counter = 0
    var number = x
    while (number != 1) {
        if (number % 2 == 0) {
            number /= 2
            counter += 1
        } else {
            number = 3 * number + 1
            counter += 1
        }
    }
    return (counter)
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var numberPower = 1
    var number = x
    while (number >= 2.0 * PI) {
        number -= 2.0 * PI
    }
    while (number <= -2.0 * PI) {
        number += 2.0 * PI
    }
    var result = 0.0
    var newMember = eps
    while (abs(newMember) >= eps) {
        newMember = (number.pow(numberPower)) / factorial(numberPower)
        if (numberPower % 4 == 1) {
            result += newMember
        } else {
            result -= newMember
        }
        numberPower += 2
    }
    return (result)
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var numberPower = 0
    var number = x
    while (number >= 2 * PI) {
        number -= 2 * PI
    }
    while (number <= 2 * PI) {
        number += 2 * PI
    }
    var result = 0.0
    var newMember = eps
    while (newMember >= eps) {
        newMember = (number.pow(numberPower)) / factorial(numberPower)
        if (numberPower % 4 == 0) {
            result += newMember
        } else {
            result -= newMember
        }
        numberPower += 2
    }
    return (result)
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var result = 0
    for (i in 1..digitNumber(number)) {
        result = result * 10 + number % 10
        number /= 10
    }
    return (result)
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    val keyDigit: Int
    var result = false
    keyDigit = number % 10
    number /= 10
    for (i in 1..digitNumber(number)) {
        if (number % 10 != keyDigit) {
            result = true
        }
        number /= 10
    }
    return (result)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var generatedSquare: Int
    var generator = 1
    var strokeLength = 0
    val result: Double
    while (true) {
        generatedSquare = generator * generator
        if (strokeLength + digitNumber(generatedSquare) >= n) {
            /*Использование Double из-за необходимости использования pow*/
            result = generatedSquare / 10.0.pow(digitNumber(generatedSquare) + strokeLength - n) % 10
            break
        } else {
            strokeLength += digitNumber(generatedSquare)
            generator += 1
        }
    }
    return result.toInt()
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var generatedFib: Int
    var generator = 1
    var strokeLength = 0
    val result: Double
    while (true) {
        generatedFib = fib(generator)
        if (strokeLength + digitNumber(generatedFib) >= n) {
            /*Переход к Double из-за необходимости использования pow*/
            result = generatedFib / 10.0.pow(digitNumber(generatedFib) + strokeLength - n) % 10
            break
        } else {
            strokeLength += digitNumber(generatedFib)
            generator += 1
        }
    }
    return result.toInt()
}
