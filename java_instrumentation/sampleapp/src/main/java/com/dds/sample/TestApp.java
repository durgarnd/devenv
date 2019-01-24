package com.dds.sample;

public class TestApp {

    public static  void main(String as[]){
        new TestApp().add();
        new TestApp().add(2,5);
    }
    public void add(){
    System.out.println("Add method executed");

    }

    public void add(int a,int b){
        System.out.println("Parameterized Add method executed :"+(a+b));

    }
}
