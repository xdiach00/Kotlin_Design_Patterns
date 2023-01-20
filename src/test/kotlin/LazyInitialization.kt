import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this: $message")
    }
}

class Window {
    val alertBox by lazy { AlertBox() }

    fun showMessage(message: String) {
        alertBox.message = message
        alertBox.show()
    }
}

class Window2 {
    lateinit var alertBox: AlertBox

    fun showMessage(message: String) {
        alertBox = AlertBox() // when using lateinit, we must initialize the property before using it
        alertBox.message = message
        alertBox.show()
    }
}

class WindowTest {
    @Test
    fun windowTest() {
        val window = Window()
        window.showMessage("Hello window!")
        Assertions.assertThat(window.alertBox).isNotNull

        val window2 = Window2()
        // println(window2.alertBox) - returns an error because the initialization is in the showMessage method
        window2.showMessage("Hello window2!")
        Assertions.assertThat(window2.alertBox).isNotNull
    }
}
