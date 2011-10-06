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
