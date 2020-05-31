# Instructions for Demo #
## Required Files for Demo ##
To run the sff algorithm, the following files need to be in ds-sim:
* Client.java
* Algorithm.java 
* Server.java
* Job.java
* ds-server
* ds-client
* config.xml files (e.g. s3sampleconfigs (contains x7 config.xml files) , configs(contains x5 config.xml files) etc.)

## Instructions ##
1. Download the directory with all the files required for the demo.

2. Unzip the folder (right-click folder and select 'Extract Here' or 'Extract To' or unzip using terminal by using the command tar -xvf <FILE NAME>  (do not write the <> with the file name)

3. Go to the folder in terminal (right-click the unzipped folder and select 'Open in Terminal' or cd <FILE NAME> (do not write the <> with the file name)

4. To make the ds-server and ds-client executable enter the following commands in terminal:
<br/>chmod +x <ds-server> (do not write the <>)
<br/>chmod +x <ds-client> (do not write the <>)

5. Compile the java code (i.e. Algorithm.java and Client.java) using javac <FILE NAME>.java
<br/>e.g. javac Client.java
<br/>e.g. javac Algorithm.java

6. Run the server-side simulator first using ./ds-server -c <CONFIG FOLDER>/<CONFIG FILE NAME>.xml -v all 
<br/>e.g. ./ds-server -c s3sampleconfigs/ds-config-s3-7.xml -v all

7. Open a new terminal (File --> New Tab or CTRL + SHIFT + T)

8. In the new terminal type java Client -a sff

9. Go back to the server terminal and see the displayed output. 

