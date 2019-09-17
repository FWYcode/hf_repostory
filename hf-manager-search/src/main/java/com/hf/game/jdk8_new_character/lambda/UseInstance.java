package com.hf.game.jdk8_new_character.lambda;

/**
 * Created by 123 on 2019-9-8.
 */
public class UseInstance {
    private static Object doSomthing(DoSomthing somthing){
        return somthing.getInstance();
    }
    public static void main(String[] args) {
        Object o = doSomthing(() -> {
            System.out.print(1111111);
            return 333;
        });
        System.out.println(o);
    }
}
