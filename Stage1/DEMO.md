# Instructions for Demo

## Requirements
Requires 2 Libraries:

 a. libxml2
 
 b. libxml2-dev
 
To install:
```
sudo apt-get install libxml2 libxml2-dev
```

Also requires JRE/JDK 11 which also needs to be downloaded. 
You will get this prompt as you try to compile a java code yourself.
The terminal will even show you what commands to run to install the java packages so that is fairly self explanatory.
Compiling the Code is in the next session.


## Making the simulator

To open this, in your terminal type this:
```
cd Downloads
```

Then Un-tar the file (you may need to check that tar is installed on the system):
```
tar -xvf ds-sim
```

Go to the folder:
```
cd ds-sim
```

Then build the simulator using this command:
```make```

You will have two files ds-server and ds-client. To make them executable each line followed by the enter/return key:
```
chmod +x ds-server
```
```
chmod +x ds-client
```
Now you will have both the server-side and client-side files ready.

To run the simulator, open two terminals. Now we want to run the server-side simulator FIRST. To do this type the following code in the original terminal:
```
./ds-server -c ds-config.xml -v all
```
Then open up the next terminal to run the client-side. Type the following code:
```
./ds-client
```

## Running the test suite

Running the test suite is rather simple.

The first step is to compile to the client file which we can run using:
```
javac Client.java
```
If you havent downloaded and installed Java before here is when you get a prompt to install java. Once installing java you can then compile the Client.java file.

After compiling the the Client.java there are two thing you can do.
The first being running the server by connecting to the right port and opening the server which can be done by running:
```
./ds-server -c your_configFile.xml -v all
```
Then running:
```
java Client
```

OR actually running the tests1.sh file which can be done using the command:
```
./tests1.sh Client.class
```

# IMPORTANT
If you may have noticed, compared to the instructions given in the tests1.sh file, we are running it without the [-n] in the command.
There is some interaction between that command and the code that does not allow for it to properly run. As such do refrain from running it with the -n command.
