package server_sharenow;

import java.io.Serializable;

public class UserFetchRequest implements Serializable {

    String name;
    int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserFetchRequest(){
        this.status=-1;
        this.name="";
    }
    public UserFetchRequest(String name){
        this.status=-1;
        this.name=name;
    }
    public UserFetchRequest(int status){
        this.status=status;
        this.name="";
    }

    @Override
    public String toString(){
        return String.valueOf(ServerRequest.USER_FETCH_REQUEST);
    }
}
