package org.szkug.unk.common.inject

import kotlin.reflect.KClass


private data class IndexTag(
    val type: KClass<*>,
    val name: String?
) {
    private fun className() = type.qualifiedName ?: "KClass@${type.hashCode()}"

    fun toIndex(): String = "${className()}:$name"
}

private sealed interface Instance<T> {
    fun get(): T
}

private class SingleInstance<T>(val factory: () -> T) : Instance<T> {
    private val _value by lazy { factory() }
    override fun get(): T = _value
    fun create() = get()
}

private class FactoryInstance<T>(val factory: () -> T) : Instance<T> {
    override fun get(): T = factory()
}

private class InstanceIndexedMap {
    private val _instances = LinkedHashMap<String, Instance<*>>()

    fun put(tag: IndexTag, instance: Instance<*>) {
        val index = tag.toIndex()
        val before = _instances.getOrPut(index) { instance }
        // not allow to overwrite instance
        if (before != instance)
            throw IllegalStateException("An instance $before corresponding to the $tag already exists.")
    }

    fun <T : Any> get(tag: IndexTag): T {
        val index = tag.toIndex()
        val instance = _instances[index]
            ?: throw IllegalStateException("No instance corresponding to the $tag is provided.")
        @Suppress("UNCHECKED_CAST")
        return instance.get() as T
    }

}

class InternalInjector : Injector {

    private val map = InstanceIndexedMap()

    override fun init(block: InjectRegister.() -> Unit) {
        val register = InternalRegister(map)
        register.block()
    }

    override fun <T : Any> get(name: String?, type: KClass<T>): T {
        return map.get(IndexTag(type, name))
    }

    override fun <T : Any> lazy(name: String?, type: KClass<T>): Lazy<T> = lazy { get(name, type) }
}

private class InternalRegister(
    private val map: InstanceIndexedMap
) : InjectRegister() {

    override fun <T : Any> single(name: String?, type: KClass<T>, createdAtStart: Boolean, factory: () -> T) {
        val tag = IndexTag(type, name)
        val instance = SingleInstance(factory)
        if (createdAtStart) instance.create()
        map.put(tag, instance)
    }

    override fun <T : Any> factory(name: String?, type: KClass<T>, factory: () -> T) {
        val tag = IndexTag(type, name)
        val instance = FactoryInstance(factory)
        map.put(tag, instance)
    }

}