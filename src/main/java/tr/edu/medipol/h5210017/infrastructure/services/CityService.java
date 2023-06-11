package tr.edu.medipol.h5210017.infrastructure.services;

import java.util.*;
import tr.edu.medipol.h5210017.domain.Party;

/**
 * @file CityService.java
 * @author enes
 * @description
 * 	CityService ile ben DenizliSecimAPI projeme dinamik şehir özelliği eklemiş oldum.
 * 	Mantığıma göre şöyle bir sonuç alacağım. Şehir adı ve Partileri içeren bir liste ile sınıfı oluşturdum.
 * 	Bu sınıf içerisinde parti oy arttır statik metadu ile istediğim partinin oyunu arttıracağım. Bu arada
 * 	case'de verdiğiniz parti adı yoksa durumunu da rahat bir şekilde sorgulayabileceğim.
 */
public class CityService {
    private String cityName;
    private List<Party> parties;

    public CityService(String cityName, List<Party> parties) {
        this.cityName = cityName;
        this.parties = parties;
    }
    
    /**
     * @method findParty
     * @return Party
     * @description
     * 	Sınıfa tanımlanan partileri döndürüp içerisinde verilen parti adı olup olmadığına bakar,
     * 	eğer verilen parti adı bulunamadıysa bunu exception ile bildirir.
     */
    public Party findParty(String partyName) {
    	for (Party party : parties) {
            if (party.getName().equals(partyName)) {
            	return party;
            }
    	}
        throw new IllegalArgumentException("Hata: " + partyName + " isimli parti bulunamadı!");
    }
    
    /**
     * @method incrementPartyVoteCount
     * @return void
     * @description
     * 	İsmi verilen partiyi kontrol eder, eğer parti varsa oyunu artırır.
     * 	Bu durumu kullanıcıya mesaj oylu ile bildirir.
     */
    public void incrementPartyVoteCount(String partyName) {
    	Party party = this.findParty(partyName);
    	
    	party.incrementVoteCount();
        System.out.println("Yeni Girdi: " + partyName + " isimli partinin güncel oy miktarı: " + party.getVoteCount());
    }
    
    /**
     * @method decrementPartyVoteCount
     * @return void
     * @description
     * 	İsmi verilen partiyi kontrol eder, eğer parti varsa oyunu azaltır.
     * 	Bu durumu kullanıcıya mesaj oylu ile bildirir.
     */
    public void decrementPartyVoteCount(String partyName) {
    	Party party = this.findParty(partyName);
    	
    	party.decrementVoteCount();
        System.out.println("Yeni Girdi: " + partyName + " isimli partinin güncel oy miktarı: " + party.getVoteCount());
    }
    
    /**
     * @method getCityName
     * @return string
     * @description Şehir ismini döndürür.
     */
    public String getCityName() {
        return cityName;
    }
    
    /**
     * @method getParties
     * @return List<Party>
     * @description Şehire tanımlı tüm partilerin listesini döndürür.
     */
    public List<Party> getParties() {
        return parties;
    }
    
    /**
     * @method getPartyVotes
     * @return List<string>
     * @description Şehire tanımlı partileri ve onların oylarını içeren bir string listesi döndürür.
     */
    public List<String> getPartyVotes() {
        List<String> partyVotes = new ArrayList<>();
        for (Party party : parties) {
            String partyVote = party.getName() + ": " + party.getVoteCount() + " oy";
            partyVotes.add(partyVote);
        }
        return partyVotes;
    }
}