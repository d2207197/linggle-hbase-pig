import org.apache.pig.{EvalFunc, FilterFunc}
import org.apache.pig.data.{Tuple, DataBag, BagFactory, TupleFactory}
import scala.collection.mutable.ListBuffer
class ValidateNgram extends FilterFunc {
  // val unigramMap = UnigramMap("/user/joe/web1t_unigrams_300000up.json")
  def exec(input : Tuple)  = {
    if (input == null || input.size() == 0)
      null
    else {
      input.get(0) match {
        // case x: String => ValidateNgram.validateNgram(x, unigramMap)
        case x: String => ValidateNgram.validateNgram(x)
      }
    }
  }
}

object ValidateNgram
{
  def joinString(s: String, ss: String* ) = ss mkString s

  // implicit def validateNgram(input : String, unigramMap: UnigramMap) : Boolean = {

  def validateNgram(input : String ) : Boolean = {
    val ngram = input.split(" ")
    val WORDS_RE = raw"""'?[a-zA-Z]+(['.][a-zA-Z]+)*\.?$$"""
    val END_SYMBOL_RE = raw"[-;,:.?!]$$"
    val INIT_SYMBOL_RE = raw"[-;,:]$$"
    val SENTENCE_TAG_RE = raw"</?S>$$"
    val LAST_RE = joinString("|", WORDS_RE, END_SYMBOL_RE, SENTENCE_TAG_RE)
    val INIT_RE = joinString("|", WORDS_RE, INIT_SYMBOL_RE, SENTENCE_TAG_RE)

    // def initMatch(unigramMap: UnigramMap)(word: String ) =
    //   word.matches(INIT_RE) && (unigramMap contains word)

    // def lastMatch(unigramMap: UnigramMap)(word: String ) =
    //   word.matches(LAST_RE) && (unigramMap contains word)

    // (ngram.init forall initMatch(unigramMap)) && lastMatch(unigramMap)(ngram.last)
    true
  }
}

// class ToFramesSels extends EvalFunc[DataBag] {
//   val unigramMap = UnigramMap("/user/joe/web1t_unigrams_300000up.json")
//   def exec(input : Tuple) : DataBag = {
//     val ngram = input.get(0).asInstanceOf[String]
//     // val count = input.get(1).asInstanceOf[Int]

//     ToFramesSels.toFramesSels(ngram, unigramMap)

//   }
// }



// import scala.collection.JavaConverters._


// object ToFramesSels {

//   val sl = new scala.collection.mutable.ListBuffer[Int]
//   val jl : java.util.List[Int] = sl.asJava
//   val sl2 : scala.collection.mutable.Buffer[Int] = jl.asScala

//   val mTupleFactory = TupleFactory.getInstance
//   val mBagFactory = BagFactory.getInstance
//   def selectNgram(ngram: Vector[String], selector: Set[Int]): Vector[String] = {
//       ngram.zipWithIndex filter( selector contains _._2 ) map (_._1)
//   }

//   def toFramesSels(_ngram: String, unigramMap: UnigramMap ): DataBag =  {
//     val ngram = _ngram.split(" ").to[Vector]
//     val selectors = (List.range(0, ngram.length).toSet.subsets drop 1).to[List]
//     val frameSels: java.util.List[Tuple] = selectors.map{ sel =>
//       val frame = selectNgram(ngram, sel) mkString " "
//       mTupleFactory.newTuple(List(frame, sel.mkString).asJava)
//     }.asJava
//     // val frameSels_jl : java.util.List[Tuple]  = frameSels.asJava
//     mBagFactory.newDefaultBag(frameSels)
//   }

// }
