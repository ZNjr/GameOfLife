import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;

class MenuButton extends JButton{
    MenuButton(String text, Rectangle.Double bounds, ActionListener listener) {
        super(text);
         setBounds(bounds.getBounds());
         addActionListener(listener);
        }
    }

