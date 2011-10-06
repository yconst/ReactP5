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

VolumetricSpaceArray volume;
IsoSurface surface;
WETriangleMesh mesh=new WETriangleMesh("fluid");

public float isoPoint;
public float volBase;
public float volMult;

public void computeVolume() {
  float[] volumeData = volume.getData();
  float[] origData = prd.getS()[0];
  for (int i=0;i<volumeData.length;i++) {
    volumeData[i] = volBase + volMult*origData[i];
  }
  //arraycopy(origData,0,volumeData,0,origData.length);
  if (cap) {
    volume.closeSides();
  }
  surface.reset();
  surface.computeSurfaceMesh(mesh,isoPoint);
  float mx = max(w,h,d)*0.6;
  mesh.scale(new Vec3D(w/mx,h/mx,d/mx));
}

void drawFilledMesh() {
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
