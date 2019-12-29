package core

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * @Author: leebin
  * @Date: 2019-11-02 22:04
  * @Description:
  */
object ReadFrameScalaEdition {
  def main(args: Array[String]): Unit = {
    //conf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test-spark-scala")
    //create session
    val sparkSession: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    sparkSession.stop()
  }
}
