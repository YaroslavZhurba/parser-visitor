package tokenizer

import ErrorState
import NumberState
import StartState
import TokenizerState
import tokens.Token
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.system.exitProcess

class Tokenizer(s: InputStream) {
    private var state: TokenizerState = StartState
    private var tokens: MutableList<Token> = mutableListOf()
    private val reader: InputStreamReader = s.reader()
    var symbol: Char = 0.toChar()

    fun run(): List<Token> {
        next()
        while (state is StartState || state is NumberState) {
            state.process(this)
        }
        when (state) {
            is ErrorState -> {
                println((state as ErrorState).err)
                exitProcess(-1)
            }
            else -> return tokens
        }
    }

    fun setState(newState: TokenizerState) {
        state = newState
    }

    fun addToken(token: Token) {
        tokens.add(token)
        next()
    }

    fun addAndStay(token: Token) {
        tokens.add(token)
    }

    fun next() {
        symbol = reader.read().toChar()
    }

}