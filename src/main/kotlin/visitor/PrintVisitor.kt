package visitor

import Brace
import Div
import LeftBrace
import Minus
import Mult
import NumberToken
import BinaryOperation
import Plus
import RightBrace
import tokens.Token
import java.io.OutputStream

class PrintVisitor(val os: OutputStream) : Visitor {

    override fun visit(token: NumberToken) {
        os.write("${token.value} ".toByteArray())
    }

    override fun visit(token: Brace) {
        os.write((when (token) {
            is LeftBrace -> "("
            is RightBrace -> ")"
            else -> ""
        } + " ").toByteArray())
    }

    override fun visit(token: BinaryOperation) {
        os.write((when (token) {
            is Plus -> "+"
            is Minus -> "-"
            is Mult -> "*"
            is Div -> "/"
            else -> ""
        } + " ").toByteArray())
    }

    fun visit(tokens: List<Token>) {
        for (elem in tokens) {
            elem.accept(this)
        }
        os.write("\n".toByteArray())
    }
}