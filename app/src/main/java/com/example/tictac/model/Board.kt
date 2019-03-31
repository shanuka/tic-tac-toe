package com.example.tictac.model

data class Board(val player: Int) {
    private val elts: CharArray
    private var currentPlayer: Char = ' '
    var isEnded: Boolean = false
        private set

    init {
        elts = CharArray(9)
    }

    fun play(x: Int, y: Int): Char {
        if (!isEnded && elts[3 * y + x] == ' ') {
            elts[3 * y + x] = currentPlayer
            changePlayer()
        }

        return checkEnd()
    }

    fun getCurrentPlayer(): Char {
        return currentPlayer
    }

    fun changePlayer() {
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }


    fun getElt(x: Int, y: Int): Char {
        return elts[3 * y + x]
    }

    fun newGame() {
        for (i in elts.indices) {
            elts[i] = ' '
        }

        currentPlayer = if (player == 0) {
            'X'
        } else {
            'O'
        }
        isEnded = false
    }

    fun checkEnd(): Char {
        for (i in 0..2) {
            if (getElt(i, 0) != ' ' &&
                getElt(i, 0) == getElt(i, 1) &&
                getElt(i, 1) == getElt(i, 2)
            ) {
                isEnded = true
                return getElt(i, 0)
            }

            if (getElt(0, i) != ' ' &&
                getElt(0, i) == getElt(1, i) &&
                getElt(1, i) == getElt(2, i)
            ) {
                isEnded = true
                return getElt(0, i)
            }
        }

        if (getElt(0, 0) != ' ' &&
            getElt(0, 0) == getElt(1, 1) &&
            getElt(1, 1) == getElt(2, 2)
        ) {
            isEnded = true
            return getElt(0, 0)
        }

        if (getElt(2, 0) != ' ' &&
            getElt(2, 0) == getElt(1, 1) &&
            getElt(1, 1) == getElt(0, 2)
        ) {
            isEnded = true
            return getElt(2, 0)
        }

        for (i in 0..8) {
            if (elts[i] == ' ')
                return ' '
        }

        return 'T'
    }

}
