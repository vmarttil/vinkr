package vinkr.vinkit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YoutubeVinkki implements Vinkki {
    public static final String NL = System.getProperty("line.separator");
    public static final SimpleDateFormat PVMMUOTO = new SimpleDateFormat("dd.MM.yyy");
    private String id;
    private String otsikko;
    private String tyyppi = "youtube";
    private String kanava;
    private Date julkaisupvm = null;
    private URL url; 
    
    public YoutubeVinkki(URL url, String otsikko, String kanava) {
        this.id = luoID();
        this.otsikko = otsikko;
        this.kanava = kanava;
        this.url = url;
    }
    
 // Getterit
    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getOtsikko() {
        return this.otsikko;
    }

    @Override
    public String getTyyppi() {
        return this.tyyppi;
    }

    public String getKanava() {
        return this.kanava;
    }
    
    public Date getJulkaisupvm() {
        return this.julkaisupvm;
    }
    
    public URL getUrl() {
        return this.url;
    }
    
 // Setterit
    @Override
    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKanava(String kanava) {
        this.kanava = kanava;
    }

    public void setJulkaisupvm(Date julkaisupvm) {
        this.julkaisupvm = julkaisupvm;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }

    // Tulostus
    @Override
    public String tulosta() {
        String tuloste = "Tyyppi: YouTube-video" + NL + "Otsikko: " + this.getOtsikko() + NL;
        if (!this.getKanava().equals("")) {
            tuloste += "Kanava: " + this.getKanava() + NL;
        }
        if (this.getJulkaisupvm() != null) {
            tuloste += "Julkaistu: " + PVMMUOTO.format(this.getJulkaisupvm()) + NL;
        }
        tuloste += "URL: " + this.getUrl().toString() + NL;
        return tuloste;
    }

    @Override
    public String toString() {
        String string = this.getOtsikko();
        if (this.getJulkaisupvm() != null) {
            string += " (" + PVMMUOTO.format(this.getJulkaisupvm()) + ")";
        }
        string += " [" + this.getUrl().toString() + "]";
        return string;
    }
    
    // Linkin avaaminen
    @Override
    public void avaaLinkki() throws IOException, URISyntaxException {
        java.awt.Desktop.getDesktop().browse(this.getUrl().toURI());
    }
    
    // Apumetodit
    private String luoID() {
        Date nykyhetki = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String id = ft.format(nykyhetki);
        return id;
    }
    
}
