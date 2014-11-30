FiHaC [![Build Status for davidafsilva/FiHaC](https://img.shields.io/codeship/2fdb2e40-54af-0132-107b-42ab35009c21/master.svg?style=flat-square)](https://codeship.com/projects/49234) [![Coverage Status for davidafsilva/FiHaC](http://img.shields.io/coveralls/davidafsilva/FiHaC/master.svg?style=flat-square)](https://coveralls.io/r/davidafsilva/FiHaC?branch=master)
======
FiHaC stands for File Hash and Cipher.
It is intended to provide a small, but useful set of file related functionality, like hashing and
ciphering/deciphering a user specified file.

Currently, these are the supported algorithms:
* [MD5](http://en.wikipedia.org/wiki/MD5)

Usage:
======
#### Use the HashCalculatorFactory to create a new HashCalculator instance for an arbitrary &lt;ALGORITHM_TYPE&gt; (enumerated at HashAlgorithm)
```java
   final HashCalculator hashCalculator = HashCalculatorFactory.getInstance()
                            .create(HashAlgorithm.<ALGORITHM_TYPE>);
```


Copyright &copy;
=================
    Copyright (C) 2014 David Silva
 
    Redistribution and use in source and binary forms, with or without modification,
    are permitted provided that the following conditions are met:
    
    1. Redistributions of source code must retain the above copyright notice, this
       list of conditions and the following disclaimer.
    
    2. Redistributions in binary form must reproduce the above copyright notice,
       this list of conditions and the following disclaimer in the documentation
       and/or other materials provided with the distribution.
    
    3. Neither the name of the David Silva nor the names of its contributors
       may be used to endorse or promote products derived from this software without
       specific prior written permission.
    
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
    OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
    OF THE POSSIBILITY OF SUCH DAMAGE.
