package visitor

import Brace
import NumberToken
import BinaryOperation
import tokens.Token
import java.util.*
import kotlin.NoSuchElementException

class CalcVisitor : Visitor {

    val acc: Deque<Int> = ArrayDeque()

    override fun visit(token: NumberToken) {
        acc.push(token.value)
    }

    override fun visit(token: Brace) =
        throw IllegalArgumentException("No parentheses expected in reverse polish notation")

    override fun visit(token: BinaryOperation) {
        try {
            val arg1 = acc.pop()
            val arg2 = acc.pop()
            acc.push(token.op(arg2, arg1))
        } catch (e: NoSuchElementException) {
            throw IllegalArgumentException("Invalid reverse polish notation: operation is present but there are not enough arguments given")
        }
    }

    fun visit(tokens: List<Token>): Int {
        for (elem in tokens) {
            elem.accept(this)
        }
        assert(acc.size == 1) { "Expression is incomplete and cannot be evaluated" }
        return acc.pop()
    }
}