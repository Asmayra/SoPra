package org.view.screens.WestBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Erhält die Infos des aktuell abgespielten Lieds und zeigt diese in einem Lauftext an
 * @author Max Küper, Tim Michels
 *
 */
public class SongTicker extends JPanel implements ActionListener{
	
	private static String tickertext = "";
	private static final int RATE = 5;
    private Timer timer = new Timer(1000/RATE, this);
    private JLabel label = new JLabel();
    private static int numberofletters;
    private static int maxnumberofletters = 30;
    private int index;
    private static SongTicker instance = null;
    
    public static SongTicker getInstance(){
    	if(instance == null){
    		instance = new SongTicker();
    	}
    	return instance;
    }

    private SongTicker() {
    	this.numberofletters = Math.min(maxnumberofletters, tickertext.length());
        StringBuilder sb = new StringBuilder(numberofletters);
        for (int i = 0; i < numberofletters; i++) {
            sb.append(' ');
        }
        this.tickertext = sb + tickertext + sb;
        label.setText(sb.toString());
        this.add(label);
        
        this.setPreferredSize(new Dimension(250,20));
    }

    
    //Startet Ticker
    public void start() {
        timer.start();
    }

    //Stoppt Ticker
    public void stop() {
        timer.stop();
    }

	public String getTickertext() {
		return tickertext;
	}

	public static void setTickertext(String tickertextinput) {
		numberofletters = Math.min(maxnumberofletters, tickertextinput.length());
		tickertext = tickertextinput+"  "+tickertextinput.substring(0, numberofletters);
	}

	/**
	 * Vom Ticker ausgelöst wird der Anzeigetext weiterlaufen gelassen
	 */
	public void actionPerformed(ActionEvent arg0) {
        index++;
        if (index > tickertext.length() - numberofletters) {
            index = 0;
        }
        label.setText(tickertext.substring(index, index + numberofletters));
		
	}

}
