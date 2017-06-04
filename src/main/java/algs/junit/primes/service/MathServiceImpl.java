package algs.junit.primes.service;

import algs.junit.primes.core.IPrimeGen;

/**
 *
 * @author root
 */
public class MathServiceImpl implements IMathService {

    private final IPrimeGen ig;

    public MathServiceImpl(IPrimeGen ig) {
        this.ig = ig;
    }

    @Override
    public boolean isPrime(long number) {
        for (int delilac = 2; delilac < number / 2; delilac++) {
            if (number % delilac == 0) {
                return false;
            }
        }
        
        return true;
    }

}
