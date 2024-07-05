import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    Timer timer;

    private static final long serialVersionUID = 1L;

    private int shotsFired = 0;

    private ArrayList<Fire> shots = new ArrayList<>();

    private BufferedImage image;

    private int firedirY = 5;
    private int ballX = 0;
    private int balldirX = 2;
    private int spaceShipX = 0;
    private int dirSpaceX = 20;

    public GamePanel() {
        this.timer = new Timer(5, actionListener);

        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(Color.BLACK);
        requestFocus(true);
        addKeyListener(keyListener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
    }

    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int c = e.getKeyCode();
            switch (c) {
                case KeyEvent.VK_LEFT -> {
                    if(spaceShipX <= 0) {
                        spaceShipX = 0;
                    } else {
                        spaceShipX -= dirSpaceX;
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if(spaceShipX >= 744) {
                        spaceShipX = 744;
                    } else {
                        spaceShipX += dirSpaceX;
                    }
                }
                case KeyEvent.VK_CONTROL -> {
                    shots.add(new Fire(spaceShipX + 15, 475));
                    shotsFired++;
                }
                default -> {
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    };

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            for(Fire fire : shots) {
                fire.setY(fire.getY() - firedirY);
            }
            
            ballX += balldirX;

            if (ballX >= 765) {
                balldirX = -balldirX;
            } else if (ballX <= 0) {
                balldirX = -balldirX;
            }
            repaint();
        }
    };
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.RED);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(image, spaceShipX, 490, image.getWidth() / 10, image.getHeight() / 10, this);
        
        for(Fire fire : shots) {
            if(fire.getY() < 0) {
                shots.remove(fire);
            }
        }
        
        g.setColor(Color.BLUE);
        
        for(Fire fire : shots) {
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }
        
        if(checkIntersect()) {
            timer.stop();
            String message = """
                             Kazandınız...
                             Harcanan ateş sayısı: """ + shotsFired;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);            
        }
    }
    
    public boolean checkIntersect() {
        for(Fire fire : shots) {
            if(new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(ballX,0,20,20))) {
                return true;
            }
        }
        return false;
    }

}

class Fire {

    private int x;
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
