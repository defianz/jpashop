package me.defian.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {

        Hello hello = new Hello();
        hello.setData("Hello");
        System.out.println("hello.getData() = " + hello.getData());


        SpringApplication.run(JpashopApplication.class, args);
    }

}
