JavaPlot
--------
A pure Java wrapper for gnuplot application.


This library can be used as a way to create gnuplot plots on the fly through pure Java commands.
In contrast with other common gnuplot java libraries, this library uses java structures to store the various plot parameters, including datasets. Moreover, is flexible enough to give special parameters to gnuplot, evn if the library does not support it (yet). Moreover, it uses java's Exceptions to inform the user if something went wrong.

Java 1.5 (or better) is needed for this library. The reason is the extensive usage of various 1.5 technologies, such as Generics and autoboxing, to help maipulation of plot data. It has been tested with gnuplot 4.2. Older versions might or might not work.

This library has been checked in Windows XP, Linux (Debian) and Mac OS X (Tiger). It should work on any other system, if you fine tune the special parameters needed.


Usage
-----

First you have to include this library in your classpath. Then the easiest way to start creating plots, is to create a new instance of JavaPlot object. For more detailed information, see the provided javadoc. Most methods should be self explanatory.

The only thing I want to note is that, most classes extend "ProeprtiesHolder" class. This class is able to store pairs of values (such as key-value pairs). Use the set() and unset() method of this class to add parameters which will be used when creating the gnuplot program. See more information on how to use these methods in the javadoc.

There are some things that are not supported yet. These are mainly the multiplot environment and splot-family commands. Still, using the technic noted above, it is still possible to simulate them.


Feedback
--------

Any problems, suggestions, corrections, ideas e.t.c. are strongly welcome. Note that this library is still alpha quality, in the sense that the library API is not fixed yet and it will change in the next version. The reason that it is downloadable, is because I believe that it is already very useful and more or less complete already (and of course I need some feedback).

To contact me, write to:
panayotis@panayotis.com

Thank you for trying this software.
