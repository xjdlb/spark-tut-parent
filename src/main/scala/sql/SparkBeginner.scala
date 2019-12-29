package sql

import lombok.extern.slf4j.Slf4j
import org.apache.spark.sql._

@Slf4j
object SparkBeginner {

  def main(args: Array[String]): Unit = {
    val path1: String = "/Users/leebin/proj/day_20190323/demo-spring-parent/spark/src/main/scala/com/hest/compute/sql/data/train.csv"
    val path2: String = ""
    // 获取spark连接
    val sc: SparkSession = SparkSession.builder().appName("SparkSqlTest").master("local[*]").getOrCreate()
    val df: DataFrame = sc.read.format("csv").option("header", value = true).csv(path1)

    df.printSchema()
    df.show()

    //groupByKey操作
    import sc.implicits._
    val kv: KeyValueGroupedDataset[String, Row] = df.groupByKey(e => e.get(2).toString)
    //
    //val k: Dataset[String] = kv.keys
    //val kvg: Dataset[(String, List[String])] = kv.mapGroups {
    //  case (k, iter) => (k, iter.map(x => x.getAs[String]("Pclass")).toList)
    //}
    //kvg.foreach(e => println(e.toString()))

    //groupByKey取行操作
    import org.apache.spark.sql.catalyst.encoders.RowEncoder
    //import org.apache.spark.sql.catalyst.encoders.encoderFor
    Encoders.tuple(
      Encoders.STRING,
      RowEncoder(df.schema)
    )

    println("=============下面是sql的用法")
    df.createOrReplaceTempView("titanic")
    val result: DataFrame = sc.sql("select * from titanic")
    //result.printSchema()
    //自定义编码器
    import org.apache.spark.sql.catalyst.encoders.RowEncoder
    val defEncoder: Encoder[(String, Row)] = Encoders.tuple(
      Encoders.STRING,
      RowEncoder(df.schema)
    )
    //使用编码器
    val ds: Dataset[(String, Row)] = df.map(e => {
      val key: String = e.getAs[String]("Pclass")
      Tuple2(key, e)
    })(defEncoder)
    ds.foreach(e => println(e._2.getAs[String](3)))


    //saprk sql操作
    //df.createOrReplaceTempView("train")
    //val frame: DataFrame = sc.sql("select Name from train")
    //val frame: DataFrame = sc.sql("select * from train")
    //frame.show()

  }
}
