package One_Music_Project.Model;

public class Artist {
    private int aid;
    private String description;
    private String aname;
    private String apic;

    public Artist(int aid, String description, String aname, String apic) {
        this.aid = aid;
        this.description = description;
        this.aname = aname;
        this.apic = apic;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApic() {
        return apic;
    }

    public void setApic(String apic) {
        this.apic = apic;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "aid=" + aid +
                ", description='" + description + '\'' +
                ", aname='" + aname + '\'' +
                ", apic='" + apic + '\'' +
                '}';
    }
}
