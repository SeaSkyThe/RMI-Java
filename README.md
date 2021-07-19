# RMI-Java

## Execution Steps (WINDOWS):

- COMPILE APPS (Navigate with cmd into the project_folder):

  - javac compute/Compute.java compute/Task.java
  - jar cvf classes/compute.jar compute/*.class
  - javac -cp ./classes/compute.jar engine/ComputeEngine.java
  - javac -cp ./classes/compute.jar client/MagicSquare.java
  - javac -cp ./classes/compute.jar client/Fibonacci.java
  - javac -cp ./classes/compute.jar client/Sort.java
  - javac -cp ./classes/compute.jar client/TCPServer.java client/Sort.java client/MagicSquare.java client/Fibonacci.java
  - javac -cp ./classes/compute.jar client/TCPClient.java client/Sort.java client/MagicSquare.java client/Fibonacci.java
   
- EXECUTE RMI REGISTRY IN A SEPARATED CMD WINDOW

  - set CLASSPATH=disk:\project_folder (My case: set CLASSPATH=C:\Users\SeaSkyThe\Desktop\TPCD)s
  - rmiregistry

- IN A NEW WINDOW EXECUTE THE EXECUTOR:

  - General: java -cp disk:\project_folder;disk:\project_folder\classes\compute.jar -Djava.rmi.server.codebase=file:/disk:/project_folder/classes/compute.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy engine.ComputeEngine
  - My case: java -cp C:\Users\SeaSkyThe\Desktop\TPCD;C:\Users\SeaSkyThe\Desktop\TPCD\classes\compute.jar -Djava.rmi.server.codebase=file:/C:/Users/SeaSkyThe/Desktop/TPCD/classes/compute.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy engine.ComputeEngine


- IN A NEW WINDOW EXECUTE THE SERVER:

  - General: java -cp disk:\project_folder;disk:\project_folder\classes\compute.jar -Djava.rmi.server.codebase=file:/disk:/classes/ -Djava.security.policy=client.policy client.TCPServer
  - My case: java -cp C:\Users\SeaSkyThe\Desktop\TPCD;C:\Users\SeaSkyThe\Desktop\TPCD\classes\compute.jar -Djava.rmi.server.codebase=file:/C:/Users/SeaSkyThe/Desktop/TPCD/classes/ -Djava.security.policy=client.policy client.TCPServer

- IN A NEW WINDOW EXECUTE THE CLIENT:

  - java client/TCPClient.java
