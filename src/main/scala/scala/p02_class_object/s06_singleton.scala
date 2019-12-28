package scala.p02_class_object

import scala.collection.mutable.ArrayBuffer

/**
  * 在scala中没有静态方法和静态字段
  * 可是使用object关键字+类名实现同样的功能
  * 1、工具类，存放常量和工具方法
  * 2、单例模式singleton
  */
object s06_singleton {
    def main(args: Array[String]): Unit = {
        //下面的调用直接用方法名即可
        val factory = SessionFactory
        println(factory.getSession)
        println(factory.getSession.size)
        println("-------------------------------------")
        println(factory.removeSession)
    }
}

object SessionFactory {
    /**
      * 下面的代码块相当于Java中的静态代码块
      */
    println("SessionFactory is running")
    private val session = new ArrayBuffer[Session]()
    //填充数组
    var i = 5
    while (i > 0) {
        session += new Session
        i -= 1
    }

    //最后一个为返回值
    def getSession = session

    //返回值为空
    def removeSession = {
        val s = session(0)
        session.remove(0)
        println("session is removed...")
    }
}

class Session {

}
