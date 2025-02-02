Feature: Käyttäjä voi listata järjestelmässä olevat lukuvinkit

    Scenario: Listaaminen, kun järjestelmään ensin lisätään lukuvinkki
        Given uusi kirjavinkki, otsikolla "The Art of Computer Programming", kirjoittajalla "Knuth, Donald", jonka ISBN on "0-201-03801-3", julkaisupaikalla "jossain", kustantajalla "joku" ja julkaisuvuodella "2008" lisataan
        When  listataan kaikki lukuvinkit
        Then  ohjelma vastaa tulosteella, jossa kohdat "Tyyppi: Kirja", "Tekijä: Knuth, Donald", "Nimeke: The Art of Computer Programming" ja "ISBN: 0-201-03801-3"

