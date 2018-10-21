package com.qatix.base.guava;

import com.google.common.base.Function;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.apache.curator.shaded.com.google.common.reflect.Invokable;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class RefectionTest {

    static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, keyToken)
                .where(new TypeParameter<V>() {}, valueToken);
    }



    private static void testTypeToken(){
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        System.out.println(stringTok.getType());//class java.lang.String
        System.out.println(intTok.getRawType()); //class java.lang.Integer
        System.out.println(intTok.getType()); //class java.lang.Integer

        TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {};
        System.out.println(stringListTok.getType()); //java.util.List<java.lang.String>
        TypeToken<Map<?, ?>> wildMapTok = new TypeToken<Map<?, ?>>() {};
        System.out.println(wildMapTok.getType()); //java.util.Map<?, ?>
        System.out.println(wildMapTok.getRawType()); //java.util.Map<?, ?>


        TypeToken<Map<String, BigInteger>> mapToken = mapToken(
                TypeToken.of(String.class),
                TypeToken.of(BigInteger.class));
        System.out.println(mapToken.getType());
        TypeToken<Map<Integer, Queue<String>>> complexToken = mapToken(
                TypeToken.of(Integer.class),
                new TypeToken<Queue<String>>() {});
        System.out.println(complexToken.getType());


        System.out.println(Util.<String, BigInteger>incorrectMapToken()); //java.util.Map<K, V>
        // just prints out "java.util.Map<K, V>"

        IKnowMyType<String> iKnowMyType  =new IKnowMyType<String>() {
        };
        System.out.println(iKnowMyType.type); //java.lang.String
        System.out.println(new IKnowMyType<List<Long>>() {
        }.type); //java.util.List<java.lang.Long>


        TypeToken<Function<Integer, String>> funToken = new TypeToken<Function<Integer, String>>() {};

        TypeToken<?> funResultToken = funToken.resolveType(Function.class.getTypeParameters()[1]);
        //0 : Integer , 1 : String
        System.out.println(funResultToken);

    }

    private static void testInvokable() throws NoSuchMethodException {
        Method getMethod = List.class.getMethod("get", int.class);
//        Invokable<List<String>, ?> invokable = new TypeToken<List<String>>() {}.method(getMethod);
//        System.out.println(invokable.getReturnType());
//        System.out.println(invokable.getOwnerType());
//        assertEquals(TypeToken.of(String.class), invokable.getReturnType()); // Not Object.class!
//        assertEquals(new TypeToken<List<String>>() {}, invokable.getOwnerType());
    }

    public static void main(String[] args) {
//        testInvokable();
        testTypeToken();
    }
}

class Util {
    static <K, V> TypeToken<Map<K, V>> incorrectMapToken() {
        return new TypeToken<Map<K, V>>() {};
    }
}

abstract class IKnowMyType<T> {
    TypeToken<T> type = new TypeToken<T>(getClass()) {};
}