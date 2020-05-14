#Stage 2 Instructions for Demo

In the environment we require these files to be in ds-sim:



Client.java


Server.java


Algorithm.java


Job.java


and the config.xml files


##Steps:
1. Download the directory with all the files required for the demo
2. Unzip the folder
3. Right click ds-sim and open in terminal
4. To compile the code use the command "javac Client.java" 
5. Run the server using the command "./ds-server -c <config.xml> -v all"
(Change 'config.xml' to the corresponding config file name used)
6. Open a new tab in the terminal (using CTRL + SHIFT + T)
7. Run the client using the command "java Client -a <ff/bf/wf>"
(select only one algorithm to be implemented, either 


  'ff' = first fit


  'bf' = best fit


  'wf' = worst fit )


8. Go back to the server tab and observe the displayed output of the chosen algorithm

