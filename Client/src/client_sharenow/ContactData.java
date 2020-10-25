package client_sharenow;

public class ContactData {
    private String FullName;
    private String username;
    private String email;
    private String contact;
    private String password;

    public ContactData(String FullName,String username,String email,String contact,String password){
        this.FullName = FullName;
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    public String getFullName(){
        return FullName;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getContact(){
        return contact;
    }

    public String getPassword(){
        return password;
    }
}
