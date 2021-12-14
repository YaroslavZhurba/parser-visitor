import tokenizer.Tokenizer
import visitor.CalcVisitor
import visitor.ParserVisitor
import visitor.PrintVisitor

fun main() {
    val s = readLine()
    val tokenizer = Tokenizer((s ?: "").byteInputStream())
    val polish = ParserVisitor().visit(tokenizer.run())
    PrintVisitor(System.out).visit(polish)
    println(CalcVisitor().visit(polish))
}