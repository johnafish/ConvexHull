package convexhull3d;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to create the convex hull of a cloud of points.
 * @author John
 */
public class HullFinder {
    Vector3f[] cloud;
    public HullFinder(Vector3f[] data){
        this.cloud = data;
    }
    int index(Vector3f p){
        for (int i = 0; i < cloud.length; i++) {
            if (p==cloud[i]){
                return i;
            };
        }
        return -1;
    }
    /**
     * Find the y-most edge in the convex hull by projecting the points onto
     * the XY plane and finding a pair on the hull with the y extreme as a 
     * starting point.
     * @return the y-most edge in the convex hull
     */
    public Edge search2D(){
        double minY = Double.POSITIVE_INFINITY;
        Vector3f startingPoint = cloud[0];
        Vector3f endPoint = cloud[0];
        for (int i = 0; i < cloud.length; i++) {
            if (cloud[i].y<minY){
                minY = cloud[i].y;
                startingPoint = cloud[i];
            }
        }
        for (int j = 0; j < cloud.length; j++) {
            Edge testEdge = new Edge(startingPoint, endPoint);
            if(testEdge.left(cloud[j])){
                endPoint = cloud[j];
            }
        }
        return new Edge(startingPoint, endPoint);
    }
    /**
     * Searches for the point on the convex hull in 3D
     * @param e some edge on the hull
     * @return a point on the hull
     */
    public Vector3f search(Edge e){
        int i;
        for (i = 0; cloud[i]==e.a || cloud[i]==e.b; i++) {}
        Vector3f candidate = cloud[i];
        Triangle candh = new Triangle(e.a,e.b,candidate);
        for (i++; i < cloud.length; i++) {
            if (cloud[i]!=e.a && cloud[i]!=e.b && !candh.outside(cloud[i])){
                candidate = cloud[i];
                candh = new Triangle(e.a,e.b,candidate);
            }
        }
        return candidate;
    }
    /**
     * Applies the Jarvis Wrap algorithm, summarized as:
     * (1)  Find the y-most edge in the hull
     * (2)  Find a point such that all other points lay to the left of the
     *      triangle formed with this point and the initial edge.
     * (3)  Continue with different edges in the stack until there are 
     *      no more edges to check.
     * @return a List<Triangle> of the faces on the hull.
     */
    public List<Triangle> JarvisWrap(){
        EdgeStack es = new EdgeStack();
        Edge e = search2D();
        es.put(e);
        es.put(new Edge(e.b, e.a));
        List<Triangle> faces = new ArrayList<Triangle>();
        while(!es.isEmpty()) {
            e = es.get();
            Vector3f cand = search(e);
            faces.add(new Triangle(e.a, e.b, cand));
            es.putp(new Edge(e.a, cand));
            es.putp(new Edge(cand, e.b));
        }
        return faces;
    }
}
