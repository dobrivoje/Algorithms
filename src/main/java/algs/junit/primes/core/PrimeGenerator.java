package algs.junit.primes.core;

/**
 *
 * @author root
 */
public class PrimeGenerator implements IPrimeGen {

    @Override
    public boolean isPrime(Long number) {
        for (long delilac = 2; delilac < number / 2; delilac ++) {
            if (number % delilac == 0) {
                return false;
            }
        }

        return true;
    }

}
