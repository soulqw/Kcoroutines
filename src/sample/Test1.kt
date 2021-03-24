package sample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Test1 {


    @JvmStatic
    fun test() {
        GlobalScope.launch {
            delay(1000)
            println("test2 ${Thread.currentThread().name}")
        }
        println("test1 ${Thread.currentThread().name}")
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }

    @JvmStatic
    fun test2() = runBlocking {
        val job = GlobalScope.launch {
            delay(1000)
            println("test2 ${Thread.currentThread().name}")
        }
        println("test1 ${Thread.currentThread().name}")
        job.join()
    }

    @JvmStatic
    fun test3() = runBlocking { // this: CoroutineScope
        launch { // 在 runBlocking 作用域中启动一个新协程
            delay(1000L)
            println("test2 ${Thread.currentThread().name}")
        }
        println("test1 ${Thread.currentThread().name}")
    }
}