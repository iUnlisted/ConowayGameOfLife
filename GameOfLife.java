import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Random;

public class GameOfLife
{
    private static JFrame frame;
    private static JPanel panel;
    public static void main(String[] args){
        frame = new JFrame("Game Of Life");
        panel = new gamePanel();

        frame.setSize(1920,1080);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
    public static class gamePanel extends JPanel implements ActionListener{
        private Timer timer;
        private boolean[][] map;
        int megaCounter = 0;
    
        public gamePanel(){
            setBackground(new Color (0,0,0));
            timer = new Timer(1000/60,this);
            timer.start();
            setFocusable(true);
            map = new boolean[1920][1080];
            Random randX = new Random();
            Random randY = new Random();
            
            for(int i =0;i<100000;i++){
                map[randX.nextInt(1919)][randY.nextInt(1079)] = true;
            }
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(new Color(255,255,255));
            for(int r = 0;r<map.length;r++){
                for(int c =0;c<map[r].length;c++){
                    if(map[r][c]){g.fillRect(r,c,1,1);}
                }
            }
            
        }
        public void updateGame(){
            boolean[][] newMap= new boolean[1920][1080];
            for(int r = 0;r<map.length;r++){
                for(int c =0;c<map[r].length;c++){
                        int counter = 0;
                        if(validPos(map,r+1,c) && map[r+1][c]){counter++;}
                        if(validPos(map,r-1,c) && map[r-1][c]){counter++;}
                        if(validPos(map,r,c+1) && map[r][c+1]){counter++;}
                        if(validPos(map,r,c-1) && map[r][c-1]){counter++;}
                        if(validPos(map,r+1,c+1) && map[r+1][c+1]){counter++;}
                        if(validPos(map,r-1,c-1) && map[r-1][c-1]){counter++;}
                        if(validPos(map,r+1,c-1) && map[r+1][c-1]){counter++;}
                        if(validPos(map,r-1,c+1) && map[r-1][c+1]){counter++;}
                        
                        if(!map[r][c] && counter == 3){newMap[r][c] = true;}
                        else if(map[r][c] && (counter ==2 ||counter ==3)){newMap[r][c] = true;}
                }
            }
            map = newMap;
            
        }
        public boolean validPos(boolean[][] map, int r, int c){
            if((r>=0 && r<map.length) &&(c>=0 && c< map[r].length)){return true;}
            return false;
        }
        public void actionPerformed(ActionEvent e){
            
            updateGame();
              
            
            repaint();
        }
    }
}
