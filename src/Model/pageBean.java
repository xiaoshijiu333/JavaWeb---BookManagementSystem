package Model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class pageBean {

    //当前页
    private int currentPage;
    //总页数
    private int totalPage;
    //总记录数
    private int totalCount;
    //当前页书籍
    private List<book> pageList=new ArrayList<>();
    //当前页记录
    private List<records> pageListRec=new ArrayList<>();
    //当前页用户
    private List<admin> pageListUser=new ArrayList<>();
    //一页显示多少条数据
    private int currentCount;
    //分类查询的时候，所必需的cid
    private int cid;
    //查询是否归还的记录的标记
    private int flag;
    //查询是否携带dname的参数
    private String dname;
    //查询是否携带dname的参数
    private String uname;
}
