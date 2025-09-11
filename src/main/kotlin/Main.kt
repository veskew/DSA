package org.example

import org.ejml.simple.SimpleMatrix

class board() {
    val current_board = SimpleMatrix(3, 3)
    var gamestate: String = "Happening"
    var rounds: Int = 0
    var winner: Double = 0.0
    fun getMove(): List<Int> {
        print("What row do you want: ")

        // Read the user's input as a String
        var row: Int = -1
        while ((0 <= row) and (row <= 2))
            row = readln().toInt()

        // Prompt for another input, for example, an age
        print("what column do you want: ")

        var col: Int = -1
        while ((0 <= col) and (col <= 2))
            col = readln().toInt()

        return listOf(row, col)
    }

    fun move(player: Double): Int{
        val rowcol: List<Int> = getMove()
        val row: Int = rowcol[0]
        val col: Int = rowcol[1]

        val valid: Boolean = when{
            current_board[row, col] == 0.0 -> true
            else -> false
        }
        if (valid) {
            current_board[row, col] = player
            println("player $player populated row $row and col $col")
            displayBoard()
            handleWin(player)
            rounds++
            return 1
        }
        else
            println("Try again!")
            return 0
    }
    fun play() {
        while (rounds < 9 && gamestate == "Happening") {
            move((rounds % 2 + 1).toDouble())
        }
        when (winner) {
            0.0 -> println("Game ends in a tie!")
            else -> println("Player ${winner.toInt()} won!")
        }
    }
    fun displayBoard() {
        println(current_board)
    }
    fun handleWin(player: Double) {
        if (checkWin(player)) {
            gamestate = "Over"
            winner = player
        }
    }
    fun checkWin(player: Double): Boolean{
        if (current_board[0,0] == player) {
            if (current_board[0,1] == player)
                if (current_board[0,2] == player) return true
            if (current_board[1,0] == player)
                if (current_board[2,0] == player) return true
            if (current_board[1,1] == player)
                if (current_board[2, 2] == player) return true
        }
        if (current_board[1,1] == player){
            if (current_board[0,2] == player)
                if (current_board[2,0] == player) return true
            if (current_board[1,2] == player)
                if (current_board[1,0] == player) return true
            if (current_board[0,1] == player)
                if (current_board[2,1] == player) return true
        }
        if (current_board[2,2] == player) {
            if (current_board[1, 2] == player)
                if (current_board[0, 2] == player) return true
            if (current_board[2, 1] == player)
                if (current_board[2, 0] == player) return true
        }
        return false
    }
}

fun testCheckWin1(): Boolean{
    val board = board()
    board.current_board[0,0] = 1.0
    board.current_board[0,1] = 1.0
    board.current_board[0,2] = 1.0
    return (board.checkWin(1.0))

}

fun testCheckWin2(): Boolean{
    val board = board()
    board.current_board[0,0] = 1.0
    board.current_board[1,1] = 1.0
    board.current_board[0,2] = 1.0
    return (!board.checkWin(1.0))

}

fun main() {
    val board = board()
    board.play()
    println("Run testCheckWin1: ${testCheckWin1()}")
    println("Run testCheckWin2: ${testCheckWin2()}")
}