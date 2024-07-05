
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;

    public GameFrame() {
        setTitle("Space Shooter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setFocusable(false);
        setLocationRelativeTo(null);

        contentPane = new GamePanel();

        add(contentPane);
    }

}
