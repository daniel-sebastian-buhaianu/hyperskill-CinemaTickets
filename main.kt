import java.util.Scanner

val MAX_ROWS = 9
val MAX_SEATS_PER_ROW = 9

val SEATS = mutableListOf<MutableList<Array<String>>>()

fun main() {

    val rows = getInt("Enter the number of rows:")
    val seatsPerRow = getInt("Enter the number of seats in each row:")
    // Ensure proper usage
    if (!isInputValid(rows, seatsPerRow)) return

    initializeSeats(rows, seatsPerRow)
    printSeats(rows, seatsPerRow)

    val targetRow = getInt("Enter a row number:")
    val targetSeat = getInt("Enter a seat number in that row:")
    // Ensure proper usage
    if (!isInputValid(targetRow, targetSeat)) return

    println("\nTicket price: $${SEATS[targetRow-1][targetSeat-1][1]}")
    
    SEATS[targetRow-1][targetSeat-1][0] = "B"

    printSeats(rows, seatsPerRow)
}

fun getInt(text: String): Int {
    val reader = Scanner(System.`in`)
    println(text)
    print("> ")
    return reader.nextInt()
}

fun isInputValid(x: Int, y: Int): Boolean {
    if (x > MAX_ROWS || y > MAX_SEATS_PER_ROW) {
        println("ERROR")
        println("Max rows: ${MAX_ROWS}\nMax seats/row: ${MAX_SEATS_PER_ROW}")
        return false
    }
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
    println()
}