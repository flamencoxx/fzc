package com.fzc.fzccommon.Test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author flamenco.xxx
 * @date 2021/12/8 10:09
 */
@SpringBootTest
public class OptionalTest {
    class Zoo {
        private Dog dog;

        public Dog getDog() {
            return dog;
        }

        public void setDog(Dog dog) {
            this.dog = dog;
        }
    }

    class Dog {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Test
    public void test1(){
        Dog dog = new Dog();
        dog.setAge(10);
        Zoo z = new Zoo();
        z.setDog(dog);
        AtomicInteger i = new AtomicInteger();
        Optional.ofNullable(z).map(o -> o.getDog()).map(o -> o.getAge()).ifPresent(age ->{
            i.set(age);
        });
        System.out.println(i);
    }
}
