package tokens

import visitor.Visitor

interface Token {
    fun accept(visitor: Visitor)
}