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

// Generic reaction method implementer class. If you plan on building a reaction solver
// please extend this class. It includes functionality for generating default simulation
// arrays.

package volatileprototypes.ReactP5;

public class ReactionMethod extends Solver {
    
    ReactP5 base;
    
    // Constructor.
    public ReactionMethod(ReactP5 nbase) {
        base = nbase;
    }
    
    // Creates a substance array with default values.
    protected float[][] createDefaultSubstancesArray() {
        return new float[1][base.w*base.h*base.d];
    }
    
    // Creates a substance array with user-defined values.
    protected float[][] createDefaultSubstancesArray(float[] npar) {
        float[][] R = new float[npar.length][base.w*base.h*base.d];
        for (int i=0;i<R[0].length;i++) {
            for (int j=0;j<R.length;j++) {
                R[j][i] = npar[j];
            }
        }
        return R;
    }
    
    // Creates a parameters array with default values.
    protected float[][] createDefaultParametersArray() {
        return new float[1][base.w*base.h*base.d];
    }
    
    // Creates a parameters array with user-defined values.
    protected float[][] createDefaultParametersArray(float[] npar) {
        float[][] R = new float[npar.length][base.w*base.h*base.d];
        for (int i=0;i<R[0].length;i++) {
            for (int j=0;j<R.length;j++) {
                R[j][i] = npar[j];
            }
        }
        return R;
    }
}