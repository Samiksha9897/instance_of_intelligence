package client_sharenow;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class FileAddRequest implements Serializable{

    private File file;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getFile() {
        return file;
    }

    public void setProduct(File file) {
        this.file = file;
    }

    public FileAddRequest(File file){
        this.file=file;
    }
    @Override
    public String toString(){
        return String.valueOf(ServerRequest.FILE_ADD_REQUEST);
    }

}
