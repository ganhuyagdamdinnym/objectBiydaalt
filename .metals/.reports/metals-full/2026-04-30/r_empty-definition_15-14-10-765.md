error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java:javax/swing/WindowConstants#EXIT_ON_CLOSE.
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
empty definition using pc, found symbol in pc: javax/swing/WindowConstants#EXIT_ON_CLOSE.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 374
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
text:
```scala
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class BasketGUI extends JFrame {
    private static final Color BG_COLOR=new Color(255,255,255);


    public BasketGUI(Basket p){
       setTitle("Онлайн Дэлгүүрийн Систем");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CL@@OSE);
        setLocationRelativeTo(null);
        //main panel
        JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel userLabel = new JLabel("Username");
        

    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: javax/swing/WindowConstants#EXIT_ON_CLOSE.