package algs.examples.threads.prodconsumer.prob;

/**
 *
 * @author root
 */
public class Res {

    private Integer broj;

    public Res(Integer broj) {
        this.broj = broj;
    }

    public synchronized Integer getBroj() {
        return broj;
    }

    public synchronized Integer incRes() {
        return ++broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    @Override
    public synchronized String toString() {
        try {
            return broj.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
