import processing.core.*; 
import processing.xml.*; 

import volatileprototypes.ReactP5.*; 
import peasy.org.apache.commons.math.*; 
import peasy.*; 
import peasy.org.apache.commons.math.geometry.*; 
import processing.opengl.*; 
import toxi.math.conversion.*; 
import toxi.geom.*; 
import toxi.math.*; 
import toxi.geom.mesh2d.*; 
import toxi.util.datatypes.*; 
import toxi.util.events.*; 
import toxi.geom.mesh.subdiv.*; 
import toxi.geom.mesh.*; 
import toxi.math.waves.*; 
import toxi.util.*; 
import toxi.math.noise.*; 
import toxi.volume.*; 
import volatileprototypes.Panel4P.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class GrayReact extends PApplet {









PFont font;
PeasyCam cam;

public int scl = 400;

ReactP5 prd;

public void setup() {
  size(720,720, OPENGL);
  frameRate(17);
  sphereDetail(5);
  initpanel();
  background(241);
  cam = new PeasyCam(this, 0,0,0,1250);
  font = createFont("Arial",12);
  reset();
  for (int i=0;i<5;i++) {
    spot();
  }
}
 
public void draw() {
  background(241);
  if (loop) {
    step();
    computeVolume();
  }
  drawFilledMesh();
  cam.beginHUD();
  textFont(font,12);
  fill(40);
  noStroke();
  text("Iteration: " + prd.getIteration(), 10, height-10);
  cam.endHUD();
}

public void step() {
  prd.step();
}

public void singlestep() {
   prd.step(20);
   computeVolume();
}

public void reset() {
  noStroke();
  i.updateVars();
  w=cw;
  h=ch;
  d=cd;
  prd = new ReactP5(w,h,d);
  prd.setDiffusionParameter(0, 0.16f);
  prd.setDiffusionParameter(1, 0.08f);
  Vec3D SCALE=new Vec3D(scl,scl,scl);
  volume=new VolumetricSpaceArray(SCALE,w,h,d);
  surface=new ArrayIsoSurface(volume);
  computeVolume();
}













VolumetricSpaceArray volume;
IsoSurface surface;
WETriangleMesh mesh=new WETriangleMesh("fluid");

public float isoPoint;
public float volBase;
public float volMult;

public void computeVolume() {
  float[] volumeData = volume.getData();
  float[] origData = prd.getSubstances()[0];
  for (int i=0;i<volumeData.length;i++) {
    volumeData[i] = volBase + volMult*origData[i];
  }
  //arraycopy(origData,0,volumeData,0,origData.length);
  if (cap) {
    volume.closeSides();
  }
  surface.reset();
  surface.computeSurfaceMesh(mesh,isoPoint);
  float mx = max(w,h,d)*0.6f;
  mesh.scale(new Vec3D(w/mx,h/mx,d/mx));
}

public void drawFilledMesh() {
  noStroke();
  fill(200);
  lights();
  beginShape(TRIANGLES);
  int num=mesh.getNumFaces();
  mesh.computeVertexNormals();
  for(int i=0; i<num; i++) {
    Face f=mesh.faces.get(i);
    Vec3D col=f.a;
    vertex(f.a.x,f.a.y,f.a.z);
    col=f.b;
    vertex(f.b.x,f.b.y,f.b.z);
    col=f.c;
    vertex(f.c.x,f.c.y,f.c.z);
  }
  endShape();
}

public void smoothMesh() {
  new LaplacianSmooth().filter(mesh, 1);
}

public void subdivideMesh() {
  SubdivisionStrategy subdiv = new MidpointDisplacementSubdivision(mesh.computeCentroid(),0.35f);
    mesh.subdivide(subdiv,0);
}


Panel4P i;

public boolean loop, cap;
public int cw, ch, cd, w, h, d, displaymode, cursormode, drawlayer;

public float amp, atk, fof, FVal, kVal;

public void initpanel() {
  i = new Panel4P(this);
  
  i.addLabel("b1", "Control");
  i.addButton("loop", "Loop", 0);
  i.addButton("singlestep", "Step x20");
  i.addButton("reset", "RESET");
  
  i.addLabel("b1", "Dimensions");
  i.addSlider("cw", "Width", 5, 260, 40);
  i.addSlider("ch", "Height", 5, 260, 40);
  i.addSlider("cd", "Depth", 5, 260, 40);
  
  i.addLabel("b3", "Starting Pattern");
  i.addButton("spot", "Single Spot");
  i.addButton("spots"," Spot Grid");
  i.addButton("xcyl", "Single X Cyl");
  i.addButton("ycyl", "Single Y Cyl");
  i.addButton("zcyl", "Single Z Cyl");
  i.addButton("cyls", "Cyl Grid");
  
  i.addLabel("b2","Display");
  i.addButton("computeVolume", "Recompute Mesh");
  i.addSlider("isoPoint", "Isopoint", 0, 1, 0.56f);
  i.addSlider("volBase", "Volume Base", 0, 1.2f, 0);
  i.addSlider("volMult", "Volume Multiplier", -1, 1, 1);
  i.addButton("cap", "Cap",1);
  
  i.addLabel("b1", "Advanced Process Parameters");
  i.addSlider("FVal", "Base F Value", 0, 0.1f, 0.051f);
  i.addSlider("kVal", "Base k Value", 0, 0.1f, 0.063f);
  i.addSlider("DR1Val", "Base Diffusion Rate 1", 0, 0.4f, 0.16f);
  i.addSlider("DR2Val", "Base Diffusion Rate 2", 0, 0.4f, 0.08f);
  
  i.addLabel("bb", "Mesh");
  i.addButton("smoothMesh", "Smooth Mesh");
  i.addButton("subdivideMesh", "Subdivide Mesh");
  
  i.addLabel("b4", "IO");
  i.addButton("export", "Export to STL");
}
public void export() {
  String of=selectOutput("Export STL file");
    if (of!=null) {
      int sl=max(0,of.length()-4);
      if (!(of.substring(sl).toLowerCase().equals(".stl"))) {
        of=of.concat(".stl");
      }
      //computeVolume();
      //drawFilledMesh();
      mesh.saveAsSTL(of);
    }
}
public void spot() {
  ReactP5Generate.randomSphere(prd);
  computeVolume();
}

public void spots() {
  for (int i=0;i<w;i+=9) {
    for (int j=0;j<h;j+=9) {
      for (int k=0;k<d;k+=9) {
        ReactP5Generate.sphere(prd, i+(float)random(4)-2, j+(float)random(4)-2, k+(float)random(4)-2, (float)random(4)+1);
      }
    }
  }
  computeVolume();
}

public void xcyl() {
  ReactP5Generate.randomXCylinder(prd);
  computeVolume();
}

public void zcyl() {
  ReactP5Generate.randomZCylinder(prd);
  computeVolume();
}

public void ycyl() {
  ReactP5Generate.randomYCylinder(prd);
  computeVolume();
}

public void cyls() {
  for (int j=0;j<h;j+=9) {
    for (int k=0;k<d;k+=9) {
      ReactP5Generate.cylinder(prd, (int)random(3), k+(float)random(4)-2, j + (float)random(4)-2, (float)random(4)+1);
    }
  }
  computeVolume();
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "GrayReact" });
  }
}
