package ideall.gabrielrunescape.com.br.objects;

import java.io.Serializable;

/**
 * Created by gabriel on 27/11/16.
 */

public class Contact implements Serializable{
    private long ID;
    private String name;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
