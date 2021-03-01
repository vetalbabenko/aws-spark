package com;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Properties;


public class SparkMain {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Java Spark SQL basic example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();

        Dataset<Row> test = spark.createDataFrame(Arrays.asList(
                new TimestampS(Timestamp.from(Instant.now())),
                new TimestampS(Timestamp.from(Instant.now())),
                new TimestampS(Timestamp.from(Instant.now())),
                new TimestampS(Timestamp.from(Instant.now()))
        ), TimestampS.class);
        Properties props = load();


        test
                .write()
                .mode(SaveMode.Append)
                .jdbc(props.getProperty("url"), "timestamp", props);
    }


    public static Properties load() {
        try (InputStream input = SparkMain.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            System.out.println(prop.getProperty("url"));
            System.out.println(prop.getProperty("user"));
            System.out.println(prop.getProperty("password"));
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getCause());
        }
    }
}