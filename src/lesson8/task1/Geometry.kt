@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.math.*

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        val circleDistance = center.distance(other.center) - radius - other.radius
        return if (circleDistance < 0) 0.0 else circleDistance
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = (p.x - center.x).pow(2) + (p.y - center.y).pow(2) <= radius.pow(2)
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
        other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    var max = 0.0
    var mxPoints = Point(0.0, 0.0) to Point(0.0, 0.0)
    for (i in points.indices) {
        for (j in i + 1..points.lastIndex) {
            if (max < points[i].distance(points[j])) {
                max = points[i].distance(points[j])
                mxPoints = points[i] to points[j]
            }
        }
    }
    if (max == 0.0) throw (IllegalArgumentException())
    return Segment(mxPoints.first, mxPoints.second)
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle =
    Circle(
        Point(
            (max(diameter.end.x, diameter.begin.x) - min(diameter.end.x, diameter.begin.x)) / 2,
            (max(diameter.end.y, diameter.begin.y) - min(diameter.end.y, diameter.begin.y)) / 2
        ),
        diameter.begin.distance(diameter.end) / 2
    )

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double, private val px: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) :
            this(point.y * cos(angle) - point.x * sin(angle), angle, point.x)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val crossX: Double
        val crossY: Double
        when {
            angle == PI / 2 && other.angle == PI / 2 -> throw (Exception("Can't find cross point: lines are parallel"))
            angle == PI / 2 || other.angle == PI / 2 -> {
                crossY = if (angle == PI / 2) {
                    crossX = px
                    crossX * sin(other.angle) / cos(other.angle) + other.b / cos(other.angle)
                } else {
                    crossX = other.px
                    crossX * sin(angle) / cos(other.angle) + other.b / cos(other.angle)
                }
            }
            else -> {
                val tgA = sin(angle) / cos(angle)
                val tgB = sin(other.angle) / cos(other.angle)
                if (tgA == tgB)
                    throw (Exception("Can't find cross point: lines are parallel"))
                else {
                    crossX = ((other.b / cos(other.angle) - b / cos(angle)) / (tgA - tgB))
                    crossY = tgA * crossX + b / cos(angle)
                }
            }
        }
        return (Point(crossX, crossY))
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun arctg(x: Double, eps: Double): Double {
    var factorialCounter = 3
    val number = (x % (2 * PI)).pow(2)
    var result = 0.0
    var newMember = x % (2 * PI)
    while (abs(newMember) >= eps) {
        result += newMember
        newMember = -1.0 * newMember * number * (factorialCounter - 2) / factorialCounter
        factorialCounter += 2
    }
    result += newMember
    return if (result >= 0) result else (result + PI)
}
fun lineBySegment(s: Segment): Line {
    val angle = arctg((s.end.y - s.begin.y) / (s.end.x - s.begin.x), 1e-5)
    val angleMultiplier = floor(arctg((s.end.y - s.begin.y) / (s.end.x - s.begin.x), 1e-5) / PI)
    return when {
        s.end.x - s.begin.x == 0.0 -> Line(s.begin, PI / 2)
        angleMultiplier == 0.0 -> Line(s.begin, angle)
        else -> Line(s.begin, PI / angleMultiplier)
    }
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = lineBySegment(Segment(a, b))

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line = TODO()

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> = TODO()

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle = TODO()

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle = TODO()

