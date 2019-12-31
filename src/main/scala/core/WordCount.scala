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
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    var sc: SparkContext = new SparkContext(conf)
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

    //fixme: 读数据时需要考虑分区的大小...

    println(result)
  }
}
