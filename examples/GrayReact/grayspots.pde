public void spot() {
  makeSpot((int)random(w),(int)random(h),(int)random(d),(int)random(10)+1);
  computeVolume();
}

public void spots() {
  for (int i=0;i<w;i+=9) {
    for (int j=0;j<h;j+=9) {
      for (int k=0;k<d;k+=9) {
        makeSpot(i+(int)random(4)-2,j+(int)random(4)-2,k+(int)random(4)-2,(int)random(4)+1);
      }
    }
  }
  computeVolume();
}

public void cyl() {
  makeCyl((int)random(h),(int)random(d),(int)random(10)+1);
  computeVolume();
}

public void cyls() {
  for (int j=0;j<h;j+=9) {
    for (int k=0;k<d;k+=9) {
      makeCyl(j+(int)random(4)-2,k+(int)random(4)-2,(int)random(4)+1);
    }
  }
  computeVolume();
}

public void xcyl() {
  makeXCyl((int)random(w),(int)random(h),(int)random(10)+1);
  computeVolume();
}

public void xcyls() {
  for (int j=0;j<w;j+=9) {
    for (int k=0;k<h;k+=9) {
      makeXCyl(j+(int)random(4)-2,k+(int)random(4)-2,(int)random(4)+1);
    }
  }
  computeVolume();
}

synchronized public void makeSpot(int x, int y, int z, int r) {
  float[][] S = prd.getS();
  int rr = r*r;
  for(int k=0; k<d; ++k)
  {
    for(int j=0; j<h; ++j)
    {
      for(int i=0; i<w; ++i)
      {
        int r2 = (x-i)*(x-i) + (y-j)*(y-j) + (z-k)*(z-k);
        if(r2 <= rr)
        {
          S[0][i + j*w + k*w*h] = 1.0;
          S[1][i + j*w + k*w*h] = 1.0;
        }
      }
    }
  }
}

synchronized public void makeCyl(int y, int z, int r) {
  float[][] S = prd.getS();
  int rr = r*r;
  for(int k=0; k<d; ++k)
  {
    for(int j=0; j<h; ++j)
    {
      for(int i=0; i<w; ++i)
      {
        int r2 = (y-j)*(y-j) + (z-k)*(z-k);
        if(r2 <= rr)
        {
          S[0][i + j*w + k*w*h] = 1.0;
          S[1][i + j*w + k*w*h] = 1.0;
        }
      }
    }
  }
}

synchronized public void makeXCyl(int x, int y, int r) {
  float[][] S = prd.getS();
  int rr = r*r;
  for(int k=0; k<d; ++k)
  {
    for(int j=0; j<h; ++j)
    {
      for(int i=0; i<w; ++i)
      {
        int r2 = (x-i)*(x-i) + (y-j)*(y-j);
        if(r2 <= rr)
        {
          S[0][i + j*w + k*w*h] = 1.0;
          S[1][i + j*w + k*w*h] = 1.0;
        }
      }
    }
  }
}

//Recompute parameter arrays using current emitters and base values
public void recomp() {
  float[][] substances = new float[2][w*h*d];
  float[][] parameters = new float[2][w*h*d];
  for (int i=0;i<w;i++) {
    for (int j=0;j<h;j++) {
      for (int k=0;k<d;k++) {
        int pos = i + j*w + k*w*h;
        parameters[0][pos] = FVal;
        parameters[1][pos] = kVal;
      }
    }
  }
  prd.setS(substances);
  prd.setReactionParameters(parameters);
}
