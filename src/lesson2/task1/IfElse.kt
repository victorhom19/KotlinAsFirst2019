@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when {
        ((age%100 > 10) and (age%100 < 20)) -> ("$age лет")
        ((age%10 > 1) and (age%10 < 5)) -> ("$age года")
        (age%10 == 1) -> ("$age год")
        else -> ("$age лет")
    }

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = v1 * t1 //длина 1ого отрезка пути
    val s2 = v2 * t2 //длина 2ого отрезка пути
    val s3 = v3 * t3 //длина 3его отрезка пути
    val s = s1 + s2 + s3 //весь путь
    return when {
        ((s / 2) <= s1) -> ((s / 2) / v1)
        ((s / 2) <= s1 + s2) -> (((s / 2) - s1) / v2 + t1)
        else -> (((s / 2) - s1 - s2) / v3 + t1 + t2)
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when {
    ((kingX == rookX1) or (kingY == rookY1)) and ((kingX == rookX2) or (kingY == rookY2)) -> (3)
    ((kingX == rookX1) or (kingY == rookY1)) -> (1)
    ((kingX == rookX2) or (kingY == rookY2)) -> (2)
    else -> (0)
}
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val isSameLine = ((kingX == rookX) or (kingY == rookY))
    //проверка того, стоят ли фигуры на одной линии (король и ладья)
    val isSameDiagonal = (abs((kingX - bishopX).toDouble() / (kingY - bishopY)) == 1.0)
    //проверка того, стоят ли фигуры на одной диагонали (король и слон)
    return when {
        isSameLine and isSameDiagonal -> 3
        isSameDiagonal -> 2
        isSameLine -> 1
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int =
    when {
        ((a + b <= c) or (b + c <= a) or (a + c <= b)) -> -1
        //проверка того, существует ли треугольник с заданными сторонами
        ((a * a + b * b == c * c) or (b * b + c * c == a * a) or (a * a + c * c == b * b)) -> 1
        //проверка того, является ли данный треугольник прямоугольным
        (((a * a + b * b - c * c) / (2 * a * b) < 0) or
        ((c * c + b * b - a * a) / (2 * c * b) < 0) or
        ((a * a + c * c - b * b) / (2 * a * c)< 0)) -> 2
        //проверка того, имеет ли данный треугольник тупой угол (использование теоремы косинусов)
        else -> 0
        //в ином случае данный треугольник является остроугольным
    }

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int =
    when {
        (( a == c) and (b == d)) -> (b - a) //случай, когда отрезки совпадают
        ((a < c) and (b > d)) -> (d - c) //случай, когда ab содержит cd
        ((c < a) and (d > b)) -> (b - a) //случай, когда cd содержит ab
        ((b >= c) and (b <= d)) -> (b - c) //случай, когда конец ab лежит внутри cd
        ((a >= c) and (a <= d)) -> (d - a) //случай, когда конец cd лежит внутри ab
        else -> -1 //в ином случае отрезки не пересекаются
    }
