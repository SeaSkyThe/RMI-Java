# RMI-Java


## Execution Steps:

- COMPILE APPS:

javac compute/Compute.java compute/Task.java
jar cvf classes/compute.jar compute/*.class
javac -cp ./classes/compute.jar engine/ComputeEngine.java
javac -cp ./classes/compute.jar client/ComputeSort.java client/Sort.java
javac -cp ./classes/compute.jar client/ComputeMagicSquare.java client/MagicSquare.java

- EXECUTE RMI REGISTRY IN A SEPARATED CMD WINDOW

set CLASSPATH=C:\Users\SeaSkyThe\Desktop\TPCD (or your project folder)
rmiregistry

- IN A NEW WINDOW EXECUTE THE SERVER:

java -cp C:\Users\SeaSkyThe\Desktop\TPCD;C:\Users\SeaSkyThe\Desktop\TPCD\classes\compute.jar -Djava.rmi.server.codebase=file:/C:/Users/SeaSkyThe/Desktop/TPCD/classes/compute.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy engine.ComputeEngine localhost 1099


- IN A NEW WINDOW EXECUTE THE CLIENT:

java -cp C:\Users\SeaSkyThe\Desktop\TPCD;C:\Users\SeaSkyThe\Desktop\TPCD\classes\compute.jar -Djava.rmi.server.codebase=file:/C:/Users/SeaSkyThe/Desktop/TPCD/classes/ -Djava.security.policy=client.policy client.ComputePi localhost 1099