import volatileprototypes.ReactP5.*;

import peasy.org.apache.commons.math.*;
import peasy.*;
import peasy.org.apache.commons.math.geometry.*;

import processing.opengl.*;

PFont font;
PeasyCam cam;

int cnt=0;
public int scl = 400;

ReactP5 prd;

void setup() {
  size(760,760, OPENGL);
  frameRate(17);
  sphereDetail(5);
  initpanel();
  background(0);
  cam = new PeasyCam(this, 0,0,0,1550);
  font = createFont("Arial",12);
  reset();
  for (int i=0;i<5;i++) {
    spot();
  }
}
 
void draw() {
  background(0);
  if (loop) {
    step();
    computeVolume();
  }
  drawFilledMesh();
  cam.beginHUD();
  textFont(font,12);
  fill(255);
  noStroke();
  text("Iteration: " + cnt, 10, height-10);
  cam.endHUD();
}

public void step() {
    if (react) {
      prd.stepR();
    }
    if (diffu) {
      prd.stepD();
    }
    cnt++;
}

public void singlestep() {
  for (int i=0;i<20;i++) {
    step();
  }
   computeVolume();
}

public void reset() {
  cnt = 0;
  noStroke();
  i.updateVars();
  w=cw;
  h=ch;
  d=cd;
  prd = new ReactP5(w,h,d);
  float[] p = {0.16,0.08};
  prd.setDiffusionParameters(p);
  recomp();
  Vec3D SCALE=new Vec3D(scl,scl,scl);
  volume=new VolumetricSpaceArray(SCALE,w,h,d);
  surface=new ArrayIsoSurface(volume);
  computeVolume();
}
