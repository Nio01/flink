import org.apache.flink.api.common.typeinfo.BasicTypeInfo
import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.source.FileProcessingMode

object source {
  def main(args: Array[String]): Unit = {
    val filePath = "./data/word.txt"
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.
    readFile(new TextInputFormat(new Path(filePath)),
      filePath,
      FileProcessingMode.PROCESS_ONCE,
      1,
      BasicTypeInfo.STRING_TYPE_INFO).print
    //调用了一下迭代器
    env.fromCollection(new CustomIterator(),BasicTypeInfo.INT_TYPE_INFO).print()
    env.execute
  }
}
