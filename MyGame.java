package com.mygame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MyGame  extends JFrame implements ActionListener
{
    JLabel heading,clockLabel;
    Font font=new Font("",Font.BOLD,40);
    JPanel mainPanel;
    JButton []btns=new JButton[9];
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int winner=2;
    boolean gameOver = false;
    MyGame()
    {
        System.out.println("Creating Instacne of Game");
        setTitle("TIC TAC TOE GAME");
        setSize(800,800);
        ImageIcon icon=new ImageIcon("src/img/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    private void createGUI()
    {
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        heading = new JLabel("TIC TAC TOE");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);
        clockLabel=new JLabel("TIME");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.white);
        this.add(clockLabel,BorderLayout.SOUTH);
 
        Thread t;
        t = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    while(true)
                    {
                        String datetime=new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                }
                catch(InterruptedException e)
                {
                }
            }
        };
        t.start();
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        for(int i=1;i<=9;i++)
        {
            JButton btn=new JButton();
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(i-1+"");
        }
        this.add(mainPanel,BorderLayout.CENTER);   
    }
    @Override
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void actionPerformed(ActionEvent e) 
    {
        JButton currentButton = (JButton)e.getSource();
        String name=currentButton.getName();
       int nam=Integer.parseInt(name.trim());
       if(gameOver == true )
       {
           JOptionPane.showMessageDialog(this, "GAME OVER");
            int i=JOptionPane.showConfirmDialog(this,"DO YOU WANT TO PLAY MORE ?");
            switch (i) {
                case 0:
                    this.setVisible(false);
                    new MyGame();
                    break;
                case 1:
                    System.exit(0);
                default:
                    break;
            }       
               gameOver=true;
           return;
       }
       if(gameChances[nam]==2)
       {
           if(activePlayer==1)
           {
               currentButton.setIcon(new ImageIcon("src/img/1.png"));
               gameChances[nam]=activePlayer;
               activePlayer=0;
           }
           else
           {
               currentButton.setIcon(new ImageIcon("src/img/0.png"));
               gameChances[nam]=activePlayer;
               activePlayer=1;
           }
           for(int []temp:wps)
           {
              if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&& (gameChances[temp[2]]!=2))
              {
                  winner=gameChances[temp[0]];
                  gameOver= true;
                  JOptionPane.showMessageDialog(null,"PLAYER "+winner+" WINNER");
                  int i = JOptionPane.showConfirmDialog(this,"DO YOU WANT TO PLAY MORE ?");
                  switch (i) {
                      case 0:
                          this.setVisible(false);
                          new MyGame();
                          break;
                      case 1:
                          System.exit(0);
                      default:
                          break;
                  }
                  System.out.println(i);
                  break;
                  }
           }
           int c=0;
           for(int x:gameChances)
           {
               if(x==2)
               {
                   c++;
                   break;
               }
           }
           if(c==0 && gameOver ==false)
           {
               JOptionPane.showMessageDialog(null, "IT IS A DRAW");
               int i=JOptionPane.showConfirmDialog(this,"DO YOU WANT TO PLAY MORE ?");
               switch (i) {
                   case 0:
                       this.setVisible(false);
                       new MyGame();
                       break;
                   case 1:
                       System.exit(0);
                   default:
                       break;
               }
               gameOver=true;
           }
       }
       else
       {
           JOptionPane.showMessageDialog(this,"POSITION ALREADY OCCUPIED");
       }
    }
}
