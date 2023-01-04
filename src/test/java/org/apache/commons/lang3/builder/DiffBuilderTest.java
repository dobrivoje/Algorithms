package org.apache.commons.lang3.builder;

import algs.TIJ4th.BeanInfo.User;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class DiffBuilderTest {

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    static class TypeTestClass implements Diffable<TypeTestClass> {

        private ToStringStyle style             = ToStringStyle.MULTI_LINE_STYLE;
        private boolean       booleanField      = true;
        private boolean[]     booleanArrayField = {true};
        private byte          byteField         = (byte) 0xFF;
        private byte[]        byteArrayField    = {(byte) 0xFF};
        private char          charField         = 'a';
        private char[]        charArrayField    = {'a'};
        private double        doubleField       = 1.0;
        private double[]      doubleArrayField  = {1.0F, 45.9F, 22.82F};
        private float         floatField        = 1.0f;
        private float[]       floatArrayField   = {1.0f};
        private int           intField          = 1;
        private int[]         intArrayField     = {1};
        private long          longField         = 1L;
        private long[]        longArrayField    = {1L};
        private short         shortField        = 1;
        private short[]       shortArrayField   = {1};
        private Object        objectField       = null;
        private Object[]      objectArrayField  = {null};
        private List<User>    users             = Arrays.asList(new User(3L, "u-1"), new User(4L, "u-2"), new User(5L, "u-3"));

        @Override
        public DiffResult diff(final TypeTestClass other) {
            return new DiffBuilder(this, other, style)
                .append("boolean polje", booleanField, other.booleanField)
                .append("booleanArrayField", booleanArrayField, other.booleanArrayField)
                .append("byteField", byteField, other.byteField)
                .append("byteArrayField", byteArrayField, other.byteArrayField)
                .append("charField", charField, other.charField)
                .append("charArrayField", charArrayField, other.charArrayField)
                .append("double", doubleField, other.doubleField)
                .append("doubleArray", doubleArrayField, other.doubleArrayField)
                .append("float", floatField, other.floatField)
                .append("floatArray", floatArrayField, other.floatArrayField)
                .append("int", intField, other.intField)
                .append("intArray", intArrayField, other.intArrayField)
                .append("long", longField, other.longField)
                .append("longArray", longArrayField, other.longArrayField)
                .append("short", shortField, other.shortField)
                .append("shortArray", shortArrayField, other.shortArrayField)
                .append("objectField", objectField, other.objectField)
                .append("objectArrayField", objectArrayField, other.objectArrayField)
                .append("user list", users, other.users)
                .build();
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, false);
        }
    }

    @Test
    public void testBoolean() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanField = false;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Boolean.class, diff.getType());
        assertEquals(Boolean.TRUE, diff.getLeft());
        assertEquals(Boolean.FALSE, diff.getRight());
    }

    @Test
    public void testBooleanArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanArrayField = new boolean[] {false, false};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);

        assertArrayEquals(ArrayUtils.toObject(class1.booleanArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.booleanArrayField),
                          (Object[]) diff.getRight());
    }

    @Test
    public void testByte() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteField = 0x01;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.byteField, diff.getLeft());
        assertEquals(class2.byteField, diff.getRight());
        System.err.println();
    }

    @Test
    public void testByteArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteArrayField = new byte[] {0x01, 0x02};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.byteArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.byteArrayField),
                          (Object[]) diff.getRight());
        System.err.println();
    }

    @Test
    public void testChar() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charField = 'z';
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.charField, diff.getLeft());
        assertEquals(class2.charField, diff.getRight());
    }

    @Test
    public void testCharArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charArrayField = new char[] {'f', 'o', 'o'};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.charArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.charArrayField),
                          (Object[]) diff.getRight());

        System.err.println(diff.getFieldName());
        System.err.println();
    }

    @Test
    public void testDouble() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleField = 99.99;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.doubleField, diff.getLeft());
        assertEquals(class2.doubleField, diff.getRight());
    }

    @Test
    public void testDoubleArray() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();

        class2.doubleArrayField = new double[] {1.0F, 45.9F, 3.0F};
        class2.users = Arrays.asList(new User(1L, "u-1-new"), new User(4L, "u-2"), new User(5L, "u-3"));
//        class2.users = Arrays.asList(new User(3L, "u-1"), new User(4L, "u-2"), new User(5L, "u-3"));

        Map<Double, Double> array1Diffs = new HashMap<>();
        for (int i = 0; i < class1.doubleArrayField.length; i++) {
            if (class1.doubleArrayField[i] != class2.doubleArrayField[i]) {
                array1Diffs.put(class1.doubleArrayField[i], class2.doubleArrayField[i]);
            }
        }
        array1Diffs.entrySet().forEach(System.err::println);

        Map<User, User> list1Diffs = new HashMap<>();
        for (int i = 0; i < class1.users.size(); i++) {
            if (!class1.users.get(i).equals(class2.users.get(i))) {
                list1Diffs.put(class1.users.get(i), class2.users.get(i));
            }
        }
        list1Diffs.entrySet().forEach(System.err::println);

        final DiffResult list = class1.diff(class2);
        assertEquals(2, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);

        Double[] left = (Double[]) diff.getLeft();
        Double[] right = (Double[]) diff.getRight();

        Set<Double> leftSet = Stream.of(left).collect(Collectors.toSet());
        Set<Double> rightSet = Stream.of(right).collect(Collectors.toSet());

        leftSet.removeAll(rightSet);
        System.err.println("diff : " + leftSet);

        assertArrayEquals(ArrayUtils.toObject(class1.doubleArrayField), left);
        assertArrayEquals(ArrayUtils.toObject(class2.doubleArrayField), right);

        Diff<List<User>> diffUsers = (Diff<List<User>>) list.getDiffs().get(1);
        System.err.println();
    }

    @Test
    public void testFloat() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatField = 99.99F;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.floatField, diff.getLeft());
        assertEquals(class2.floatField, diff.getRight());
    }

    @Test
    public void testFloatArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatArrayField = new float[] {3.0F, 2.9F, 2.8F};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.floatArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.floatArrayField),
                          (Object[]) diff.getRight());
    }

    @Test
    public void testInt() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 42;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Integer.valueOf(class1.intField), diff.getLeft());
        assertEquals(Integer.valueOf(class2.intField), diff.getRight());
    }

    @Test
    public void testIntArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intArrayField = new int[] {3, 2, 1};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.intArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.intArrayField),
                          (Object[]) diff.getRight());
    }

    @Test
    public void testLong() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longField = 42L;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Long.valueOf(class1.longField), diff.getLeft());
        assertEquals(Long.valueOf(class2.longField), diff.getRight());
    }

    @Test
    public void testLongArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longArrayField = new long[] {3L, 2L, 1L};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.longArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.longArrayField),
                          (Object[]) diff.getRight());
    }

    @Test
    public void testShort() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortField = 42;
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Short.valueOf(class1.shortField), diff.getLeft());
        assertEquals(Short.valueOf(class2.shortField), diff.getRight());
    }

    @Test
    public void testShortArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortArrayField = new short[] {3, 2, 1};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.shortArrayField),
                          (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.shortArrayField),
                          (Object[]) diff.getRight());
    }

    @Test
    public void testObject() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectField = "Some string";
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.objectField, diff.getLeft());
        assertEquals(class2.objectField, diff.getRight());
    }

    /**
     * Test that "left" and "right" are the same instance and are equal.
     */
    @Test
    public void testObjectsSameAndEqual() throws Exception {
        final Integer sameObject = 1;
        final TypeTestClass left = new TypeTestClass();
        left.objectField = sameObject;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = sameObject;
        assertTrue(left.objectField == right.objectField);
        assertTrue(left.objectField.equals(right.objectField));

        final DiffResult list = left.diff(right);
        assertEquals(0, list.getNumberOfDiffs());
    }

    /**
     * Test that "left" and "right" are the same instance but are equal.
     */
    @Test
    public void testObjectsNotSameButEqual() throws Exception {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = new Integer(1);
        final TypeTestClass right = new TypeTestClass();
        right.objectField = new Integer(1);
        assertFalse(left.objectField == right.objectField);
        assertTrue(left.objectField.equals(right.objectField));

        final DiffResult list = left.diff(right);
        assertEquals(0, list.getNumberOfDiffs());
    }

    /**
     * Test that "left" and "right" are not the same instance and are not equal.
     */
    @Test
    public void testObjectsNotSameNorEqual() throws Exception {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = 4;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = 100;
        assertFalse(left.objectField == right.objectField);
        assertFalse(left.objectField.equals(right.objectField));

        final DiffResult list = left.diff(right);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testObjectArray() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        Object[] left = (Object[]) diff.getLeft();
        assertArrayEquals(class1.objectArrayField, left);
        Object[] right = (Object[]) diff.getRight();
        assertArrayEquals(class2.objectArrayField, right);
        System.err.println();
    }

    @Test
    public void testObjectArrayEqual() throws Exception {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class1.objectArrayField = new Object[] {"string", 1, 2};
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult list = class1.diff(class2);
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testByteArrayEqualAsObject() throws Exception {
        final DiffResult list = new DiffBuilder("String1", "String2", SHORT_STYLE)
            .append("foo", (Object) new boolean[] {false}, (Object) new boolean[] {false})
            .append("foo", (Object) new byte[] {0x01}, (Object) new byte[] {0x01})
            .append("foo", (Object) new char[] {'a'}, (Object) new char[] {'a'})
            .append("foo", (Object) new double[] {1.0}, (Object) new double[] {1.0})
            .append("foo", (Object) new float[] {1.0F}, (Object) new float[] {1.0F})
            .append("foo", (Object) new int[] {1}, (Object) new int[] {1})
            .append("foo", (Object) new long[] {1L}, (Object) new long[] {1L})
            .append("foo", (Object) new short[] {1}, (Object) new short[] {1})
            .append("foo", (Object) new Object[] {1, "two"}, (Object) new Object[] {1, "two"})
            .build();

        assertEquals(0, list.getNumberOfDiffs());
        System.err.println();
    }

    @Test
    public void testDiffResult() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 2;

        final DiffResult list = new DiffBuilder(class1, class2, SHORT_STYLE)
            .append("prop1", class1.diff(class2))
            .build();
        assertEquals(1, list.getNumberOfDiffs());
        assertEquals("prop1.int", list.getDiffs().get(0).getFieldName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullLhs() {
        new DiffBuilder(null, this, ToStringStyle.DEFAULT_STYLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRhs() {
        new DiffBuilder(this, null, ToStringStyle.DEFAULT_STYLE);
    }

    @Test
    public void testSameObjectIgnoresAppends() {
        final TypeTestClass testClass = new TypeTestClass();
        final DiffResult list = new DiffBuilder(testClass, testClass, SHORT_STYLE)
            .append("ignored", false, true)
            .build();
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testSimilarObjectIgnoresAppends() {
        final TypeTestClass testClass1 = new TypeTestClass();
        final TypeTestClass testClass2 = new TypeTestClass();
        final DiffResult list = new DiffBuilder(testClass1, testClass2, SHORT_STYLE)
            .append("ignored", false, true)
            .build();
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testStylePassedToDiffResult() {
        final TypeTestClass class1 = new TypeTestClass();
        DiffResult list = class1.diff(class1);
        assertEquals(SHORT_STYLE, list.getToStringStyle());

        class1.style = ToStringStyle.MULTI_LINE_STYLE;
        list = class1.diff(class1);
        assertEquals(ToStringStyle.MULTI_LINE_STYLE, list.getToStringStyle());
    }

    @Test
    public void testTriviallyEqualTestDisabled() {
        final Matcher<Integer> equalToOne = equalTo(1);

        // Constructor's arguments are not trivially equal, but not testing for that.
        DiffBuilder explicitTestAndNotEqual1 = new DiffBuilder(1, 2, null, false);
        explicitTestAndNotEqual1.append("letter", "X", "Y");
        assertThat(explicitTestAndNotEqual1.build().getNumberOfDiffs(), equalToOne);

        // Constructor's arguments are trivially equal, but not testing for that.
        DiffBuilder explicitTestAndNotEqual2 = new DiffBuilder(1, 1, null, false);
        // This append(f, l, r) will not abort early.
        explicitTestAndNotEqual2.append("letter", "X", "Y");
        assertThat(explicitTestAndNotEqual2.build().getNumberOfDiffs(), equalToOne);
    }

    @Test
    public void testTriviallyEqualTestEnabled() {
        final Matcher<Integer> equalToZero = equalTo(0);
        final Matcher<Integer> equalToOne = equalTo(1);

        // The option to test if trivially equal is enabled by default.
        DiffBuilder implicitTestAndEqual = new DiffBuilder(1, 1, null);
        // This append(f, l, r) will abort without creating a Diff for letter.
        implicitTestAndEqual.append("letter", "X", "Y");
        assertThat(implicitTestAndEqual.build().getNumberOfDiffs(), equalToZero);

        DiffBuilder implicitTestAndNotEqual = new DiffBuilder(1, 2, null);
        // This append(f, l, r) will not abort early
        // because the constructor's arguments were not trivially equal.
        implicitTestAndNotEqual.append("letter", "X", "Y");
        assertThat(implicitTestAndNotEqual.build().getNumberOfDiffs(), equalToOne);

        // This is explicitly enabling the trivially equal test.
        DiffBuilder explicitTestAndEqual = new DiffBuilder(1, 1, null, true);
        explicitTestAndEqual.append("letter", "X", "Y");
        assertThat(explicitTestAndEqual.build().getNumberOfDiffs(), equalToZero);
    }

    @Test
    public void testVisePolja() {
        TypeTestClass class1 = new TypeTestClass();
        TypeTestClass class2 = new TypeTestClass();

        class2.booleanField = false;
        class2.floatField = 1.11f;
        class2.byteField = 0X79;
        class2.doubleArrayField = new double[] {1.0F, 2.0, 3.0};
//        class2.users = Arrays.asList(new User(79L, "dobri"), new User(2312L, "nina"));
        class2.users = Arrays.asList(new User(3L, "u-1-novo"), new User(4L, "u-2-novo"), new User(5L, "u-3"), new User(777L, "777"));

        DiffResult list = class1.diff(class2);
        assertEquals(5, list.getNumberOfDiffs());

        List<Diff<?>> diffs = list.getDiffs();

        Diff<?> diff1 = diffs.get(0);
        assertEquals(Boolean.class, diff1.getType());
        assertEquals(/*Boolean.TRUE*/ true, diff1.getLeft());
        assertEquals(/*Boolean.FALSE*/false, diff1.getRight());

        Diff<?> diff2 = diffs.get(1);
        assertEquals(Byte.class, diff2.getType());
        assertEquals((byte) 0xFF, diff2.getLeft());
        assertEquals((byte) 0X79, diff2.getRight());

//        printAllFiledsDiffs(diffs);

        diffs.forEach(System.err::println);

        System.err.println("------");

        Diff<?> diff3 = diffs.get(2);
        if (diff3.getLeft() instanceof Object[]) {
            System.err.println();
        }

        Diff<?> diff4 = diffs.get(3);
        if (diff4.getLeft() instanceof Collection) {
            System.err.println("kolekcija");
        }

        for (int i = 0; i < diffs.size(); i++) {
            Diff<?> ddd = diffs.get(i);

            String fieldName = ddd.getFieldName();
            Object left1 = ddd.getLeft();
            Object right1 = ddd.getRight();
            System.err.println(fieldName + " : " + left1 + "/" + right1);

            Set<Class<?>> primitives = new HashSet<>(Arrays.asList(Integer.class, Boolean.class, String.class));
            if (ddd.getType().getClass().isPrimitive() || primitives.contains(ddd.getValue().getClass()))
                System.err.println("primitive. type = " + ddd.getType().getTypeName());
            else
                System.err.println("not primitive. type: " + ddd.getType().getTypeName());

            if (ddd.getLeft() != null) {
                if (!(ddd.getLeft() instanceof Collection<?>)) {
                    Object left = ddd.getLeft();
                    Object right = ddd.getRight();
//                    System.err.println(left.getClass().getName()+" : "+ ddd.getLeft();

                    /*assertEquals(Byte.class, ddd.getType());
                    assertEquals((byte) 0xFF, ddd.getLeft());
                    assertEquals((byte) 0X79, ddd.getRight());*/
                } else {
                    Collection<?> gen = (Collection<?>) ddd.getLeft();
                    Boolean isUserType = Optional.ofNullable(gen.iterator().next()).map(e -> e instanceof User).orElse(false);
                    System.err.println("isUserType  ? " + isUserType);

                    Collection<User> left = (Collection<User>) ddd.getLeft();
                    List<User> LL = new ArrayList<>(left);

                    Collection<User> right = (Collection<User>) ddd.getRight();
                    List<User> RR = new ArrayList<>(right);

                    System.err.println();
                    System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    LL.forEach(System.err::println);
                    System.err.println("---");
                    RR.forEach(System.err::println);

                    System.err.println("**************************************************");
                    LL.retainAll(RR);
                    System.err.println(LL);
                    System.err.println("**************************************************");

                    System.err.println("-------------------------------------------------");
                    System.err.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                }
            }
        }
    }

    @Test
    public void test1() {
//        List<User> L1 = Arrays.asList(new User(3L, "u-1-novo"), new User(4L, "u-2-novo"), new User(5L, "u-3"), new User(777L, "777"));
        List<User> L1 = new ArrayList<>(User.users);
        List<User> L2 = L1.stream().map(SerializationUtils::clone).collect(Collectors.toCollection(ArrayList::new));

        User u1 = L1.get(1);
        User u2 = L2.get(1);

        int L1size = L1.size();
        int L1Csize = u1.getChildren().size();
        int L2size = L2.size();
        int L2Csize = u2.getChildren().size();

        u1.setCreateDate(null);

        List<Integer> children = u1.getChildren();
        children.remove(0);
        children.remove(1);

//        L1.removeIf(user -> user.getUserId().equals(u1.getUserId()));
        L1.remove(0);
        assertEquals(L1size - 1, L1.size());
        assertEquals(L1Csize - 2, u1.getChildren().size());

        assertEquals(L2size, L2.size());
        assertEquals(L2Csize, u2.getChildren().size());

        assertNotEquals(u1.getCreateDate(), u2.getCreateDate());

        User u3 = new User();
        u3.setCreateDate(new Date());
        User u4 = new User();
        u4.setCreateDate(u3.getCreateDate());
        u3.setCreateDate(null);

        assertNotEquals(u3.getCreateDate(), u4.getCreateDate());
    }
}