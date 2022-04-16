import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl1111w.Application;
import pl1111w.config.MyYamlConfig;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/16 15:01
 */
@SpringBootTest(classes = Application.class)
public class Junit5Test {

    @Autowired
    private MyYamlConfig yamlConfig;

    @Test
    @DisplayName("Test_1")
    public void test() {
        System.out.println("this is test method...");
    }

    @Test
    @DisplayName("Test_2")
    public void test2() {
        System.out.println("this is test2 method...");
    }

    @Test
    @DisplayName("SPRING")
    public void test3() {
        System.out.println(yamlConfig.toString());
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("this method before executed!");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("this method after executed!");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("these methods beforeAll executed!");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("these methods afterAll executed!");
    }

    @RepeatedTest(2)
    public void repeat() {
        System.out.println("*********");
    }

    @Test
    @Disabled
    @DisplayName("assertEquals")
    public void assertEquals() {
        int a = Math.addExact(2, 4);
        Assertions.assertEquals(a, 9, "unequals");
        System.out.println("this is assertEquals method...");
    }

    @Test
    @Disabled
    @DisplayName("assertEquals")
    public void assertSame() {
        int[] a = new int[2];
        int[] b = new int[2];
        Assertions.assertSame(a, b, "not same");
        System.out.println("this is assertSame method...");
    }

    @Test
    @Disabled
    @DisplayName("assertEquals")
    public void assertArrayEquals() {
        int[] a = new int[2];
        int[] b = new int[2];
        Assertions.assertArrayEquals(a, b, "not Array same");
        System.out.println("this is assertSame method...");
    }

    @Test
    @Disabled
    @DisplayName("assertAll")
    public void assertAll() {
        Assertions.assertAll("TEST", () -> Assertions.assertTrue(true)
                , () -> Assertions.assertTrue(true));
        System.out.println("assertAll.......");
    }

    @Test
    @Disabled
    @DisplayName("assertAll")
    public void assertThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
                  int a =  1 / 0;
                },
                "failed,,,,,");
        System.out.println("assertThrows.......");
    }

    @Test
    @Disabled
    @ParameterizedTest
    @MethodSource("stream")
    public void parameterizedTest(String fruit) {
        System.out.println(fruit);
    }
    static Stream<String> stream(){
        return Arrays.asList("apple","orange").stream();
    }

    @Test
    @Disabled
    @ParameterizedTest
    @ValueSource(doubles = {1.5,1.9})
    public void parameterizedTest2(Double num) {
        System.out.println(num);
    }
}
