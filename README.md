# 1717.-Maximum-Score-From-Removing-Substrings

# 🧮 1717. Maximum Score From Removing Substrings

[Leetcode Link 🔗](https://leetcode.com/problems/maximum-score-from-removing-substrings/)  
**Difficulty:** Medium  
**Topics:** String, Stack, Greedy  
**Status:** ✅ Solved

---

## 📄 Problem Statement

You are given a string `s` and two integers `x` and `y`. You can perform two types of operations any number of times:

- Remove substring `"ab"` and gain `x` points.
- Remove substring `"ba"` and gain `y` points.

Return the **maximum points** you can gain after applying the above operations on `s`.

---

## 💡 Approach

- The idea is to **greedily remove the substring** that gives more score first.
- We define a helper function `func(sb, f, s, x, y)` which:
  - Traverses the string and counts the number of matching `f` before `s`.
  - Adds score `x` when a valid substring (`f` followed by `s`) is found.
  - When encountering other characters, we remove all remaining `fs` and `ss` pairs, gaining `y` points.
- If `x > y`, we remove `"ab"` first, otherwise remove `"ba"` first.

This ensures we always take the **highest scoring substring first** for maximum gain.

---

## ✅ Code (Java)

```java
class Solution {
    public int func(StringBuilder sb, char f, char s, int x, int y) {
        int i = 0;
        int count = 0;
        int a = 0;
        int b = 0;
        while (i < sb.length()) {
            if (sb.charAt(i) == f) {
                a++;
            } else if (sb.charAt(i) == s) {
                if (a > 0) {
                    count += x;
                    a--;
                } else {
                    b++;
                }
            } else {
                count += Math.min(a, b) * y;
                a = 0;
                b = 0;
            }
            i++;
        }
        if (a > 0) count += Math.min(a, b) * y;
        return count;
    }

    public int maximumGain(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        if (x > y)
            return func(sb, 'a', 'b', x, y);
        else
            return func(sb, 'b', 'a', y, x);
    }
}
