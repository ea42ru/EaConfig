package ru.ea42.EaConfig;

public class Main {
    public static void main(String[] args) {
        Config conf = new Config();
        System.out.println(conf.jsStr);
        System.out.println("Test =>" + conf.getParamAsString("Test"));
    }
}