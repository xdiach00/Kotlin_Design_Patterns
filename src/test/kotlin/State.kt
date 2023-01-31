import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val username: String) : AuthorizationState()

class AuthorizationPresenter() {
    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = state is Authorized

    val username: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.username
                else -> "Unknown"
            }
        }

    fun loginUser(username: String) {
        state = Authorized(username)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "User $username is ${if (isAuthorized) "authorized" else "unauthorized"}"
}

class StateTest {
    @Test
    fun testState() {
        val authorizationPresenter = AuthorizationPresenter()

        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isTrue
        Assertions.assertThat(authorizationPresenter.username).isEqualTo("admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isFalse
        Assertions.assertThat(authorizationPresenter.username).isEqualTo("Unknown")
    }
}
