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

// Helper class to generate various shapes and distributions of substances.

package volatileprototypes.ReactP5;

public class ReactP5Generate {
    
    // Direction Constants
    public static int DIRECTION_X = 0;
    public static int DIRECTION_Y = 1;
    public static int DIRECTION_Z = 2;
    
    // Creates a sphere of random position and dimensions.
    public static void randomSphere(ReactP5 r5) {
        float x = (float)Math.random() * r5.w;
        float y = (float)Math.random() * r5.h;
        float z = (float)Math.random() * r5.d;
        float r = (float)Math.random() * Math.min(r5.w, Math.min(r5.h, r5.d)) * 0.5f;
        sphere(r5, x, y, z, r);
    }
    
    // Creates a sphere of user-defined position and dimensions.
    public static void sphere(ReactP5 r5, float x, float y, float z, float r) {
        float[][] S = r5.getSubstances();
        float rr = r*r;
        int d = r5.d;
        int h = r5.h;
        int w = r5.w;
        for(int k=0; k<d; ++k)
        {
            for(int j=0; j<h; ++j)
            {
                for(int i=0; i<w; ++i)
                {
                    float r2 = (x-i)*(x-i) + (y-j)*(y-j) + (z-k)*(z-k);
                    if(r2 <= rr)
                    {
                        S[0][i + j*w + k*w*h] = 1.0f;
                        S[1][i + j*w + k*w*h] = 1.0f;
                    }
                }
            }
        }
    }
    
    // Creates a random cylinder with an X-orientation.
    public static void randomXCylinder(ReactP5 r5) {
        float y = (float)Math.random() * r5.h;
        float z = (float)Math.random() * r5.d;
        float r = (float)Math.random() * Math.min(r5.h, r5.d) * 0.5f;
        cylinder(r5, DIRECTION_X, y, z, r);
    }
    
    // Creates a random cylinder with an Y-orientation.
    public static void randomYCylinder(ReactP5 r5) {
        float x = (float)Math.random() * r5.w;
        float z = (float)Math.random() * r5.d;
        float r = (float)Math.random() * Math.min(r5.w, r5.d) * 0.5f;
        cylinder(r5, DIRECTION_Y, x, z, r);
    }
    
    // Creates a random cylinder with an Z-orientation.
    public static void randomZCylinder(ReactP5 r5) {
        float x = (float)Math.random() * r5.w;
        float y = (float)Math.random() * r5.h;
        float r = (float)Math.random() * Math.min(r5.w, r5.h) * 0.5f;
        cylinder(r5, DIRECTION_Z, x, y, r);
    }
    
    // Creates a cylinder with user-defined orientation, position and radius.
    public static void cylinder(ReactP5 r5, int direction, float d1, float d2, float r) {
        float[][] S = r5.getSubstances();
        float rr = r*r;
        int d = r5.d;
        int h = r5.h;
        int w = r5.w;
        if (direction == DIRECTION_X) {
            for(int k=0; k<d; ++k)
            {
                for(int j=0; j<h; ++j)
                {
                    for(int i=0; i<w; ++i)
                    {
                        float r2 = (d1-j)*(d1-j) + (d2-k)*(d2-k);
                        if(r2 <= rr)
                        {
                            S[0][i + j*w + k*w*h] = 1.0f;
                            S[1][i + j*w + k*w*h] = 1.0f;
                        }
                    }
                }
            }
        } else if (direction == DIRECTION_Y) {
            for(int k=0; k<d; ++k)
            {
                for(int j=0; j<h; ++j)
                {
                    for(int i=0; i<w; ++i)
                    {
                        float r2 = (d1-i)*(d1-i) + (d2-k)*(d2-k);
                        if(r2 <= rr)
                        {
                            S[0][i + j*w + k*w*h] = 1.0f;
                            S[1][i + j*w + k*w*h] = 1.0f;
                        }
                    }
                }
            }
        } else if (direction == DIRECTION_Z) {
            for(int k=0; k<d; ++k)
            {
                for(int j=0; j<h; ++j)
                {
                    for(int i=0; i<w; ++i)
                    {
                        float r2 = (d1-i)*(d1-i) + (d2-j)*(d2-j);
                        if(r2 <= rr)
                        {
                            S[0][i + j*w + k*w*h] = 1.0f;
                            S[1][i + j*w + k*w*h] = 1.0f;
                        }
                    }
                }
            }
        }
    }
}