package Model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter@Getter
public class admin {
    private int id;
    private String uname;
    private  String password;
    private String email;
    private int type;

    public admin(String uname, String password, String email, int type) {
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public admin() {
    }

    public admin(int id, String uname, String password, String email, int type) {
        this.id = id;
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.type = type;
    }



}
