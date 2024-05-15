package org.szkug.keeting.common.log

import org.szkug.keeting.common.PlatformContext


enum class LogLevel {

    VERBOSE, DEBUG, INFO, WARN, ERROR

}

interface ILogger {

    fun init(context: PlatformContext, level: LogLevel)

    // verbose
    fun v(tag: String, message: () -> String)

    // debug level
    fun d(tag: String, message: () -> String)

    // info level
    fun i(tag: String, message: () -> String)

    // warn level
    fun w(tag: String, message: () -> String)

    // error level
    fun e(tag: String, message: () -> String)

    // error level
    fun e(tag: String, throwable: Throwable, message: () -> String)

    // log error and crash!
    fun wtf(tag: String, throwable: Throwable)
}