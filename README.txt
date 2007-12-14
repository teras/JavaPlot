JavaPlot
--------
A pure Java wrapper for gnuplot application.


This library can be used as a way to create gnuplot plots on the fly through pure Java commands.
In contrast with other common gnuplot java libraries, this library uses java structures to store the various plot parameters, including datasets. Moreover, is flexible enough to give special parameters to gnuplot, evn if the library does not support it (yet). Moreover, it uses java's Exceptions to inform the user if something went wrong.

Java 1.5 (or better) is needed for this library. The reason is the extensive usage of various 1.5 technologies, such as Generics and autoboxing, to help maipulation of plot data. It has been tested with gnuplot 4.2. Older versions might or might not work.

This library has been checked in Windows XP, Linux (Debian) and Mac OS X (Tiger & Leopard). It should work on any other system, if you fine tune the special parameters needed.


Usage
-----

First you have to include this library in your classpath. Then the easiest way to start creating plots, is to create a new instance of JavaPlot object.

A test case can be found under test/com/panayotis/gnuplot/GNUPlotTest.java. It needs JUnit4 to run, but you can safely copy&paste the ocde from this example to match your needs. For more detailed information, see the provided javadoc. Most methods should be self explanatory.

If you want to go deeper into the library, it is important to understand "ProeprtiesHolder" class, which is the base properties holder of this library. . This class is able to store pairs of values (such as key-value pairs). Use the set() and unset() method of this class to add parameters which will be used when creating the gnuplot program. 

There are some things that are not supported yet. These are mainly the multiplot environment and splot-family commands. Still, using methods like getPreInit() and getPostInit() you might be able to simulate them.

If you want to use SVG output in Java, you need a library to handle SVG files. Such a library is SVGSalamander provided with this package. There is a bug in this library, though, which ignores color values. Thus all colors in SVG graphs are black.


Feedback
--------

Any problems, suggestions, corrections, ideas e.t.c. are strongly welcome. Note that this library is still alpha quality, in the sense that the library API is not fixed yet and it will change in the next version. The reason that it is downloadable, is because I believe that it is already very useful and more or less complete already (and of course I need some feedback).

To contact me, write to:
panayotis (a) panayotis.com

Thank you for trying this software.
