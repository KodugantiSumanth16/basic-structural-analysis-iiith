package com.vlab.geo;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Formatter;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.PolygonAttributes;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import org.omg.CORBA.Bounds;

import eerc.vlab.common.FullViewGraph;
//import eerc.vlab.common.GraphPlotter;
import eerc.vlab.common.HelpWindow;
import eerc.vlab.common.HorizontalGraph;
import eerc.vlab.common.HorizontalGraphWrapper;
//import eerc.vlab.common.ImagePanel;
import eerc.vlab.common.J3DShape;
import eerc.vlab.common.Resources;
//import eerc.vlab.demos.Pendulum;


//import com.sun.j3d.utils.geometry.Box;
//import com.sun.j3d.utils.geometry.ColorCube;
//import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class attenberg<PlotGraph> extends javax.swing.JPanel {
	/**
	 * 
	 */
	private javax.swing.JPanel simulationPanel1;
    private javax.swing.JPanel bottomPanel1;
    private javax.swing.JPanel topPanel1;  
    
    private float[] fields1;
    
 ////////////////////////////Java3D componenet ///////////////////////////
	
    private SimpleUniverse 	univ1 = null;			// Simple Universe Java3D
    private BranchGroup 	scene1 = null;			// BranchGroup Scene graph   
    private TransformGroup 	objTransGroup=null;			// RootGroup

    private attenbergBody 	freeBody1 =null;			// Shape3D
    //private GraphAxis		graphBody = null;		// Shape3D
 
    
    private ArrayList 		m_vrtx=new ArrayList(); // Graph List
    
    private Timer 			timer1=null;				// Timer for simulation 
    
    private static final String defaultFileName = "resources/images/brick.jpg";
    private java.net.URL texImage = null;
    
 //  private Alpha rotationAlpha1;   
    // End of variables declaration//GEN-END:variables   
    
   // public BranchGroup createSceneGraph() {
	// Create the root of the branch graph
//	BranchGroup objRoot = new BranchGroup();
//	objRoot.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND );
//	objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
//	objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
//	objRoot.setCapability( BranchGroup.ALLOW_DETACH );
	

	//ColorCube sphere = new ColorCube(0.05);
	
    //TransformGroup tg = new TransformGroup();

    //Transform3D transform = new Transform3D();
    //Vector3f vector = new Vector3f( 0.0f, -0.4f, 0.0f);
    //transform.set(1, vector);
    //transform.setTranslation(vector);
    //transform.setScale(new Vector3d(18,.5,1));
    
//    	Transform3D scale = new Transform3D();
//    scale.setScale(new Vector3d(5,1,1));
//    transform.mul(scale);
    //tg.setTransform(transform);    
    
    //tg.addChild(sphere);
    
//	objTransGroup = new TransformGroup();
//	objTransGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND );
//	objTransGroup.setCapability(Group.ALLOW_CHILDREN_READ);
//	objTransGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
//	objTransGroup.setCapability( BranchGroup.ALLOW_DETACH );
    
 
	private static final long serialVersionUID = 1L;
private static final float Y = 0;
private static final double X = 0;
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
	
	
	private JComboBox spr_mat;
	private JSlider m_Slider[] = new JSlider[4];
	private String BOS;
	
	private javax.swing.JButton rightIcon=null;

	//private GraphPlotter         graphPlotter;
	////////////////////////////Java3D componenet ///////////////////////////

	private SimpleUniverse      univ = null;                  // Simple Universe Java3D
	private BranchGroup         scene = null;                 // BranchGroup Scene graph
	TransformGroup objSwitchPos = new TransformGroup(new Transform3D());
	
	private Switch objSwitch = new Switch();
	Appearance appea = new Appearance();
	
	private attenbergBody       freeBody =null;               // Shape3D
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
	
	private boolean startStop = false;
	private boolean valChange = true;
	
//	private JComboBox ch;
//	private JComboBox che;
//	private JLabel lbl_k;
	
	
//	private String[] units ={" (m) "," (m) "," (mm) "," (Kg/m^3) ",
//							 " (m) "," (mm) "," (mm) ","",
//							 " (m/s) "," (mm) "," (%) "};
	
	public BranchGroup createSceneGraph() 
	{
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		objRoot.setCapability(Group.ALLOW_CHILDREN_EXTEND );
		objRoot.setCapability(Group.ALLOW_CHILDREN_READ);
		objRoot.setCapability(Group.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability( BranchGroup.ALLOW_DETACH );
		
		
		
		objRoot.addChild(createVirtualLab());
		
//		 Floor
		int i,j;
		for(i=-4;i<=4;i++)
		{
		for(j=-4;j<=4;j++)
		{
		objRoot.addChild(m_j3d.createBox(new Vector3d((float)(i),-0.6,(float)(j)),new Vector3d(0.5,.01,0.5),new Vector3d(0,0,0),new Color3f(.8f, .8f, .8f),"resources/images/tile.jpg"));
		}
		}
		// objRoot.addChild(m_j3d.createBox(new Vector3d(0,-0.6, -.1),new Vector3d(1.5,.01,1.5),new Vector3d(0,0,0),new Color3f(.8f, .8f, .8f),"resources/images/tile.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.4,-2.5),new Vector3d(10,10,.01),new Vector3d(0f, 0f,0f), new Color3f(0.5f,0.6f,0.72f)));
		// Walls and roof
		objRoot.addChild(m_j3d.createBox(new Vector3d(1,0.1f,0), new Vector3d(0.05,0.7f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/floor.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(-1,0.1f,0), new Vector3d(0.05,0.7f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/floor.jpg"));
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
		freeBody1 = new attenbergBody();    
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

  private Object loadObjectFile(String string, Vector3d vector3d,
		Vector3d vector3d2, Color3f color3f) {
	// TODO Auto-generated method stub
	return null;
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

      univ1 = new SimpleUniverse(c);

      // This will move the ViewPlatform back a bit so the
      // objects in the scene can be viewed.
      
      //ViewingPlatform viewingPlatform = univ.getViewingPlatform();
      setLight();
      
      univ1.getViewingPlatform().setNominalViewingTransform();

      
      ViewingPlatform vp = univ1.getViewingPlatform();
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
      univ1.getViewer().getView().setMinimumFrameCycleTime(5);

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

          ViewingPlatform viewingPlatform = univ1.getViewingPlatform();
          viewingPlatform.setPlatformGeometry( pg );


  }

  
  private void destroy() {
      univ1.cleanup();
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
      
    //  objtrans.addChild(new Box(4.0f, 4.0f, 4.0f,app));
      
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
      
     // objtrans.addChild(new Box(4.0f, 4.0f, 4.0f,app));
      
      return objtrans;
  }
  
  
  
  
	private Group createVirtualLab() {	
		
		
		TransformGroup cyl1 = new TransformGroup();
		TransformGroup cyl2 = new TransformGroup();
		TransformGroup cyl3 = new TransformGroup();
	    TransformGroup cyl4 = new TransformGroup();
	    TransformGroup box =new TransformGroup();
	    TransformGroup table=new TransformGroup();
	    
	    
	    TransformGroup obj1=new TransformGroup();
	    TransformGroup obj2=new TransformGroup();
	    TransformGroup obj3=new TransformGroup();
	    TransformGroup obj4=new TransformGroup();
	    TransformGroup text=new TransformGroup();
	    
	    
		cyl1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);		
		
		cyl2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		cyl3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		cyl4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		box.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		box.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
	//	obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
//		obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
//		obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
//		obj4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		obj4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
	    Transform3D t = new Transform3D();
      BranchGroup objroot = new BranchGroup();
      
	    TransformGroup objtrans = new TransformGroup(t);
	    objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	    
	    
	   
                         
      objroot.addChild(objtrans);
      
	    float H;
	    H = 0.36f;
	    final float L0 = 0.36f;
	    
	   
	    
	    
	    
	   
	  table.addChild(m_j3d.createBox(new Vector3d(0.0f,-0.1f,0.0f),new Vector3d(0.5f,0.02f,0.4f), new Vector3d(12f,0.09f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/soil1.jpg"));
	    table.addChild(m_j3d.createCylinder(new Vector3d(0.35,-0.35f,-0.35),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f),"resources/images/bluegem.jpg", hm));
	    table.addChild(m_j3d.createCylinder(new Vector3d(-0.35,-0.35f,-0.35),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f),"resources/images/bluegem.jpg", hm));
	    table.addChild(m_j3d.createCylinder(new Vector3d(-0.35,-0.35f,0.35),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f),"resources/images/bluegem.jpg", hm));
	    table.addChild(m_j3d.createCylinder(new Vector3d(0.35,-0.35f,0.35),new Vector3d(0.3,3.0f,0.3),new Vector3d(0,0,0),new Color3f(0.4f,0.2f,0.0f),"resources/images/bluegem.jpg", hm));
	     double length = 0;
	int i = 0;
	int ang = 0;

	    //empty
	    
obj3.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.4f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(1800, 1900, 2000)));
obj3.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.8f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj3.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.45f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj3.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.85f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj3.addChild(m_j3d.createBox(new Vector3d(-.20f,-.11,.8),new Vector3d(0.12f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.3f,0.9f,0.0f),"resources/images/k3.jpeg"));
obj3.addChild(m_j3d.createBox(new Vector3d(-.23f,-.11,.18),new Vector3d(0.11f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.1f,0.9f,0.0f),"resources/images/groove.jpeg"));
obj3.addChild(m_j3d.createBox(new Vector3d(0.0,.05f,.50),new Vector3d(0.2f, .05f, 0.5f),new Vector3d(0.51f, 0f,0f), m_j3d.getColor3f(130, 120, 100),"resources/images/tile2.jpg"));
obj3.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0f,.18f,0.45f),new Vector3d(0.2,0.05,0.3),new Vector3d(15,0,0), new Color3f(1800, 1900, 2000)));
obj3.addChild(m_j3d.createBox(new Vector3d(0,0.16f,-0.70),new Vector3d(0.03,0.10f,0.03),new Vector3d(0,0,0),new Color3f(0f,0f,0.0f),"resources/images/aquamarine.jpg"));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.22f,.23f,.15f),new Vector3d(0.10,4.10,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(130, 120, 100)));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0f,.21f,.15f),new Vector3d(0.15,0.29,0.1),new Vector3d(0,0,0), m_j3d.getColor3f(0, 0, 0)));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.42f,.14f,.15f),new Vector3d(0.10,2.0,0.06),new Vector3d(0,90,0), m_j3d.getColor3f(130, 120, 100)));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.52f,.07f,.15f),new Vector3d(0.10,1.90,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(0, 0, 0)));

obj1.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.4f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(1800, 1900, 2000)));
obj1.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.8f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj1.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.45f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj1.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.85f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj1.addChild(m_j3d.createBox(new Vector3d(-.20f,-.11,.8),new Vector3d(0.12f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.3f,0.9f,0.0f),"resources/images/k3.jpeg"));
obj1.addChild(m_j3d.createBox(new Vector3d(-.23f,-.11,.18),new Vector3d(0.11f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.1f,0.9f,0.0f),"resources/images/groove.jpeg"));
obj1.addChild(m_j3d.createBox(new Vector3d(0.0,.05f,.50),new Vector3d(0.2f, .05f, 0.5f),new Vector3d(0.51f, 0f,0f), m_j3d.getColor3f(130, 120, 100),"resources/images/tile2.jpg"));
obj1.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0f,.18f,0.45f),new Vector3d(0.2,0.05,0.3),new Vector3d(15,0,0), new Color3f(1800, 1900, 2000)));
obj1.addChild(m_j3d.createBox(new Vector3d(0,0.16f,-0.70),new Vector3d(0.03,0.10f,0.03),new Vector3d(0,0,0),new Color3f(0f,0f,0.0f),"resources/images/aquamarine.jpg"));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.22f,.23f,.15f),new Vector3d(0.10,4.10,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(130, 120, 100)));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0f,.21f,.15f),new Vector3d(0.15,0.29,0.1),new Vector3d(0,0,0), m_j3d.getColor3f(0, 0, 0)));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.42f,.14f,.15f),new Vector3d(0.10,2.0,0.06),new Vector3d(0,90,0), m_j3d.getColor3f(130, 120, 100)));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.52f,.06f,.15f),new Vector3d(0.10,1.90,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(0, 0, 0)));

obj2.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.4f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(1800, 1900, 2000)));
obj2.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.8f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj2.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.45f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj2.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.85f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj2.addChild(m_j3d.createBox(new Vector3d(-.20f,-.11,.8),new Vector3d(0.12f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.3f,0.9f,0.0f),"resources/images/k3.jpeg"));
obj2.addChild(m_j3d.createBox(new Vector3d(-.23f,-.11,.18),new Vector3d(0.11f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.1f,0.9f,0.0f),"resources/images/groove.jpeg"));
obj2.addChild(m_j3d.createBox(new Vector3d(0.0,.05f,.50),new Vector3d(0.2f, .05f, 0.5f),new Vector3d(0.51f, 0f,0f), m_j3d.getColor3f(130, 120, 100),"resources/images/tile2.jpg"));
obj2.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0f,.19f,0.45f),new Vector3d(0.2,0.05,0.3),new Vector3d(0,15,0), new Color3f(1800, 1900, 2000)));
obj2.addChild(m_j3d.createBox(new Vector3d(0,0.16f,-0.70),new Vector3d(0.03,0.10f,0.03),new Vector3d(0,0,0),new Color3f(0f,0f,0.0f),"resources/images/aquamarine.jpg"));
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.23f,.23f,.15f),new Vector3d(0.10,4.10,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(130, 120, 100)));
obj2.addChild(m_j3d.createCylinder(new Vector3d(0f,.21f,.15f),new Vector3d(0.15,0.29,0.1),new Vector3d(0,0,0), m_j3d.getColor3f(0, 0, 0)));
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.44f,.32f,.15f),new Vector3d(0.10,2.0,0.06),new Vector3d(0,90,0), m_j3d.getColor3f(130, 120, 100)));
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.54f,.40f,.15f),new Vector3d(0.10,1.90,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(0, 0, 0)));

obj4.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.4f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(1800, 1900, 2000)));
obj4.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.0f,-.1f,0.8f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj4.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.45f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj4.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0.21f,-.1f,0.85f),new Vector3d(0.1,0.01,0.3),new Vector3d(0,0,0), new Color3f(180, 190, 200)));
obj4.addChild(m_j3d.createBox(new Vector3d(-.20f,-.11,.8),new Vector3d(0.12f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.3f,0.9f,0.0f),"resources/images/k3.jpeg"));
obj4.addChild(m_j3d.createBox(new Vector3d(-.23f,-.11,.18),new Vector3d(0.11f, .005f, 0.05f),new Vector3d(27f, 0.10f,0f), new Color3f(0.1f,0.9f,0.0f),"resources/images/groove.jpeg"));
obj4.addChild(m_j3d.createBox(new Vector3d(0.0,.05f,.50),new Vector3d(0.2f, .05f, 0.5f),new Vector3d(0.51f, 0f,0f), m_j3d.getColor3f(130, 120, 100),"resources/images/tile2.jpg"));
obj4.addChild(m_j3d.loadObjectFile("resources/geometry/vase1.obj",new Vector3d(0f,.19f,0.45f),new Vector3d(0.2,0.05,0.3),new Vector3d(0,15,0), new Color3f(1800, 1900, 2000)));
obj4.addChild(m_j3d.createBox(new Vector3d(0,0.16f,-0.70),new Vector3d(0.03,0.10f,0.03),new Vector3d(0,0,0),new Color3f(0f,0f,0.0f),"resources/images/aquamarine.jpg"));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.23f,.23f,.15f),new Vector3d(0.10,4.10,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(130, 120, 100)));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0f,.21f,.15f),new Vector3d(0.15,0.29,0.1),new Vector3d(0,0,0), m_j3d.getColor3f(0, 0, 0)));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.44f,.32f,.15f),new Vector3d(0.10,2.0,0.06),new Vector3d(0,90,0), m_j3d.getColor3f(130, 120, 100)));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.54f,.40f,.15f),new Vector3d(0.10,1.90,0.1),new Vector3d(0,0,90), m_j3d.getColor3f(0, 0, 0)));

obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.20f,.92f),new Vector3d(0.99,.2,1),new Vector3d(15,0,0), m_j3d.getColor3f(139, 69, 19)));
obj1.addChild(m_j3d.createCylinder(new Vector3d(.001f,.20f,.92f),new Vector3d(0.99,.2,1),new Vector3d(15,0,0), m_j3d.getColor3f(139, 69, 19)));

	    
	    
	    objSwitch = new Switch(Switch.CHILD_MASK);
      objSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
      
   // create switch value interpolator
     // if(stage==-1)
      objroot.addChild(table);      
      objroot.addChild(objSwitchPos);        
      /*objSwitch.addChild(obj3);
      objSwitch.addChild(obj1);
      objSwitch.addChild(obj2);*/
      
     
      
      objSwitch.insertChild(obj3, 0);
      objSwitch.insertChild(obj1, 1);
      objSwitch.insertChild(obj2, 2);
      objSwitch.insertChild(obj4,3);
      
      
      //objSwitch.addChild(spring4);
      //objSwitch.addChild(spring5);
      //objSwitch.addChild(spring6);
      //objSwitch.addChild(spring7);
      objSwitchPos.addChild(objSwitch);       
      
      //objroot.addChild(swiInt);
      //swiInt.setLastChildIndex(6);       
	    return objroot;
	}  
	
	public Group createCylinder(Vector3d pos, Vector3d scale, Vector3d rot, double theta, double length) {
		// Setting the 6 degrees of freedom and Scale

		Transform3D t = new Transform3D();
		float rad = (float) Math.PI / 180;
		if (rot.x != 0)
			t.rotX(rad * rot.x);
		else if (rot.y != 0)
			t.rotY(rad * rot.y);
		else if (rot.z != 0)
			t.rotZ(rad * rot.z);
		t.setScale(scale);
		t.setTranslation(pos);

		TransformGroup objtrans = new TransformGroup(t);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		double ang = theta / 10;
		theta = theta * 2.0 * Math.PI / 3600.0;
		int i, j;
		double X, Y;
		X = 0.0;
		Y = 0.0;
		for (i = 0; i < 10; i++) {
	objtrans.addChild(m_j3d.createCylinder(new Vector3d(-2 * Y, 2 * X, 0),
					new Vector3d(length / 10, 0.025, 0.025), new Vector3d(0, 0,
							-ang * (i + 1)), new Color3f(1, 1, 1),
					"resources/images/floor.jpg", hm));
			X += Math.cos(i * theta) * length / 10;
			Y -= Math.sin(i * theta) * length / 10;
		}

		return objtrans;
	}

	 private String addChild(Group createBox) {
		// TODO Auto-generated method stub
		return null;
	}

/**
   * Creates new form FreeVibration
   */
  public attenberg(Container container) {
      // Initialize the GUI components
      JPopupMenu.setDefaultLightWeightPopupEnabled(false);
      initComponents();

      centerPanel(container);
      // Create Canvas3D and SimpleUniverse; add canvas to drawing panel
      
//      scene.addChild(bgleg);
  }

  
  // ----------------------------------------------------------------
  
  // Applet framework

  public static class MyApplet extends JApplet {
      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		attenberg mainPanel;

      public void init() {
          setLayout(new BorderLayout());
          mainPanel = new attenberg(this);
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
          setTitle("Atternberg limits Applet");
          getContentPane().add(new attenberg(this), BorderLayout.CENTER);
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
//    new GridLayout(2, 1)
      setLayout(new java.awt.BorderLayout());
      
      bottomPanel1 = new javax.swing.JPanel(); 	// input from user at bottom
      simulationPanel1 = new javax.swing.JPanel(); // 3D rendering at center
      topPanel1= new javax.swing.JPanel();    		// Pause, resume, Next
      rightPanel = new javax.swing.JPanel();    	// Graph and Input and Output Parameter
              
       
      topPanel();                 
      bottomPanel();        
      rightPanel();
      
//    Set Alignment
      //add(guiPanel, java.awt.BorderLayout.NORTH);
      add(topPanel1, java.awt.BorderLayout.NORTH);
      add(simulationPanel1, java.awt.BorderLayout.CENTER);
      add(bottomPanel1, java.awt.BorderLayout.SOUTH);
      add(rightPanel, java.awt.BorderLayout.EAST); 
      
      startStop = false;
  	valChange = true;    
      
      timer1 = new Timer(800,new ActionListener() {
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
              
//      javax.swing.JButton pauseButton = new javax.swing.JButton();  
//      javax.swing.JButton startButton = new javax.swing.JButton(); 
      reStartButton = new javax.swing.JButton("Re-Start");
      ImageIcon icon = m_j3d.createImageIcon("resources/icons/restart.png"); 
      reStartButton.setIcon(icon);
      startButton = new javax.swing.JButton("Start");
      icon = m_j3d.createImageIcon("resources/icons/start.png"); 
      startButton.setIcon(icon);
      nextButton = new javax.swing.JButton("Next");
      icon = m_j3d.createImageIcon("resources/icons/next.png");        
      nextButton.setIcon(icon);
//      ImageIcon icon = m_j3d.createImageIcon("resources/images/show_graph.png");        
//      startButton.setIcon(icon);
      //startButton.setPreferredSize(new Dimension(100,30));
      
           
      reStartButton.setText("Re-Start");  
      reStartButton.setEnabled(true);
      nextButton.setEnabled(true);
      
      
      
      guiPanel.setBackground(new Color(67,143,205));//Color.BLACK
      topPanel1.setLayout(new java.awt.BorderLayout());
      topPanel1.add(guiPanel, java.awt.BorderLayout.NORTH);
      
      
      startButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
          	// Toggle
          	stage=0;
          //	cnt=0;
          	startStop = !startStop;
          	
          	if(startStop)  startSimulation(evt); 
          	else pauseSimulation();
          	
              univ1.getCanvas().repaint();
          }
        });
      
      
      reStartButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
          	reStartButton.setEnabled(true);
              //startButton.setEnabled(true);
              startButton.setText("Start");
              startStop = !startStop;
             // startStop = false;
              
              outputGraph.clearGraphValue();
          //    outputGraph2.clearGraphValue();
              
              valChange = true;
              startSimulation(evt);
              univ1.getCanvas().repaint();
             
              
//          	reStartButton.setEnabled(false);
//              //startButton.setEnabled(true);
//              startButton.setText("Start");
//              startStop = false;
//              timer.stop();
//              outputGraph.clearGraphValue();
//              outputGraph2.clearGraphValue();
//              
//              valChange = true;
              
          }
        });
      
      nextButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {                
          	stage++;
          	//cnt++;
          	//if(cnt==3)
         	//{
         		//nextButton.setEnabled(false);
         		//stage++;
          	//}
          //	else
          		nextButton.setEnabled(true); 
          	onNextStage();
          	univ1.getCanvas().repaint();
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
    
     rightPanel.setLayout(new java.awt.BorderLayout());

      JPanel panel = new JPanel();
      panel.setBackground(new Color(140,200,240));
      panel.setBorder(BorderFactory.createLineBorder(new Color(132,132,255),4));
      panel.setBorder(new EmptyBorder(10, 10, 0, 0));
    
      ImageIcon icon = m_j3d.createImageIcon("resources/images/chart1.jpeg"); 
    
//     
      rightIcon=new javax.swing.JButton(" ");
      rightIcon.setIcon(icon);
      panel.add(rightIcon);
//      
      panel.setPreferredSize(new Dimension(300,250));
//      rightTop.setBackground(new Color(200,200,100));
      rightPanel.add(panel,BorderLayout.NORTH);
//
      JPanel rightBottom = new JPanel();
      rightBottom.setPreferredSize(new Dimension(400, 395));
      rightBottom.setBackground(new Color(100,200,100));
      rightBottom = new JPanel(new java.awt.GridLayout(17,0));
      rightBottom.setBorder(BorderFactory.createLineBorder(new Color(140,200,240),8));
      
      JLabel lab1=new JLabel("RESULTS",JLabel.CENTER); 
      lab1.setFont(new Font("Arial", Font.BOLD, 17));
      lab1.setForeground(Color.blue);
      JLabel lab=new JLabel(" ",JLabel.CENTER);  
      rightBottom.add(lab1);
      rightBottom.add(lab);
      
lab=new JLabel("Moisture can and lid number              11",JLabel.RIGHT);        
    //  outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
      rightBottom.add(lab);
      rightBottom.add(outlbl_val[0]);
     

lab=new JLabel("MC=Massofempty,cleancan+lid(grams)       22.23",JLabel.RIGHT);        
    //  outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
      rightBottom.add(lab);
      rightBottom.add(outlbl_val[0]);
     
lab=new JLabel("MCMS=Massofcan,lid,andmoistsoil(grams)   28.56",JLabel.RIGHT);        
    //    outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
        rightBottom.add(lab);
        rightBottom.add(outlbl_val[0]);
       
lab=new JLabel("MCDS=Massofcan,lid,anddrysoil(grams)     27.40",JLabel.RIGHT);        
      //    outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
          rightBottom.add(lab);
          rightBottom.add(outlbl_val[0]);
        
lab=new JLabel("MS =Massofsoilsolids(grams)               5.03",JLabel.RIGHT);        
        //    outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
            rightBottom.add(lab); 
            rightBottom.add(outlbl_val[0]);
         
 lab=new JLabel("MW =Massofporewater(grams)                1.16",JLabel.RIGHT);        
          //    outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
              rightBottom.add(lab);
              rightBottom.add(outlbl_val[0]);
      
lab=new JLabel("w = Water content, w%23.06",JLabel.RIGHT);        
            //    outlbl_val[0]=new JLabel("0", JLabel.CENTER);;
                rightBottom.add(lab);
                rightBottom.add(outlbl_val[0]);
              
                
lab=new JLabel("No. of drops (N)                            31",JLabel.RIGHT);        
              //    outlbl_val[0]=new JLabel("0", JLabel.CE NTER);;
                  rightBottom.add(lab);
                  rightBottom.add(outlbl_val[0]);
              
      outputGraph = new HorizontalGraph(300,80,"t ","u(t)"); 
      outputGraph.setHeading("Displacement Response ");
      outputGraph.setAxisUnit("sec","m");
      outputGraph.setYAxisColor(Color.BLUE);
      outputGraph.setYScale(100);
      outputGraph.fitToYwindow(true);    
      
      HorizontalGraphWrapper wrapper = new HorizontalGraphWrapper(outputGraph,100,2,Color.GRAY);
      
     // rightPanel.add(outputGraph);     //added wrapper instead of outputGraph
      
//      rightPanel.add(outputGraph2);
      
     
      // Can't call draw graph here as Graphics object is not initialize
      
      rightPanel.add(rightBottom,BorderLayout.CENTER);
              
      
      rightPanel.setVisible(false);

  }
  


	private static void enable(Container root, boolean enable) {
	    Component children[] = root.getComponents();
	    for(int i = 0; i < children.length; i++) 
			    children[i].setEnabled(enable);
  }
  
  private void centerPanel(Container container){
  	
  	 simulationPanel1.setPreferredSize(new java.awt.Dimension(1024, 600));
       simulationPanel1.setLayout(new java.awt.BorderLayout());
      
       javax.swing.JPanel guiPanel = new javax.swing.JPanel();
       guiPanel.setBackground(new Color(100,100,100));
       JLabel lbl = new JLabel("Atternberg Limits Experiment", JLabel.CENTER);
       lbl.setFont(new Font("Arial", Font.BOLD, 18));

       lbl.setForeground(Color.orange);
       //lbl.setBackground(Color.BLACK);
       guiPanel.add(lbl);
       simulationPanel1.add(guiPanel, BorderLayout.NORTH);
       
       Canvas3D c = createUniverse(container);
       simulationPanel1.add(c, BorderLayout.CENTER);

       JPanel btmPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
       simulationPanel1.add(btmPanel, BorderLayout.SOUTH);
       
       guiPanel = new javax.swing.JPanel();
       guiPanel.setBackground(new Color(100,100,100));         
       simulationPanel1.add(guiPanel, BorderLayout.EAST);
       
       guiPanel = new javax.swing.JPanel();
       guiPanel.setBackground(new Color(100,100,100));         
       simulationPanel1.add(guiPanel, BorderLayout.WEST);
       
       // Create the content branch and add it to the universe
       scene1 = createSceneGraph();
       univ1.addBranchGraph(scene1);
       
   

       
       m_Objective = new JLabel(">: Start the exeriment and put the soil.", JLabel.LEFT);
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
          	 m_cameraTimer = new Timer(800,new ActionListener() {
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
                  	 bottomPanel1.setVisible(true);
                   else
                  	 bottomPanel1.setVisible(false);
                   univ1.getCanvas().repaint();
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
                  	 rightPanel.setVisible(true);
                   else
                  	 rightPanel.setVisible(false);
                   univ1.getCanvas().repaint();
                          
           }
       });
       guiPanel.add(chkbox, gridBagConstraints);
       guiPanel.add(lbl, gridBagConstraints);

       
       btmPanel.add(guiPanel,BorderLayout.CENTER);
               
       guiPanel = new javax.swing.JPanel(); // 
       guiPanel.setBackground(new Color(130,169,193));
       guiPanel.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),4));
//       guiPanel.add(createInputOutputPanel());
//     btmPanel.add(guiPanel,BorderLayout.SOUTH);
       
      
       

  }
  
  
//  private JPanel createInputOutputPanel(){
//  	
//  	JPanel ioparm = new JPanel(new java.awt.GridLayout(1,3));
//  	//ioparm.setPreferredSize(new java.awt.Dimension(600, 100));
//  	ioparm.setBackground(new Color(67,143,205));
//  	JPanel parm = new JPanel(new java.awt.GridLayout(4,2,0,10)); 
//  	parm.setBackground(new Color(130,169,193));
//  	outlbl_val = new JLabel[4];// new  = 
//  	
//  	int i=0;
//  	JLabel lbl = new JLabel("Input  ", JLabel.RIGHT);   parm.add(lbl); lbl.setForeground(Color.yellow);
//  	lbl = new JLabel("Parameters", JLabel.LEFT);    	parm.add(lbl); lbl.setForeground(Color.yellow);
//  	lbl = new JLabel("Mass", JLabel.LEFT);	    		parm.add(lbl);
//  	outlbl_val[i] = new JLabel(getMass() + " Kg", JLabel.LEFT);   outlbl_val[i].setForeground(Color.white); parm.add(outlbl_val[i++]);
//  	lbl = new JLabel("Stiffness", JLabel.LEFT);	    	parm.add(lbl);
//  	outlbl_val[i] = new JLabel((float)getStiff() + " N/m", JLabel.LEFT);   outlbl_val[i].setForeground(Color.white); parm.add(outlbl_val[i++]);
//  	
//  	ioparm.add(parm);
//  	
//  	
//  	parm = new JPanel(new java.awt.GridLayout(4,2,0,10)); //additionally added
//  	parm.setBackground(new Color(130,169,193));
//  	lbl = new JLabel("            ", JLabel.RIGHT);   parm.add(lbl); lbl.setForeground(Color.yellow);
//  	lbl = new JLabel("            ", JLabel.RIGHT);    	parm.add(lbl); lbl.setForeground(Color.yellow);    
//  	
//  	lbl = new JLabel("            ", JLabel.RIGHT);					parm.add(lbl); 
//  	lbl = new JLabel("            ", JLabel.RIGHT);					parm.add(lbl); 
//  	lbl = new JLabel("            ", JLabel.RIGHT);					parm.add(lbl); 
//  	lbl = new JLabel("            ", JLabel.RIGHT);					parm.add(lbl); 
//  
//  	ioparm.add(parm);
//  	
//  	
//  	
//  	
//  	parm = new JPanel(new java.awt.GridLayout(4,2,20,0)); 
//  	parm.setBackground(new Color(130,169,193));
//  	lbl = new JLabel("Output  ", JLabel.RIGHT);   parm.add(lbl); lbl.setForeground(Color.yellow);
//  	lbl = new JLabel("Parameters", JLabel.LEFT);    	parm.add(lbl); lbl.setForeground(Color.yellow);
//       
//  	
//      
//  	lbl = new JLabel("Time", JLabel.RIGHT);					parm.add(lbl); 
//  	outlbl_val[i] = new JLabel("t (sec)", JLabel.RIGHT);   	outlbl_val[i].setForeground(Color.white);  parm.add(outlbl_val[i++]); // fmt.format("%.15s", outlbl_val[i++]);  parm.add(toString(fmt));
//  	lbl = new JLabel("Displacement", JLabel.RIGHT);			parm.add(lbl);
//  	outlbl_val[i] = new JLabel("d (m)", JLabel.RIGHT);   	outlbl_val[i].setForeground(Color.white); parm.add(outlbl_val[i++]);
//
//  	ioparm.add(parm);
//  	
//  	return  ioparm;
//      
//  }


  private void bottomPanel() {
		initInputControlsField();

		Color bk = new Color(219, 226, 238);
		bottomPanel1.setLayout(new java.awt.GridLayout(1, 3));
		bottomPanel1.setBackground(Color.black);
		bottomPanel1.setBorder(BorderFactory.createLineBorder(new Color(235,
				233, 215), 8));

		in1 = new JPanel(new java.awt.GridLayout(2, 3));
		in1.setBackground(bk);
		bottomPanel1.add(in1);

		in2 = new JPanel(new java.awt.GridLayout(2, 2));
		in2.setBackground(bk);

		bottomPanel1.add(in2);

		in3 = new JPanel(new java.awt.GridLayout(2, 2));
		in3.setBackground(bk);
		bottomPanel1.add(in3);

		JLabel lab = new JLabel("Type of Soil", JLabel.LEFT);
		// lab.setForeground(Color.white);
		String[] Bstr = new String[4];
		Bstr[0] = "Type 1";
		Bstr[1] = "Type 2";
		Bstr[2] = "Type 3";
	//	Bstr[3] = "Type 4";
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
		in1.add(iLabel[1]);
		iLabel[0].setForeground(Color.BLUE);
		JLabel lab1 = new JLabel("        ", JLabel.LEFT);
		in1.add(lab1);
		
		lab = new JLabel("Weight of Soil            700 ", JLabel.LEFT);
		m_Slider[0] = new JSlider(JSlider.HORIZONTAL, 100, 500, 100);
		m_Slider[0].setMajorTickSpacing(400);
		m_Slider[0].setPaintTicks(true);
		m_Slider[0].setPaintLabels(true);
		m_Slider[0].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;
				int val = ((JSlider) e.getSource()).getValue();
				fields1[1] = val;

				iLabel[0].setText(":: " + fields1[1] + " Gm");
				repaint();
			}
		});
		m_Slider[0].setBackground(bk);
		in1.add(lab);
		in1.add(m_Slider[0]);
		in1.add(iLabel[0]);

		outlbl_val = new JLabel[3];
	//	lab = new JLabel("Weight of Water (in gm.) ", JLabel.RIGHT);
		outlbl_val[0] = new JLabel(" 0 ", JLabel.RIGHT);
		in3.add(lab);
		in3.add(outlbl_val[0]);

	//	lab = new JLabel("Weight of Soil ( gm. ) ", JLabel.RIGHT);
		outlbl_val[1] = new JLabel(" 0 ", JLabel.RIGHT);
		in3.add(lab);
		in3.add(outlbl_val[1]);

		bottomPanel1.setVisible(false);
		// ///////// Enable/Disable function for Input parameters
		enable(in1, true);
		// enable(in2,false);
		 enable(in3,true);
	}
  public int getStr() {
		if (BOS == "Type 1") {
			 System.out.println(BOS);
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
		iLabel[i] = new JLabel("  1000 ", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);// new JLabel(":Shear (Hinge)",
												// JLabel.LEFT);
												// iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("  ", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel(":: 500 m", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);
		i = 0;
		fields1 = new float[9];
		fields1[0] = 0;
		fields1[1] = 15;
		fields1[2] = 30;	

	}

	public float getWeight() {
		return fields1[1];
	}

  
   
  private void onNextStage()
  {
	 /* stage++;
	  stage = stage%4;
      
     	
      //java.util.BitSet visibleNodes = new java.util.BitSet( objSwitch.numChildren() );
      //visibleNodes.set(stage);
      //objSwitch.setChildMask(visibleNodes);
      if(stage==1)
      	objSwitch.setWhichChild(0);
      if(stage==2)
      	objSwitch.setWhichChild(1);
      if(stage==3)
      	objSwitch.setWhichChild(2);
      if(stage==0)
      	objSwitch.setWhichChild(3);   */ 	
  	valChange = false; // Clear the graph. or Graph will restart on Play    	
  	resetOutputParameters(); // Clear the Output Parameters
  	bottomPanel1.setVisible(false);
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
//  	case 2:
//
//  		enable(in1,false);	 enable(in2,false);		 enable(in3,true);	
//  		break;
//			
//  	case 3:
//  		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
//		break;
  	
  
  	}
  	
  }
  
  private void setInstructionText()
  {
  	    	
  	valChange = true; // Clear the graph. or Graph will restart on Play    	
  	resetOutputParameters(); // Clear the Output Parameters
  	
  	
  	switch(stage){
  	case 0: // Home 
  		m_Objective.setText(">: Observe the weight");
  		m_Objective.setForeground(Color.WHITE);
  					break;
  	case 1:
  		m_Objective.setText(">: Observe the weight");
  		m_Objective.setForeground(Color.GREEN);
  		    		break;

  	}
  		
 		 
  }
  
  private void resetOutputParameters()
  {
//  	int i=0;
//  	outlbl_val[i++].setText(getMass() + " Kg");
//      outlbl_val[i++].setText(String.valueOf(getStiff()).substring(0,4)+ String.valueOf(getStiff()).substring(String.valueOf(getStiff()).length()-4,String.valueOf(getStiff()).length())+" N/m");
//  	 i=2;
//      outlbl_val[i++].setText(" t sec");
//      outlbl_val[i++].setText(" d m");
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
    
//  	if (!rightPanel.isVisible()){
//  		rightPanel.setVisible(true);
//  		bottomPanel.setVisible(true);
//  	}
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

  			freeBody1.Init(weight, type);
  		  			outputGraph.clearGraphValue();
  			//inputGraph.clearGraphValue();
               float scaleXZ = (float)(fields1[1])/15.0f; 
  	        Vector3d scaleVec = new Vector3d(scaleXZ,1,scaleXZ);
  	        Transform3D scaleT = new Transform3D();
  	        scaleT.setScale(scaleVec);
  	        objSwitchPos.setTransform(scaleT);
  	        
  	        LineAttributes la = new LineAttributes();
  	        la.setLineWidth((float)fields1[0]/10);
  	        appea.setLineAttributes(la);	        	      	
          }
          
           timer1.start();
          System.out.println("Timer started");

  }
  
 
  
  // Resume Button Action
  private void timerActionPerformed(java.awt.event.ActionEvent evt)
  {
    
  	  if(stage==0)
  		  stage++;
	  float Water = (float) (freeBody1.getWW());
		float Soil = (float) (freeBody1.getWS());

		int i = 0;
		// ///////// Text

		outlbl_val[i++].setText(String.valueOf(Water) + " gm");
		outlbl_val[i++].setText(String.valueOf(Soil) + " gm");
		
		if(stage==1&& cnt==0)
	      	objSwitch.setWhichChild(0); 
       if(rightPanel.isVisible())
        {
        	outputGraph.drawGraph();
        	outputGraph2.drawGraph();
       
        }
                
       
        stage++;
        stage = stage%5;
        
               	
        java.util.BitSet visibleNodes = new java.util.BitSet( objSwitch.numChildren() );
        visibleNodes.set(stage);
        objSwitch.setChildMask(visibleNodes);
        if(stage==1)
        	objSwitch.setWhichChild(0);
        if(stage==2)
        	objSwitch.setWhichChild(1);
        if(stage==3)
        	objSwitch.setWhichChild(2);
        if(stage==0)
        	objSwitch.setWhichChild(3);
        freeBody1.update();        
        if(stage==4)
        	objSwitch.setWhichChild(3);
      if(stage==5)
      	objSwitch.setWhichChild(4);
      
      if(freeBody1.isDataCompleted()) {
      	pauseSimulation();
      	return;
      }
      
      
             	
            
      return;            
  }
  
  private void updateSimulationBody(double disp){
  	
  	Shape3D shape = (Shape3D)hm.get("Cylinder");
  	shape.setGeometry(m_j3d.createBoxGeom((float)disp*3));
  	
  	TransformGroup tgp = (TransformGroup)hm.get("Cylinder");
  	Transform3D trans = new Transform3D();
  	tgp.getTransform(trans);
      trans.setTranslation(new Vector3d( disp -0,0.17, -.1));       
      tgp.setTransform(trans);
      

  }
  
  private void pauseSimulation()
  {
  	
		timer1.stop();
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

  

 

//Force Body Motion Object

class attenbergBody
{
	private double WW, WS, wt,estimate;

	public void Init(double soilwt, int soil) {
		wt=soilwt;
		System.out.println("Soil Weight: " + soilwt + "Soil Type: " + soil);

		
		switch (soil) {

		case 1:
			estimate = 20/100.0;
			break;
		case 2:
			estimate = 30/100.0;
			break;
		case 3:
			estimate = 40/100.0;
			break;
		case 4:
			estimate = 50/100.0;
			break;
		default:
			break;
			
		}
	
	
	WS = (estimate*wt);
	WW = (wt-WS);
	}
	public double getWW() {
		return WW;
	}

	public double getWS() {
		return WS;
	}
 public void update()
 {
 			    	
 }
boolean isDataCompleted()
	{
	
		return false;
	}
 }

