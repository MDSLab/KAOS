# KAOS
A kinetic theory of active particles modeling and analysis tool

To compile under Linux:

    $cd Kaos
 
    $javac *.java
 
    $jar cvf ../Kaos.jar *.class
 
    $cd ..
 
    $jar -cvmf MANIFEST.MF kaos.jar  *.class Kaos icons org layout com
 
 
To run under Linux:

    $java -jar kaos.jar
    
To compile under Windows:

    cd kaos
    
    javac Kaos\*.java 
    
    javac *.java
    
    jar –cvfm  kaos.jar  manifest.mf   *.class  org  layout  icons  Kaos  com

To start the tool: 

    java –jar kaos.jar
