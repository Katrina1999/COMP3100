**INSTRUCTIONS FOR DEMO**

1. Open the Virtual Machine and select myLinux 

2. Open terminal in the virtual machine

3. Log in to the terminal using set username and password

4. The libraries of: libxml2 and libxml2-dev are required before compiling and running simulator code
  To install these libraries correctly, enter the following into terminal:
  sudo apt-get install libxml2 libxml2-dev
  
5. Once installation is completed, go to the folder you have downloaded 'ds-sim'
   To open this, in your terminal type cd 'foldername'
   
6. Un-tar the file with the command:  tar -xvf ds-sim

7. Go to the folder:  cd ds-sim
   
8. To build the simulator, enter the following code: make

9. You have two files ds-server and ds-client
To make them executable each line followed by the enter/return key:
   chmod +x ds-server
   chmod +x ds-client
   Now you have both the server-side and client-side files ready
   
10. To run the simulator, open two terminals
We want to run the server-side simulator FIRST
To do this, we type the following code in the original terminal:
    ./ds-server -c ds-config.xml -v all
    
11. We then open up the next terminal and run the client-side 
Type the following code:
    ./ds-client
   
