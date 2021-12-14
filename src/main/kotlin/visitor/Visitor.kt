package visitor

import Brace
import NumberToken
import BinaryOperation

interface Visitor {
    fun visit(token: NumberToken)

    fun visit(token: Brace)

    fun visit(token: BinaryOperation)
}