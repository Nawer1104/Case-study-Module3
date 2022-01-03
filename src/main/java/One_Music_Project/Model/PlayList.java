package One_Music_Project.Model;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private int pid;
    private int uid;
    private String pname;

    public PlayList(int pid, int uid, String pname) {
        this.pid = pid;
        this.uid = uid;
        this.pname = pname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "pid=" + pid +
                ", uid=" + uid +
                ", pname='" + pname + '\'' +
                '}';
    }
}
