
public class User {
    protected String id;
    protected String name;
    protected String password;

    public User(String id,String userName,String password){
        this.id=id;
        this.name=userName;
        this.password=password;

    }

    public boolean Login(String inputPassword){
    //    return this.password.equals(inputPassword);
    return true;
    }


    public String getId(){
        return id;
    }

    public String getUsername(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    
}
