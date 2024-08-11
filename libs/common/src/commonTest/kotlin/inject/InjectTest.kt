package inject

import org.szkug.unk.common.Env
import org.szkug.unk.common.PlatformContext
import org.szkug.unk.common.inject.InjectUtil
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