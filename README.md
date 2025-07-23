# 1717. Maximum Score From Removing Substrings

[Link to Problem](https://leetcode.com/problems/maximum-score-from-removing-substrings/)  
**Difficulty:** Medium  
**Topics:** Stack, Greedy, String  
**Tags:** Two Pointers, String Manipulation  

![Status](https://img.shields.io/badge/Status-Solved-brightgreen.svg)
![Language](https://img.shields.io/badge/Language-Java-blue.svg)

---

## 🧠 Problem Summary

You are given a string `s` and two integers `x` and `y`.

You can perform two operations:

1. Remove the substring `"ab"` and gain `x` points.
2. Remove the substring `"ba"` and gain `y` points.

You may perform these operations any number of times in any order. Return the **maximum points** you can gain after applying the operations optimally.

---

## 🔍 Examples

Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation: Remove "ba" -> "cdbcbbaabab" -> Remove "ab" -> "cdbcbbaab" -> ...


---

## 🚀 Solution Idea

- Use a greedy approach: always prioritize removing the substring with the **higher score** first.
- Use a helper function `func(...)` that processes one type of substring and accumulates points.
- Iterate the string, counting and resolving character pairs, and then process the remaining characters for the second operation.

---

## 🧾 Code

```java
class Solution {
    public int func(StringBuilder sb,char f, char s, int x, int y){
        int i = 0;
        int count = 0;
        int a = 0;
        int b = 0;
        while(i<sb.length()){
            if(sb.charAt(i)==f) a++;
            else if(sb.charAt(i)==s){
                if(a>0){
                    count+=x;
                    a--;
                }else b++;
            }else{
                count+= Math.min(a,b)*y;
                a=0;
                b=0;
            }
            i++;
        }
        if(a>0) count+= Math.min(a,b)*y;
        return count;
    }

    public int maximumGain(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        int c = 0;
        if(x>y) c = func(sb,'a','b',x,y);
        else c = func(sb,'b','a',y,x);
        return c;
    }
}
🧠 Complexity Analysis
Time Complexity: O(n)

Space Complexity: O(1) (Ignoring output and input, and only using constant extra space)

