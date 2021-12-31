package One_Music_Project.Model;

public class Song {
    private int sid;
    private String sname;
    private String slink;
    private String simg;
    private int srepeat;
    private int aid;
    private int cid;

    public Song(int sid, String sname, String slink, String simg, int srepeat, int aid, int cid) {
        this.sid = sid;
        this.sname = sname;
        this.slink = slink;
        this.simg = simg;
        this.srepeat = srepeat;
        this.aid = aid;
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSlink() {
        return slink;
    }

    public void setSlink(String slink) {
        this.slink = slink;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public int getSrepeat() {
        return srepeat;
    }

    public void setSrepeat(int srepeat) {
        this.srepeat = srepeat;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Song{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", slink='" + slink + '\'' +
                ", simg='" + simg + '\'' +
                ", srepeat=" + srepeat +
                ", aid=" + aid +
                ", cid=" + cid +
                '}';
    }
}
