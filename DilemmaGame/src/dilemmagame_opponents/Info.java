package dilemmagame_opponents;

import java.awt.*;
import javax.swing.*;

public abstract class Info extends JPanel implements PlayerInfoObserver {
    protected String header;
    protected JLabel infoLabel; //show info
    
    Info(String header) {
        this.header = header;
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), header));
        
        infoLabel = new JLabel();
        add(infoLabel);
        
        setPreferredSize(new Dimension(255, 64));
    }
}
