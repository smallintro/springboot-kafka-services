package io.github.smallintro.kafka.domaincrawler.model;

import java.util.List;
import java.util.Objects;

public class DomainList {

    List<DomainInfo> domains;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainList)) return false;
        DomainList that = (DomainList) o;
        return Objects.equals(getDomains(), that.getDomains());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDomains());
    }

    public List<DomainInfo> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainInfo> domains) {
        this.domains = domains;
    }

    public DomainList() {
    }

    public DomainList(List<DomainInfo> domains) {
        this.domains = domains;
    }
}
