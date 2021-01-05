
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainPage {
    
        private Connection con = null;
        private Statement statement = null;
        private PreparedStatement preparedStatement = null;
        
        public ArrayList<ChiefAccess> chiefDatabase_bring(){
            
            ArrayList<ChiefAccess> output = new ArrayList<ChiefAccess>();
            try {
                statement=con.createStatement();
                String query = "Select * From customerinfo";
                
                ResultSet rs = statement.executeQuery(query);
                
                while(rs.next()) {
                
                int TT = rs.getInt("TT");
                String Name = rs.getString("Name");
                String Email = rs.getString("Email");
                String Address  = rs.getString("Address");
                String Description = rs.getString("Description");
                String Team = rs.getString("Team");
                String Status = rs.getString("Status");
                
                
                output.add(new ChiefAccess(TT, Name, Email, Address, Description, Team, Status));
                
                
                
                
            }
            return output;
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
        
        
        }
        
        public ArrayList<TeamAccess> teamDatabase_bring(){
            
            ArrayList<TeamAccess> output = new ArrayList<TeamAccess>();
            try {
                statement=con.createStatement();
                String query = "Select * From customerinfo";
                
                ResultSet rs = statement.executeQuery(query);
                
                while(rs.next()) {
                
                int TT = rs.getInt("TT");
                String Name = rs.getString("Name");
                String Email = rs.getString("Email");
                String Address  = rs.getString("Address");
                String Description = rs.getString("Description");
                String Team = rs.getString("Team");
                String Status = rs.getString("Status");
                
                
                output.add(new TeamAccess(TT, Name, Email, Address, Description, Team, Status));
                
                
            }
            return output;
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
        
        
        }
        public void teamAssignment_Save(int TT, String new_Team){
            String query ="Update customerinfo set Team = ? where TT = ?";
            try {
                
                preparedStatement =con.prepareStatement(query);
                
                preparedStatement.setString(1, new_Team);
                preparedStatement.setInt(2, TT);
                
                preparedStatement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public String sendMail(int TTT,String body)
        {
            String toMail="";
             try {
                //statement=con.createStatement();
                String query = "Select * From customerinfo";
                //String query = "Select * From customerinfo where TT = ?";
                preparedStatement =con.prepareStatement(query);
                //preparedStatement.setInt(1, TTT);
                //System.out.println("TT: "+TTT);
                        
                ResultSet rs = statement.executeQuery(query);
                
                while(rs.next()) {
                
                    
                String Email = rs.getString("Email");
                int TT = rs.getInt("TT");
                /*
                String Name = rs.getString("Name");
                
                String Address  = rs.getString("Address");
                String Description = rs.getString("Description");
                String Team = rs.getString("Team");
                String Status = rs.getString("Status");
                */
                if(TT==TTT)
                {
                    toMail=Email;
                    System.out.println("Email sent to "+Email+ "\nSubject: "+body);
                    //JOptionPane.showMessageDialog(TeamDatabase, "You Pressed Button FOUR","INFORMATION",  JOptionPane.INFORMATION_MESSAGE);
                    
                    Mail.sendFromGMail("Status Updated", "Your status is updated: "+body, Email);
                }
                //output.add(new ChiefAccess(TT, Name, Email, Address, Description, Team, Status));
                
                
            }
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
             return toMail;
        }
        public String statusAssignment_Save(int TT, String new_Status){
            String query ="Update customerinfo set Status = ? where TT = ?";
            String toMail="";
            try {
                
                preparedStatement =con.prepareStatement(query);
                
                preparedStatement.setString(1, new_Status);
                preparedStatement.setInt(2, TT);
                
                preparedStatement.executeUpdate();
                //System.out.println("MainPage.statusAssignment_Save() TT:"+ TT);
                toMail=sendMail(TT,new_Status);
                
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            return toMail;
        
        
        }
        public boolean chiefEnter(String chiefUsername, String chiefPassword){
        
            String query = "Select * From chiefinfo where Username = ? and Password = ?";
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,chiefUsername);
                preparedStatement.setString(2,chiefPassword);
            
                ResultSet rs =  preparedStatement.executeQuery();
            
                return rs.next();
            
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
         }
        public boolean teamEnter(String teamUsername, String teamPassword){
        
            String query = "Select * From teaminfo where Username = ? and Password = ?";
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,teamUsername);
                preparedStatement.setString(2,teamPassword);
            
                ResultSet rs =  preparedStatement.executeQuery();
            
                return rs.next();
            
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
         }
        
        public void customerInformation(String customerName, String customerEmail, String customerAddress, String customerDescription){
           
             String insert = "Insert Into customerinfo (TT,Name,Email,Address,Description,Team,Status) VALUES(?,?,?,?,?,?,?)";
             /*String  insert = "INSERT INTO customerinfo ( Name, Email, Address, Description) VALUES("
                                    + customerName + "'" + customerEmail + "'" + customerAddress + "'" + customerDescription + "')" ;*/
             
            try{
                
                preparedStatement = con.prepareStatement(insert);
                 preparedStatement.setInt(1,0);
                preparedStatement.setString(2, customerName);
                preparedStatement.setString(3, customerEmail);
                preparedStatement.setString(4, customerAddress);
                preparedStatement.setString(5, customerDescription);
                 preparedStatement.setString(6, "");
                preparedStatement.setString(7,"");
               
                preparedStatement.executeUpdate();
                
                Mail.sendFromGMail("Your request has been received", "Description: "+customerDescription, customerEmail);
                System.out.println("Email sent to "+customerEmail+ "\nSubject: Your request has been received");
              
            } catch (SQLException ex) {
                System.err.println("Got an exception");
                
                
            }
        }
        public MainPage() {
		String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.database_name;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			
			System.out.println("Driver not found");
		}
		
		try {
			con = DriverManager.getConnection(url, Database.username, Database.password);
			System.out.println("Connection successfull");
		} catch(SQLException ex) {
			
			System.out.println("Connection unsuccessfull");
			
		}
                
                
	}
}
    
