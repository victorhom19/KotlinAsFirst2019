@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.minDivisor
import kotlin.math.pow
import kotlin.math.sqrt

val alphabet = listOf(
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p',
    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
)

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var resultVector = 0.0
    for (componentVector in v) {
        resultVector += componentVector.pow(2)
    }
    resultVector = sqrt(resultVector)
    return resultVector
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.sum() / list.size else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    val mean = mean(list)
    for (i in list.indices) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    if (a.isEmpty()) return 0
    var result = 0
    for (i in a.indices) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var result = 0.0
    for (i in p.indices) {
        result += p[i] * x.toDouble().pow(i)
    }
    return result.toInt()
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isNotEmpty()) {
        var sum = 0
        for (i in list.indices) {
            sum += list[i]
            list[i] = sum
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val simpleMultipliers = mutableListOf<Int>()
    var number = n
    while (number > 1) {
        simpleMultipliers.add(minDivisor(number))
        number /= minDivisor(number)
    }
    return simpleMultipliers
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var number = n
    while (number >= base) {
        result.add(0, number % base)
        number /= base
    }
    result.add(0, number)
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var stroke = ""
    for (digit in convert(n, base)) {
        stroke += alphabet[digit]
    }
    return stroke
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for (i in digits) {
        result = result * base + i
    }
    return (result)
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    for (char in str) {
        result = result * base + alphabet.indexOf(char)
    }
    return (result)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    var stroke = ""
    val keyAlphabet = listOf('C', 'C', 'X', 'X', 'I', 'I', 'I')
    val keyAlphabetValues = listOf(100, 100, 10, 10, 1, 1, 1)
    val rAlphabet = listOf('M', 'D', 'C', 'L', 'X', 'V', 'I')
    val rAlphabetValues = listOf(1000, 500, 100, 50, 10, 5, 1)
    for (i in rAlphabet.indices) {
        while (number >= rAlphabetValues[i]) {
            stroke += rAlphabet[i]
            number -= rAlphabetValues[i]
        }
        if ((number > 1) && (number >= rAlphabetValues[i] - keyAlphabetValues[i])) {
            stroke = stroke + keyAlphabet[i] + rAlphabet[i]
            number = number + keyAlphabetValues[i] - rAlphabetValues[i]
        }
    }
    return (stroke)
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val thousands = n / 1000
    val hundreds = n % 1000
    var stroke = ""
    if (thousands != 0) {
        stroke += when (thousands / 100) {
            1 -> "сто "
            2 -> "двести "
            3 -> "триста "
            4 -> "четыреста "
            5 -> "пятьсот "
            6 -> "шестьсот "
            7 -> "семьсот "
            8 -> "восемьсот "
            9 -> "девятьсот "
            else -> ""
        }
        if (thousands % 100 in 11..19) {
            stroke += when (thousands % 100) {
                11 -> "одиннадцать тысяч "
                12 -> "двенадцать тысяч "
                13 -> "тринадцать тысяч "
                14 -> "четырнадцать тысяч "
                15 -> "пятнадцать тысяч "
                16 -> "шестнадцать тысяч "
                17 -> "семнадцать тысяч "
                18 -> "восемнадцать тысяч "
                19 -> "девятнадцать тысяч "
                else -> ""
            }
        } else {
            stroke += when (thousands / 10 % 10) {
                1 -> "десять "
                2 -> "двадцать "
                3 -> "тридцать "
                4 -> "сорок "
                5 -> "пятьдесят "
                6 -> "шестьдесят "
                7 -> "семьдесят "
                8 -> "восемьдесят "
                9 -> "девяносто "
                else -> ""
            }
            stroke += when (thousands % 10) {
                0 -> "тысяч "
                1 -> "одна тысяча "
                2 -> "две тысячи "
                3 -> "три тысячи "
                4 -> "четыре тысячи "
                5 -> "пять тысяч "
                6 -> "шесть тысяч "
                7 -> "семь тысяч "
                8 -> "восемь тысяч "
                9 -> "девять тысяч "
                else -> ""
            }
        }
    }
    if (hundreds != 0) {
        stroke += when (hundreds / 100) {
            1 -> "сто "
            2 -> "двести "
            3 -> "триста "
            4 -> "четыреста "
            5 -> "пятьсот "
            6 -> "шестьсот "
            7 -> "семьсот "
            8 -> "восемьсот "
            9 -> "девятьсот "
            else -> ""
        }
        if (hundreds % 100 in 11..19) {
            stroke += when (hundreds % 100) {
                11 -> "одиннадцать "
                12 -> "двенадцать "
                13 -> "тринадцать "
                14 -> "четырнадцать "
                15 -> "пятнадцать "
                16 -> "шестнадцать "
                17 -> "семнадцать "
                18 -> "восемнадцать "
                19 -> "девятнадцать "
                else -> ""
            }
        } else {
            stroke += when (hundreds / 10 % 10) {
                1 -> "десять "
                2 -> "двеадцать "
                3 -> "тридцать "
                4 -> "сорок "
                5 -> "пятьдесят "
                6 -> "шестьдесят "
                7 -> "семьдесят "
                8 -> "восемьдесят "
                9 -> "девяносто "
                else -> ""
            }
            stroke += when (hundreds % 10) {
                1 -> "один"
                2 -> "два"
                3 -> "три"
                4 -> "четыре"
                5 -> "пять"
                6 -> "шесть"
                7 -> "семь"
                8 -> "восемь"
                9 -> "девять"
                else -> ""
            }
        }
    }
    return stroke.trim()
}