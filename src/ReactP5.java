//  Copyright (c) 2011, Volatile Prototypes / Ioannis (Yiannis) Chatzikonstantinou,
//  All rights reserved.
//  http://volatileprototypes.com
//  http://yconst.com
// 
//  Redistribution and use in source and binary forms, with or without modification, 
//  are permitted provided that the following conditions are met:
//  	- Redistributions of source code must retain the above copyright 
//  notice, this list of conditions and the following disclaimer.
//  	- Redistributions in binary form must reproduce the above copyright 
//  notice, this list of conditions and the following disclaimer in the documentation 
//  and/or other materials provided with the distribution.
//  
//  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
//  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
//  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
//  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
//  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
//  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
//  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
//  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
//  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
//  OF SUCH DAMAGE.

package volatileprototypes.ReactP5;

public class ReactP5 {

    private ReactionMethod R;              // Reactor module
    private DiffusionMethod D;             // Diffuser module
    
    protected int w,h,d;                   // Dimensions
    
    protected int I, Ir, Id;               // Iterations: General, Reaction, Diffusion
    
    protected float[][] S;                 // Substances
    protected float[][] Sn;                // Next Substances
    
    protected float[][] dRS;               // Substances diffusion rates
    protected float[][] P;                 // parameter arrays
    
    // Constructor. Initializes a copy of ReactP5
    public ReactP5(int nw,int nh,int nd) {
        w = nw;
        h = nh;
        d = nd;
        R = new GSReactionMethod(this);
        D = new BasicDiffusionMethod(this);
        reset();
    }
    
    // Resets by replacing all Arrays with new ones.
    public void reset() {
        S = R.createSubstancesArray();
        Sn = R.createSubstancesArray();
        P = R.createParametersArray();
        dRS = D.createDiffusionArray();
        I = Ir = Id = 0;
    }
    
    //
    // Step Functions
    // ___________________________________________________
    
    // Steps both Reaction & Diffusion methods once.
    public void step() {
        D.step();
        R.step();
        I++;
        Ir++;
        Id++;
    }
    
    // Performs multiple steps of Reaction & Diffusion
    // consequently.
    public void step(int steps) {
        for (int i = 0; i < steps; i++) {
            D.step();
            R.step();
        }
        I+=steps;
        Ir+=steps;
        Id+=steps;
    }
    
    // Only steps reaction method once.
    public void stepR() {
        R.step();
        Ir++;
    }
    
    // Only steps diffusion method once.
    public void stepD() {
        D.step();
        Id++;
    }
    
    //
    // Dimensions
    // ___________________________________________________
    
    // Gets Width of simulation.
    public int getW() {
        return w;
    }
    
    // Gets Height of simulation.
    public int getH() {
        return h;
    }
    
    // Gets Depth of simulation.
    public int getD() {
        return d;
    }
    
    //
    // Iterations
    // ___________________________________________________
    
    public int getIteration() {
        return I;
    }
    
    public int getReactionIteration() {
        return Ir;
    }
    
    public int getDiffusionIteration() {
        return Id;
    }
    
    //
    // Substance Concentrations
    // ___________________________________________________
    
    // Return the array holding substance concentrations.
    public float[][] getSubstances() {
        return S;
    }
    
    // Sets a new substance concentration array.
    public void setSubstances(float[][] nS) {
        S = nS;
        Sn = new float[S.length][S[0].length];
    }
    
    //
    // Diffusion & Reaction Parameters
    // ___________________________________________________
    
    // Creates a uniform field of the given reaction parameters.
    // index, value
    public void setReactionParameter(int index, float value) {
        P = R.createParametersArray(P, index, value);
    }
    
    // Creates a uniform field of the given reaction parameters.
    // [parameter index] value
    public void setReactionParameters(float[] nparm) {
        P = R.createParametersArray(nparm);
    }
    
    // Replaces the current reaction parameter array with a
    // user-defined field of reaction parameters.
    // [parameter index][position] value.
    public void setReactionParameters(float[][] nparam) {
        P = nparam;
    }
    
    // Returns the reaction parameters.
    // [parameter index][position] value
    public float[][] getReactionParameters() {
        return P;
    }
    
    // Creates a uniform field of reaction parameters.
    // index, value
    public void setDiffusionParameter(int index, float value) {
        dRS = D.createDiffusionArray(dRS, index, value);
    }
    
    // Creates a uniform field of diffusion parameters.
    // [parameter index] value
    public void setDiffusionParameters(float[] nparm) {
        dRS = D.createDiffusionArray(nparm);
    }
    
    // Replaces the current diffusion parameter array with a
    // user-defined field of diffusion parameters.
    // [parameter index][position] value.
    public void setDiffusionParameters(float[][] nparam) {
        dRS = nparam;
    }
    
    public float[][] getDiffusionParameters() {
        return dRS;
    }
    
    public void setP(float[][] np) {
        P = np;
    }
    
    //
    // Diffusion & Reaction Methods
    // ___________________________________________________
    
    public void setDiffusionMethod(DiffusionMethod nd) {
        nd.setBase(this);
        D = nd;
        reset();
    }
    
    public void setReactionMethod(ReactionMethod nr) {
        nr.setBase(this);
        R = nr;
        reset();
    }
}