package scala.p04_Higher_Order_Functions

object s13_Function_As_value {
    def main(args: Array[String]): Unit = {
        println("1-------------------------------------------------")
        //声明：方式1
        val func: Int => Int = x => x * x
        println(func(4))
        //使用
        val arr = Array(1, 2, 3, 4, 5, 6)
        val arr_square = arr.map(x => func(x))
        println(arr_square.toBuffer)
        println("2-------------------------------------------------")

        //方法转化成函数
        def m1(x: Int) = x * x

        val arr1 = arr.map(x => m1(x))
        println(arr1.toBuffer)

        val arr2 = arr.map(func) //简单使用
        println(arr2.toBuffer)
        println("3-------------------------------------------------")

        //Curry柯里化：多参数声明，自定义多参数的方法，声明方式1
        def currying(x: Int)(y: Int) = x * y

        println(currying(3)(4))

        //单一参数调用：研究单一参数的影响
        val curry = currying(3) _
        println(curry(6))
        println("4-------------------------------------------------")

        //定义方法
        def m2(x: Int)(y: Int) = x * y

        //柯里化的方法
        def m3(x: Int)(implicit y: Int = 5) = x * y

        println(m3(3)(6))
        println(m3(3))
        implicit val xxxx = 100 //名字不关系，柯里化会自动在上下文中找到变量
        println(m3(4))
        println("5-------------------------------------------------")

        //实现value相加
        val arr3 = Array(("scala", 1), ("scala", 2), ("scala", 3))
        println(arr3.foldLeft(0)(_ + _._2))

        //柯里化新的定义方法，声明方式2
        def curry1(x: Int) = (y: Int) => x * y

        println(curry1(3)(7))

        println("6-------------------------------------------------")
    }
}
