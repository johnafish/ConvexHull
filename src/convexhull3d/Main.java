package convexhull3d;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import java.util.List;

/**
 * 3D Convex Hull
 * @author johnafish
 */
public class Main extends SimpleApplication {
    //Starts our app
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    //Runs once at the start and never again
    @Override
    public void simpleInitApp() {
        //Material and colour initialization
        Material pointMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Material lineMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Material hullMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        pointMat.setColor("Color", ColorRGBA.White);
        lineMat.setColor("Color", ColorRGBA.Red);
        hullMat.setColor("Color", ColorRGBA.Green);
        
        //Generate our cloud and draw it to the screen
        Vector3f[] cloud = PointCloud.getCubeCloud(50, 5);
        for(int i=0; i<cloud.length; i++){
            Box box = new Box(cloud[i], .01f,.01f,.01f);
            Geometry pointGeo = new Geometry("Box", box);
            pointGeo.setMaterial(pointMat);
            rootNode.attachChild(pointGeo);
        }
        
        //Generate our hull and draw it to the screen
        //TODO: Make this more elegant by not hardcoding all three edges
        HullFinder finder = new HullFinder(cloud);
        List<Triangle> hullTriangles = finder.JarvisWrap();
        for (Triangle t : hullTriangles) {
            Line jarvisPair = new Line(t.a, t.b);
            Line jarvisPair2 = new Line(t.b, t.c);
            Line jarvisPair3 = new Line(t.a, t.c);
            jarvisPair.setLineWidth(2);
            jarvisPair2.setLineWidth(2);
            jarvisPair3.setLineWidth(2);
            Geometry jarvisGeo = new Geometry("Bullet", jarvisPair);
            Geometry jarvisGeo2 = new Geometry("Bullet", jarvisPair2);
            Geometry jarvisGeo3 = new Geometry("Bullet", jarvisPair3);
            jarvisGeo.setMaterial(hullMat);
            jarvisGeo2.setMaterial(hullMat);
            jarvisGeo3.setMaterial(hullMat);
            rootNode.attachChild(jarvisGeo);
            rootNode.attachChild(jarvisGeo2);
            rootNode.attachChild(jarvisGeo3);
        }
    }
}
