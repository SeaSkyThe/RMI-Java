# RMI-Java


## Execution Steps (WINDOWS):

- COMPILE APPS (Navigate with cmd into the project_folder):

  - javac compute/Compute.java compute/Task.java
  - jar cvf classes/compute.jar compute/*.class
  - javac -cp ./classes/compute.jar engine/ComputeEngine.java
  - javac -cp ./classes/compute.jar client/ComputeSort.java client/Sort.java
  - javac -cp ./classes/compute.jar client/ComputeMagicSquare.java client/MagicSquare.java

- EXECUTE RMI REGISTRY IN A SEPARATED CMD WINDOW

  - set CLASSPATH=disk:\project_folder
  - rmiregistry

- IN A NEW WINDOW EXECUTE THE SERVER:

  - java -cp disk:\project_folder;disk:\project_folder\classes\compute.jar -Djava.rmi.server.codebase=file:/disk:/project_folder/classes/compute.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy engine.ComputeEngine localhost 1099


- IN A NEW WINDOW EXECUTE THE CLIENT:

java -cp disk:\project_folder;disk:\project_folder\classes\compute.jar -Djava.rmi.server.codebase=file:/disk:/classes/ -Djava.security.policy=client.policy client.ComputePi localhost 1099
