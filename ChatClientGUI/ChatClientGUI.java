package ChatClientGUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ChatClient.ChatClient;

public class ChatClientGUI extends JFrame {
    private JTextArea messageArea;
    private JTextField messageInput;
    private ChatClient client;
    private JButton exitButton;

    public ChatClientGUI() {
        super("Chat Client Application");

        String name = JOptionPane.showInputDialog(this, "Enter your name:", "Name Entry",
                JOptionPane.PLAIN_MESSAGE);
        this.setTitle("Chat Application - " + name);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color backgroundColor = new Color(240, 240, 240);
        Color buttonColor = new Color(75, 75, 75);
        Color textColor = new Color(50, 50, 50);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBackground(backgroundColor);
        messageArea.setForeground(textColor);
        messageArea.setFont(textFont);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        messageInput = new JTextField();
        messageInput.setBackground(backgroundColor);
        messageInput.setForeground(textColor);
        messageInput.setFont(textFont);
        messageInput.setBorder(BorderFactory.createLineBorder(buttonColor, 1));

        messageInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + name + ": "
                        + messageInput.getText();
                client.sendMessage(message);
                messageInput.setText("");
            }
        });
        add(messageInput, BorderLayout.SOUTH);

        exitButton = new JButton("Exit");
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(buttonFont);

        exitButton.addActionListener(e -> {
            // Send a departure message to the server
            String departureMessage = name + " has left the chat.";
            client.sendMessage(departureMessage);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            System.exit(0);
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor); 
        bottomPanel.add(messageInput, BorderLayout.CENTER);
        bottomPanel.add(exitButton, BorderLayout.EAST); 
        add(bottomPanel, BorderLayout.SOUTH); 

        try {
            this.client = new ChatClient("localhost", 2000, this::onMessageReceived);
            client.startClient();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + e.getMessage(), "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);

        }

    }

    public void onMessageReceived(String message) {
        SwingUtilities.invokeLater(() -> {
            messageArea.append(message + "\n");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatClientGUI chatClientGUI = new ChatClientGUI();
            chatClientGUI.setVisible(true);
        });
    }
}
