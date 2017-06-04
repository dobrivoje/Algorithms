package junit.tutorialspoint.com;

/**
 *
 * @author root
 */
public class PrimeNumberChecker {

    public boolean validate(Integer inputNumber) {
        for (int i = 2; i < inputNumber / 2; i++) {
            if (inputNumber % i == 0) {
                return false;
            }
        }

        return true;
    }

}
