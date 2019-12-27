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
    println(result)
  }
}
