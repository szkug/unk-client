package org.szkug.unk.common.inject

import kotlin.reflect.KClass


interface Injector {
    fun init(block: InjectRegister.() -> Unit)
    fun <T : Any> get(name: String? = null, type: KClass<T>): T
    fun <T : Any> lazy(name: String? = null, type: KClass<T>): Lazy<T>
}


abstract class InjectRegister {
    /**
     * Register a singleton,
     * each request will get the same instance.
     *
     * @param name request instance name,
     */
    abstract fun <T : Any> single(name: String? = null, type: KClass<T>, createdAtStart: Boolean = false, factory: () -> T)
    inline fun <reified T : Any> single(
        name: String? = null,
        createdAtStart: Boolean = false,
        noinline factory: () -> T
    ) = single(name, T::class, createdAtStart, factory)

    /**
     * Register a request factory,
     * that will provide a new instance each time request
     */
    abstract fun <T : Any> factory(name: String? = null, type: KClass<T>, factory: () -> T)
    inline fun <reified T : Any> factory(
        name: String? = null,
        noinline factory: () -> T
    ) = factory(name, T::class, factory)

}


// current inject implement
private val InjectorImpl = InternalInjector()

object InjectUtil : Injector by InjectorImpl {
    inline fun <reified T : Any> get(name: String? = null): T = get(name, T::class)
    inline fun <reified T : Any> lazy(name: String? = null): Lazy<T> = lazy(name, T::class)
}