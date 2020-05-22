# COMP3100 Distributed Systems

This is an assignment attempted by the students of Macquarie under the unit of Distributed Systems COMP3100. The aim of this assignment is to create a job scheduler based of priority scheduling, implemented into a client-server communication model.

## Introduction

Job schedulers have been used different areas; some uses are more common that others however they are most often in charge of the execution of jobs for unattended background programs. Job schedulers have the task of allocating system resources for the execution of different tasks or jobs by a system.

As for the priority at which the jobs are done, that is entirely dependent on the pre-defined set of rules and the algorithm that the assigned to the job scheduler. It is essentially an automated system that initiates and manages job. Typically, job schedulers are created such that they can easily be controlled by a user; this is done by implementing a GUI that allows users to have a single access point of control.

## Project Description

The project revolves around the concept of job or task scheduling with client-server models. The server is set up to be a Linux based server with the use of a VM. The use of a Linux based server was chosen for its stability and reliability. The code for the client and the server is written in Java as per the ease of use and familiarity from students involved in the project. This job scheduler is created with the topic of implementation in a distributed system in mind.

## Design Philosophy
To create an Intelligent Job Scheduler for distributed systems that allows for improved user interfaces that rival traditional desktop software that performs simulations and visualisations. We will also be using free or open source software (FOSS) libraries and frameworks on both the client-side and server-side that facilitate low-cost and rapid application development that complies to modern practices. 

## Client-side Simulator
A client-side simulator is a simulation program that runs on a computer that makes a request for services. Our client-side simulator will be implementing socket programming and will be using networking services that will be provided to us by the transport layer. The Transport Control Protocol (TCP) will provide a reliable flow of data between client-side simulators whereas the User Datagram Protocol (UDP) will send independent packets of data from one client-side to another with no guarantees about arrival and sequencing. However, in order to utilise the client-side to the best of its ability the group will have to ensure prior to the client-side simulator running,  that no firewall is active and blocks the communication between the client-side and server-side. 


## Server-Side Simulator
A server-side simulator a simulation program that runs on a computer that offers requested services from one or more clients. The server-side simulator provided to all students uses socket programming and is assigned to a specific port. The server-side simulator will then listen to the socket and wait for the client-side to make a connection request.  When the server-side simulator accepts the connection, it will receive a new socket that is assigned to a different port. It is important to note that the server-side will continue to require a new socket and port number so that it can continue to listen to the original socket for connection requests while serving the connected client. 

## Considerations
A consideration of the client-side simulator is the possibility of client-side attacks occurring. Client-side attacks are attacks that initiate when a user downloads malicious content off the internet. They also take advantage of weaknesses in the software that are loaded onto clients. Two types of client-side attacks that need to be considered include content spoofing which tricks a user into believing that the content on a website is true and is not from an external source whereas cross-site Scripting (XSS) enables an attacker to execute scripts in a user’s web browser. 

## Constraints 
A major constraint that needs to be considered for both client-side and server-side simulators is how fast the user is able to catch invalid data on the client-side. If the user is able to catch invalid data on the client-side, they can fix it right away, however if the data gets to the server-side and is rejected, a significant delay is instigated by a round-trip to the server-side and then back to the client-side to inform the user to fix the data. 

## System Architecture
The client-server architecture of the system, consists of a client device sending a task request to the server through the internet. In return, the server will process and accept the job request and efficiently deliver the requested data directly to the client. The fundamental architecture of the system starts with a network protocol. Network protocol is the basic rules and procedures that guide how data is transferred between the client and server database in a system. In our system we use the Transport Control Protocol(TCP) to efficiently transfer data between the server and client. The network protocol combines with a network programming interface to connect between the client and server databases. In our system, we use socket programming as our network program interface. Inorder to create a network domain to allow multiple devices to connect to the system, we must use a windows network technology. Multiplexed connection is also used to aid in handling multiple client connections the server may receive. This gives the system the ability to connect one client to multiple servers which can provide different services dividing the workload and completing tasks more time efficiently. Most importantly, a database server is required for the client server to establish a connection with inorder to send requests and receive data.  This architecture allows the server to oversee it’s clients and efficiently manage multiple tasks at synchronous times. 

## Components of the system 
To design and develop the priority intelligent scheduler in a simulated distributed system, it is essential to identify the required and desired components that make up the distributed system. The distributed system is made up of two main components: the client side simulator and server side simulator. To use the system, the client must be connected to an electronic device that has internet connection. The server-side in our case is a virtual machine which must have an established internet connection.  Together, they are connected through socket-programming which allows them to communicate to each other to perform the functions of the job scheduler in a simulated distributed system. A socket connection means both machines know the network location and TCP port of each other’s device. To establish a connection, the server application requires two sockets of a server socket and an old plain socket, that have been implemented to our system. The system also utilises a discrete event simulator to simulate the system with the client-side and server side simulators.

## Functions of the system 
The system utilizes a discrete event simulator to provide simulation to the system. The server-side simulator was designed and developed prior by the computing department to user specified configurations. The server-side simulator oversees the overall simulation of the distributed system. It simultaneously works alongside the client-side simulator. Our client-side simulator will consist of network services and multiple algorithms to efficiently run and allow job requests to be sent and delivered to the server of the system. The client-side simulator utilises socket programming using the two sockets of a server socket and an old plain socket, and allows it to complete tasks that have been requested by the client. The server socket waits for job requests from the client and processes the task. Upon completion, the old plain socket communicates directly with the client, delivering the requested data and services to it. 

Figure [1] : Relationships and functions occurring within the intelligent system 

## System relationships between components
Figure [1] above based on the simulation steps when executing the client-server task scheduler taken from the project description gives a good illustration of the practical workings of the task scheduler as it is running. Java is the programming language used to enable the client-server communication in the task scheduler. 

In Figure [1], it is important to highlight key processes such as the creation of the system file. This is a critical document needed for the task scheduler to perform its function which is created or written by the Server side. This then creates an open port. The Client side reads this system file and then connects to the Server to establish a connection to then allow the client-server communication for the task scheduler. In other words, the client would then send a request which the server listens for and processes, however if there is an error, the server would generally return an error message and then wait for another request sent by the client. 

As this is a task scheduler, multiple jobs and requests are handled and thus there needs to be some form of way of handling these requests or rather prioritizing these requests, which is done by prioritizing tasks which have the lowest estimated runtime and executing them first. This means that tasks that may have been sent first but have a longer estimated runtime compared to a task sent after them, will get lower priority of execution and thus be executed after the a task with a lower estimated runtime; allowing for a task that would take a large amount of time to delay the entire system from running other tasks.

Several packages from the Java libraries were imported to enable the use of different features required for creating a Task Scheduler. An example of this being the use of the XML Parser. The server writes information into an xml file called ‘system.xml’ which is a configuration file that provides support for XML which to run. The client is then able to read the information from the various tags provided. The Java function then assigns the attributes in the XML file to its appropriate data types.

The client-server communication is separated into two different files: one file to run the Client-side Java code and the other used for running the Server-side Java code. When initialising the task scheduler, the Server (with the config file) must be booted to allow an open connection on a specified port. The client can then be connected to the server in which the client will simulate events.

The task scheduler is run on a Linux machine. Linux servers are reliable for their stability and its ability to continue running even after a long duration of use. As such, console commands such as ‘ls’, ‘java <file_name>’ and ‘javac filename.java’ will be utilised allowing Java in a Linux environment. As the Linux terminal is a GCC, one can compile their java code without the need for an external Java compiler.



