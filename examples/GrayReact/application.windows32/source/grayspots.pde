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


