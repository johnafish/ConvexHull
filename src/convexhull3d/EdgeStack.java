package convexhull3d;

import java.util.Stack;

/**
 * A typical stack with symmetric equality edge AB==BA.
 * @author John
 */
public class EdgeStack {
    public Stack data;
    public EdgeStack(){
        data = new Stack();
    }
    public Edge get() {
        return (Edge) data.pop();
    }
    public void put(Edge e){
        data.push(e);
    }
    /**
     * Add an edge to the stack if it doesn't already exist
     * If it does exist, remove the other instance.
     * @param e the edge to be checked and potentially added/removed.
     */
    public void putp (Edge e){
        int index = data.indexOf(e);
        if(index==-1){
            data.push(e);
        } else {
            data.removeElementAt(index);
        }
    }
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
