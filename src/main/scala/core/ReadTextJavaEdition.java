package core;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;

import java.util.List;

/**
 * @Author: leebin
 * @Date: 2019-11-02 21:51
 * @Description:
 */
@Slf4j
public class ReadTextJavaEdition {

    public static void main(String[] args) {
        //test spark using java.
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("test-spark");
        SparkSession session = SparkSession.builder().config(conf).getOrCreate();
        SparkContext sc = session.sparkContext();
        sc.setLogLevel("warn");
        RDD<String> rdd = sc.textFile("/Users/leebin/Downloads/gender_submission.csv", 1);
        List<String> lines = rdd.toJavaRDD().collect();
        for (String line : lines) {
            System.out.println(line);
        }
        session.stop();
    }
}
