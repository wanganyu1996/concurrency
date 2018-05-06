package com.concurrency.example.immutable;

import com.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * Created by wanganyu on 2018/05/01.
 */
@NotThreadSafe
public class ImmutableExample3 {
    private final static ImmutableList list=ImmutableList.of(1,2,3);
    private final static ImmutableSet set=ImmutableSet.copyOf(list);

    private final String b="2";
    private  static Map<Integer,Integer> map= ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer,Integer> map2=ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();
    public static void main(String[] args){
     // map2.put(1,2);
        System.out.println(map2.get(1));
        set.add(4);
    }


}
