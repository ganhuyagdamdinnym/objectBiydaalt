error id: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/LoginGUI.java:javax/swing/Box#createRigidArea().
file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/LoginGUI.java
empty definition using pc, found symbol in pc: javax/swing/Box#createRigidArea().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3262
uri: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/LoginGUI.java
text:
```scala
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.util.ArrayList;

public class LoginGUI extends JFrame {
    private static final String FILE_NAME = "users.csv";
    private JTextField usernameField;
    private JPasswordField passwordField;
    private java.util.List<User> users = new ArrayList<>();
    private ShopManager shop;
    private ShopGUI shopGUI;

    public LoginGUI(ShopManager shop, ShopGUI shopGUI) {
        this.shop    = shop;
        this.shopGUI = shopGUI;

        loadUsersFromFile(); 

        setTitle("Нэвтрэх");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setBackground(new Color(0,229,100));;

        JPanel panel = new JPanel();
        // panel.setBackground(new Color(2,3,4));
        panel.setLayout(new BoxLayout(panel, BoxLayout. Y_AXIS));
        // panel.setLayout(new BoxLayout(panel, BoxLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel userLabel = new JLabel("Хэрэглэгчийн нэр");
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        usernameField.setBorder(new RoundedBorder(8));

        JLabel passLabel = new JLabel("Нууц үг");
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        passwordField.setBorder(new RoundedBorder(8));
        JPanel testlabel=new JPanel();
        testlabel.setSize(new Dimension(Integer.MAX_VALUE,50));
        testlabel.setLayout(new BoxLayout(testlabel, BoxLayout.X_AXIS));
        testlabel.setBackground(new Color(0,0,0));
        
        JButton loginBtn    = new RoundedButton("Нэвтрэh",         new Color(83, 74, 183), Color.WHITE);
        testlabel.add(loginBtn);
        
        JButton registerBtn = new RoundedButton("Бүртгүүлэх",      Color.WHITE,            new Color(60, 60, 60));
        testlabel.add(registerBtn);
        JButton adminBtn    = new RoundedButton("Админаар нэвтрэх", new Color(83, 74, 183), Color.WHITE);

        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));

        // loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        // registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        // registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        // adminBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> register());
        adminBtn.addActionListener(e -> loginAsAdmin());

        panel.add(userLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(usernameField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(passLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(passwordField);
        panel.add(Box.createRigi@@dArea(new Dimension(0, 15)));
        // panel.add(loginBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        // panel.add(registerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(adminBtn);

        add(panel);
    }

    // ── Бүртгүүлэх ───────────────────────────────────────────
    private void register() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Хэрэглэгчийн нэрээ оруулна уу!");
            return;
        }
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нууц үгээ оруулна уу!");
            return;
        }
        for (User u : users) {
            if (u.getUsername().equals(user)) {
                JOptionPane.showMessageDialog(this, "Хэрэглэгч аль хэдийн бүртгэлтэй!");
                return;
            }
        }

        users.add(new User("U" + (users.size() + 1), user, pass));
        saveToFile();
        usernameField.setText("");
        passwordField.setText("");
        JOptionPane.showMessageDialog(this, "Амжилттай бүртгэгдлээ!");
    }

    // ── Нэвтрэх ──────────────────────────────────────────────
    private void login() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        for (User u : users) {
            if (u.getUsername().equals(user) && u.Login(pass)) {
                JOptionPane.showMessageDialog(this, "Амжилттай нэвтэрлээ!");
                shopGUI.setVisible(true); // байгаа shopGUI-г нээнэ
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Нэвтрэх мэдээлэл буруу!");
    }

    // ── Админаар нэвтрэх ─────────────────────────────────────
    private void loginAsAdmin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (user.equals("admin") && pass.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Админаар нэвтэрлээ!");
            new AdminGUI(shop).setVisible(true); // shopGUI дамжуулна
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Админ нэвтрэх мэдээлэл буруу!");
        }
    }

    // ── Файлд хадгалах ────────────────────────────────────────
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User u : users) {
                writer.write(u.getId() + "," + u.getUsername() + "," + u.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Файлд хадгалахад алдаа: " + e.getMessage());
        }
    }

    // ── Файлаас уншина ────────────────────────────────────────
    private void loadUsersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                users.add(new User(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Файл уншихад алдаа: " + e.getMessage());
        }
    }

    // ── Дугуй хүрээтэй Input ─────────────────────────────────
    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        RoundedBorder(int radius) { this.radius = radius; }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(180, 180, 190));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(x + 0.6f, y + 0.6f, w - 1.2f, h - 1.2f, radius, radius));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) { return new Insets(6, 10, 6, 10); }
        @Override
        public boolean isBorderOpaque() { return false; }
    }

    // ── Дугуй товч ───────────────────────────────────────────
    static class RoundedButton extends JButton {
        private final Color bg;

        RoundedButton(String text, Color bg, Color fg) {
            super(text);
            this.bg = bg;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setForeground(fg);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) {
                g2.setColor(bg.darker());
            } else if (getModel().isRollover()) {
                g2.setColor(bg.equals(Color.WHITE) ? new Color(240, 240, 245) : bg.darker());
            } else {
                g2.setColor(bg);
            }
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
            if (bg.equals(Color.WHITE)) {
                g2.setColor(new Color(180, 180, 190));
                g2.setStroke(new BasicStroke(1.2f));
                g2.draw(new RoundRectangle2D.Float(0.6f, 0.6f, getWidth() - 1.2f, getHeight() - 1.2f, 10, 10));
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: javax/swing/Box#createRigidArea().