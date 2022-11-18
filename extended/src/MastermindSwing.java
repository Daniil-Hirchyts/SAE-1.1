import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MastermindSwing extends Canvas implements MouseListener, ActionListener {
    private static final int ROWS = 10;
    private int dw, dh, currentColor, currentRow;
    private Color colors[] = { Color.red,  Color.green,   Color.blue,
            Color.cyan, Color.magenta, Color.yellow };
    private Color guesses[][] = new Color[ROWS][4];
    private Color responses[][] = new Color[10][4];
    private int[] input = new int[4], bw = new int[2];
//    private MastermindModel mm = new MastermindModel();
    private boolean displayAnswer;

    public void actionPerformed( ActionEvent e ) {
        if (e.getActionCommand().equals("Submit guess")) {
//            mm.guess( input, bw );
            for (int i=0; i < bw[0]; i++)
                responses[currentRow][i] = Color.black;
            for (int i=bw[0]; i < bw[0]+bw[1]; i++)
                responses[currentRow][i] = Color.white;
            currentRow++;
            if (bw[0] == 4)
                ((JButton)e.getSource()).setText( "Go again?" );
        } else {
//            mm.reset();
            ((JButton)e.getSource()).setText( "Submit guess" );
            reset();
        }
        repaint();
    }

    public void reset() {
        for (int i=0; i < ROWS; i++)
            for (int j=0; j < 4; j++) {
                guesses[i][j] = Color.white;
                responses[i][j] = Color.lightGray;
            }
        currentRow = 0;
        currentColor = 0;
        displayAnswer = false;
    }

    public void mousePressed( MouseEvent e ) {
        int x = e.getX(), y = e.getY();
        if (y < dh) {                         // click above the board
            displayAnswer = ! displayAnswer;    // to toggle the answer
            repaint();
        } else if (y < dh*(ROWS+1)+dh/2) {    // click in a column to
            x = (x - dw) / dw;                  // place a color
            input[x] = currentColor;
            guesses[currentRow][x] = colors[currentColor];
            repaint();
        } else {                              // click below the board
            currentColor = (x - dw/2) / dw;     // to select a color
            System.out.println( "color is " + currentColor );
        } }
    public void mouseEntered(  MouseEvent e ) { }
    public void mouseExited(   MouseEvent e ) { }
    public void mouseClicked(  MouseEvent e ) { }
    public void mouseReleased( MouseEvent e ) { }

    public void paint( Graphics g ) {
        dw = (int) getSize().width / 7;
        dh = (int) getSize().height / (ROWS+3);
        fillBoard( g );
        drawBoard( g );
    }

    private void fillBoard( Graphics g ) {
        if (displayAnswer) {
//            int[] answer = mm.getAnswer();
            for (int j=0; j < 4; j++) {
//                g.setColor( colors[answer[j]] );
                g.fillRect( dw*j+dw, 0, dw, dh );
            } }
        for (int i=0; i <= currentRow; i++)
            for (int j=0; j < 4; j++) {
                g.setColor( guesses[i][j] );
                g.fillRect( dw*(j+1), dh*(i+1), dw, dh );
            }
        for (int i=0; i < currentRow; i++)
            for (int j=0; j < 4; j++) {
                g.setColor( responses[i][j] );
                g.fillRect( 5*dw+j%2*dw/2, dh*(i+1)+j/2*dh/2, dw/2, dh/2 );
            }   }

    private void drawBoard( Graphics g ) {
        g.setColor( Color.black );
        // draw the rows
        for (int i=0; i < ROWS+1; i++)
            g.drawLine( dw, dh*(i+1), 6*dw, dh*(i+1) );
        // draw the columns
        for (int i=0; i <  6; i++)
            g.drawLine( dw*(i+1), dh, dw*(i+1), (ROWS+1)*dh );
        // draw the black-white horizontal dividers
        for (int i=0; i < ROWS; i++)
            g.drawLine( dw*5, (i+1)*dh+dh/2, dw*6, (i+1)*dh+dh/2 );
        // draw the black-white vertical divider
        g.drawLine( dw*5+dw/2, dh, dw*5+dw/2, dh*(ROWS+1) );
        // draw the color palette
        for (int i=0; i < 6; i++) {
            g.setColor( colors[i] );
            g.fillRect( i*dw+dw/2, dh*(ROWS+1)+dh/2, dw, dh );
        } }

    public MastermindSwing() {
        reset();

        JFrame frame = new JFrame( "Mastermind" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBackground( Color.white );

        JButton submit = new JButton( "Submit guess" );
        submit.addActionListener( this );
        addMouseListener( this );

        frame.getContentPane().add( this );
        frame.getContentPane().add( submit, BorderLayout.SOUTH );

        frame.setSize( new Dimension( 220, 450 ) );
        frame.setVisible( true );
    }

    public static void main( String[] args ) {
        new MastermindSwing();
    } }