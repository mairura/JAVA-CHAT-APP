# Concepts Covered in this Java Chat App

1. Creating an engaging UI using Java Swig
2. Implementing core functionalities with Java such as connecting to chat server, sending messages and displaying incoming messages in real time
3. Updating the chat interface dynamically based on user interactions, ensuring smooth and responsive experience
4. Using some advanced concepts like name entry, message timestamps and graceful exit mechanism with departure message
5. Some basic UI implementaion to enhance application usability, appearance and performance

# Fun Part: How to use the app
1. Clone the repo

2. Compile all files into the out/ folder
   
    - Compile : javac -d out ChatServer/ChatServer.java ChatClient/ChatClient.java ChatClientGUI/ChatClientGUI.java

3. Run the server
   
     - Run : java -cp out ChatServer.ChatServer
       
4. In a new terminal, run the GUI client

     - java -cp out ChatClientGUI.ChatClientGUI

# Tip
- You can many instances of the GUI to have many UI interactions to mimic many users in the chat app

# Note
- This is just a starter chat app for in-house usage. Play around with it and reach out where I can improve on it. Thanks.


         
