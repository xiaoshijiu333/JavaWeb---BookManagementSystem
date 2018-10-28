package Model;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class catagery {
    private int id;
    private String cname;

    public catagery(int id, String cname) {
        this.id = id;
        this.cname = cname;
    }

    public catagery() {
    }

    public catagery(String cname) {
        this.cname = cname;
    }
}
