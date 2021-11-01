package io.github.smallintro.kafka.domainprocessor.model;

import java.io.Serializable;
import java.util.Objects;

public class DomainInfo implements Serializable  {

    String domain;
    String create_date;
    String update_date;
    String country;
    boolean isDead;
    String A;
    String NS;
    String CNAME;
    String MX;
    String TXT;

    public DomainInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainInfo)) return false;
        DomainInfo that = (DomainInfo) o;
        return isDead == that.isDead && Objects.equals(domain, that.domain) && Objects.equals(create_date, that.create_date) && Objects.equals(update_date, that.update_date) && Objects.equals(country, that.country) && Objects.equals(A, that.A) && Objects.equals(NS, that.NS) && Objects.equals(CNAME, that.CNAME) && Objects.equals(MX, that.MX) && Objects.equals(TXT, that.TXT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, create_date, update_date, country, isDead, A, NS, CNAME, MX, TXT);
    }

    public DomainInfo(String domain, String create_date, String update_date, String country, boolean isDead, String a, String NS, String CNAME, String MX, String TXT) {
        this.domain = domain;
        this.create_date = create_date;
        this.update_date = update_date;
        this.country = country;
        this.isDead = isDead;
        A = a;
        this.NS = NS;
        this.CNAME = CNAME;
        this.MX = MX;
        this.TXT = TXT;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }

    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public String getMX() {
        return MX;
    }

    public void setMX(String MX) {
        this.MX = MX;
    }

    public String getTXT() {
        return TXT;
    }

    public void setTXT(String TXT) {
        this.TXT = TXT;
    }
}