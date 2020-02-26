package com.fosuchao.multithreading.atomic.entity;

/**
 * @Description:
 * @Auther: Joker Ye
 * @Date: 2020/2/4 10:37
 */
public class User {
    String name;
    public volatile int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
