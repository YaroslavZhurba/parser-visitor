package visitor

import Brace
import LeftBrace
import NumberToken
import BinaryOperation
import RightBrace
import tokens.Token
import java.util.*

class ParserVisitor : Visitor {

    val operations: Deque<Token> = ArrayDeque()
    val polishExpr: MutableList<Token> = mutableListOf()

    override fun visit(token: NumberToken) {
        polishExpr.add(token)
    }

    override fun visit(token: Brace) {
        when (token) {
            is LeftBrace -> operations.push(token)
            is RightBrace -> {
                while (operations.isNotEmpty() and (operations.peek() !is LeftBrace)) {
                    polishExpr.add(operations.pop())
                }
                if (operations.isEmpty())
                    throw IllegalStateException("Unbalanced parentheses found")
                operations.pop()
            }
        }
    }

    override fun visit(token: BinaryOperation) {
        while (operations.isNotEmpty()) {
            val op = operations.peek()
            if (op is BinaryOperation) {
                if (op.priority >= token.priority) {
                    polishExpr.add(operations.pop())
                    continue
                }
            }
            break
        }
        operations.push(token)
    }

    fun visit(tokens: List<Token>): List<Token> {
        for (elem in tokens)
            elem.accept(this)
        while (operations.isNotEmpty()) {
            val op = operations.pop()
            if (op !is BinaryOperation)
                throw IllegalStateException("Unbalanced operations/operands")
            polishExpr.add(op)
        }
        if (polishExpr.count { it is NumberToken } - polishExpr.count { it is BinaryOperation } != 1)
            throw IllegalStateException("Unbalanced operations/operands")
        return polishExpr
    }

}