package convexhull3d;

import com.jme3.math.Vector3f;
import java.util.Random;

/**
 * @author John
 * TODO: Add other types of clouds (Sphere, etc.)
 */
public class PointCloud {
    /**
     * Generate a cloud of Vector3fs.
     * @param size the number of points wished
     * @param radius half the side length of the cube for which the points must
     * fall into
     * @return Vector3f[] of length size filled with random Vector3fs.
     */
    public static Vector3f[] getCubeCloud(int size, int radius){
        Random rand = new Random();
        Vector3f[] points = new Vector3f[size];
        for (int i = 0; i < size; i++) {
            float x = rand.nextFloat()*radius-radius/2;
            float y = rand.nextFloat()*radius-radius/2;
            float z = rand.nextFloat()*radius-radius/2;
            points[i]=new Vector3f(x,y,z);
        }
        return points;
    }
}
