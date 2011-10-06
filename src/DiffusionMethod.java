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

// Generic class implementing a framework for diffusion methods.

package volatileprototypes.ReactP5;

public class DiffusionMethod extends Solver {
    
    ReactP5 base;

    protected int[][] N;        // Neighbors references for diffusion algorithm acceleration
    
    // Constructor.
    public DiffusionMethod(ReactP5 nbase) {
        base = nbase;
    }
    
    // Swaps current matrix with next. Just a shortcut for convenience.
    protected void swap() {
        float[][] temp = base.S;
        base.S = base.Sn;
        base.Sn = temp;
    }
    
    // Creates a uniform diffusion array with default values.
    protected float[][] createDefaultDiffusionArray() {
        float[][] R = new float[base.S.length][base.w*base.h*base.d];
        for (int i=0;i<R[0].length;i++) {
            for (int j=0;j<R.length;j++) {
                R[j][i] = 0.01f;
            }
        }
        return R;
    }
    
    // Creates a uniform diffusion array with user-defined values.
    protected float[][] createDefaultDiffusionArray(float[] nd) {
        float[][] R = new float[base.S.length][base.w*base.h*base.d];
        for (int i=0;i<R[0].length;i++) {
            for (int j=0;j<R.length;j++) {
                R[j][i] = nd[j];
            }
        }
        return R;
    }
    
    // Creates a neighbor map for neighbor lookup acceleration.
    protected void createNeighborMap() {
        int w=base.w, h=base.h, d=base.d;
        N = new int[6][w*h*d];
        for (int i=0;i<w;i++) {
            for (int j=0;j<h;j++) {
                for (int k=0;k<d;k++) {
                    int p = i + j*w + k*w*h;
                    if (i == 0)     N[0][p] = p + (w-1);       else N[0][p] = p - 1;
                    if (i == w - 1) N[1][p] = p - (w-1);       else N[1][p] = p + 1;
                    if (j == 0)     N[2][p] = p + w*(h-1);     else N[2][p] = p - w;
                    if (j == h - 1) N[3][p] = p - w*(h-1);     else N[3][p] = p + w;
                    if (k == 0)     N[4][p] = p + w*h*(d-1);   else N[4][p] = p - w*h;
                    if (k == d - 1) N[5][p] = p - w*h*(d-1);   else N[5][p] = p + w*h;
                }
            }
        }
    }
}