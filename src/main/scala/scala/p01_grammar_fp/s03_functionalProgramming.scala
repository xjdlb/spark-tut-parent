package scala.p01_grammar_fp

class s03_functionalProgramming {

}

object s03_functionalProgramming {
    def main(args: Array[String]): Unit = {
        println("创建一个List")
        val list0 = List(2, 5, 9, 6, 7, 1, 8, 3, 4, 0)
        println(list0)
        //-------------------------------------------------------
        println("将每个元素乘以2生成新的List")
        val list1 = list0.map(_ * 2) //查看源码可以直接在pom中加入依赖
        println(list1)
        //-------------------------------------------------------
        println("将偶数取出来新建一个集合")
        val list2 = list0.filter(_ % 2 == 0)
        println(list2)
        //-------------------------------------------------------
        println("将list0排序生成新的集合")
        val list3 = list0.sorted
        println(list3)
        //-------------------------------------------------------
        println("翻转排序")
        val list4 = list3.reverse //调用方法不用小括号：这是没有参数，精简了
        println(list4)
        //-------------------------------------------------------
        println("将list中的元素4个一组生成Interator[List]")
        val it = list0.grouped(4)
        //        println(it)
        //        println(it.toBuffer)
        val list5 = it.toList
        println(list5)
        //-------------------------------------------------------
        println("把多个List压扁转化成一个List")
        val list6 = list5.flatten
        println(list6)
        //-------------------------------------------------------
        println("先按空格切分，再压平")
        val lines = List("hello java", "hello python hello php")
        println("实现方式1") //实现方式1
        val words = lines.map(_.split(" "))
        val flattenedWords = words.flatten
        println(words.toBuffer)
        println(flattenedWords) //List(hello, java, hello, python, hello, php)
        println("实现方式2") //实现方式2
        val res = lines.flatMap(_.split(" "))
        println(res) //List(hello, java, hello, python, hello, php)
        //-------------------------------------------------------
        println("实现求和")
        val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val sum1 = arr.sum //串行计算
        println(sum1)
        println("并行")
        val sum2 = arr.par.sum //并行计算Parallel Computing
        println(sum2)
        //-------------------------------------------------------
        println("按照特定的顺序来聚合")
        val sum3 = arr.reduce(_ + _) //以累加的形式进行迭代
        println(sum3)
        //如何理解顺序呢？1+2=3 3+3=6 ...
        //-------------------------------------------------------
        val sum4 = arr.par.reduce(_ - _) //多个线程执行叠减的过程，一旦并行就不能保证其指定的顺序
        println(sum4)
        //-------------------------------------------------------
        println("折叠")
        // val foldSum = arr.fold(10)(_ + _)
        //有初始值，无特定顺序，结果是动态的
        val foldSum = arr.par.fold(10)(_ + _)
        println(foldSum)
        //有初始值，有特定顺序,结果不是动态的
        val foldSum1 = arr.par.foldLeft(10)(_ + _)
        println(foldSum1)
        //-------------------------------------------------------
        println("聚合操作")
        val list7 = List(List(1, 2, 3), List(4, 5), List(6, 7), List(8))
        val sum5 = list7.flatten.reduceLeft(_ + _)
        println(sum5)
        val sum6 = list7.aggregate(0)(_ + _.sum, _ + _) //每个单元两两求和，之后进行和聚合
        println(sum6)
        //-------------------------------------------------------
        println("交并差")
        val list8 = List(1, 2, 3, 4)
        val list9 = List(4, 5, 6, 7)
        //交
        val list10 = list8 intersect list9
        println(list10)
        //并
        val list11 = list8 union list9
        println(list11)
        //差
        val list12 = list8 diff list9
        println(list12)
    }
}
