package ejercicio4;
 
import ejercicio1.StackLink;
import Actividad1.ExceptionIsEmpty;
 
public class Application {
   public static boolean symbolBalancing(String s) {
       StackLink<Character> stack = new StackLink<>();
 
       for (int i = 0; i < s.length(); i++) {
           char ch = s.charAt(i);
 
           if (ch == '(' || ch == '[' || ch == '{') {
               stack.push(ch);
           }
           else if (ch == ')' || ch == ']' || ch == '}') {
               if (stack.isEmpty()) return false;
               try {
                   char open = stack.pop();
                   if (!match(open, ch)) return false;
               } catch (ExceptionIsEmpty e) {
                   return false;
               }
           }
       }
 
       return stack.isEmpty(); 
   }
 
   private static boolean match(char open, char close) {
       return (open == '(' && close == ')') ||
              (open == '[' && close == ']') ||
              (open == '{' && close == '}');
   }
}