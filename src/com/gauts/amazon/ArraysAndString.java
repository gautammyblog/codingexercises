package com.gauts.amazon;

import java.util.*;
import java.util.stream.Collectors;

public class ArraysAndString {
    public static void main(String[] args) {
        ArraysAndString app = new ArraysAndString();
//        System.out.println(app.intToRoman(1994));
//        System.out.println(app.isValid("()"));
//        System.out.println(app.groupAnagrams(new String[]{"cab","tin","pew","duh","may","ill","buy","bar","max","doc"}));
//        System.out.println(app.minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(app.minWindow("a", "aa"));
        System.out.println(app.minWindow("aab", "abb"));
    }

    public String minWindow(String s, String t) {
        if(s.length() < t.length())
            return "";
        if(s.equals(t))
            return s;
        char[] tchars = t.toCharArray();
        int leftIndex = 0;
        String str = s.substring(leftIndex);
        while(stringContainsChars(str, tchars)){
            leftIndex++;
            str = s.substring(leftIndex);
        }
        if(leftIndex > 0)
            leftIndex--;
        str = s.substring(leftIndex);
        int rightIndex = s.length();
        while(stringContainsChars(str, tchars)){
            rightIndex--;
            str = s.substring(leftIndex, rightIndex);
        }
        if(rightIndex < s.length())
            ++rightIndex;
        str = s.substring(leftIndex, rightIndex);
        if(stringContainsChars(str, tchars)) {
            return str;
        }else{
            return "";
        }
    }
    public boolean stringContainsChars(String str, char[] tchars){
        for(char c : tchars){
            if(str.indexOf(c) == -1)
                return false;
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        /* Anagram is a string whose character sum is same. */
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String charTotal = String.valueOf(chars);
            if(map.containsKey(charTotal)){
                List<String> list = (List)map.get(charTotal);
                list.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(charTotal, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        boolean valid = true;
        char[] charArr = s.toCharArray();
        for(int i=0; i<charArr.length; i++){
            switch(charArr[i]){
                case '{':
                case '(':
                case '[':
                    stack.push(charArr[i]);
                    break;
                case '}':
                    char pop1 = stack.pop();
                    valid = (pop1 == '{');
                    break;
                case ')':
                    char pop2 = stack.pop();
                    valid = (pop2 == '(');
                    break;
                case ']':
                    char pop3 = stack.pop();
                    valid = (pop3 == '[');
                    break;
            }
            if(!valid){
                break;
            }
        }
        return valid && stack.isEmpty();
    }

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        char[] c = String.valueOf(num).toCharArray();
        int mul = 1;
        if(c.length == 4){
            mul = 1000;
        } else if(c.length == 3){
            mul = 100;
        } else if(c.length == 2){
            mul = 10;
        }
        for(int i=0; i< c.length; i++){
            int n = Integer.valueOf("" + c[i]);
            roman.append(findRoman(n*mul));
            mul /= 10;
        }
        return roman.toString();
    }

    public String findRoman(int value){
        switch(value){
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            case 10: return "X";
            case 20: return "XX";
            case 30: return "XXX";
            case 40: return "XL";
            case 50: return "L";
            case 60: return "LX";
            case 70: return "LXX";
            case 80: return "LXXX";
            case 90: return "XC";
            case 100: return "C";
            case 200: return "CC";
            case 300: return "CCC";
            case 400: return "CD";
            case 500: return "D";
            case 600: return "DC";
            case 700: return "DCC";
            case 800: return "DCCC";
            case 900: return "CM";
            case 1000: return "M";
            case 2000: return "MM";
            case 3000: return "MMM";
            default: return "";
        }
    }
}
