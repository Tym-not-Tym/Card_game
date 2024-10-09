import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Welcome extends JFrame{

    BlackJackGUI(){

        //create label 
        JLabel label = new JLabel(); 
        label.setText("Welcome to BlackJack!");
        label.setHorizontalTextPosition(JLabel.CENTER); //set the text in the center of the window 
        label.setVerticalTextPosition(JLabel.TOP); //set text at the top 
        label.setFont(new Font("MV Boli",Font.PLAIN,20)); 
        label.setIconTextGap(100);

        
        //initialize a small window and add label 
        this.setTitle("BlackJack (21)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true); //prevent from being resized
        this.setSize(420,420);
        this.setVisible(true);
        this.add(label);

        //change the window icon and set background color
        ImageIcon image = new ImageIcon("Black_Jack/download.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.green);

       
       
    }
     
}
