fun main() {
    val arr = arrayOf(3, 7, 8, 2, 10, 4, 6, 1, 5)
    sort(arr)
    arr.forEach { print("$it ") }
}

tailrec fun <T : Comparable<T>> sort(arr: Array<T>, len: Int = 1) {
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
    if (2 * len >= arr.size) {
        return
    } else {
        sort(arr, 2 * len)
    }
}

fun <T : Comparable<T>>sortTwoParts(arr: Array<T>, first: Int, sfirst: Int, after_last: Int) {
    val tempArr = arr.copyOfRange(first, after_last)
    var i = first
    var j = sfirst
    var pos = first
    while (i < sfirst && j < after_last) {
        if (tempArr[i - first] <= tempArr[j - first]) {
            arr[pos] = tempArr[i - first]
            ++i
        } else {
            arr[pos] = tempArr[j - first]
            ++j
        }
        ++pos
    }
    while (i == sfirst && j != after_last) {
        arr[pos] = tempArr[j - first]
        ++j
        ++pos
    }
    while (i != sfirst && j == after_last) {
        arr[pos] = tempArr[i - first]
        ++i
        ++pos
    }
}