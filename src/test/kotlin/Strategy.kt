import org.junit.jupiter.api.Test
import java.util.*

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategy(message))
    }
}

val lowerCaseFormatter = { message: String -> message.lowercase(Locale.getDefault()) }
val upperCaseFormatter = { message: String -> message.uppercase(Locale.getDefault()) }

class StrategyTest {
    @Test
    fun testStrategy() {
        val inputString = "LOREM ipsum DOLOR sit amet"

        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.printString(inputString)

        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.printString(inputString)
    }
}
