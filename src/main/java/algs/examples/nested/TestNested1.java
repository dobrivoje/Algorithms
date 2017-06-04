package algs.examples.nested;

public class TestNested1 implements IShowable.IMessage {

    public static void main(String... args) {
        System.err.println("test");
        new TestNested1().msg();
    }

    @Override
    public void msg() {
        System.err.println("IShowable.IMessage::msg");
    }

}
