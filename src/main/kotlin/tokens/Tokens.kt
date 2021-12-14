import tokens.Token
import visitor.Visitor

interface Brace : Token {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class LeftBrace : Brace

class RightBrace : Brace

class NumberToken(val value: Int) : Token {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

interface BinaryOperation : Token {
    val priority: Int

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    fun op(arg1: Int, arg2: Int): Int
}

class Plus : BinaryOperation {
    override val priority: Int = 1

    override fun op(arg1: Int, arg2: Int): Int = arg1 + arg2
}

class Minus : BinaryOperation {
    override val priority: Int = 1

    override fun op(arg1: Int, arg2: Int): Int = arg1 - arg2
}

class Mult : BinaryOperation {
    override val priority: Int = 2

    override fun op(arg1: Int, arg2: Int): Int = arg1 * arg2
}

class Div : BinaryOperation {
    override val priority: Int = 2

    override fun op(arg1: Int, arg2: Int): Int = arg1 / arg2
}