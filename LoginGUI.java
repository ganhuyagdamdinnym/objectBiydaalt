import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private java.util.List<User> users = new java.util.ArrayList<>();
    private ShopManager shop;

   public LoginGUI(ShopManager shop) {
    this.shop = shop;

    setTitle("Login");
    setSize(400, 250);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Main panel
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    // Username
    JLabel userLabel = new JLabel("Username");
    usernameField = new JTextField();
    usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    // Password
    JLabel passLabel = new JLabel("Password");
    passwordField = new JPasswordField();
    passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    // Buttons
    JButton loginBtn = new JButton("Loginss");
    JButton registerBtn = new JButton("Register");

    loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    loginBtn.addActionListener(e -> login());
    registerBtn.addActionListener(e -> register());

    // Add spacing + components
    panel.add(userLabel);
    panel.add(Box.createRigidArea(new Dimension(0,5)));
    panel.add(usernameField);

    panel.add(Box.createRigidArea(new Dimension(0,10)));

    panel.add(passLabel);
    panel.add(Box.createRigidArea(new Dimension(0,5)));
    panel.add(passwordField);

    panel.add(Box.createRigidArea(new Dimension(0,15)));

    panel.add(loginBtn);
    panel.add(Box.createRigidArea(new Dimension(0,5)));
    panel.add(registerBtn);

    add(panel);

    setLocationRelativeTo(null);
}
  private void register() {
    String user = usernameField.getText();
    String pass = new String(passwordField.getPassword());

    for(User u : users){
        if(u.getUsername().equals(user)){
            JOptionPane.showMessageDialog(this, "User already exists!");
            return;
        }
    }

    users.add(new User("U" + (users.size()+1), user, pass));
    usernameField.setText("");
    passwordField.setText("");
    JOptionPane.showMessageDialog(this, "Амжилттай бүртгэгдлээ!");
}
private void login() {
    // String user = usernameField.getText();
    // String pass = new String(passwordField.getPassword());
 new ShopGUI(shop).setVisible(true);
    // for(User u : users){
    //     if(u.getUsername().equals(user) && u.Login(pass)){
    //         JOptionPane.showMessageDialog(this, "Амжилттай нэвтэрлээ");

    //         new ShopGUI(shop).setVisible(true);
    //         dispose();
    //         return;
    //     }
    // }

    // JOptionPane.showMessageDialog(this, "Нэвтрэх мэдээлэл буруу!");
}
}
