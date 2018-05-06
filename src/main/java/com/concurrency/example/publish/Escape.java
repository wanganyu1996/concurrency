package com.concurrency.example.publish;

/**
 * Created by wanganyu on 2018/04/28.
 */
public class Escape {
    private int thisCanBeEscape=0;
    public Escape(){
      new InnerClass();
    }
    private class InnerClass{
        public InnerClass(){
            System.out.println("{}"+Escape.this.thisCanBeEscape);
        }
    }
    public static void main(String[] args){
        new Escape();
    }
}
