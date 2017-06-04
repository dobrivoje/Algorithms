package algs.examples.threads.prodconsumer.prob;

/**
 *
 * @author root
 */
public class test {

    public static void main(String[] args) {
        Res res = new Res(100);
        
        Inicijator i = new Inicijator(res);
        Pratilac p = new Pratilac(res);

        System.err.println("p.resurs : " + p.getResurs().getBroj());

        res.incRes();

        System.err.println("p.resurs : " + p.getResurs().getBroj());
    }

}
