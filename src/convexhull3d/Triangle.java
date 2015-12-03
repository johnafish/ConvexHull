/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull3d;

import com.jme3.math.Vector3f;

/**
 * @author John
 */
public class Triangle {
    Vector3f a,b,c;
    public Triangle(Vector3f au, Vector3f bu, Vector3f cu){
        this.a = au;
        this.b = bu;
        this.c = cu;
    }
    /**
     * Calculate whether a point is outside the triangle or not
     * 
     * Uses the fact that if the point is outside the triangle then the volume
     * of the polyhedron formed will be positive; otherwise it will be negative.
     * 
     * To calculate the volume, we must simply take the determinant of the x,y,z
     * components with a bottom row of 1s.
     * @param other
     * @return boolean whether a point is outside a triangle.
     */
    public boolean outside(Vector3f other){
        double[][] m = {{this.a.x, this.b.x, this.c.x, other.x},
                        {this.a.y, this.b.y, this.c.y, other.y},
                        {this.a.z, this.b.z, this.c.z, other.z},
                        {1,1,1,1}};
        if(DeterminantTools.get4x4Determinant(m)>0){
            return true;
        } else {
            return false;
        }
    }
}
