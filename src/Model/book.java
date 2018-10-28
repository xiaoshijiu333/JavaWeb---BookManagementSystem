package Model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class book {
    private int id;
    private String dname;
    private String writer;
    private String position;
    private int have_count;
    private int lend_count;
    private String book_desc;
    private int cid;

    public book(int id, String dname, String writer, String position, int have_count, int lend_count, String book_desc, int cid) {
        this.id = id;
        this.dname = dname;
        this.writer = writer;
        this.position = position;
        this.have_count = have_count;
        this.lend_count = lend_count;
        this.book_desc = book_desc;
        this.cid = cid;
    }

    public book() {
    }

    public book(String dname, String writer, String position, int have_count, int lend_count, String book_desc, int cid) {
        this.dname = dname;
        this.writer = writer;
        this.position = position;
        this.have_count = have_count;
        this.lend_count = lend_count;
        this.book_desc = book_desc;
        this.cid = cid;
    }
}
