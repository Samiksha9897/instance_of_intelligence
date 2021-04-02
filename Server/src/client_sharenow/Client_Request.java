package client_sharenow;

import java.io.*;
import java.net.Socket;

public class Client_Request implements Runnable {
    private Socket socket;
    ObjectInputStream ois;//Input Stream of client socket
    ObjectOutputStream oos;//Output Stream of client socket

    public Client_Request(Socket socket) {
        this.socket = socket;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                Object obj;
                try {
                    obj = ois.readObject();
                } catch (EOFException e) {
                    System.out.println("Client disconnected");
                    break;
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                    break;
                }
                assert obj != null;
                String req = obj.toString();
                System.out.println(req);
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
                    System.out.println("d");
                    try {
                        // yaha kya kiya ha ? new server kyo ?
                        //sir server ka thread start krna tha yahan se login k baad
                        // 5000 port to busy hoga
                        //toh koi or port se connect karu? yes
                        Thread s = new Thread(new SERVER(5001, socket));
                        s.start();
                        System.out.println("a");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


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
                        oos.writeObject(userFetch.fetch());
                    else
                        oos.writeObject(userFetch.fetch());
                    oos.flush();
                }

            } catch (StreamCorruptedException e) {
                /*
                try {
                    ois=new ObjectInputStream(socket.getInputStream());
                    oos=new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }*/
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }


    }
}
