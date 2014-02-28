package org.view.screens.WestBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SongTicker extends JPanel implements ActionListener{
	
	private String tickertext = "";
	private static final int RATE = 12;
    private Timer timer = new Timer(1000/RATE, this);
    private JLabel label = new JLabel();
    private int numberofletters;
    private int maxnumberofletters = 15;
    private int index;

    public SongTicker() {
    	this.numberofletters = Math.min(maxnumberofletters, tickertext.length());
        StringBuilder sb = new StringBuilder(numberofletters);
        for (int i = 0; i < numberofletters; i++) {
            sb.append(' ');
        }
        this.tickertext = sb + tickertext + sb;
        label.setText(sb.toString());
        this.add(label);
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

	public void setTickertext(String tickertextinput) {
		this.numberofletters = Math.min(maxnumberofletters, tickertextinput.length());
		this.tickertext = tickertextinput+"  "+tickertextinput.substring(0, numberofletters);
	}

	public void actionPerformed(ActionEvent arg0) {
        index++;
        if (index > tickertext.length() - numberofletters) {
            index = 0;
        }
        label.setText(tickertext.substring(index, index + numberofletters));
		
	}

}
