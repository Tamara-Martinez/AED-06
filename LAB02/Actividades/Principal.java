package Cm.bolsa;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); 

        System.out.print("Ingrese el límite de chocolatinas en la bolsa: ");
        int limiteChocolatinas = scanner.nextInt();
        scanner.nextLine();
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(limiteChocolatinas);

        for (int i = 0; i < limiteChocolatinas; i++) {
            System.out.print("Ingrese la marca de la chocolatina #" + (i + 1) + ": ");
            String marca = scanner.nextLine();
            Chocolatina chocolatina = new Chocolatina(marca);
            try {
                bolsaCho.add(chocolatina);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        System.out.print("\nIngrese el límite de golosinas en la bolsa: ");
        int limiteGolosinas = scanner.nextInt();
        scanner.nextLine();
        Bolsa<Golosina> bolsaGol = new Bolsa<>(limiteGolosinas);

        for (int i = 0; i < limiteGolosinas; i++) {
            System.out.print("Ingrese el nombre de la golosina #" + (i + 1) + ": ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el peso de la golosina #" + (i + 1) + ": ");
            while (!scanner.hasNextDouble()) {  
                System.out.println("Entrada inválida. Ingrese un número válido.");
                scanner.next();  
            }
            double peso = scanner.nextDouble();
            scanner.nextLine(); 

            Golosina golosina = new Golosina(nombre, peso);
            try {
                bolsaGol.add(golosina);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        System.out.println("\nChocolatinas en la bolsa:");
        for (Chocolatina chocolatina : bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }

        System.out.println("\nGolosinas en la bolsa:");
        for (Golosina golosina : bolsaGol) {
            System.out.println("Nombre: " + golosina.getNombre() + ", Peso: " + golosina.getPeso() + "g");
        }

        scanner.close();
    }
}
