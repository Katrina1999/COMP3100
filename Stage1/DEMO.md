**INSTRUCTIONS FOR DEMO**
1. Open Virtual Machine and select myLinux (this was set up in week1)
2. Open terminal in your virtual machine. 
3. Log in to the terminal using your username and password. 
4. You will need the libraries before compiling and running simulator code:
  a. libxml2
  b. libxml2-dev
  To install these libraries correctly, enter the following into terminal:
  sudo apt-get install libxml2 libxml2-dev
5. After the installation is completed, go to the folder you have downloaded 'ds-sim'. It is assumed that Downloads is the directory for the file. 
   To open this, in your terminal type cd Downloads
6. Un-tar the file (you may need to check that tar is installed on the system):
   tar -xvf ds-sim
7. Go to the folder:
   cd ds-sim
8. To build the simulator you need to enter the following code:
   make
9. You will have two files ds-server and ds-client. To make them executable each line followed by the enter/return key:
   chmod +x ds-server
   chmod +x ds-client
   Now you will have both the server-side and client-side files ready.
10. To run the simulator, open two terminals. Now we want to run the server-side simulator FIRST. To do this type the following code in the original terminal:
    ./ds-server -c ds-config.xml -v all
11. Then open up the next terminal to run the client-side. Type the following code:
    ./ds-client
   
