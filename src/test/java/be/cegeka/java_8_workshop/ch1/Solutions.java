package be.cegeka.java_8_workshop.ch1;

import be.cegeka.java_8_workshop.ch1.ex6.RunnableEx;
import be.cegeka.java_8_workshop.ch1.ex7.ChainedRunnable;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
@RunWith(JUnitParamsRunner.class)
public class Solutions {

    private static final File TEST_DIR = new File("./src/test/resources/");
    private static final File SUB_DIR_ONE = new File("./src/test/resources/subDirOne/");
    private static final File SUB_DIR_TWO = new File("./src/test/resources/subDirTwo/");
    private static final File PIXEL_PNG = new File("./src/test/resources/pixel.png");
    private static final File TEST_TXT = new File("./src/test/resources/test.txt");

    @Test
    public void exercise_01() {
        Thread mainThread = Thread.currentThread();

        String[] array = {"one", "two"};

        Arrays.sort(array, (o1, o2) -> {
            assertThat(mainThread).isSameAs(Thread.currentThread());
            return o1.compareTo(o2);
        });
    }

    @Test
    public void exercise_02() {
        File[] directoriesWithLambda = TEST_DIR.listFiles(f -> f.isDirectory());
        // of
        File[] directoriesWithMethodRef = TEST_DIR.listFiles(File::isDirectory);

        assertThat(directoriesWithLambda).containsExactly(directoriesWithMethodRef);
        assertThat(directoriesWithLambda).hasSize(2);
    }

    @Test
    @Parameters({"exe,0", "png,1"})
    public void exercise_03(String extension, int count) {
        File[] files = TEST_DIR.listFiles(((dir, name) -> name.endsWith("." + extension)));
        assertThat(files).hasSize(count);
    }

    @Test
    public void exercise_04() {
        File[] files = TEST_DIR.listFiles();
        Arrays.sort(files, Comparator.comparing(File::isDirectory).reversed().thenComparing(File::getName));

        assertThat(files).containsExactly(SUB_DIR_ONE, SUB_DIR_TWO, PIXEL_PNG, TEST_TXT);
    }

    @Test
    @Ignore("See be.cegeka.java_8_workshop.ch1.ex5.OldVorderingFunctions and be.cegeka.java_8_workshop.ch1.ex5.NewVorderingFunctions")
    public void exercise_05() {
    }

    @Test
    public void exercise_06() {
        new Thread(() -> {
            try {
                exceptionThrowing();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new Thread(RunnableEx.unCheck(() -> exceptionThrowing()));
        new Thread(RunnableEx.unCheck(Solutions::exceptionThrowing));
    }

    public static void exceptionThrowing() throws Exception {
        throw new Exception();
    }

    @Test
    public void exercise_07() {
        new Thread(andThen(
                () -> System.out.println("1"),
                () -> System.out.println("2")
        )).start();


        new Thread(new ChainedRunnable(() -> System.out.println("1"))
                .andThen(() -> System.out.println("2"))
                .andThen(() -> System.out.println("3"))).start();
    }

    public static Runnable andThen(Runnable r1, Runnable r2) {
        return () -> {
            r1.run();
            r2.run();
        };
    }

    @Test
    public void exercise_08() throws InterruptedException {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println("forEach: " + name));
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> System.out.println("indexed: " + name));
        }

        for (Runnable runner : runners) {
            new Thread(runner).start();
            Thread.sleep(500);
        }
    }

    @Test
    public void exercise_09() {
        Collection2<String> c = new ArrayList2<>("a", "b", "cd", "e", "fg");
        c.forEachIf(System.out::println, (s) -> s.length() == 2);
        c.forEachIfStreams(System.out::println, (s) -> s.length() == 1);
    }


    public interface Collection2<E> extends Collection<E> {
        default void forEachIf(Consumer<E> action, Predicate<E> filter) {
            for (E e : this) {
                if (filter.test(e)) {
                    action.accept(e);
                }
            }
        }

        default void forEachIfStreams(Consumer<E> action, Predicate<E> filter) {
            this.stream().filter(filter::test).forEach(action::accept);
        }
    }

    private static class ArrayList2<E> extends ArrayList<E> implements Collection2<E> {

        public ArrayList2(E... elements) {
            super(Arrays.asList(elements));
        }
    }

    @Test
    @Ignore("Do this if time is left")
    public void exercise_10() {

    }

    @Test
    public void exercise_11() {
    }

//    public static class IJ implements I, J {
//
//        @Override
//        public String abstract_I_abstract_J() {
//            return "abstract_I_abstract_J -> IJ";
//        }
//
//        @Override
//        public String abstract_I_default_J() {
//            return "abstract_I_default_J -> IJ";
//        }
//
//        @Override
//        public String default_I_default_J() {
//            return "default_I_default_J -> IJ";
//        }
//
//
//    }

    public interface I {
        String abstract_I_abstract_J();

        String abstract_I_default_J();

        String abstract_I_static_J();

        default String default_I_default_J() {
            return "default_I_default_J -> I";
        }

        default String default_I_static_J() {
            return "default_I_static_J -> I";
        }

        static String static_I_static_J() {
            return "static_I_static_J -> I";
        }
    }

    public interface J {
        String abstract_I_abstract_J();

        default String abstract_I_default_J() {
            return "abstract_I_default_J -> J";
        }

        static String abstract_I_static_J() {
            return "abstract_I_static_J -> J";
        }

        default String default_I_default_J() {
            return "default_I_default_J -> J";
        }

        static String default_I_static_J() {
            return "default_I_static_J -> J";
        }

        static String static_I_static_J() {
            return "static_I_static_J -> J";
        }
    }

//    public static class ImplementsF extends SuperF implements InterfaceF {
//
//        @Override
//        public String abstract_I_abstract_S() {
//            return null;
//        }
//
//        @Override
//        public String abstract_I_default_S() {
//            return null;
//        }
//
//        @Override
//        public String default_I_abstract_S() {
//            return null;
//        }
//
//    }

    public static abstract class SuperF {

        public abstract String abstract_I_abstract_S();

        public String abstract_I_default_S() {
            return "abstract_I_default_S -> SuperF";
        }

        public static String abstract_I_static_S() {
            return "abstract_I_static_S -> SuperF";
        }

        public abstract String default_I_abstract_S();

        public static String default_I_static_S() {
            return "default_I_static_S -> SuperF";
        }

        public static String static_I_static_S() {
            return "static_I_static_J -> SuperF";
        }

    }

    public interface InterfaceF {

        String abstract_I_abstract_S();

        String abstract_I_default_S();

        String abstract_I_static_S();

        default String default_I_abstract_S() {
            return "default_I_abstract_S -> InterfaceF";
        }

        default String default_I_static_S() {
            return "default_I_static_S -> InterfaceF";
        }

        static String static_I_static_S() {
            return "static_I_static_S -> InterfaceF";
        }
    }

    @Test
    public void exercise_12() {
        // TODO: Create java 7 collection interface like below to run on Java 8
    }

    public interface MyCollection<E> extends Collection<E> {
        // void stream();
    }


}
