package Model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class records {
    private int id;
    private String dname;
    private String uname;
    private String lendtime;
    private String returntime;
    private String shouldtime;

    public records(int id, String dname, String uname, String lendtime, String returntime, String shouldtime) {
        this.id = id;
        this.dname = dname;
        this.uname = uname;
        this.lendtime = lendtime;
        this.returntime = returntime;
        this.shouldtime = shouldtime;
    }

    public records() {
    }
}
