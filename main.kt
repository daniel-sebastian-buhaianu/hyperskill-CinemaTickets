import java.util.Scanner

val MAX_ROWS = 9
val MAX_COLUMNS = 9

val SEATS = mutableListOf<MutableList<Array<String>>>()

fun main() {

    // Get input from user
    val userInput = getUserInput()

    // Ensure proper usage
    if (!isUserInputValid(userInput)) {
        println("ERRO!")
        println("Maximum number of rows allowed: ${MAX_ROWS}")
        println("Maximum number of seats per row allowed: ${MAX_COLUMNS}")
        return
    }

    val rows = userInput.first()
    val columns = userInput.last()

    initializeSeats(rows, columns)

    printSeats(rows, columns)
}

fun getUserInput(): List<Int> {
    
    val reader = Scanner(System.`in`)

    println("Enter the number of rows:")
    print("> ")
    val rowsCount: Int = reader.nextInt()

    println("Enter the number of seats in each row:")
    print("> ")
    val seatsPerRow: Int = reader.nextInt()

    val userInput: List<Int> = listOf(rowsCount, seatsPerRow)
    return userInput
}

fun isUserInputValid(userInput: List<Int>): Boolean {
    if (userInput[0] > MAX_ROWS) return false
    if (userInput[1] > MAX_COLUMNS) return false
    return true
}

fun initializeSeats(rows: Int, columns: Int) {
    val totalSeats = rows * columns

    repeat(rows) { i ->
        val row = mutableListOf<Array<String>>()
        repeat(columns) {
            val tuple: Array<String> = Array(2) { index -> 
                val result: String
                if (index == 0) {
                    result = "S"
                } else {
                    if (totalSeats <= 60) {
                        result = "10"
                    } else {
                        if (i+1 <= rows/2) {
                            result = "10"
                        } else {
                            result = "8"
                        }
                    }
                }
                result
            }
            row.add(tuple)
        }
        SEATS.add(row)
    }
}

fun printSeats(rows: Int, columns: Int) {
    println()
    println("Cinema: ")
    print("  ")
    for (i in 1..columns) {
        print("$i ")
    }
    println()
    repeat(rows) { i ->
        print("${i+1} ")
        repeat(columns) { j ->
            print("${SEATS[i][j][0]} ")
        }
        println()
    }
}