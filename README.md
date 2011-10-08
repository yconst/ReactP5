# ReactP5


## Introduction

Reaction–diffusion systems are mathematical models which explain how the concentration of one or more substances distributed in space changes under the influence of two processes: local chemical reactions in which the substances are transformed into each other, and diffusion which causes the substances to spread out over a surface in space.

This description implies that reaction–diffusion systems are naturally applied in chemistry. However, the system can also describe dynamical processes of non-chemical nature. Examples are found in biology, geology and physics and ecology. A traditionally common application of such processes is the modeling of naturally occurring textures, such as animal hide patterns.

Reaction-diffusion systems rely on the discretization of a portion of space within which the reaction and diffusion algorithms are repeatedly applied to model the behavior of the system over time.


## The ReactP5 Library

The ReactP5 library provides an easy way for building 3-dimensional Reaction-Diffusion simulations. It makes use of multi-threading to speed up the simulation. In addition to the 2 Reaction methods and the simple Diffusion method that is included in the library the possibility is offered to extend the library's functionality by implementing new Reaction and Diffusion methods on top of the existing Generic Reaction/Diffusion Method Class.

Please note that the library is responsible for the simulation of the Reaction/Diffusion system, not it's visualization. For this purpose there exist already excellent libraries for Processing. See [Libraries](http://processing.org/reference/libraries/) for more information.


## Using ReactP5
    
Import:

    import volatileprototypes.ReactP5.*;
    
Create a new ReactP5 simulation. A Gray-Scott Reaction/Diffusion simulation
is created by default:

    ReactP5 rp5 = new ReactP5(width_dimension, height_dimension, depth_dimension);
    
Set simulation parameters:

    rp5.setReactionParameter(0, 0.2);
    rp5.setReactionParameter(1, 0.5);
    rp5.setDiffusionParameter(0, 0.3);
    
Create a few random shapes:

    ReactP5Generate.randomSphere(rp5);
    ReactP5Generate.randomCylinder(rp5);
    
Step the simulation:

    rp5.step();
    rp5.step(20);
    
Reset the simulation:

    rp5.reset();    
    
Choose a Reaction and Diffusion method:

    rp5.SetReactionMethod(new FNReactionMethod());
    rp5.SetDiffusionMethod(new BasicDiffusionMethod());
    
    
## License

ReactP5

Multi-threaded Reaction-Diffusion Library for Processing.

Copyright (c) 2011, Volatile Prototypes / Ioannis (Yiannis) Chatzikonstantinou,
All rights reserved.
http://volatileprototypes.com
http://yconst.com
 
 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the following conditions are met:
 	- Redistributions of source code must retain the above copyright 
 notice, this list of conditions and the following disclaimer.
 	- Redistributions in binary form must reproduce the above copyright 
 notice, this list of conditions and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 OF SUCH DAMAGE.

    