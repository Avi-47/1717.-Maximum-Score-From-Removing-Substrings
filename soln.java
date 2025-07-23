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
