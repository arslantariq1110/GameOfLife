//game of life frame
package JavaSwing;

import Factory.Constants;
import BLLayer.GameOfLife;
import BLLayer.Grid;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class GameOfLifeFrame extends JFrame implements KeyListener, ActionListener
{
    File file = new File("C:\\Users\\myacc\\Data\\IdealProjects\\GameOfLife\\src\\JavaSwing\\mi.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();

    private final JFrame f = new JFrame("Game Of Life");
    private final GameBoard board;
    JButton startBtn;
    JButton nextBtn;
    JButton resetBtn;

    JButton saveStateBtn;
    JButton deleteStateBtn;
    JButton loadStateBtn;
    JButton viewStateBtn;
    JLabel genLabel;
    JLabel zoomLabel;
    JLabel speedLabel;

    JSlider speedSlider;
    JSlider zoomSlider;
    double bottomControlsPanelRatio = 15.75;

    public GameOfLifeFrame(GameOfLife g) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        clip.open(audioStream);


        f.setBackground(Color.blue);
        f.setLayout(null);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board = new GameBoard(screenSize.width, (int) (screenSize.height-(screenSize.getHeight()*bottomControlsPanelRatio/100)), g);

        startBtn = new JButton("Start");
        nextBtn = new JButton("Next");
        resetBtn = new JButton("Clear");
        saveStateBtn = new JButton("Save State");
        deleteStateBtn = new JButton("Delete State");
        loadStateBtn = new JButton("Load State");
        viewStateBtn = new JButton("View State");
        genLabel = new JLabel("0");
        zoomLabel = new JLabel("Zoom");
        speedLabel = new JLabel("Speed");
        speedSlider = new JSlider(1, 7, 4);
        zoomSlider = new JSlider(1, 7, 4);


        f.addKeyListener(this);

        f.add(board);
        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting buttons position (dynamically according to resolution)
        int menu1Y = (screenSize.height - (int) (screenSize.getHeight() * bottomControlsPanelRatio/100) + (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)/3 - (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)*26/100);
        int menu1Height = (int) (screenSize.getHeight() * bottomControlsPanelRatio/100)*26/100;
        int menu1Spacing = (int) (screenSize.getWidth()*3/100);

        int menu2Y = menu1Y + menu1Height*2;
        int menu2Spacing = (int) (screenSize.width * 1.56 /100);

        int startBtnWidth = (screenSize.width * 20 /100);
        int starBtnX = (screenSize.width/2) - startBtnWidth/2;

        int nextBtnWidth = (screenSize.width *12/ 100);
        int nextBtnX = (starBtnX + startBtnWidth + menu1Spacing);

        int resetBtnWidth = (screenSize.width *12/ 100);
        int resetBtnX = (nextBtnX + nextBtnWidth + menu1Spacing - 10);

        int zoomSliderWidth = (screenSize.width *12/ 100);
        int zoomSliderX = starBtnX - menu1Spacing - zoomSliderWidth;

        int speedSliderWidth = (screenSize.width *12/ 100);
        int speedSliderX = zoomSliderX - zoomSliderWidth - menu1Spacing + 10;

        int menu2BtnWidth = (screenSize.width/2)/4 - menu2Spacing/2;
        int saveBtnX = (screenSize.width/4) - menu2Spacing/2;
        int deleteBtnX =  saveBtnX + menu2BtnWidth + menu2Spacing;
        int loadBtnX = deleteBtnX + menu2BtnWidth +menu2Spacing;
        int viewBtnX = loadBtnX + menu2BtnWidth +menu2Spacing;

        //adding Buttons to Frame
        startBtn.setBounds(starBtnX, menu1Y , startBtnWidth, menu1Height);
        nextBtn.setBounds(nextBtnX, menu1Y+2, nextBtnWidth, menu1Height-2);
        resetBtn.setBounds(resetBtnX, menu1Y+2, resetBtnWidth, menu1Height-2);
        speedSlider.setBounds(speedSliderX, menu1Y+2, speedSliderWidth, menu1Height-2);
        zoomSlider.setBounds(zoomSliderX, menu1Y+2, zoomSliderWidth, menu1Height-2);

        saveStateBtn.setBounds(saveBtnX, menu2Y, menu2BtnWidth, menu1Height);
        deleteStateBtn.setBounds(deleteBtnX, menu2Y, menu2BtnWidth, menu1Height);
        loadStateBtn.setBounds(loadBtnX, menu2Y, menu2BtnWidth, menu1Height);
        viewStateBtn.setBounds(viewBtnX, menu2Y, menu2BtnWidth, menu1Height);

        zoomLabel.setBounds(zoomSliderX+5, menu1Y+30, 100, 20);
        speedLabel.setBounds(speedSliderX+5, menu1Y+30, 100, 20);
        genLabel.setBounds(resetBtnX+resetBtnWidth+20, menu1Y, 100, 30);


        //Zoom Changed Listener
        zoomSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                zoom_Slider_StateChanged(evt);
            }

            private void zoom_Slider_StateChanged(ChangeEvent evt)
            {
                int zoom;

                if (zoomSlider.getValue() == GameBoard.zoomSize /5)
                    return;

                zoom =(zoomSlider.getValue()*5);
                board.gameControls.zoomChanged(zoom);
                board.updateBoard(board.gameControls.getGrid());
                genLabel.setText(Integer.toString(board.gameControls.getGeneration()));

            }
        } );
        //Speed Changed Listener
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                speed_Slider_StateChanged(evt);
            }

            private void speed_Slider_StateChanged(ChangeEvent evt)
            {
                int speed;

                if (speedSlider.getValue() == GameBoard.speed /160)
                    return;

                speed = Constants.minSpeed - speedSlider.getValue()*160;
                board.gameControls.speedChanged(speed);
                board.updateBoard(board.gameControls.getGrid());
                genLabel.setText(Integer.toString(board.gameControls.getGeneration()));


            }
        } );


        //add action key listeners to button
        startBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        saveStateBtn.addActionListener(this);
        deleteStateBtn.addActionListener(this);
        loadStateBtn.addActionListener(this);
        viewStateBtn.addActionListener(this);

        //setting button's properties
        startBtn.setLayout(null);
        startBtn.setFocusable(false);
        nextBtn.setLayout(null);
        nextBtn.setFocusable(false);
        resetBtn.setLayout(null);
        resetBtn.setFocusable(false);
        saveStateBtn.setLayout(null);
        saveStateBtn.setFocusable(false);
        deleteStateBtn.setLayout(null);
        deleteStateBtn.setFocusable(false);
        loadStateBtn.setLayout(null);
        loadStateBtn.setFocusable(false);
        viewStateBtn.setLayout(null);
        viewStateBtn.setFocusable(false);
        speedSlider.setLayout(null);
        speedSlider.setFocusable(false);
        zoomSlider.setLayout(null);
        zoomSlider.setFocusable(false);

        startBtn.setBackground(Color.darkGray);
        startBtn.setForeground(Color.white);
        nextBtn.setBackground(Color.darkGray);
        nextBtn.setForeground(Color.white);
        resetBtn.setBackground(Color.darkGray);
        resetBtn.setForeground(Color.white);
        saveStateBtn.setBackground(Color.darkGray);
        saveStateBtn.setForeground(Color.white);
        deleteStateBtn.setBackground(Color.darkGray);
        deleteStateBtn.setForeground(Color.white);
        loadStateBtn.setBackground(Color.darkGray);
        loadStateBtn.setForeground(Color.white);
        viewStateBtn.setBackground(Color.darkGray);
        viewStateBtn.setForeground(Color.white);
        speedSlider.setBackground(Color.darkGray);
        speedSlider.setForeground(Color.white);
        zoomSlider.setBackground(Color.darkGray);
        zoomSlider.setForeground(Color.white);

        //add buttons to frame
        f.add(startBtn);
        f.add(nextBtn);
        f.add(resetBtn);
        f.add(saveStateBtn);
        f.add(deleteStateBtn);
        f.add(loadStateBtn);
        f.add(viewStateBtn);
        f.add(genLabel);
        f.add(speedSlider);
        f.add(zoomSlider);
        f.add(zoomLabel);
        f.add(speedLabel);

    }


    @Override
    public void keyTyped(KeyEvent e)
    {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            try
            {
                if(board.currentBoardWindowY !=0)
                    board.currentBoardWindowY -=1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }

        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            try
            {
                if(board.currentBoardWindowY < GameBoard.rows - board.boardHeight / GameBoard.zoomSize)
                    board.currentBoardWindowY += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            try
            {
                if(board.currentBoardWindowX < Constants.gridCols -board.boardWidth / GameBoard.zoomSize)
                    board.currentBoardWindowX += 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            try
            {
                if(board.currentBoardWindowX !=0)
                    board.currentBoardWindowX -= 1;
                board.repaint();
            }
            catch (ArrayIndexOutOfBoundsException error)
            {
                //do nothing
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //do nothing
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startBtn)
        {
            board.gameControls.startStopButtonClick();
            startBtn.setText("Stop");
            resetBtn.setText("Reset");
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            Thread GameLoop = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    saveStateBtn.setVisible(false);
                    loadStateBtn.setVisible(false);
                    viewStateBtn.setVisible(false);
                    deleteStateBtn.setVisible(false);

                    //game loop continues if in running state
                    while(board.gameControls.isGameRunning())
                    {
                        board.gameControls.next();
                        board.updateBoard(board.gameControls.getGrid());
                        genLabel.setText(Integer.toString(board.gameControls.getGeneration()));
                        try
                        {
                            Thread.sleep(board.gameControls.getSpeed());
                        }
                        catch(InterruptedException e)
                        {
                            //do nothing
                        }
                    }
                    startBtn.setText("Start");
                    resetBtn.setText("Clear");

                    saveStateBtn.setVisible(true);
                    loadStateBtn.setVisible(true);
                    viewStateBtn.setVisible(true);
                    deleteStateBtn.setVisible(true);
                    clip.stop();
                }
            });
            GameLoop.start();
        }
        else if(e.getSource() == nextBtn)
        {
            board.gameControls.nextButtonClick();
            board.updateBoard(board.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.gameControls.getGeneration()));

        }
        else if(e.getSource() == resetBtn)
        {
            board.gameControls.resetButtonClicked();
            board.updateBoard(board.gameControls.getGrid());
            genLabel.setText(Integer.toString(board.gameControls.getGeneration()));
        }
        else if(e.getSource() == saveStateBtn)
        {
            NameInputDialogBox box = new NameInputDialogBox();
            String stateName = null;
            stateName = box.get_string();
            if(stateName!=null)
            {
                board.gameControls.saveStateButtonClick(stateName);
                board.updateBoard(board.gameControls.getGrid());
                genLabel.setText(Integer.toString(board.gameControls.getGeneration()));
            }
        }
        else if(e.getSource() == deleteStateBtn)
        {
            String[] s = board.gameControls.getSavedStates();
            StateListPanel statesListPanel = new StateListPanel(s);
            final String[] selectedState = {null};

            Thread deleteStateLoop = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(selectedState[0] == null)
                    {
                        selectedState[0] = statesListPanel.getSelectedState();
                    }
                    if(selectedState[0] == null)
                        return;
                    board.gameControls.deleteStateButtonClick(selectedState[0]);
                    board.updateBoard(board.gameControls.getGrid());
                    genLabel.setText(Integer.toString(board.gameControls.getGeneration()));
                    selectedState[0]= null;
                }
            });
            deleteStateLoop.start();
        }
        else if(e.getSource() == loadStateBtn)
        {

            String[] s = board.gameControls.getSavedStates();
            StateListPanel statesListPanel = new StateListPanel(s);
            final String[] selectedState = {null};

            Thread loadStateLoop = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(selectedState[0] == null)
                    {
                        selectedState[0] = statesListPanel.getSelectedState();
                    }
                    if(selectedState[0] == null)
                        return;
                    board.gameControls.loadStateButtonClick(selectedState[0]);
                    board.updateBoard(board.gameControls.getGrid());
                    genLabel.setText(Integer.toString(board.gameControls.getGeneration()));
                    selectedState[0]= null;
                }
            });
            loadStateLoop.start();
        }
        else if(e.getSource() == viewStateBtn)
        {
            String[] s = board.gameControls.getSavedStates();
            StateListPanel statesListPanel = new StateListPanel(s);
            final String[] selectedState = {null};


            Thread viewStateLoop = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(selectedState[0] == null)
                    {
                        selectedState[0] = statesListPanel.getSelectedState();
                    }

                    if(selectedState[0] == null)
                        return;
                    Grid g = board.gameControls.getState(selectedState[0]);
                    FrameForViewState f2 = new FrameForViewState(g);
                    selectedState[0]= null;
                }
            });
            viewStateLoop.start();



        }

    }
}
