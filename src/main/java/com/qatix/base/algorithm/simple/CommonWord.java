package com.qatix.base.algorithm.simple;

import java.util.*;

public class CommonWord {
    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph.replaceAll("/[!?',;.]+/"," ");
        System.out.println(paragraph);
        String symbols = "!?',;.";
        for(char ch : symbols.toCharArray()){
            paragraph = paragraph.replace(ch, ' ');
        }
        System.out.println(paragraph);
        List<Integer> stack =  new LinkedList<>();
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph.replaceAll("/[!?',;]/"," ");
        String[] parts = paragraph.split(" ");
        Map<String,Integer> map = new HashMap<>();
        Set<String> banset = new HashSet<>(Arrays.asList(banned));
        for(String s:parts){
            s = s.trim();
            if(banset.contains(s)){
                continue;
            }
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }else{
                map.put(s,1);
            }
        }
        System.out.println(map);
        String word = null;
        int maxCount = 0;
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                word = entry.getKey();
            }
        }
        return word;
    }
}
