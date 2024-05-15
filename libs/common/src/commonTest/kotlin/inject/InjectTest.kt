package inject

import org.szkug.keeting.common.Env
import org.szkug.keeting.common.PlatformContext
import org.szkug.keeting.common.inject.InjectUtil
import kotlin.test.BeforeTest
import kotlin.test.Test


class InjectTest {

    private val env by InjectUtil.lazy<Env>()

    @BeforeTest
    fun init() {
        InjectUtil.init {
            single { Env(
                true,
                PlatformContext(null)
            ) }

        }
    }

    @Test
    fun debugEnv() {
        println(env)
    }
}