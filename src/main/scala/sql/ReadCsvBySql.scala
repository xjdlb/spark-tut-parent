package sql

import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadCsvBySql {
  val pathIn: String = "/Users/leebin/proj/day_20190323/demo-spring-parent/spark/src/main/scala/com/hest/compute/sql/Stu.csv"
  val pathOut: String = "/Users/leebin/proj/day_20190323/demo-spring-parent/spark/src/main/scala/com/hest/compute/sql/Res"

  def doTask(spark: SparkSession): Unit = {
    val df: DataFrame = SparkSqlUtil
      .changeCsvColumnName(SparkSqlUtil.readCsvBySparkSql(spark, pathIn))
    //展示
    df.show()
    df.withColumn("sum", df("subj1") + df("subj2")).show()
    df.show()
    SparkSqlUtil.writeCsvBySparkSql(df, pathOut)
  }

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSqlUtil.getSqlContext
    doTask(spark)
  }
}
