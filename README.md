# KAOS
A kinetic theory of active particles modeling and analysis tool

To compile under Linux:

    $javac Kaos/*.java layout/*.java *.java

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
