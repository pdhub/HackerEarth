import java.lang.*;
import java.util.*;
class Stack1 {
    public boolean isValid(String s) {
        Stack stack = new Stack();
        Map map = new HashMap();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        for(int i=0; i<s.length(); i++){
            if((s.charAt(i)=='}' || s.charAt(i)==')' || s.charAt(i)==']')){
                if(!stack.isEmpty() && map.get(s.charAt(i)) == stack.peek())
                    stack.pop();
                else
                    stack.push(s.charAt(i));
            }else
                stack.push(s.charAt(i));
        }
        return stack.isEmpty();
    }
}