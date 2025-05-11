package ejercicio4;

public class Test {
   public static void main(String[] args) {
       String[] casos = {
           "()()()[()]()",
           "((()))[]",
           "([])[](",
           "([{)]}",
           "[",
           "[][][]{{{}}}"
       };
 
       for (String s : casos) {
           boolean balanceado = ejercicio4.Application.symbolBalancing(s);
           System.out.println("Cadena: " + s + " => " + balanceado);
       }
   }
}

