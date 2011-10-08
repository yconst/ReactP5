import volatileprototypes.Panel4P.*;

Panel4P i;

public boolean loop, cap;
public int cw, ch, cd, w, h, d, displaymode, cursormode, drawlayer;

public float amp, atk, fof, FVal, kVal;

void initpanel() {
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
  i.addSlider("isoPoint", "Isopoint", 0, 1, 0.56);
  i.addSlider("volBase", "Volume Base", 0, 1.2, 0);
  i.addSlider("volMult", "Volume Multiplier", -1, 1, 1);
  i.addButton("cap", "Cap",1);
  
  i.addLabel("b1", "Advanced Process Parameters");
  i.addSlider("FVal", "Base F Value", 0, 0.1, 0.051);
  i.addSlider("kVal", "Base k Value", 0, 0.1, 0.063);
  i.addSlider("DR1Val", "Base Diffusion Rate 1", 0, 0.4, 0.16);
  i.addSlider("DR2Val", "Base Diffusion Rate 2", 0, 0.4, 0.08);
  
  i.addLabel("bb", "Mesh");
  i.addButton("smoothMesh", "Smooth Mesh");
  i.addButton("subdivideMesh", "Subdivide Mesh");
  
  i.addLabel("b4", "IO");
  i.addButton("export", "Export to STL");
}
