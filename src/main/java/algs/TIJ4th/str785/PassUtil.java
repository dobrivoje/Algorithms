package algs.TIJ4th.str785;

import algs.TIJ4th.str785.annotations.UseCase;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author root
 */
public class PassUtil implements Serializable {

    @UseCase(id = 47, description = "at least one numeric")
    public boolean validatePass(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryptPass(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, description = "New passwords can’t equal previously used ones")
    public boolean checkForNewPass(List<String> prevPasss, String password) {
        return !prevPasss.contains(password);
    }

    public boolean passExistInHistory(List<String> prevPasss, String password) {
        return prevPasss.contains(password);
    }

}
