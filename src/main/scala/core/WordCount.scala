package core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: leebin
 * @Date: 2019/12/27 11:55 下午
 * @Description:
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    /*
    fixme: SparkConf源码解析
    SparkConf类很简单
    private val settings = new ConcurrentHashMap[String, String]()

    fixme: SparkContext源码解析

     */
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(conf)

    //源码里面有这种声明方式
    val context = new SparkContext("", "", "", Nil, Map("" -> ""))

    //查看有哪些输入方式或者RDD的构造方式？
    val lines: RDD[String] = sc.textFile("in")
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val word2one: RDD[(String, Int)] = words.map((_, 1))
    val word2sum: RDD[(String, Int)] = word2one.reduceByKey(_ + _)
    val result: Array[(String, Int)] = word2sum.collect()

    //RDD的创建 defaultParallelism
    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3))
    //自定义分区
    val rdd2: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 2)
    //底层实现一样
    val rdd3: RDD[Int] = sc.parallelize(List(1, 2, 3))

    //从当前项目文件中创建 defaultMinPartitions
    val rdd4: RDD[String] = sc.textFile("input")
    //删除到文件，可以看到默认分区为8个
    rdd1.saveAsTextFile("output")

    /*
    fixme: map源码解析
    移动计算不移动数据，将map转换成MapPartitions
    new MapPartitionsRDD[U, T](this, (context, pid, iter) => iter.map(cleanF))
    */
    val rdd5: RDD[Int] = sc.makeRDD(1 to 10).map(x => x * 2)
    val rdd6: RDD[Int] = sc.makeRDD(1 to 10).map(_ * 2)
    println(result)
  }
}
