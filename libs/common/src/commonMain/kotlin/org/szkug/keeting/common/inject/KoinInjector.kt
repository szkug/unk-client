package org.szkug.keeting.common.inject

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

@PublishedApi
internal object KoinInjector : Injector, KoinComponent {

    override fun init(block: ModuleRegister.() -> Unit) {
        startKoin {
            val register = KoinModuleRegister()
            register.block()
            modules(register.build())
        }
    }

    inline fun <reified T : Any> koinGet(name: String? = null): T = if (name == null) get<T>(qualifier<T>())
    else get(qualifier(name))

    inline fun <reified T : Any> koinLazy(name: String? = null): Lazy<T> = if (name == null) inject<T>(qualifier<T>())
    else inject(qualifier(name))
}

@PublishedApi
internal class KoinModuleRegister : ModuleRegister {

    @PublishedApi
    internal val registers = mutableListOf<Module.() -> Unit>()

    inline fun <reified T : Any> named(name: String, createdAtStart: Boolean = false, crossinline factory: () -> T) {
        registers.add { single(qualifier(name), createdAtStart) { factory() } }
    }

    inline fun <reified T : Any> single(createdAtStart: Boolean = false, crossinline factory: () -> T) {
        registers.add { single(qualifier<T>(), createdAtStart) { factory() } }
    }

    fun build(): Module = module {
        registers.forEach { it(this) }
    }

}