package tr.edu.medipol.h5210017.presentation.system;

import java.util.List;
import tr.edu.medipol.h5210017.domain.*;
import tr.edu.medipol.h5210017.infrastructure.services.*;

/**
 * @file Main.java
 * @author enes
 * @description
 * 	DenizliSecimAPI uygulaması bir konsol uygulaması olduğundan dolayı
 * 	presentation katmanında "system" paketini oluşturdum. Bu katman DDD için
 * 	uygulamaya erişim sağlayacağımız alanı kapsadığından (örn: http, graphql gibi)
 * 	system ismini uygun gördüm. console olması da muhtemel olabilirdi.
 */
public class Main {

	public static void main(String[] args) {	
        Party party1 = new Party("AK Parti");
        Party party2 = new Party("CHP");
        Party party3 = new Party("MHP");
        Party party4 = new Party("İYİ Parti");

        List<Party> parties = List.of(party1, party2, party3, party4);
        CityService cityService = new CityService("Denizli", parties);

        String cityName = cityService.getCityName();
        List<Party> cityParties = cityService.getParties();

        System.out.println("Denizli Secim API\n");
        System.out.println("Şehir: " + cityName);
        System.out.println("Parti Listesi:");

        for (Party party : cityParties) {
        	 System.out.println("- " + party.getName());
        }
        
        System.out.println("");
        
        cityService.incrementPartyVoteCount("İYİ Parti");
        cityService.incrementPartyVoteCount("İYİ Parti");
        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("CHP");
        cityService.incrementPartyVoteCount("MHP");
        
        List<String> partyVotes = cityService.getPartyVotes();

        System.out.println("");
        
        System.out.println("Parti Oyları:");
        for (String partyVote : partyVotes) {
            System.out.println(partyVote);
        }
	}

}
