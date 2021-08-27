import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

        
public class TextAreaTester 
{
    JFrame testFrame = new JFrame();
    JTextArea jtaText;
    
    public TextAreaTester()
    {
        testFrame = new JFrame();
        testFrame.setLocation(100,100);
        testFrame.setSize(400,400);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setTitle("TextArea Tester");

        initializeComponents();
        
        testFrame.pack();
        testFrame.setVisible(true);
    }
    
    public void initializeComponents()
    {
        JPanel jpNorth = new JPanel();
        jtaText = new JTextArea(5,25);
        jtaText.setEditable(false);
        jtaText.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jtaText);
        jpNorth.add(jsp);
        
        JPanel jpSouth = new JPanel();
        JButton jbOne = new JButton("One");
        JButton jbTwo = new JButton("Two");
        JButton jbThree = new JButton("Three");
        JButton jbFour = new JButton("Four");
        
        
        jbOne.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jtaText.append("One");
            }
        }
        );
        
        jbTwo.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jtaText.append("Two");
            }
        }
        );
        jbThree.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jtaText.append("Three ");
            }
        }
        );
        jbFour.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jtaText.append("Four ");
            }
        }
        );
        
        jpSouth.add(jbOne);
        jpSouth.add(jbTwo);
        jpSouth.add(jbThree);
        jpSouth.add(jbFour);
        
        
        testFrame.add(jpNorth,BorderLayout.PAGE_START);
        testFrame.add(jpSouth, BorderLayout.PAGE_END);
        
    }

    public static void main(String[] args)
    {
        new TextAreaTester();
    }
}


