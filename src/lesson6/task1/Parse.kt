@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    if (parts.count() != 3) {
        return ""
    }
    val day = parts[0].toIntOrNull() ?: return ""
    if (day !in 1..31) {
        return ""
    }
    val month = parts[1]
    if (month !in months.keys) {
        return ""
    }
    val year = parts[2].toIntOrNull() ?: return ""
    if (year < 0) {
        return ""
    }
    val daysInMonth = daysInMonth(months.getValue(month).toInt(), year)
    if (day > daysInMonth) {
        return ""
    }
    return String.format("%02d.${months.getValue(month)}.%04d", day, year)
}

private val months = mapOf(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
)

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    if (parts.count() != 3) {
        return ""
    }
    val day = parts[0].toIntOrNull() ?: return ""
    if (day !in 1..31) {
        return ""
    }
    val month = parts[1]
    if (month !in months.values) {
        return ""
    }
    val year = parts[2].toIntOrNull() ?: return ""
    if (year < 0) {
        return ""
    }
    val daysInMonth = daysInMonth(month.toInt(), year)
    if (day > daysInMonth) {
        return ""
    }
    val monthDigit = months.toList().firstOrNull { it.second == month }?.first ?: return ""
    return String.format("$day $monthDigit $year")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val symbolsToReplace = listOf(" ", "-", "(", ")")
    val positivSymbols = "+0123456789"
    var phoneNew = phone
    //val phoneNew = phone.let { ph -> symbolsToReplace.forEach { ph.replace(it, "") } }
    for (s in symbolsToReplace) {
        phoneNew = phoneNew.replace(s, "")
    }
    for (c in phoneNew) {
        if (c !in positivSymbols) {
            return ""
        }
    }
    val phonePlusesLength = phoneNew.filter { it == '+' }.length
    if (phonePlusesLength > 1 || (phonePlusesLength == 1 && !phoneNew.startsWith('+'))) {
        return ""
    }
    return phoneNew
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val steps = jumps.split(" ")
    var bestJump = -1
    for (step in steps) {
        val curStep = step.toIntOrNull()
        if (curStep != null && curStep > bestJump) {
            bestJump = curStep
        } else if (curStep == null) {
            if (step !in listOf("-", "%")) {
                return -1
            }
        }
    }
    return bestJump
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val steps = jumps.split(" ")
    if (steps.count() % 2 != 0) {
        return -1
    }
    var bestJump = -1
    var idx = 0
    while (idx < steps.count() - 1) {
        val step = steps[idx]
        val curStep = step.toIntOrNull()
        val nextStep = steps[idx + 1]
        if (curStep != null && curStep > bestJump && nextStep == "+") {
            bestJump = curStep
        } else if (curStep == null && step.any { it !in listOf('-', '%', '+') }) {
            return -1
        }
        idx += 2
    }
    return bestJump
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val steps = expression.split(" ")
    if (steps.count() % 2 == 0) {
        throw IllegalArgumentException()
    }
    var summ = steps.first().getIntWithoutSymbols()
    var i = 1
    while (i < steps.count() - 1) {
        val step = steps[i]
        val nextStep = steps[i + 1].getIntWithoutSymbols()
        when (step) {
            "+" -> summ += nextStep
            "-" -> summ -= nextStep
            else -> throw IllegalArgumentException()
        }
        i += 2
    }
    return summ
}

private fun String.getIntWithoutSymbols(): Int {
    if (first().toString().toIntOrNull() == null) {
        throw IllegalArgumentException()
    }
    return toIntOrNull() ?: throw IllegalArgumentException()
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val words = str.split(" ")
    if (words.count() < 2) return -1
    for (i in 0 until words.count() - 1) {
        val word = words[i]
        val nextWord = words[i + 1]
        if (word.toLowerCase() == nextWord.toLowerCase()) {
            return words.filterIndexed { index, _ -> index < i }.sumBy { it.count() } + i
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val goods = description.split("; ")
    var maxGood = "" //название товара с максимальной ценой
    var maxPrice = 0.0 //максимальная цена товара
    for (good in goods) {
        val parts = good.split(" ")
        if (parts.count() != 2) return ""
        val price = parts[1].toDoubleOrNull() ?: return ""
        if (price >= maxPrice) {
            maxPrice = price
            maxGood = parts.first()
        }
    }
    return maxGood
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val res: MutableList<Int> = mutableListOf()
    val finalRes: MutableList<Int> = mutableListOf()
    repeat(cells) { res.add(0) }
    if (commands.any { it !in " ><+-[]" } || commands.count { it == '[' } != commands.count { it == ']' }) {
        throw IllegalArgumentException()
    }
    var curIdx = cells / 2

    val openedCommandIdxes = mutableListOf<Pair<Int, Int>>()// индекс, значение датчика
    var executedCommandsCount = 0
    var curCommandIdx = 0
    while (curCommandIdx < commands.count()) {
        when (commands[curCommandIdx]) {
            ' ' -> {
            }
            '>' -> {
                if (openedCommandIdxes.lastOrNull()?.second != 0) {
                    curIdx++
                    if (curIdx >= res.count() || curIdx < 0) {
                        throw IllegalStateException()
                    }
                }
            }
            '<' -> {
                if (openedCommandIdxes.lastOrNull()?.second != 0) {
                    curIdx--
                    if (curIdx >= res.count() || curIdx < 0) {
                        throw IllegalStateException()
                    }
                }
            }
            '+' -> if (openedCommandIdxes.lastOrNull()?.second != 0) {
                res[curIdx]++
            }
            '-' -> if (openedCommandIdxes.lastOrNull()?.second != 0) {
                res[curIdx]--
            }
            '[' -> if (openedCommandIdxes.lastOrNull()?.second != 0) {
                openedCommandIdxes.add(Pair(curCommandIdx, res[curIdx]))
            }
            ']' -> if (res[curIdx] != 0) {
                curCommandIdx = openedCommandIdxes.last().first
            } else {
                openedCommandIdxes.removeAt(openedCommandIdxes.count() - 1)
            }
            else -> throw IllegalArgumentException()
        }
        if (openedCommandIdxes.lastOrNull()?.second != 0) {
            executedCommandsCount++
        }
        curCommandIdx++
        if (executedCommandsCount <= limit) {
            finalRes.clear()
            finalRes.addAll(res)
        }
    }
    return finalRes
}
