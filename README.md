# 1717. Maximum Score From Removing Substrings

[Link to Problem](https://leetcode.com/problems/maximum-score-from-removing-substrings/)  
**Difficulty:** Medium  
**Topics:** Stack, Greedy, String  
**Tags:** Two Pointers, String Manipulation  
![Leetcode](https://img.shields.io/badge/Leetcode-1717-blue)
![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)
![Language](https://img.shields.io/badge/Language-Java-orange)

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
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.

---

🚀 Solution Idea
The problem requires us to maximize the total score by repeatedly removing either the substring "ab" (worth x points) or "ba" (worth y points) from the string s. Since both operations can be performed any number of times and in any order, the order of operations will significantly affect the final score.

Here's the step-by-step logic:
🔧 Greedy Strategy
Always prioritize removing the substring with the higher score first.
For example, if x > y, remove "ab" first, since it's more valuable.
If y > x, remove "ba" first.
This greedy strategy ensures we capture the maximum points from high-value patterns before potentially breaking them by removing lower-value ones.

🛠️ Helper Function func(...)
We define a helper function func(sb, f, s, x, y) that:
Scans the string left to right using a counter-style logic.
When it finds the matching pattern (either "ab" or "ba" depending on f and s), it adds the corresponding score (x) and continues.
If the character is neither f nor s, we compute how many valid pairs (in reverse order) can still be removed and reset the counters.

🔄 How It Works
Convert the input string s to a StringBuilder (to allow in-place edits).
Based on whether x > y or x < y, call func(...) with the appropriate pair ("ab" or "ba" first).
After one full pass, the remaining characters may still contain the opposite substring.
Call func(...) again to process the remaining pattern and add those points too.
Return the total score.
This approach runs in O(n) time and O(1) space (excluding the input string), making it both efficient and clean.

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
```
🧠 Complexity Analysis
Time Complexity: O(n)

Space Complexity: O(1) (Ignoring output and input, and only using constant extra space)

