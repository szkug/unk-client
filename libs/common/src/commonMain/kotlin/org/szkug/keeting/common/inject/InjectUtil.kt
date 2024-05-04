package org.szkug.keeting.common.inject


interface Injector {
    fun init(block: ModuleRegister.() -> Unit)
}

interface ModuleRegister

inline fun <reified T : Any> ModuleRegister.named(
    name: String,
    createdAtStart: Boolean = false,
    crossinline factory: () -> T
) = (this as KoinModuleRegister).named(name, createdAtStart, factory)

inline fun <reified T : Any> ModuleRegister.single(
    createdAtStart: Boolean = false,
    crossinline factory: () -> T
) = (this as KoinModuleRegister).single(createdAtStart, factory)


// current inject implement
@PublishedApi
internal val InjectorImpl = KoinInjector

object InjectUtil : Injector by InjectorImpl

inline fun <reified T : Any> InjectUtil.get(name: String? = null): T = InjectorImpl.koinGet<T>(name)
inline fun <reified T : Any> InjectUtil.lazy(name: String? = null): Lazy<T> = InjectorImpl.koinLazy<T>(name)

