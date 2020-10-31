package client_sharenow;

import java.io.Serializable;
import java.util.Date;

public class Files implements Serializable{

    private String name;
    private User user;
    private Date uploaded_date,expiry;
    private String filetype;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFileType(String filetype) {
        this.filetype = filetype;
    }

    public void setDate(Date uploaded_date) {
        this.uploaded_date = uploaded_date;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public String getFileType() {
        return filetype;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Date getDate() {
        return uploaded_date;
    }

    public Date getExpiry() {
        return expiry;
    }

}
