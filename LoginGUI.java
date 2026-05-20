import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private java.util.List<User> users = new java.util.ArrayList<>();
    private ShopManager shop;

    public LoginGUI(ShopManager shop) {
        this.shop = shop;

        setTitle("Login");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel userLabel = new JLabel("Username");
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        usernameField.setBorder(new RoundedBorder(8));

        JLabel passLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        passwordField.setBorder(new RoundedBorder(8));

        JButton loginBtn    = new RoundedButton("Нэвтрэх",          new Color(83, 74, 183), Color.WHITE);
        JButton registerBtn = new RoundedButton("Бүртгүүлэх",       Color.WHITE,            new Color(60, 60, 60));
        JButton adminBtn    = new RoundedButton("Админаар нэвтрэх",  new Color(83, 74, 183), Color.WHITE);

        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        adminBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));

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
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(loginBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(registerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(adminBtn);

        add(panel);
        setLocationRelativeTo(null);
    }

    // ── Дугуй хүрээтэй Input ──────────────────────────────────

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

    // ── Logic (өөрчлөгдөөгүй) ────────────────────────────────

    private void register() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if(user.isEmpty()){
             JOptionPane.showMessageDialog(this, "Хэрэглэгчийн нэрээ оруулна уу!");
                return;
        }else if(pass.isEmpty()){
            JOptionPane.showMessageDialog(this,"Нууц үгээ оруулна уу!");
            return;
        }

        for (User u : users) {
            if (u.getUsername().equals(user)) {
                JOptionPane.showMessageDialog(this, "Хэрэглэгч аль хэдийн бүртгэлтэй!");
                return;
            }
        }

        users.add(new User("U" + (users.size() + 1), user, pass));
        usernameField.setText("");
        passwordField.setText("");
        JOptionPane.showMessageDialog(this, "Амжилттай бүртгэгдлээ!");
    }

    private void login() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        for (User u : users) {
            if (u.getUsername().equals(user) && u.Login(pass)) {
                JOptionPane.showMessageDialog(this, "Амжилттай нэвтэрлээ!");
                new ShopGUI(shop).setVisible(true);
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Нэвтрэх мэдээлэл буруу!");
    }

    private void loginAsAdmin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (user.equals("admin") && pass.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Админаар нэвтэрлээ!");
            new AdminGUI(shop).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Админ нэвтрэх мэдээлэл буруу!");
        }
    }
}