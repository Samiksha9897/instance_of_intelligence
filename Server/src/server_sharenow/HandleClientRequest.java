package server_sharenow;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class HandleClientRequest extends ClientHandler implements Runnable {
    private Socket socket;
    ObjectInputStream ois;//Input Stream of client socket
    ObjectOutputStream oos;//Output Stream of client socket

    public HandleClientRequest(Socket clientSocket, SocketServer ss, SimpleDateFormat sdf, int counter) {
        super(clientSocket, ss, sdf, counter);
    }


    @Override
    public void run() {
        while (true) {
            try {
                Object obj=null;
                try{
                    obj = ois.readObject();
                }catch (EOFException e){
                    System.out.println("Client disconnected");
                    break;
                }
                String req = (String) obj.toString();
                if (req.equals(String.valueOf(ServerRequest.SIGNUP_REQUEST))) {
                    Signup newsignup = new Signup((SignupRequest) obj);
                    String status = newsignup.put();
                    oos.writeObject(status);
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.LOGIN_REQUEST))) {
                    Login login = new Login((LoginRequest) obj);
                    User user = login.check();
                    oos.writeObject(user);
                    oos.flush();
                }

                /*if (req.equals(String.valueOf(ServerRequest.CATEGORIES))) {
                    CategoriesFetch categoriesFetch = new CategoriesFetch();
                    Categories categories = categoriesFetch.fetch();
                    oos.writeObject(categories);
                    oos.flush();
                }*/

                if (req.equals(String.valueOf(ServerRequest.ADMIN_LOGIN_REQUEST))) {
                    AdminLoginRequest adminLoginRequest = (AdminLoginRequest) obj;
                    AdminLogin adminLogin = new AdminLogin(adminLoginRequest);
                    oos.writeObject(adminLogin.check());
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.USER_FETCH_REQUEST))) {
                    UserFetchRequest userFetchRequest = (UserFetchRequest) obj;
                    UserFetch userFetch = new UserFetch();
                    if (!userFetchRequest.getName().equals(""))
                        oos.writeObject(userFetch.fetch(userFetchRequest.getName()));
                    else if (userFetchRequest.getStatus() != -1)
                        oos.writeObject(userFetch.fetch(Integer.toString(userFetchRequest.getStatus())));
                    else
                        oos.writeObject(userFetch.fetch());
                    oos.flush();
                }
                /*if (req.equals(String.valueOf(ServerRequest.CATEGORY_REMOVE_REQUEST))) {
                    CategoryRemoveRequest categoryRemoveRequest = (CategoryRemoveRequest) obj;
                    CategoryRemove categoryRemove = new CategoryRemove(categoryRemoveRequest);
                    oos.writeObject(categoryRemove.remove());
                    oos.flush();
                }*/

            } catch (StreamCorruptedException e){
                try {
                    ois=new ObjectInputStream(socket.getInputStream());
                    oos=new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
