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
	if (digit == 0) return 1
	while (digit > 0) {
		digit /= 10
		digitCounter += 1
	}
	return (digitCounter)
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
	var previousNumber = 1
	var presentNumber = 1
	var t: Int
	for (i in 3..n) {
		t = presentNumber
		presentNumber += previousNumber
		previousNumber = t
	}
	return if ((n == 1) or (n == 2)) 1
	else (presentNumber)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
	var q = m
	var a: Int
	var b: Int
	var result = 0
	a = max(m, n)
	b = min(m, n)
	while (q != 0) { /*находим НОД при помощи алгоритма Евклида*/
		result = q
		q = a % b
		a = b
		b = q
	}
	return (m * n / result) /*находим НОК через НОД*/
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
	var result = 0
	return if (n % 2 == 0) (2)
	else {
		for (i in 3..n step 2) {
			if (n % i == 0) {
				result = i
				break
			}
		}
		return (result)
	}
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
	var result = 0
	for (i in 1 until (n - 1)) {
		if (n % i == 0) {
			result = i
		}
	}
	return (result)
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
	var result = true
	for (i in 2..max(m, n)) {
		if ((m % i == 0) and (n % i == 0)) result = false
	}
	return (result)
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
	return when {
		(sqrt(n.toDouble()).toInt() != sqrt(m.toDouble()).toInt()) -> true
		(sqrt(n.toDouble()) % 1 == 0.0) or (sqrt(m.toDouble()) % 1 == 0.0) -> true
		else -> false
	}
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
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
	var number = n
	var result = 0.0
	var numberLength = 0
	if (number == 0) return (0)
	while (number > 0) {
		number /= 10
		numberLength += 1
	}
	numberLength -= 1
	number = n
	while (numberLength >= 0) {
		result += (number % 10) * 10.0.pow(numberLength)
		number /= 10
		numberLength -= 1
	}
	return (result.toInt())
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
fun isPalindrome(n: Int): Boolean {
	var numberLength = 0
	var number = n
	var firstHalf = 0
	val secondHalf: Int
	while (number > 0) {
		number /= 10
		numberLength += 1
	}
	if (numberLength == 1) return true
	else {
		number = n
		if (numberLength % 2 == 0) {
			for (i in 1..numberLength / 2) {
				firstHalf += (number % 10) * 10.0.pow(i - 1).toInt()
				number /= 10
			}
			secondHalf = revert(number)
		} else {
			for (i in 1..numberLength / 2) {
				firstHalf += (number % 10) * 10.0.pow(i - 1).toInt()
				number /= 10
			}
			firstHalf += (number % 10) * 10.0.pow((numberLength / 2)).toInt()
			secondHalf = revert(number)
		}
		return (firstHalf == secondHalf)
	}
}

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
	var numberLength = 0
	val keyDigit: Int
	var result = false
	while (number > 0) {
		number /= 10
		numberLength += 1
	}
	number = n
	keyDigit = number % 10
	number /= 10
	while (numberLength > 1) {
		if (number % 10 != keyDigit) {
			result = true
		}
		number /= 10
		numberLength -= 1
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
	var counter = 0
	var generator = 1
	var generatedSqr: Int
	var squareLength: Int
	var result = 0
	while (counter != n) {
		generatedSqr = generator * generator
		squareLength = 0
		while (generatedSqr > 0) {
			generatedSqr /= 10
			squareLength += 1
		}
		generatedSqr = generator * generator
		if (counter + squareLength >= n) {
			generatedSqr = revert(generatedSqr)
			for (i in 1..squareLength) {
				counter += 1
				if (counter == n) {
					result = generatedSqr % 10
					break
				} else {
					generatedSqr /= 10
				}
			}
		} else {
			counter += squareLength
		}
		generator += 1
	}
	return (result)
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
fun fibSequenceDigit(n: Int): Int = TODO()
