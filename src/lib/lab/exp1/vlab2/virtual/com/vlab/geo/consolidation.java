package com.vlab.geo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Formatter;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.TransparencyAttributes;

import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;

import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.util.*;

import org.omg.CORBA.Bounds;

import eerc.vlab.common.FullViewGraph;
import eerc.vlab.common.HelpWindow;
import eerc.vlab.common.HorizontalGraph;
import eerc.vlab.common.HorizontalGraphWrapper;
import eerc.vlab.common.ImagePanel;
import eerc.vlab.common.J3DShape;
import eerc.vlab.common.Resources;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class consolidation extends javax.swing.JPanel {
	//  Variables declaration - do not modify//GEN-BEGIN:variables
	//////////////////////////GUI componenet ///////////////////////////
	private javax.swing.JPanel topPanel;
	private javax.swing.JPanel simulationPanel;
	private javax.swing.JPanel bottomPanel;
	private javax.swing.JPanel rightPanel;
	  
	private javax.swing.JPanel in1;			// Input panel 1
	private javax.swing.JPanel in2;			// Input panel 2
	private javax.swing.JPanel in3;			// Input panel 3
	
	
//	private javax.swing.JPanel rp1;			// Right Input panel 1
//	private javax.swing.JPanel rp2;			// Right Input panel 2
//	private javax.swing.JPanel ImagePanel;
	
	private javax.swing.JButton startButton=null;
	private javax.swing.JButton reStartButton=null;
	private javax.swing.JButton nextButton=null;
	
	
	private String BOS;
	private JSlider m_Slider[] = new JSlider[4];
	
	private javax.swing.JButton rightIcon=null;

	//private GraphPlotter         graphPlotter;
	////////////////////////////Java3D componenet ///////////////////////////

	private SimpleUniverse      univ = null;                  // Simple Universe Java3D
	private BranchGroup         scene = null;                 // BranchGroup Scene graph
	TransformGroup objSwitchPos = new TransformGroup(new Transform3D());
	
	private Switch objSwitch = new Switch();
	Appearance appea = new Appearance();
	
	private SpecificGravityBody       freeBody =null;               // Shape3D
	private HorizontalGraph		outputGraph =null;
	private HorizontalGraph		outputGraph2 =null;
	private FullViewGraph  		fullViewGraph = new FullViewGraph();
	

	@SuppressWarnings("unchecked")
	private HashMap 			hm = new HashMap();
	private eerc.vlab.common.J3DShape 			m_j3d	= new J3DShape();

	private float fields[];
	private JLabel outlbl_val[];
	private JLabel iLabel[];
	private JLabel m_Objective= new JLabel("Objective:");
	
	private Timer timer=null;
	private Timer m_cameraTimer=null; 
	private float m_cameraViews[];
	private int m_cameraEye;
	// Timer for simulation    
	
	private int stage = 0;	
	private int cnt = 0;
	private int ct=0;
	private int mycount=0;
	private boolean startStop = false;
	private boolean valChange = true;	
	BranchGroup objRoot = new BranchGroup();
	
	public BranchGroup createSceneGraph() 
	{
		// Create the root of the branch graph
		
		objRoot.setCapability(Group.ALLOW_CHILDREN_EXTEND );
		objRoot.setCapability(Group.ALLOW_CHILDREN_READ);
		objRoot.setCapability(Group.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability( BranchGroup.ALLOW_DETACH );
		
		
		
		objRoot.addChild(drawVirtualLab());
		
//		 Floor
		int i,j;
		for(i=-4;i<=4;i++)
		{
			for(j=-4;j<=4;j++)
			{
				objRoot.addChild(m_j3d.createBox(new Vector3d((float)(i),-0.6,(float)(j)),new Vector3d(0.5,.01,0.5),new Vector3d(0,0,0),new Color3f(.8f, .8f, .8f),"resources/images/tile.jpg"));
			}
		}
	//	objRoot.addChild(m_j3d.createBox(new Vector3d(0,-0.6, -.1),new Vector3d(1.5,.01,1.5),new Vector3d(0,0,0),new Color3f(.8f, .8f, .8f),"resources/images/tile.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.4,-2.5),new Vector3d(10,10,.01),new Vector3d(0f, 0f,0f), new Color3f(0.5f,0.6f,0.72f)));
		// Walls and roof
		objRoot.addChild(m_j3d.createBox(new Vector3d(1,0.1f,0), new Vector3d(0.05,0.7f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/floor.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(-1,0.1f,0), new Vector3d(0.05,0.7f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/floor.jpg"));
		//objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.1f,-2.0), new Vector3d(1,0.7f,0.05),new Vector3d(0,0,0),m_j3d.getColor3f(105, 105, 105)));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.1f,-2.0), new Vector3d(1,0.7f,0.05),new Vector3d(0,0,0),new Color3f(1f, 1f, 1f),"resources/images/tile69.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.84f,0), new Vector3d(1.05,0.04f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 1f),"resources/images/floor.jpg"));
		
		
		float rad = (float)Math.PI/180;
		Transform3D t = new Transform3D();
		t.setScale(0.4);
		t.invert();
		TransformGroup tg = new TransformGroup();
		t = new Transform3D();
		t.rotX(rad*10);
		t.setScale(new Vector3d(.5f,.05f,.5f));        
		t.setTranslation(new Vector3d(.3,.3,0));
		tg.setTransform(t);
		freeBody = new SpecificGravityBody();    
		Color3f light1Color = new Color3f(1f, 1f, 1f);
		
	    BoundingSphere bounds =  new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	
	    Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
	
	    DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
	
	    light1.setInfluencingBounds(bounds);    
	    objRoot.addChild(light1);
	
	    AmbientLight ambientLight =  new AmbientLight(new Color3f(.3f,.3f,.3f));
	
	    ambientLight.setInfluencingBounds(bounds);
	
	    objRoot.addChild(ambientLight);
		return objRoot;
		
	}

    private Canvas3D createUniverse(Container container) {
        GraphicsDevice graphicsDevice;
        if (container.getGraphicsConfiguration() != null) {
            graphicsDevice = container.getGraphicsConfiguration().getDevice();
        } else {
            graphicsDevice =
                    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        }
        GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
        GraphicsConfiguration config = graphicsDevice.getBestConfiguration(template);

        Canvas3D c = new Canvas3D(config);

        univ = new SimpleUniverse(c);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        
        //ViewingPlatform viewingPlatform = univ.getViewingPlatform();
        setLight();
        
        univ.getViewingPlatform().setNominalViewingTransform();

        
        ViewingPlatform vp = univ.getViewingPlatform();
   	    TransformGroup steerTG = vp.getViewPlatformTransform();
   	    Transform3D t3d = new Transform3D();
   	    steerTG.getTransform(t3d);
   	    Vector3d s = new Vector3d();
   	    Vector3f currPos=new Vector3f();
   	    t3d.get(currPos); 
   	    
   	   // System.out.println("current Pos:" + currPos);
   	  
   	    
   	    t3d.lookAt( new Point3d(0, .2, 4 ), new Point3d(0,0,0), new Vector3d(0,1,0));
   	    t3d.invert();
   	    
   	    //t3d.setTranslation(new Vector3d(0,0,8));
   	    steerTG.setTransform(t3d);
        
        
        // Ensure at least 5 msec per frame (i.e., < 200Hz)
        univ.getViewer().getView().setMinimumFrameCycleTime(5);

        return c;
    }
    
    private void setLight() {
            BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
            PlatformGeometry pg = new PlatformGeometry();


            Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
            AmbientLight ambientLightNode = new AmbientLight(ambientColor);
            ambientLightNode.setInfluencingBounds(bounds);
            pg.addChild(ambientLightNode);


            Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
            Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
            Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
            Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);

            DirectionalLight light1
                    = new DirectionalLight(light1Color, light1Direction);
            light1.setInfluencingBounds(bounds);
            pg.addChild(light1);

            DirectionalLight light2
                    = new DirectionalLight(light2Color, light2Direction);
            light2.setInfluencingBounds(bounds);
            pg.addChild(light2);

            ViewingPlatform viewingPlatform = univ.getViewingPlatform();
            viewingPlatform.setPlatformGeometry( pg );


    }

    
    private void destroy() {
        univ.cleanup();
    }

    
    public Group createBox(Vector3d pos,Vector3d scale,Vector3d rot,Color3f colr) {
        // Create a transform group node to scale and position the object.
    	//new Point3d(0.0, 0.0, 0.0)
        Transform3D t = new Transform3D();
        float rad = (float)Math.PI/180;
		if(rot.x != 0)
			t.rotX(rad*rot.x);
		else if(rot.y != 0)
			t.rotY(rad*rot.y);
		else if(rot.z != 0)
			t.rotZ(rad*rot.z);
        t.setScale(scale);        
        t.setTranslation(pos);
        
        TransformGroup objtrans = new TransformGroup(t);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        // Create a simple shape leaf node and add it to the scene graph
        //Shape3D shape = new Box(1.0, 1.0, 1.0);       

        // Create a new ColoringAttributes object for the shape's
        // appearance and make it writable at runtime.
        Appearance app = new Appearance();
        TransparencyAttributes ta = new TransparencyAttributes();
        ta.setTransparencyMode (TransparencyAttributes.BLENDED);
        ta.setTransparency (0.7f);
        app.setTransparencyAttributes (ta);
        
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(colr);
        app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        app.setColoringAttributes(ca);
        
        objtrans.addChild(new Box(4.0f, 4.0f, 4.0f,app));
        
        return objtrans;
    }     
    
    
    public Group createWireBox(Vector3d pos,Vector3d scale,Vector3d rot,Color3f colr) {
        // Create a transform group node to scale and position the object.
    	//new Point3d(0.0, 0.0, 0.0)
        Transform3D t = new Transform3D();
        float rad = (float)Math.PI/180;
		if(rot.x != 0)
			t.rotX(rad*rot.x);
		else if(rot.y != 0)
			t.rotY(rad*rot.y);
		else if(rot.z != 0)
			t.rotZ(rad*rot.z);
        t.setScale(scale);        
        t.setTranslation(pos);
        
        TransformGroup objtrans = new TransformGroup(t);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        // Create a simple shape leaf node and add it to the scene graph
        //Shape3D shape = new Box(1.0, 1.0, 1.0);       

        // Create a new ColoringAttributes object for the shape's
        // appearance and make it writable at runtime.
        Appearance app = new Appearance();
        
        
        PolygonAttributes polyAttr = new PolygonAttributes();
        polyAttr.setPolygonMode(PolygonAttributes.POLYGON_LINE);
       // polyAttr.setPolygonMode(PolygonAttributes.POLYGON_POINT);
        polyAttr.setCullFace(PolygonAttributes.CULL_NONE);
        app.setPolygonAttributes(polyAttr);
        
        
        /*LineAttributes LineAttr = new LineAttributes();
        LineAttr.setLinePattern(LineAttributes.PATTERN_SOLID);
       // polyAttr.setPolygonMode(PolygonAttributes.POLYGON_POINT);
        //polyAttr.setCullFace(PolygonAttributes.CULL_NONE);
        app.setLineAttributes(LineAttr);*/
        
        //ta.setTransparencyMode (TransparencyAttributes.BLENDED);
        //ta.setTransparency (0.7f);
        //app.setTransparencyAttributes (ta);
        
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(colr);
        app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        app.setColoringAttributes(ca);
        
        objtrans.addChild(new Box(1.0f, 1.0f, 1.0f,app));
        
        return objtrans;
    }
    
    public Geometry generate(double radius, int steps) 
	{
		
		int total=steps+1;
		int stripCounts[]={total};
		LineStripArray rect=new LineStripArray(total,LineStripArray.COORDINATES|LineStripArray.COLOR_3,stripCounts);
		
		Color3f red = m_j3d.getColor3f(0,0,205);
		float ang= (float) (Math.PI*2 / steps);
		Point3f coords[]=new Point3f[total];
		Color3f colors[] = new Color3f[total];
		
	
		
		float x,z;
		double r=radius;
	
		float y=0;
		int i;
		for(i=0;i<total;i++)
		{
			ang=(float)((Math.PI*2/steps)*i);
			x=(float) (r * Math.sin(ang));
			z=(float) (r * Math.cos(ang));
			coords[i]=new Point3f(x, y, z);
			
			colors[i]=red;
			
		}
		rect.setCoordinates(0, coords);
		rect.setColors(0, colors);
		return rect;
	}
    
    
    
    public Group createCylinder(Vector3d pos,Vector3d scale,Vector3d rot,Color3f colr) {
	       // Create a transform group node to scale and position the object.
	   	//new Point3d(0.0, 0.0, 0.0)
		   Transform3D t = new Transform3D();
	       float rad = (float)Math.PI/180;
			if(rot.x != 0)
				t.rotX(rad*rot.x);
			else if(rot.y != 0)
				t.rotY(rad*rot.y); 
			else if(rot.z != 0)
				t.rotZ(rad*rot.z);
	       t.setScale(scale);        
	       t.setTranslation(pos);
	       
	       TransformGroup objtrans = new TransformGroup(t);
	       objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	       objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		    
	       // Create a simple shape leaf node and add it to the scene graph
	       //Shape3D shape = new Box(1.0, 1.0, 1.0);       

	       // Create a new ColoringAttributes object for the shape's
	       // appearance and make it writable at runtime.
	       
	       Appearance app = new Appearance();
	        TransparencyAttributes ta = new TransparencyAttributes();
	        ta.setTransparencyMode (TransparencyAttributes.BLENDED);
	        ta.setTransparency (0.6f);
	        app.setTransparencyAttributes (ta);
	       
	       
	//       Appearance app = new Appearance();
	       ColoringAttributes ca = new ColoringAttributes();
	       ca.setColor(colr);
	       app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
	       app.setColoringAttributes(ca);
	       objtrans.addChild(new Cylinder(0.1f,0.1f,app));
	      
	       
	       return objtrans;
	   }
    
    public BranchGroup verticalRod(float[] knots,Quat4f[] quats,Point3f[] positions ,float y)
    {
    	System.out.println("Inside vertical rod");
		RotPosPathInterpolator rotPosPath;
		float movement = 100000*0.01f;
		int temps  = (int)(movement);
		BranchGroup objRoots = new BranchGroup();                      // Create the root of the branch graph
		Alpha alpha = new Alpha(1, 6000000);                           // alpha is for moving the point froddm initial to final position
		//    ( 1 is the no of times it is moved and 100000 is the time   
		TransformGroup target = new TransformGroup();                 // target is the thing which moves according to alpha
		Transform3D axisOfRotPos = new Transform3D();           
		target.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		AxisAngle4f axis = new AxisAngle4f(0.0f,0.0f,0.0f,0.0f);      // axis tells about angle of rotation about translation axis
		axisOfRotPos.set(axis);
		rotPosPath = new RotPosPathInterpolator           
			(alpha, target, axisOfRotPos, knots, quats, positions);  //rotPosPath tells about rotation and translation
		rotPosPath.setSchedulingBounds(new BoundingSphere());
  //      target.addChild(m_j3d.createCylinder(new Vector3d(0,0.35,0.5), new Vector3d(0.15,1,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
      target.addChild(m_j3d.createCylinder(new Vector3d(0,0.15,0), new Vector3d(0.15,3,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));

		objRoots.addChild(target);
		objRoots.addChild(rotPosPath);
		return objRoots;
}
    
    public BranchGroup soilSample(float[] knots,Quat4f[] quats,Point3f[] positions ,float y)
    {
    	System.out.println("Inside soilsample");
		RotPosPathInterpolator rotPosPath;
		float movement = 100000*0.01f;
		int temps  = (int)(movement);
		BranchGroup objRoots = new BranchGroup();                      // Create the root of the branch graph
		Alpha alpha = new Alpha(1, 20000);                           // alpha is for moving the point froddm initial to final position
		//    ( 1 is the no of times it is moved and 100000 is the time   
		TransformGroup target = new TransformGroup();                 // target is the thing which moves according to alpha
		Transform3D axisOfRotPos = new Transform3D();           
		target.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		AxisAngle4f axis = new AxisAngle4f(0.0f,0.0f,0.0f,0.0f);      // axis tells about angle of rotation about translation axis
		axisOfRotPos.set(axis);
		rotPosPath = new RotPosPathInterpolator           
			(alpha, target, axisOfRotPos, knots, quats, positions);  //rotPosPath tells about rotation and translation
		rotPosPath.setSchedulingBounds(new BoundingSphere());
		target.addChild(m_j3d.createCylinder(new Vector3d(0.5,0,0.5), new Vector3d(0.6,0.4,0.6),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
		target.addChild(m_j3d.createCylinder(new Vector3d(0.5,0,0.5), new Vector3d(0.4,0.4,0.4),new Vector3d(0,0,0), new Color3f((float)255/255,(float)0/255,(float)0/255)));
		objRoots.addChild(target);
		objRoots.addChild(rotPosPath);
		return objRoots;
}
    public BranchGroup moveup(float[] knots,Quat4f[] quats,Point3f[] positions ,float y)
    {
    	System.out.println("Inside soilsample");
		RotPosPathInterpolator rotPosPath;
		float movement = 100000*0.01f;
		int temps  = (int)(movement);
		BranchGroup objRoots = new BranchGroup();                      // Create the root of the branch graph
		Alpha alpha = new Alpha(1, 25000);                           // alpha is for moving the point froddm initial to final position
		//    ( 1 is the no of times it is moved and 100000 is the time   
		TransformGroup target = new TransformGroup();                 // target is the thing which moves according to alpha
		Transform3D axisOfRotPos = new Transform3D();           
		target.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		AxisAngle4f axis = new AxisAngle4f(0.0f,0.0f,0.0f,0.0f);      // axis tells about angle of rotation about translation axis
		axisOfRotPos.set(axis);
		rotPosPath = new RotPosPathInterpolator           
			(alpha, target, axisOfRotPos, knots, quats, positions);  //rotPosPath tells about rotation and translation
		rotPosPath.setSchedulingBounds(new BoundingSphere());
		 target.addChild(m_j3d.createCylinder(new Vector3d(0.0,0.3,0.5), new Vector3d(0.8,2.5,0.8), new Vector3d(90,0,0), new Color3f((float)152/255,(float)251/255,(float)152/255)));
     //    target.addChild(m_j3d.createBox(new Vector3d(0,-0.3,0),new Vector3d(.28,0.005,.28), new Vector3d(0,0,0), new Color3f(0,0,0)));
         target.addChild(m_j3d.createBox(new Vector3d(0,0.1,0.5),new Vector3d(.2,0.03,.2), new Vector3d(0,0,0), new Color3f((float)49/255,(float)79/255,(float)79/255)));
         target.addChild(m_j3d.createCylinder(new Vector3d(0,0.1,0.5), new Vector3d(0.15,4,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
		
		objRoots.addChild(target);
		objRoots.addChild(rotPosPath);
		return objRoots;
}
    
    private Group drawVirtualLab()
    {
    	 Transform3D t = new Transform3D();
         BranchGroup objroot = new BranchGroup();
         
 	    TransformGroup objtrans = new TransformGroup(t);
 	    objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
 	    objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	    
 	    
 	   TransformGroup objtest = new TransformGroup();
 	   objtest.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
 	   objtest.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);         

  	   TransformGroup apparatus = new TransformGroup();
  	   apparatus.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  	   apparatus.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         Transform3D transform = new Transform3D();
         Vector3f vector = new Vector3f( 0.0f, 0.15f, 0.5f);
         transform.setTranslation(vector);
         apparatus.setTransform(transform);

          objroot.addChild(apparatus);                   
          objroot.addChild(objtrans);
          objroot.addChild(objtest);
         
         
  	   TransformGroup objtable = new TransformGroup();
  	   objtable.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  	   objtable.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         objtable.addChild(m_j3d.createBox(new Vector3d(0.0f,-0.18f,0.4f),new Vector3d(0.4f,0.02f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/wood2.jpg"));
         objtable.addChild(m_j3d.createCylinder(new Vector3d(0.35,-0.35f,0.05),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f)));
         objtable.addChild(m_j3d.createCylinder(new Vector3d(-0.35,-0.35f,0.05),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f)));
         objtable.addChild(m_j3d.createCylinder(new Vector3d(-0.35,-0.35f,0.75),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f)));
         objtable.addChild(m_j3d.createCylinder(new Vector3d(0.35,-0.35f,0.75),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f)));
         
         objroot.addChild(objtable);
         
    	if(mycount == 1)
    	{
        	System.out.println("Inside count "+mycount);

    	  
    		
    		Vector3d pos_beaker=new Vector3d(0,-0.15,0);
            Vector3d scale_beaker=new Vector3d(1.6,2.5,1.6);
            Vector3d rot_beaker=new Vector3d(0,0,0);
            Color3f colr_beaker=new Color3f(1,(float)239/255,(float)213/255);
  		   Transform3D t_beaker = new Transform3D();
  	       float rad_beaker = (float)Math.PI/180;
  			if(rot_beaker.x != 0)
  				t_beaker.rotX(rad_beaker*rot_beaker.x);
  			else if(rot_beaker.y != 0)
  				t_beaker.rotY(rad_beaker*rot_beaker.y); 
  			else if(rot_beaker.z != 0)
  				t_beaker.rotZ(rad_beaker*rot_beaker.z);
  	       t_beaker.setScale(scale_beaker);        
  	       t_beaker.setTranslation(pos_beaker);
  	       
  	       TransformGroup objBeaker = new TransformGroup(t_beaker);
  	       objBeaker.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  	       objBeaker.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
  		    
  	       // Create a simple shape leaf node and add it to the scene graph
  	       //Shape3D shape = new Box(1.0, 1.0, 1.0);       

  	       // Create a new ColoringAttributes object for the shape's
  	       // appearance and make it writable at runtime.
  	       
  	       Appearance app = new Appearance();
  	       TransparencyAttributes t_attr =

  	    		   new TransparencyAttributes(TransparencyAttributes.BLENDED,0.5f,

  	    		TransparencyAttributes.BLEND_SRC_ALPHA,

  	    		TransparencyAttributes.BLEND_ONE);
  	        app.setTransparencyAttributes (t_attr);
  	       
  	       
  	//       Appearance app = new Appearance();
  	       ColoringAttributes ca = new ColoringAttributes();
  	       ca.setColor(colr_beaker);
  	       app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
  	       app.setColoringAttributes(ca);
  	       objBeaker.addChild(new Cylinder(0.1f,0.1f,app));
  	       apparatus.addChild(objBeaker);
  
         apparatus.addChild(m_j3d.createCylinder(new Vector3d(-0.18,-0.03,0), new Vector3d(0.15,5,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0.18,-0.03,0), new Vector3d(0.15,5,0.15), new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
//         apparatus.addChild(m_j3d.createTransparentCylinder(new Vector3d(0.0,-0.18,0), new Vector3d(1.6,2.5,1.6), new Vector3d(0,0,0), new Color3f((float)1,(float)239/255,(float)213/255)));
//         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0.0,0.3,0), new Vector3d(0.8,2.5,0.8), new Vector3d(90,0,0), new Color3f((float)152/255,(float)251/255,(float)152/255)));
         apparatus.addChild(m_j3d.createBox(new Vector3d(0,-0.3,0),new Vector3d(.28,0.005,.28), new Vector3d(0,0,0), new Color3f(0,0,0)));
//         apparatus.addChild(m_j3d.createBox(new Vector3d(0,0.06,0),new Vector3d(.2,0.03,.2), new Vector3d(0,0,0), new Color3f((float)49/255,(float)79/255,(float)79/255)));
//         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0,0.13,0), new Vector3d(0.15,2.5,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));

         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0,-0.21,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));

    	}
    	
  
    	
    	else if(mycount == 2)
    	{
    		System.out.println("Inside count "+mycount);
  /*  		
    		Vector3d pos_beaker=new Vector3d(0,-0.18,0);
            Vector3d scale_beaker=new Vector3d(1.6,2.5,1.6);
            Vector3d rot_beaker=new Vector3d(0,0,0);
            Color3f colr_beaker=new Color3f(1,(float)239/255,(float)213/255);
  		   Transform3D t_beaker = new Transform3D();
  	       float rad_beaker = (float)Math.PI/180;
  			if(rot_beaker.x != 0)
  				t_beaker.rotX(rad_beaker*rot_beaker.x);
  			else if(rot_beaker.y != 0)
  				t_beaker.rotY(rad_beaker*rot_beaker.y); 
  			else if(rot_beaker.z != 0)
  				t_beaker.rotZ(rad_beaker*rot_beaker.z);
  	       t_beaker.setScale(scale_beaker);        
  	       t_beaker.setTranslation(pos_beaker);
  	       
  	       TransformGroup objBeaker = new TransformGroup(t_beaker);
  	       objBeaker.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  	       objBeaker.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
  		    
  	       // Create a simple shape leaf node and add it to the scene graph
  	       //Shape3D shape = new Box(1.0, 1.0, 1.0);       

  	       // Create a new ColoringAttributes object for the shape's
  	       // appearance and make it writable at runtime.
  	       
  	       Appearance app = new Appearance();
  	       TransparencyAttributes t_attr = new TransparencyAttributes(TransparencyAttributes.BLENDED,0.5f,TransparencyAttributes.BLEND_SRC_ALPHA,TransparencyAttributes.BLEND_ONE);
  	        app.setTransparencyAttributes (t_attr);
  	       
  	       
  	//       Appearance app = new Appearance();
  	       ColoringAttributes ca = new ColoringAttributes();
  	       ca.setColor(colr_beaker);
  	       app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
  	       app.setColoringAttributes(ca);
  	       objBeaker.addChild(new Cylinder(0.1f,0.1f,app));
  	       apparatus.addChild(objBeaker);  
  
         apparatus.addChild(m_j3d.createCylinder(new Vector3d(-0.18,0,0), new Vector3d(0.15,6,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0.18,0,0), new Vector3d(0.15,6,0.15), new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
//         apparatus.addChild(m_j3d.createTransparentCylinder(new Vector3d(0.0,-0.18,0), new Vector3d(1.6,2.5,1.6), new Vector3d(0,0,0), new Color3f((float)1,(float)239/255,(float)213/255)));
         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0.0,0.3,0), new Vector3d(0.8,2.5,0.8), new Vector3d(90,0,0), new Color3f((float)152/255,(float)251/255,(float)152/255)));
         apparatus.addChild(m_j3d.createBox(new Vector3d(0,-0.3,0),new Vector3d(.28,0.005,.28), new Vector3d(0,0,0), new Color3f(0,0,0)));
 //        apparatus.addChild(m_j3d.createBox(new Vector3d(0,0.08,0),new Vector3d(.2,0.01,.2), new Vector3d(0,0,0), new Color3f((float)49/255,(float)79/255,(float)79/255)));
//         apparatus.addChild(m_j3d.createCylinder(new Vector3d(0,0.1,0), new Vector3d(0.15,4,0.15),new Vector3d(0,0,0), new Color3f((float)220/255,(float)220/255,(float)220/255)));
*/
    		
    		 TransformGroup sample = new TransformGroup();
    	 	   sample.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	 	   sample.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	        Transform3D transform_sample = new Transform3D();
    	        Vector3f vector_sample = new Vector3f( 0.5f, 0f, 0.5f);
    	        transform_sample.setTranslation(vector_sample);
    	        sample.setTransform(transform_sample);
    	        
    	        float[] knots = new float[4];
    			knots[0] = 0;
    			knots[1] = 0.33f;
    			knots[2] = 0.66f;
    			knots[3] = 1.0f;
    //		j	knots[2] = 1.0f;
    			Quat4f[] quats = new Quat4f[4];
    			Point3f[] positions = new Point3f[4];
    			quats[0] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
    			quats[1] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
    			quats[2] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
    			quats[3] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
  //  			quats[2] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);

    			positions[0] = new Point3f(-0.22f,-0.5f,0);
    			positions[1] = new Point3f(-0.22f,0.4f,0);
    			positions[2] = new Point3f(-0.5f,0.4f,0);
    			positions[3] = new Point3f(-0.5f,0.02f,0f);
    	//		positions[2] = new Point3f(-1.0f,0.1f,0f);
    			objtest.addChild(soilSample(knots,quats,positions,0.13f));
    			
    	/*		 float[] v_knots = new float[2];
    				v_knots[0] = 0;
    				v_knots[1] = 1.0f;
    				Quat4f[] v_quats = new Quat4f[2];
    				Point3f[] v_positions = new Point3f[2];
    				v_quats[0] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
    				v_quats[1] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);

    				v_positions[0] = new Point3f(0,0,0);
    				v_positions[1] = new Point3f(0f,-0.1f,0f);
    			objtest.addChild(verticalRod(v_knots,v_quats,v_positions,0.13f));
    			*/
    	}
    	else if(mycount == 3)
    	{
            float[] knots = new float[2];
   			knots[0] = 0;
   			knots[1] = 1.0f;
   			Quat4f[] quats = new Quat4f[2];
   			Point3f[] positions = new Point3f[2];
   			quats[0] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
   			quats[1] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);

   			positions[0] = new Point3f(0,0.4f,0);
   			positions[1] = new Point3f(0f,0.13f,0f);
   			objtest.addChild(moveup(knots,quats,positions,0.13f));

    	}

    	else if(mycount == 4)
    	{
    		float[] v_knots = new float[2];
			v_knots[0] = 0;
			v_knots[1] = 1.0f;
			Quat4f[] v_quats = new Quat4f[2];
			Point3f[] v_positions = new Point3f[2];
			v_quats[0] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);
			v_quats[1] = new Quat4f(0.0f, 0.0f, 0.0f, 0.0f);

			v_positions[0] = new Point3f(0,0,0);
			v_positions[1] = new Point3f(0f,-0.1f,0f);
		objtest.addChild(verticalRod(v_knots,v_quats,v_positions,0.13f));
    	
        apparatus.addChild(m_j3d.createCylinder(new Vector3d(0,-0.3,0), new Vector3d(1.6,0.3,1.6),new Vector3d(0,0,0), new Color3f((float)0/255,(float)255/255,(float)255/255)));
		
    	}  
    	return objroot;
    	
    }
    
  
	
	
    /**
     * Creates new form FreeVibration
     */
    public consolidation(Container container) {
        // Initialize the GUI components
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        initComponents();

        centerPanel(container);
        // Create Canvas3D and SimpleUniverse; add canvas to drawing panel
        
//        scene.addChild(bgleg);
    }

    
    // ----------------------------------------------------------------
    
    // Applet framework

    public static class MyApplet extends JApplet {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		consolidation mainPanel;

        public void init() {
            setLayout(new BorderLayout());
            mainPanel = new consolidation(this);
            add(mainPanel, BorderLayout.CENTER);
                        
        }

        public void destroy() {
            mainPanel.destroy();
        }
    }

    // Application framework

    private static class MyFrame extends JFrame {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyFrame() {
            setLayout(new BorderLayout());
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Specific Gravity of Soil Applet");
            getContentPane().add(new consolidation(this), BorderLayout.CENTER);
            pack();
        }
    }

    // Create a form with the specified labels, tooltips, and sizes.
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFrame().setVisible(true);
            }
        });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        objSwitchPos.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        appea.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        appea.setCapability(Appearance.ALLOW_LINE_ATTRIBUTES_WRITE);
        ColoringAttributes ca = new ColoringAttributes(new Color3f(0,0,0), ColoringAttributes.SHADE_FLAT);
      	appea.setColoringAttributes(ca);
//      new GridLayout(2, 1)
        setLayout(new java.awt.BorderLayout());
        
        bottomPanel = new javax.swing.JPanel(); 	// input from user at bottom
        simulationPanel = new javax.swing.JPanel(); // 3D rendering at center
        topPanel= new javax.swing.JPanel();    		// Pause, resume, Next
        rightPanel = new javax.swing.JPanel();    	// Graph and Input and Output Parameter
                
         
        topPanel();                 
        bottomPanel();        
        rightPanel();
        
//      Set Alignment
        //add(guiPanel, java.awt.BorderLayout.NORTH);
        add(topPanel, java.awt.BorderLayout.NORTH);
        add(simulationPanel, java.awt.BorderLayout.CENTER);
        add(bottomPanel, java.awt.BorderLayout.SOUTH);
        add(rightPanel, java.awt.BorderLayout.EAST); 
        
        startStop = false;
    	valChange = true;    
        
        timer = new Timer(100,new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	timerActionPerformed(evt);
            	
            }
        });
        
                            

        
    }// </editor-fold>//GEN-END:initComponents

    private void topPanel() {
            
        java.awt.GridBagConstraints gridBagConstraints;
            
        javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
        guiPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);  
                
//        javax.swing.JButton pauseButton = new javax.swing.JButton();  
//        javax.swing.JButton startButton = new javax.swing.JButton(); 
        reStartButton = new javax.swing.JButton("Re-Start");
        ImageIcon icon = m_j3d.createImageIcon("resources/icons/restart.png"); 
        reStartButton.setIcon(icon);
        startButton = new javax.swing.JButton("Start");
        icon = m_j3d.createImageIcon("resources/icons/start.png"); 
        startButton.setIcon(icon);
        nextButton = new javax.swing.JButton("Next");
        icon = m_j3d.createImageIcon("resources/icons/next.png");        
        nextButton.setIcon(icon);
//        ImageIcon icon = m_j3d.createImageIcon("resources/images/show_graph.png");        
//        startButton.setIcon(icon);
        //startButton.setPreferredSize(new Dimension(100,30));
        
             
        //reStartButton.setText("Re-Start");  
        reStartButton.setEnabled(true);
        nextButton.setEnabled(true);
        
        
        
        guiPanel.setBackground(new Color(67,143,205));//Color.BLACK
        topPanel.setLayout(new java.awt.BorderLayout());
        topPanel.add(guiPanel, java.awt.BorderLayout.NORTH);
        
        
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	// Toggle
            	stage=0;
            	startStop = !startStop;
            	
            	if(startStop)  startSimulation(evt); 
            	else pauseSimulation();
            	
                univ.getCanvas().repaint();
            }
          });
        
        
        reStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	reStartButton.setEnabled(false);
                //startButton.setEnabled(true);
                startButton.setText("Start");
                startStop = !startStop;
               // startStop = false;
             
                outputGraph.clearGraphValue();
            //    outputGraph2.clearGraphValue();
                
                valChange = true;
                startSimulation(evt);
                univ.getCanvas().repaint();
               
                
//            	reStartButton.setEnabled(false);
//                //startButton.setEnabled(true);
//                startButton.setText("Start");
//                startStop = false;
//                timer.stop();
//                outputGraph.clearGraphValue();
//                outputGraph2.clearGraphValue();
//                
//                valChange = true;
                
            }
          });
        
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {      
            	mycount++;
            	System.out.println("count is "+mycount);

            	objRoot.addChild(drawVirtualLab());
            	stage++;
            	cnt++;
            	if(mycount==4)
              	{
              		nextButton.setEnabled(false);
              		stage--;
              	}
              	else
              		nextButton.setEnabled(true);             	
          //  	onNextStage();
            	univ.getCanvas().repaint();
            }
          });
        
       
        javax.swing.JButton btn= new javax.swing.JButton("Full View Graph");
        guiPanel.add(btn, gridBagConstraints);
        icon = m_j3d.createImageIcon("resources/icons/graph_window.png");        
        btn.setIcon(icon);
        btn.addActionListener(new java.awt.event.ActionListener() {
            @SuppressWarnings("static-access")
			public void actionPerformed(java.awt.event.ActionEvent evt) {                
 
            	HorizontalGraph graph[] ={outputGraph};
             	int max[]={1000,100};
             	int magX[]={2,2};
             	int magY[]={2,2};
            	
            	JFrame frame = new JFrame("Full View Graph");
            	//GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //Add contents to the window.
            	
                //frame.add(p);
            	frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);

                //frame.setMaximizedBounds(e.getMaximumWindowBounds());
                //frame.setSize(e.getMaximumWindowBounds().width, e.getMaximumWindowBounds().height);
                
                //Display the window.
                frame.pack();
                frame.setVisible(true);
                //frame.setResizable(false);
                
                fullViewGraph =new FullViewGraph(graph,max,magX,magY,frame.getWidth()-20, frame.getHeight());
                frame.add(fullViewGraph);   //Pradeep: added
           //     System.out.println("w " + frame.getWidth() + " h " + frame.getHeight());
                
                
            }
          });
        
      
      guiPanel.add(reStartButton, gridBagConstraints);
      guiPanel.add(startButton, gridBagConstraints);
      guiPanel.add(nextButton, gridBagConstraints);
        
      btn= new javax.swing.JButton("Manual"); 
      icon = m_j3d.createImageIcon("resources/icons/manual.png");        
      btn.setIcon(icon);
      //startButton.setPreferredSize(new Dimension(100,30));
   //     guiPanel.add(btn, gridBagConstraints);
        btn.setVisible(false);                  //Pradeep: they said to remove Manual
        
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {                
            	
                HelpWindow.createAndShowGUI("forcedVib");
            }
          });

        
        
        
    }
    
    
    private void rightPanel() {
    	
    	      
    	 rightPanel.setLayout(new java.awt.GridLayout(2,1,0,1));
         
         outputGraph2 = new HorizontalGraph(300,280,"t","u''(t)");
         outputGraph2.setHeading("Input Ground Motion ");
         outputGraph2.setAxisUnit("sec","g");
         outputGraph2.setYAxisColor(Color.GREEN);
         outputGraph2.setYScale(100);
         outputGraph2.fitToYwindow(false);
      
//         rightPanel.setLayout(new java.awt.BorderLayout());
//  
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(140,200,240));
//      
//        ImageIcon icon = m_j3d.createImageIcon("resources/Data/Helix/stepforce1.jpg"); 
//      
//       
//        rightIcon=new javax.swing.JButton(" ");
//        rightIcon.setIcon(icon);
//        panel.add(rightIcon);
//        
//        panel.setPreferredSize(new Dimension(300,175));
//    //    rightTop.setBackground(new Color(200,200,100));
//        rightPanel.add(panel,BorderLayout.NORTH);
//
//        JPanel rightBottom = new JPanel();
//        rightBottom.setPreferredSize(new Dimension(300, 295));
//  //      rightBottom.setBackground(new Color(100,200,100));
       
        
        
        
        outputGraph = new HorizontalGraph(300,280,"t ","u(t)"); 
        outputGraph.setHeading("Displacement Response ");
        outputGraph.setAxisUnit("sec","m");
        outputGraph.setYAxisColor(Color.BLUE);
        outputGraph.setYScale(500);
        outputGraph.fitToYwindow(true);        
        HorizontalGraphWrapper wrapper = new HorizontalGraphWrapper(outputGraph,1000,2,Color.GRAY);
        
        rightPanel.add(outputGraph);     //added wrapper instead of outputGraph
        
        rightPanel.add(outputGraph2);
        
       
        // Can't call draw graph here as Graphics object is not initialize
                
        rightPanel.setVisible(false);

    }
    
  

	private static void enable(Container root, boolean enable) {
	    Component children[] = root.getComponents();
	    for(int i = 0; i < children.length; i++) 
			    children[i].setEnabled(enable);
    }
    
    private void centerPanel(Container container){
    	
    	 simulationPanel.setPreferredSize(new java.awt.Dimension(1024, 600));
         simulationPanel.setLayout(new java.awt.BorderLayout());
        
         javax.swing.JPanel guiPanel = new javax.swing.JPanel();
         guiPanel.setBackground(new Color(100,100,100));
         JLabel lbl = new JLabel("Consolidation Experiment ", JLabel.CENTER);
         lbl.setFont(new Font("Arial", Font.BOLD, 18));

         lbl.setForeground(Color.orange);
         //lbl.setBackground(Color.BLACK);
         guiPanel.add(lbl);
         simulationPanel.add(guiPanel, BorderLayout.NORTH);
         
         Canvas3D c = createUniverse(container);
         simulationPanel.add(c, BorderLayout.CENTER);

         JPanel btmPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
         simulationPanel.add(btmPanel, BorderLayout.SOUTH);
         
         guiPanel = new javax.swing.JPanel();
         guiPanel.setBackground(new Color(100,100,100));         
         simulationPanel.add(guiPanel, BorderLayout.EAST);
         
         guiPanel = new javax.swing.JPanel();
         guiPanel.setBackground(new Color(100,100,100));         
         simulationPanel.add(guiPanel, BorderLayout.WEST);
         
         // Create the content branch and add it to the universe
         scene = createSceneGraph();
         univ.addBranchGraph(scene);
         
     
  
         
         m_Objective = new JLabel(">: Start the experiment and observe the Displacement Response with respect to Time.", JLabel.LEFT);
         m_Objective.setFont(new Font("Arial", Font.BOLD, 13));
         m_Objective.setForeground(Color.WHITE);
         guiPanel = new javax.swing.JPanel();
         guiPanel.setBackground(new Color(100,100,100));        
         guiPanel.add(m_Objective);
         btmPanel.add(guiPanel,BorderLayout.NORTH);
         
         
         
         guiPanel = new javax.swing.JPanel(); //          
         guiPanel.setBackground(new Color(235,233,215));
         guiPanel.setLayout(new java.awt.GridBagLayout());
         java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);  

         javax.swing.JButton viewButton= new javax.swing.JButton("Horizontal View");
         ImageIcon icon = m_j3d.createImageIcon("resources/icons/h-view.png"); 
         viewButton.setIcon(icon);
         viewButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {                
             	
            	 //  0 , 
            	 if(m_cameraTimer!=null && m_cameraTimer.isRunning()){ m_cameraTimer.stop();}
            	 setCameraViews();
            	 m_cameraTimer = new Timer(200,new ActionListener() {
                     public void actionPerformed(ActionEvent evt) {
                         //...Perform a task...
                    	 timerActionHorizontalCameraMotion(evt);
                     }
                 });
            	 m_cameraTimer.start();
             }
           });

         
         guiPanel.add(viewButton, gridBagConstraints);
          
         viewButton= new javax.swing.JButton("Vertical View");
         icon = m_j3d.createImageIcon("resources/icons/v-view.png");
         viewButton.setIcon(icon);
         viewButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {                
             	
            	 if(m_cameraTimer!=null && m_cameraTimer.isRunning()){ m_cameraTimer.stop();}
            	 setCameraViews();
            	 m_cameraTimer = new Timer(200,new ActionListener() {
                     public void actionPerformed(ActionEvent evt) {
                         //...Perform a task...
                    	 timerActionVerticalCameraMotion(evt);
                     }
                 });
            	 m_cameraTimer.start();
            	 
             }
           });
         
         guiPanel.add(viewButton, gridBagConstraints);
         
         JCheckBox chkbox = new JCheckBox("");
         lbl = new JLabel("Change Input Parameters", JLabel.CENTER);
         //lbl.setFont(new Font("Arial", Font.BOLD, 18));
         icon = m_j3d.createImageIcon("resources/icons/tasklist.png");        
         lbl.setIcon(icon);
         chkbox.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent event){                               
                     boolean bChecked =((JCheckBox)event.getSource()).isSelected();
                     if(bChecked)
                    	 bottomPanel.setVisible(true);
                     else
                    	 bottomPanel.setVisible(false);
                     univ.getCanvas().repaint();
                             // (a) checkbox.isSelected();
                             // (b) ((JCheckBox)event.getSource()).isSelected();
                            
             }
         });

         guiPanel.add(chkbox, gridBagConstraints);
         guiPanel.add(lbl, gridBagConstraints);
         
         chkbox = new JCheckBox("");
         lbl = new JLabel("Show Graphs", JLabel.CENTER);
         //lbl.setFont(new Font("Arial", Font.BOLD, 18));
         icon = m_j3d.createImageIcon("resources/icons/show_graph.png");        
         lbl.setIcon(icon);
         chkbox.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent event){                               
                     boolean bChecked =((JCheckBox)event.getSource()).isSelected();
                     if(bChecked)
                    	 rightPanel.setVisible(false);
                     else
                    	 rightPanel.setVisible(false);
                     univ.getCanvas().repaint();
                            
             }
         });
     //    guiPanel.add(chkbox, gridBagConstraints);
     //    guiPanel.add(lbl, gridBagConstraints);

         
         btmPanel.add(guiPanel,BorderLayout.CENTER);
                 
         guiPanel = new javax.swing.JPanel(); // 
         guiPanel.setBackground(new Color(130,169,193));
         guiPanel.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),4));
//         guiPanel.add(createInputOutputPanel());
//         btmPanel.add(guiPanel,BorderLayout.SOUTH);
         
        
         

    }
    

    private void bottomPanel() {
		initInputControlsField();

		Color bk = new Color(219, 226, 238);
		bottomPanel.setLayout(new java.awt.GridLayout(1, 3));
		bottomPanel.setBackground(Color.black);
		bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(235,
				233, 215), 8));

		in1 = new JPanel(new java.awt.GridLayout(2, 3));
		in1.setBackground(bk);
		bottomPanel.add(in1);

		in2 = new JPanel(new java.awt.GridLayout(2, 2));
		in2.setBackground(bk);

		bottomPanel.add(in2);

		in3 = new JPanel(new java.awt.GridLayout(2, 2));
		in3.setBackground(bk);
		bottomPanel.add(in3);

		JLabel lab = new JLabel("Type of soil    ", JLabel.LEFT);
		// lab.setForeground(Color.white);
		String[] Bstr = new String[4];
		Bstr[0] = "Type 1";
		Bstr[1] = "Type 2";
		Bstr[2] = "Type 3";
		Bstr[3] = "Type 4";
		JComboBox BehaviourOfString = new JComboBox(Bstr);
		BehaviourOfString
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						valChange = true;
						JComboBox cb = (JComboBox) e.getSource();
						BOS = (String) cb.getSelectedItem();
						valChange = true;

						iLabel[0].setText(" 100 ");
						// iLabel[0].setText(":: " + BOS + " ");

						repaint();

					}
				});
		in1.add(lab);
		in1.add(BehaviourOfString);
		in1.add(iLabel[0]);
		iLabel[0].setForeground(Color.BLUE);
		JLabel lab1 = new JLabel("        ", JLabel.LEFT);
		in1.add(lab1);
		
		lab = new JLabel("Weight of Soil ", JLabel.LEFT);
		m_Slider[0] = new JSlider(JSlider.HORIZONTAL, 100, 500, 100);
		m_Slider[0].setMajorTickSpacing(400);
		m_Slider[0].setPaintTicks(true);
		m_Slider[0].setPaintLabels(true);
		m_Slider[0].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;
				int val = ((JSlider) e.getSource()).getValue();
				fields[1] = val;

				iLabel[0].setText(":: " + fields[1] + " Gm");
				repaint();
			}
		});
		m_Slider[0].setBackground(bk);
		in1.add(lab);
		in1.add(m_Slider[0]);
		in1.add(iLabel[0]);

		outlbl_val = new JLabel[3];
		lab = new JLabel("Weight of Water (in gm.) ", JLabel.RIGHT);
		outlbl_val[0] = new JLabel(" 0 ", JLabel.RIGHT);
		in3.add(lab);
		in3.add(outlbl_val[0]);

		lab = new JLabel("Weight of Soil ( gm. ) ", JLabel.RIGHT);
		outlbl_val[1] = new JLabel(" 0 ", JLabel.RIGHT);
		in3.add(lab);
		in3.add(outlbl_val[1]);

		bottomPanel.setVisible(false);
		// ///////// Enable/Disable function for Input parameters
		enable(in1, false);
		// enable(in2,false);
		 enable(in3,false);
	}
  public int getStr() {
		if (BOS == "Type 1") {
			// System.out.println(BOS);
			return 1;
		}

		if (BOS == "Type 2") {
			return 2;
		}

		if (BOS == "Type 3") {
			return 3;
		}
		return 4;
	}

	private void initInputControlsField() {

		iLabel = new JLabel[9];
		int i = 0;
		iLabel[i] = new JLabel("  100 ", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);// new JLabel(":Shear (Hinge)",
												// JLabel.LEFT);
												// iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("  ", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel(":: 50 m", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);
		i = 0;
		fields = new float[9];
		fields[0] = 0;
		fields[1] = 15;
	

	}
	public float getWeight() {
		return fields[1];
	}
    
     
    private void onNextStage()
    {
    	stage = stage%4;
    	ct++;
 //   	System.out.println("ct is incresed");
    	if(stage==2)
          	objSwitch.setWhichChild(1);
          if(stage==3)
          	objSwitch.setWhichChild(2);
          if(stage==0)
          	objSwitch.setWhichChild(3);
    	valChange = true; // Clear the graph. or Graph will restart on Play    	
    	resetOutputParameters(); // Clear the Output Parameters
    	bottomPanel.setVisible(true);
    	enableStage(stage);
    	setInstructionText();
   		 
    }
    private void enableStage(int s){
    	switch(s){
    	case 0: // Home     		
    		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
			
    		break;
    	case 1: // Home 
    		
    		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
			
			
    		break;
    	
    	case 2:

    		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
    		break;
    	
    	case 3:

    		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
    		break;
//    	case 2:
//
//    		enable(in1,false);	 enable(in2,false);		 enable(in3,true);	
//    		break;
//			
//    	case 3:
//    		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
//		break;
    	
    
    	}
    	
    }
    
    private void setInstructionText()
    {
    	    	
    	valChange = true; // Clear the graph. or Graph will restart on Play    	
    	resetOutputParameters(); // Clear the Output Parameters
    	
    	
    	switch(stage){
    	case 0: // Home 
    		m_Objective.setText(">: Run the experiment with selected 'type of force'.");
    		m_Objective.setForeground(Color.WHITE);
    					break;
    	case 1:
    		m_Objective.setText(">: Observe the effect of time period on maximum response.");
    		m_Objective.setForeground(Color.GREEN);
    		    		break;

    	}
    		
   		 
    }
    
    private void resetOutputParameters()
    {
//    	int i=0;
//    	outlbl_val[i++].setText(getMass() + " Kg");
//        outlbl_val[i++].setText(String.valueOf(getStiff()).substring(0,4)+ String.valueOf(getStiff()).substring(String.valueOf(getStiff()).length()-4,String.valueOf(getStiff()).length())+" N/m");
//    	 i=2;
//        outlbl_val[i++].setText(" t sec");
//        outlbl_val[i++].setText(" d m");
//       
        
    }
    
    private void  setCameraViews()
    {
    	m_cameraViews = new float[360];
    	int i=0;
    	for(i=0;i<90;i++)
    		m_cameraViews[i] = i;
    	for(int j=0;j<90;j++,i++)
    		m_cameraViews[i] = (90-j);
    	for(int j=0;j<90;j++,i++)
    		m_cameraViews[i] = -j;
    	for(int j=0;j<90;j++,i++)
    		m_cameraViews[i] = -(90-j);
    	
    	m_cameraEye =0;
    	
    }
    private void  timerActionVerticalCameraMotion(java.awt.event.ActionEvent evt)
    {
    	ViewingPlatform vp = univ.getViewingPlatform();
	    TransformGroup steerTG = vp.getViewPlatformTransform();
	    Transform3D t3d = new Transform3D();
	    steerTG.getTransform(t3d);
	    //Vector3d s = new Vector3d();
	    Vector3f currPos=new Vector3f();
	    t3d.get(currPos); 
	    
	   // System.out.println("current Pos:" + currPos);
	 /*   float y = (float)(float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye]));
	    float z = 2.41f - Math.abs(y);//((float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye])));
	    // default (0, 0, 2.41)
	   // System.out.println("x" + x);
	    t3d.lookAt( new Point3d(0, y,z), new Point3d(0,0,0), new Vector3d(0,1,0));
	    t3d.invert();*/
	    float z = (float)5*(float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye]));
	    if(z<0) z=-z;
	    t3d.lookAt( new Point3d(0, 0,-z-2), new Point3d(0,0,-20), new Vector3d(0,1,0));
	    
	    //t3d.setTranslation(new Vector3d(0,0,8));
	    steerTG.setTransform(t3d);
	    m_cameraEye++;
	    if(m_cameraEye == 180){
	    	m_cameraTimer.stop();
	    	m_cameraEye = 0;
	    }
    }
    
    private void  timerActionHorizontalCameraMotion(java.awt.event.ActionEvent evt)
    {
    	ViewingPlatform vp = univ.getViewingPlatform();
	    TransformGroup steerTG = vp.getViewPlatformTransform();
	    Transform3D t3d = new Transform3D();
	    steerTG.getTransform(t3d);
	    //Vector3d s = new Vector3d();
	    Vector3f currPos=new Vector3f();
	    t3d.get(currPos);
	    
	   // System.out.println("current Pos:" + currPos);
	    
	    
	   float x = (float)(float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye]));
	    float z = 2.41f - Math.abs(x);
	    //((float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye])));
	    // default (0, 0, 2.41)
	    // System.out.println("x" + x);
	    t3d.lookAt( new Point3d(x, 0,z), new Point3d(0,0,0), new Vector3d(0,1,0));
	    
	    t3d.invert();
	    
	    //t3d.setTranslation(new Vector3d(0,0,8));
	    steerTG.setTransform(t3d);
	    m_cameraEye++;
	    if(m_cameraEye == 360){
	    	m_cameraTimer.stop();
	    	m_cameraEye = 0;
	    }
    }
    // Resume Button Action
    private void startSimulation(java.awt.event.ActionEvent evt)
    {
      
//    	if (!rightPanel.isVisible()){
//    		rightPanel.setVisible(true);
//    		bottomPanel.setVisible(true);
//    	}
    	rightPanel.setVisible(false);
    	ImageIcon icon = m_j3d.createImageIcon("resources/icons/stop.png"); 
    	startButton.setIcon(icon);
    	startButton.setText("Stop");
    	enableStage(0);   // -1 	
        reStartButton.setEnabled(true);
        nextButton.setEnabled(true);
        outputGraph.setState(1);
        outputGraph2.setState(1);
        
        if(valChange){
        	
        	   //	System.out.println("Value Changed");
          	double weight = getWeight();
  			int type = getStr();
  			// float time = getTime();

  			// System.out.println("mass: "+ mass+ " K:" +stiffness+ " Crt:"
  			// +crt_damp+ " Time:"+ time+ " data:" +datafile);

  			freeBody.Init(weight, type);
  		  			outputGraph.clearGraphValue();
    
  		  			
  		  			float scaleXZ = (float)(fields[1])/15.0f; 
	        Vector3d scaleVec = new Vector3d(scaleXZ,1,scaleXZ);
	        Transform3D scaleT = new Transform3D();
	        scaleT.setScale(scaleVec);
	        objSwitchPos.setTransform(scaleT);
	        
	        LineAttributes la = new LineAttributes();
	        la.setLineWidth((float)fields[0]/10);
	        appea.setLineAttributes(la);	        	      	
        }
        
         timer.start();
        System.out.println("Timer started");
    }
    
   
    
    // Resume Button Action
    private void timerActionPerformed(java.awt.event.ActionEvent evt)
    {
    	if(stage==0)
    		  stage++;
    	    	
    	
  	  float Water = (float) (freeBody.getWW());
  		float Soil = (float) (freeBody.getWS());

  		int i = 0;
  		// ///////// Text

  		outlbl_val[i++].setText(String.valueOf(Water) + " gm");
  		outlbl_val[i++].setText(String.valueOf(Soil) + " gm");
      
  		if(stage==1&& cnt==0)
	      	objSwitch.setWhichChild(0); 
       
  
        
//        if(rightPanel.isVisible())
//        {
//        	outputGraph.drawGraph();
//        	outputGraph2.drawGraph();
//       
//        }

        
        freeBody.update();        
        
        if(freeBody.isDataCompleted()) {
        	pauseSimulation();
        	return;
        }
       /* stage++;
        stage = stage%4;
        
               	
        java.util.BitSet visibleNodes = new java.util.BitSet( objSwitch.numChildren() );
        visibleNodes.set(stage);
        objSwitch.setChildMask(visibleNodes);
//        objSwitch.setWhichChild(-3);
        
        if(stage==1)
        	objSwitch.setWhichChild(0);
        if(stage==2)
        	objSwitch.setWhichChild(1);
        if(stage==3)
        	objSwitch.setWhichChild(2);
        if(stage==0)
        	objSwitch.setWhichChild(3);*/

     

        return;            
    }
    
    private void updateSimulationBody(double disp){
    	
    	Shape3D shape = (Shape3D)hm.get("block1");
    	shape.setGeometry(m_j3d.createBoxGeom((float)disp*3));
    	
    	TransformGroup tgp = (TransformGroup)hm.get("roof1");
    	Transform3D trans = new Transform3D();
    	tgp.getTransform(trans);
        trans.setTranslation(new Vector3d( disp -0,0.17, -.1));       
        tgp.setTransform(trans);
        

    }
    
    private void pauseSimulation()
    {
    	
		timer.stop();
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/start.png"); 
    	startButton.setIcon(icon);
    	startButton.setText("Start");
    	reStartButton.setEnabled(true);
         // bottomPanel.setVisible(true);
        nextButton.setEnabled(true);
    	
    	rightPanel.setVisible(false);
		enableStage(stage);
		outputGraph.setState(0);
//		outputGraph2.setState(0);
        //startButton.setEnabled(true);
		       
        valChange = false;
         
		repaint();
    }
 
    
}///////////////// Defination COmplete

    

   

