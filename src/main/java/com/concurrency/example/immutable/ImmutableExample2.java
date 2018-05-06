package com.concurrency.example.immutable;

import com.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by wanganyu on 2018/05/01.
 */
@NotThreadSafe
public class ImmutableExample2 {
    private final static Integer a=1;
    private final String b="2";
    private  static Map<Integer,Integer> map= Maps.newHashMap();
    static{
       map.put(1,2);
       map.put(3,4);
       map.put(5,6);
       map= Collections.unmodifiableMap(map);
    }
    public static void main(String[] args){
       map.put(1,3);
        System.out.println(""+map.get(1));
    }


}
