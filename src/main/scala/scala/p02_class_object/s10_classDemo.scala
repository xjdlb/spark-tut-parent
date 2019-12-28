package scala.p02_class_object

//记住：main方法要写在object里面
object s10_classDemo {
    def main(args: Array[String]): Unit = {
        val human = new Human
        println(human.name)
        println(human.climb)
    }
}

/**
  * 特质：接口，可以声明有实现的方法
  */
trait Flyable {
    //声明一个字段
    val distance: Int = 1000

    //声明一个没有实现的方法
    def finght: String

    //声明一个实现的方法
    def fly: Unit = {
        println("I can fly")
    }
}

/**
  * 抽象类：可以声明有实现的方法
  */
abstract class Animal {
    //声明一个没有赋值的字段
    val name: String

    //声明一个没有实现的方法
    def run(): String

    //声明一个有实现
    def climb: String = {
        "I can climb"
    }
}

/**
  * 继承：extends
  * 实现：with
  */
class Human extends Animal with Flyable {
    override val name: String = "zhangsan"

    //重写了抽象类中没有实现的方法
    override def run(): String = "I can run"

    //重新了抽象类中已经已经实现了方法
    override def climb: String = super.climb

    //上文中有初始值就可以不用重写了
    //override val distance: Int = 1000
    //重写特质中没有实现的方法

    //这里可以不用override关键字
    override def finght: String = "with gun"

    //重新特质中已经实现了的方法
    override def fly: Unit = println("override fly")
}
