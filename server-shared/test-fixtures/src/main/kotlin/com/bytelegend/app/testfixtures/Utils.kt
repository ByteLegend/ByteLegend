package com.bytelegend.app.testfixtures

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun runBlockingUnit(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> Unit) = runBlocking(context, block)
