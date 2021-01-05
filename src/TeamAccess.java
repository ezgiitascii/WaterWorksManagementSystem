
public class TeamAccess {
    private int TT;
    private String Name;
    private String Email;
    private String Address;
    private String Description;
    private String Team;
    private String Status;

    public TeamAccess(int TT, String Name, String Email, String Address, String Description, String Team, String Status) {
        this.TT = TT;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.Description = Description;
        this.Team = Team;
        this.Status = Status;
    }

    public int getTT() {
        return TT;
    }

    public void setTT(int TT) {
        this.TT = TT;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String Team) {
        this.Team = Team;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
