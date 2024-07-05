
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameFrame frame = new GameFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }
}
