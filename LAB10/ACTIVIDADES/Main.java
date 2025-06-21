package prueba; 

import btree.BTree; 

import Exceptions.ItemDuplicated; 



public class Main { 

    public static void main(String[] args) { 

        try { 

            BTree<Integer> arbol = new BTree<>(5);  

            int[] valores = {100, 50, 20, 70, 10, 30, 80, 90, 200, 25, 15, 5, 65, 35, 60, 18, 93,94 }; 

            for (int v : valores) { 

                arbol.insert(v); 

            } 

            System.out.println(arbol); 

        } catch (ItemDuplicated e) { 

            System.err.println(e.getMessage()); 

        } 

    } 

} 