
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import org.apache.pig.data.Tuple
import org.apache.pig.data.TupleFactory

class PigHbaseLinggleSpec extends FlatSpec with ShouldMatchers {
  import udf._
    val vn = new ValidateNgram
    val tuple = TupleFactory.getInstance.newTuple(1)
  "ValidateNgram" should "correctly validate ngrams" in {
    tuple.set(0, "hello world")
    vn.exec(tuple) should be === true
    tuple.set(0, "- hey")
    vn.exec(tuple) should be === true
    tuple.set(0, "hey yo .")
    vn.exec(tuple) should be === true
    tuple.set(0, "$ hello world")
    vn.exec(tuple) should be === false
  }


}
