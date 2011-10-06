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

// Gray-Scott Reaction

package volatileprototypes.ReactP5;

public class GSReactionMethod extends ReactionMethod {

    // Constructor.
    public GSReactionMethod(ReactP5 nbase) {
        super(nbase);
    }
    
    // The actual solver (step) function for the Gray-Scott
    // equations, implemented to use multi-threading.
    @Override
    protected final void stepFunction(int step, int offset) {
        int i, j, k, p;
        int w=base.w, h=base.h, d=base.d;
        float uVal, vVal, FVal, kVal;
        float[][] S = base.S;
        float[] bF = base.P[0];
        float[] bk = base.P[1];
        
        for(i=offset;i<w;i+=step) {
            for(j=0;j<h;j++) {
                for(k=0;k<d;k++) {
                    p = i + j*w + k*w*h;
                    uVal = S[0][p];
                    vVal = S[1][p];
                    FVal = bF[p];
                    kVal = bk[p];
                    S[0][p] = S[0][p] + FVal * ( 1.0f - uVal ) - uVal * vVal * vVal;
                    S[1][p] = S[1][p] - (FVal + kVal) * vVal + uVal * vVal * vVal;
                }
            }
        }
    }
    
    @Override
    protected float[][] createDefaultSubstancesArray() {
        float[][] R = new float[2][base.w*base.h*base.d];
        for (int i=0;i<R[0].length;i++) {
            R[0][i] = 1.0f;
            R[1][i] = 0.0f;
        }
        return R;
    }
    
    @Override
    protected float[][] createDefaultParametersArray() {
        float[][] R = new float[2][base.w*base.h*base.d];
        for (int i=0;i<R[0].length;i++) {
            R[0][i] = 0.051f; //F
            R[1][i] = 0.063f; //k
        }
        return R;
    }
}