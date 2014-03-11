package org.control;

import org.view.MainScreen;
import org.view.MessagePopUpScreen;
import org.view.RegScreen;
import org.view.TicketScreen;
import org.view.screens.Center.AdminGenreScreen;
import org.view.screens.Center.AdminLabelScreen;
import org.view.screens.Center.DiscoverExtendedScreen;
import org.view.screens.Center.HomeScreen;
import org.view.screens.Center.LabelScreen;
import org.view.screens.Center.Mailbox;
import org.view.screens.Center.OwnSongsScreen;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.SubscriptionScreen;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;
import org.view.screens.EastBar.SearchMask;
import org.view.screens.WestBar.PlaylistMiniScreen;
import org.view.screens.WestBar.SongTicker;

/**
 * Stellt Methode zum zerstören aller Singletons bereit
 * @author Matse
 *
 */
public class SingletonControl {
	
	
	/**
	 * Zerstört alle Singletons
	 */
	public static void destroyAll()
	{
		//Kontroll Klassen
		MailboxControl.destroy();
		PlaylistControl.destroy();
		RecommendationControl.destroy();
		SubscriptionControl.destroy();
		TicketControl.destroy();
		
		
		//View Klassen
		MainScreen.destroy();
		MessagePopUpScreen.destroy();
		RegScreen.destroy();
		TicketScreen.destroy();
		
		AdminGenreScreen.destroy();
		AdminLabelScreen.destroy();
		DiscoverExtendedScreen.destroy();
		HomeScreen.destroy();
		Mailbox.destroy();
		OwnSongsScreen.destroy();
		PlaylistExtendedScreen.destroy();
		SubscriptionScreen.destroy();
		
		DiscoverMiniScreen.destroy();
		EastBar.destroy();
		SearchMask.destroy();
		
		PlaylistMiniScreen.destroy();
		SongTicker.destroy();
		
	}

}
