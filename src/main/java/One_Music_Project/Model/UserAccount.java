package One_Music_Project.Model;

public class UserAccount {
    private int uid;
    private String uname;
    private String uaccount;
    private String upassword;
    private String uimg;
    private double ubalance;
    private int ispremium;
    private int isuser;
    private int isadmin;

    public UserAccount(int uid, String uname, String uaccount, String upassword, String uimg, double ubalance, int ispremium, int isuser, int isadmin) {
        this.uid = uid;
        this.uname = uname;
        this.uaccount = uaccount;
        this.upassword = upassword;
        this.uimg = uimg;
        this.ubalance = ubalance;
        this.ispremium = ispremium;
        this.isuser = isuser;
        this.isadmin = isadmin;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public double getUbalance() {
        return ubalance;
    }

    public void setUbalance(double ubalance) {
        this.ubalance = ubalance;
    }

    public int getIspremium() {
        return ispremium;
    }

    public void setIspremium(int ispremium) {
        this.ispremium = ispremium;
    }

    public int getIsuser() {
        return isuser;
    }

    public void setIsuser(int isuser) {
        this.isuser = isuser;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", uaccount='" + uaccount + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uimg='" + uimg + '\'' +
                ", ubalance='" + ubalance + '\'' +
                ", ispremium=" + ispremium +
                ", isuser=" + isuser +
                ", isadmin=" + isadmin +
                '}';
    }
}
