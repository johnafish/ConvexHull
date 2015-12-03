package convexhull3d;

import com.jme3.math.Vector3f;

/**
 * Creates an edge between two vectors.
 * @author John
 */
public class Edge {
    Vector3f a,b;
    public Edge(Vector3f c, Vector3f d){
        this.a = c;
        this.b = d;
    }
    /**
     * Determines if other point is to the left of an edge in 2 space
     * Uses a similar fact to the Triangle class, the area of the triangle
     * formed by the edge and the point will be positive if the point is to 
     * the left of the edge, negatively otherwise.
     * 
     * We can do the same as in the Triangle, but this time taking the 3x3
     * determinant of the x, y coordinates and the bottom row of 1s.
     * 
     * @param other some Vector3f to be determined whether left or not
     * @return boolean whether point is left of edge.
     */
    public boolean left(Vector3f other){
        double[][] m = {{this.a.x, this.b.x, other.x},
                        {this.a.y, this.b.y, other.y},
                        {1,1,1}};
        if(DeterminantTools.get3x3Determinant(m)<0){
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * This customization of equals because AB==BA in our case,
     * so if the components are switched then we must return true as well
     * @param o the object to be tested if is equal to
     * @return whether or not the object is equal
     */
    @Override
    public boolean equals(Object o) {
    if (o instanceof Edge) {
      Edge e = (Edge) o;
      return (a == e.a && b == e.b) || (a == e.b && b == e.a) ;
    } else {
      return false;
    }
  }
    @Override
    public String toString(){
        String s = "Edge between ("+this.a.x+","+this.a.y+","+this.a.z+") and ("+this.b.x+","+this.b.y+","+this.b.z+").";
        return s;
    }
}
