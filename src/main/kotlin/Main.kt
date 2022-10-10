fun main() {
    sort(arrayOf(3, 7, 8, 2, 10, 4, 6, 1, 5)).forEach { print("$it ") }.also { println() }
    sort(emptyArray<Int>()).forEach { print("$it ") }.also { println() }
    sort(arrayOf(100)).forEach { print("$it ") }.also { println() }
}

tailrec fun <T : Comparable<T>> sort(arr: Array<T>, len: Int = 1): Array<T> {
    var i = 0
    while (true) {
        val first = i
        var secondFirst: Int
        if (i + len <= arr.size) {
            secondFirst = i + len
        } else {
            break
        }
        val afterLast = if (i + 2 * len < arr.size) {
            i + 2 * len
        } else {
            arr.size
        }
        sortTwoParts(arr, first, secondFirst, afterLast)
        ++i
    }
    return if (2 * len >= arr.size) {
        arr
    } else {
        sort(arr, 2 * len)
    }
}

fun <T : Comparable<T>>sortTwoParts(arr: Array<T>, first: Int, secondFirst: Int, after_last: Int) {
    val tempArr = arr.copyOfRange(first, after_last)
    var i = first
    var j = secondFirst
    var pos = first
    while (i < secondFirst && j < after_last) {
        arr[pos++] = if (tempArr[i - first] <= tempArr[j - first]) {
            tempArr[i++ - first]
        } else {
            tempArr[j++ - first]
        }
    }
    while (i == secondFirst && j != after_last) {
        arr[pos++] = tempArr[j++ - first]
    }
    while (i != secondFirst && j == after_last) {
        arr[pos++] = tempArr[i++ - first]
    }
}