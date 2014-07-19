package udf

import org.apache.hadoop.fs.Path

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper


import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem


class UnigramMap(toIdxMap:Map[String, Int], fromIdxMap:Map[Int, String]) {
  def apply(unigram:String) = toIdxMap(unigram)
  def apply(count:Int) = fromIdxMap(count)
  def contains(unigram:String) = toIdxMap contains unigram
  def contains(count:Int) = fromIdxMap contains count
}

object UnigramMap {
  def apply(jsonPath: String) = {
    val conf = new Configuration
    conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
    conf.set("fs.defaultFS", "hdfs://lost.nlpweb.org/")
    val fs = FileSystem.get(conf)
    // val path = new Path(jsonPath)

    // conf.addResource(new Path("/etc/hbase/conf.dist/hbase-site.xml"))

    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val _to:Map[String, Int] =  mapper.readValue[Map[String,Int]](    fs.open(new Path(jsonPath)))
    val _from = _to map {_.swap}
    new UnigramMap(_to, _from)
  }
}


class POS(posMap: Map[String, Vector[String]]) {
  def apply(unigram: String) = posMap(unigram)
  def apply(unigram: String, pos: String) =
    if (posMap contains unigram)
      posMap(unigram) contains pos
    else false
}

object POS {
  def apply(jsonPath: String) = {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val posMap:Map[String, Vector[String]] =  mapper.readValue[Map[String,Vector[String]]](new java.io.File (jsonPath))
    new POS(posMap)
  }
}

