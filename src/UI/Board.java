package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
<<<<<<< HEAD
import java.awt.Color;
import javax.swing.JPanel;
import com.company.Factory;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;


public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    GameOfLifeControls controls = new GameOfLifeControls();

    javax.swing.JPanel pn1 = new javax.swing.JPanel();
    javax.swing.JScrollPane scroll_pane1 = new javax.swing.JScrollPane();;
    javax.swing.JSlider slider_1=new javax.swing.JSlider();;
=======

import Factory.Factory;
import com.company.Grid;

public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    GameOfLifeControls controls = Factory.controler;
>>>>>>> a3c7014c040936d932f612838555dcc708de0b02

    static final int cols = Factory.gridCols;
    static final int rows = Factory.gridRows;
    static final int originX = 0;
    static final int originY = 0;
    static final int size = Factory.defaultZoom;

    int xPanel = 1100, yPanel = 700;

    boolean[][] life = new boolean[xPanel/size][yPanel/size];
    boolean start = true;
    int check=0;
    boolean clicked = false;


    public void drawBoard()
    {
        setSize(1100, 700);
        setLayout(new BorderLayout());

        setBackground(Color.black);
        addMouseListener(this);
        addMouseListener(this);
        controls.setBoard(this);

        //new Timer(200, this).start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        grid(g);
        //spawn(g);
        drawGraphics(g);
    }


    private void grid(Graphics g)
    {
<<<<<<< HEAD
        Graphics2D g_2d = (Graphics2D)  g.create();
        g_2d.setColor(Color.darkGray);
        g_2d.scale((double)slider_1.getValue(),(double)slider_1.getValue());
        g_2d.translate(originX/2, originY/2);
        for(int i=0;i<=rows;i++)
=======
        g.setColor(Color.darkGray);

        for(int i=0;i<xPanel/size;i++)
>>>>>>> a3c7014c040936d932f612838555dcc708de0b02
        {
            for (int j=0;j<yPanel/size;j++)
            {
<<<<<<< HEAD
                g_2d.drawRect((j * size) + originX, (i*size)+originY, size, size);
            }
        }
//           g.setColor(Color.darkGray);
//            for(int i=0;i<=rows;i++)
//            {
//                for (int j=0;j<cols;j++)
//                {
//                    g.drawRect((j * size) + originX, (i*size)+originY, size, size);
//                 }
//             }

        /*for(int i=0;i<life.length;i++)
        {
            g.drawLine(0, i*size, xPanel, i*size);   //row
            g.drawLine(i*size, 0, i*size, yPanel);   //col
        }*/
=======
                g.drawRect((i * size) + originX, (j*size)+originY, size, size);
            }
        }

>>>>>>> a3c7014c040936d932f612838555dcc708de0b02
    }
    public class perform_Zoom extends javax.swing.JFrame {
        perform_Zoom() {
            slider_1.setMinimum(1);
            slider_1.setMaximum(100);
            slider_1.setValue(1);

            slider_1.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    jSlider1StateChanged(evt);
                }
            });
            scroll_pane1.setPreferredSize(new Dimension((slider_1.getValue() * 5), (slider_1.getValue() * 10)));
            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pn1);
            pn1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );

            scroll_pane1.setViewportView(pn1);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(slider_1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                            .addComponent(scroll_pane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(slider_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scroll_pane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addContainerGap())
            );

            pack();
        }

        ;
    }
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        pn1.repaint();
    }
    private void drawGraphics(Graphics g)
    {
        g.setColor(Color.blue);
        for(int x=0;x<xPanel/size;x++)
        {
            for(int y=0;y<(yPanel/size);y++)
            {
                if(life[x][y]==true)
                    g.fillRect(y*size, x*size, size, size);
            }
        }

    }

    public void updateBoard(Grid g)
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                life[i][j] = g.grid[i][j].isAlive();
            }
        }
        repaint();
    }


    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }


    public void mouseDragged(MouseEvent e)
    {

    }
    public void mouseMoved(MouseEvent e)
    {
        System.out.print("9");
    }
    public void mouseClicked(MouseEvent e)
    {
        clicked = false;
    }
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX()/size;
        int y = e.getY()/size;

        controls.setCell(y, x, true);
        /*life[x][y] = true;
        repaint();*/

    }
    public void mouseReleased(MouseEvent e)
    {

    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
}
