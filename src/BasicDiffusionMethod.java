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

// A class implementing a basic diffusion method.

package volatileprototypes.ReactP5;

public class BasicDiffusionMethod extends DiffusionMethod {
    
    // Constructor. 
    public BasicDiffusionMethod(ReactP5 nbase) {
        super(nbase);
        createNeighborMap();
    }
    
    // The actual diffusion method. Makes use of a neighbor map
    // for speedup.
    @Override
    protected final void stepFunction(int step, int offset) {
        int i,j,k;
        int w=base.w, h=base.h, d=base.d;
        float[][] S = base.S;
        float[][] Sn = base.Sn;
        float[][] dRS = base.dRS;
        
        for (int n=0;n<S.length;n++) {
            for(i=offset;i<w;i+=step) {
                for(j=0;j<h;j++) {
                    for(k=0;k<d;k++) {
                        int p = i+j*w+k*w*h;
                        Sn[n][p] = S[n][p] +
                        dRS[n][p] * ( S[n][N[0][p]] + S[n][N[1][p]] 
                                                  + S[n][N[2][p]] + S[n][N[3][p]] 
                                                  + S[n][N[4][p]] + S[n][N[5][p]] 
                                                  - 6*S[n][p] );
                    }
                }
            }
        }
        swap();
    }
}