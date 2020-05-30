# Instructions for Demo

In the environment we require these files to be in ds-sim:



*Client.java


*Server.java


*Algorithm.java


*Job.java


*config.xml files


## Steps:
1. Download the directory with all the files required for the demo
2. Unzip the folder
3. Right click ds-sim and open in terminal
Important Step
4. Build the simulator using this command:
```make```

5. You will have two files ds-server and ds-client. To make them executable each line followed by the enter/return key:
```
chmod +x ds-server
chmod +x ds-client
```
Now you will have both the server-side and client-side files ready.

6. To compile the code use the command "javac Client.java" 
7. Run the server using the command "./ds-server -c <config.xml> -v all"
(Change 'config.xml' to the corresponding config file name used)
8. Open a new tab in the terminal (using CTRL + SHIFT + T)
9. Run the client using the command "java Client -a <ff/bf/wf>"
(select only one algorithm to be implemented, either 


   *'ff' = first fit


   *'bf' = best fit


   *'wf' = worst fit
   *'bd' = new algorithm )


10. Go back to the server tab and observe the displayed output of the chosen algorithm

