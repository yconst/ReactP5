import volatileprototypes.ReactP5.*;

import peasy.org.apache.commons.math.*;
import peasy.*;
import peasy.org.apache.commons.math.geometry.*;

import processing.opengl.*;

PFont font;
PeasyCam cam;

public int scl = 400;

ReactP5 prd;

void setup() {
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
 
void draw() {
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
  prd.setDiffusionParameter(0, 0.16);
  prd.setDiffusionParameter(1, 0.08);
  Vec3D SCALE=new Vec3D(scl,scl,scl);
  volume=new VolumetricSpaceArray(SCALE,w,h,d);
  surface=new ArrayIsoSurface(volume);
  computeVolume();
}
