package scala.p02_class_object

/**
  * 声明类是不需要加public关键字的，默认就是public
  * 一个文件可以声明多个类
  * 类文件名字和类名字可以不一样
  */
class PersonClass {
    //属性(不需要有get(var,val)和set(var)方法)
    val id: String = "100" //只读，没有set方法
    var name: String = _ //占位符，可变的需要用war修饰，相当于基友get和set方法
    //用private修饰的属性，该属性属于私有变量，只有对象和本类才能访问，伴生对象能访问，其他类不能访问
    private var age: Int = _
    //用private[this]修饰的属性，该属性属于对象的私有变量，只有对象和本类才能访问，伴生对象不能访问，其他类不能访问
    private[this] val gender = "male"
}

/**
  * 是PersonClass类的伴生对象（类似于老婆）
  */
object PersonClass {
    def main(args: Array[String]): Unit = {
        //-------------------------------------------
        println("在伴生对象类中创建对象")
        val p1 = new PersonClass
        println(p1.id)
        println(p1.name)
        p1.name = "Peter"
        println(p1.name)
        println(p1.age)
        p1.age = 100
        println(p1.age)
        //这里是访问不到gender
    }
}

/**
  * 静态类，可以不和类名相同
  */
object Test {
    //-------------------------------------------
    println("在其他类创建对象")
    val p1 = new PersonClass
    println(p1.id)
    println(p1.name)
}
