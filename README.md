# KAOS
A kinetic theory of active particles modeling and analysis tool

To compile under Linux:

 $cd DeMeo
 $javac *.java
 $jar cvf ../DeMeo.jar *.class
 $cd ..
 $javac -cp jgraph-5.13.0.0.jar:jgrapht-ext-0.9.1-uber.jar:jgraphx-2.0.0.1.jar:tablelayout.jar:DeMeo.jar:. JGraphTXAdapter.java
 $javac -cp jgraph-5.13.0.0.jar:jgrapht-ext-0.9.1-uber.jar:jgraphx-2.0.0.1.jar:tablelayout.jar:DeMeo.jar:. Demo.java
 $jar -cvmf MANIFEST.MF kaos.jar *.jar *.class DeMeo icons
 
To run under Linux:

 $java -jar kaos.jar