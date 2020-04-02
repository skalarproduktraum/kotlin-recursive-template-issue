import net.imglib2.type.numeric.NumericType
import net.imglib2.type.numeric.integer.UnsignedByteType
import net.imglib2.type.numeric.integer.UnsignedShortType
import net.imglib2.type.numeric.real.DoubleType
import net.imglib2.type.numeric.real.FloatType
import kotlin.random.Random

class RecursiveTest {

    companion object {
        fun <T: NumericType<T>> doSomethingWithImglib(type: T) {
            System.err.println("Hi, my type is ${type.javaClass.simpleName}")
        }

        fun doSomethingElse() {
            val whatTypeAmI = when(Random.nextInt(0, 4)) {
                // editing these lines brings the kotlin.types module to high CPU usage, and IntelliJ to a grinding halt
                0 -> UnsignedShortType()
                1 -> UnsignedByteType()
                2 -> FloatType()
                3 -> DoubleType()
                // Uncommenting the following line and using the action menu for import results in long wait time
                //  4 -> IntType()
                else -> throw Exception("")
            }

            // does not work, results in absurdly long compiler errors
            // Error:(28, 35) Kotlin: Type mismatch: inferred type is {AbstractRealType<CapturedType(out ({AbstractRealType<out ({AbstractRealType<out ({AbstractRealType<out ({AbstractRealType<out Any?> & NativeType<out Any?>}..{AbstractRealType<out Any?>? & NativeType<out Any?>?})> & NativeType<out ...
            // (continues for about 25000 chars)
            doSomethingWithImglib(whatTypeAmI)
            // does work
            doSomethingWithImglib(UnsignedByteType())
        }
    }
}