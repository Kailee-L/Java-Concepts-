import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Primes {
	public static final IntFunction<IntStream> RANGE        = r -> IntStream.range(2,r);
	public static final IntFunction<IntStream> DIVISORS     = d-> RANGE.apply(d).filter(n-> d %n == 0);
	public static final IntPredicate           IS_PRIME     = i-> DIVISORS.apply(i).count() ==0 && i >0;
	public static final Supplier<IntStream>    PRIME_STREAM = () -> RANGE.apply(Integer.MAX_VALUE).filter(IS_PRIME);
	}
}