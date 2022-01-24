package main;

import connection.PostService;
import post_create.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class HandleRequest implements Runnable{
    private Socket socket;

    public HandleRequest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PostData postData = (PostData) ois.readObject();
            UserConfigs configs = new UserConfigs();
            Post post = new Post(postData.getKeyword(), configs.getSiteName(), configs.getSiteUrl(), postData.getStatus());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            try {
                PostService.savePost(post, configs);
                oos.writeObject("Uspesno sacuvan post!");
            } catch (SQLException e) {
                oos.writeObject(e.toString());
                e.printStackTrace();
            }
            Main.CLIENT_NUMBER--;
            System.out.println("Client disconnected...");
            System.out.println("Total clients: " + Main.CLIENT_NUMBER);
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
