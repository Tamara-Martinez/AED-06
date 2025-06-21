package btree; 

 

import java.util.ArrayList; 

 

public class BNode<E extends Comparable<E>> { 

    private static int idCounter = 1; 

 

    public final int idNode; 

    public ArrayList<E> keys; 

    public ArrayList<BNode<E>> childs; 

    public int count; 

    public BNode<E> parent; 

 

    public BNode(int order) { 

        this.idNode = idCounter++; 

        this.count = 0; 

        this.keys = new ArrayList<>(); 

        this.childs = new ArrayList<>(); 

        this.parent = null; 

 

        for (int i = 0; i < order; i++) { 

            keys.add(null); 

        } 

 

        for (int i = 0; i < order + 1; i++) { 

            childs.add(null); 

        } 

    } 

 

    public boolean nodeFull(int maxKeys) { 

        return count == maxKeys; 

    } 

 

    public boolean nodeEmpty() { 

        return count == 0; 

    } 

 

    public boolean searchNode(E key, int[] pos) { 

        pos[0] = 0; 

        while (pos[0] < count && key.compareTo(keys.get(pos[0])) > 0) { 

            pos[0]++; 

        } 

        return pos[0] < count && key.compareTo(keys.get(pos[0])) == 0; 

    } 

 

    @Override 

    public String toString() { 

        StringBuilder sb = new StringBuilder(); 

        sb.append("("); 

        for (int i = 0; i < count; i++) { 

            sb.append(keys.get(i)); 

            if (i < count - 1) { 

                sb.append(", "); 

            } 

        } 

        sb.append(")"); 

        return sb.toString(); 

    } 

} 