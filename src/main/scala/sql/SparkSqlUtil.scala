package sql

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlUtil {
  /**
    * 获取sparkSql Session
    *
    * @return
    */
  def getSqlContext: SparkSession = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("SparkSqlTest")
      .master("local[*]")
      .getOrCreate()
    spark
  }


  def changeCsvColumnName(df: DataFrame): DataFrame = {
    df.withColumnRenamed("_c0", "name")
      .withColumnRenamed("_c1", "subj1")
      .withColumnRenamed("_c2", "subj2")
      .withColumnRenamed("_c3", "dt")
  }

  def readCsvBySparkSql(spark: SparkSession, path: String): DataFrame = {
    val df: DataFrame = spark
      .read
      .format("csv")
      .option("header", value = false)
      .csv(path)
    df
  }

  def writeCsvBySparkSql(df: DataFrame, path: String): Unit = {
    df.repartition(1)
      .write
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ",")
      .save(path)
  }
}
