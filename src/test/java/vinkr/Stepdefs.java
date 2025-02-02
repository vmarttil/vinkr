
package vinkr;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;

public class Stepdefs {
    Vinkr app;
    String input;
    ByteArrayOutputStream uiOutput;
    TextUI ui;
    InputStream uiInput;
    String validiUrl;
    String validiOtsikko;
    
    @Before
    public void setup() {
        input = "";
        validiUrl = "http://github.com/";
        validiOtsikko = "TestiOtsikko";
    }
        //GIVENIT
    @Given("komento {string} annetaan ohjelmalle")
    public void komentoValittu(String komento) throws Throwable {
        
        input += komento + "\n";
    }
    
    @Given("uusi kirjavinkki, otsikolla {string}, kirjoittajalla {string}, jonka ISBN on {string}, julkaisupaikalla {string}, kustantajalla {string} ja julkaisuvuodella {string} lisataan")
    public void lisataanJarjestelmaanTiettyKirjavinkki(String otsikko, String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) {
        input += "lisaa" + "\n";
        input += "kirja" + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
    }
    /*
    @Given("uusi artikkelivinkki, urlilla {string}, otsikolla {string}, kirjoittajalla {string}, julkaisulla {string} ja julkaisupaivalla {string} annetaan")
    public void lisataanJarjestelmaanTiettyArtikkelivinkki(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) {
        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "lopeta" + "\n";
    }
        //WHENIT
    */
    @When("listataan kaikki lukuvinkit")
    public void listataanLukuvinkit() {
        input += "listaa" + "\n";
        input += "lopeta" + "\n";
        luoUIjaStreamit();
    }
    
    @When("komento suoritetaan")
    public void komentoSuoritetaan() throws Throwable {
        input += "lopeta\n";
        luoUIjaStreamit();
    }
    /*
    @When("kayttaja valitsee vinkin numero {string}")
    public void valitaanVinkki(String numero) {
        input += numero + "\n";
        input += "1" + "\n";
        
        input += "lopeta" + "\n";
        luoUIjaStreamit();
    }
    */
    @When("tyyppi {string}, otsikko {string}, kirjoittaja {string}, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) {
        
        input += tyyppi + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, virheellinen otsikko {string}, kirjoittaja {string}, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaVirheellinenOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) {

        input += tyyppi + "\n";
        input += otsikko + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, URL {string}, otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaUrljaMuutAnnetaan(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaEpakelpoUrljaMuutAnnetaan(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) {

        input += tyyppi + "\n";
        input += url + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiUrl + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaEpakelpoOtsikkojaUrljaMuutAnnetaan(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += validiOtsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        
        input += "lopeta" + "\n";
        
        System.out.println(input);
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, URL {string}, otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonUrlOtsikkoJaKanavaAnnetaanJaMuut(String tyyppi, String url, String otsikko, String kanava, String julkaisupaivamaara) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaivamaara + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonEpakelpoUrlOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava, String julkaisupaivamaara) {

        input += tyyppi + "\n";
        input += url + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiUrl + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaivamaara + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonUrlEpakelpoOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava, String julkaisupaivamaara) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaivamaara + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
        //THENIT
    
    @Then("ohjelmaan tulostuu {string}")
    public void ohjelmaVastaaHalutullaTulosteella(String odotettuTuloste) throws UnsupportedEncodingException {
        System.out.println(uiOutput.toString("UTF-8"));
        assertTrue(uiOutput.toString("UTF-8").contains(odotettuTuloste));
    }
    
    @Then("ohjelma vastaa tulosteella, jossa kohdat {string}, {string}, {string} ja {string}")
    public void ohjelmaVastaaHalutullaTulosteellaJossaTietytNeljaKohtaa(String kohta1, String kohta2, String kohta3, String kohta4) throws UnsupportedEncodingException {
        
        assertTrue(uiOutput.toString("UTF-8").contains(kohta1));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta2));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta3));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta4));
    }
    
    // ei vielä käytössä
    @Then("ohjelma reagoi tulosteella {string}")
    public void ohjelmanViimeisinTulostettuRivi(String odotettuTuloste) throws UnsupportedEncodingException {
        String[] tulostetutRivit = uiOutput.toString("UTF-8").split("\n");
    }
    
    private void luoUIjaStreamit() {
        uiInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        uiOutput = new ByteArrayOutputStream();
       
        app = new Vinkr();
        ui = new TextUI(app, uiInput, uiOutput, null);
        ui.run();
    }
}