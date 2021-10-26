package com.smallintro.springboot.domaincrawler.controller;

import com.smallintro.springboot.domaincrawler.service.DomainLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainCrawlerController {

    @Autowired
    private DomainLookupService domainLookupService;

    @GetMapping("/lookup/{domain}")
    public ResponseEntity lookupDomain(@PathVariable("domain") final String domainName, @RequestParam("zone") final String zone) {
        try {
            if (null == domainName || "".equals(domainName.trim())) {
                return new ResponseEntity("Invalid domain", HttpStatus.BAD_REQUEST);
            }
            domainLookupService.lookupDomain(domainName, zone);
            return new ResponseEntity("Success to lookup. Domain: " + domainName, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Failed to lookup domain: " + domainName + " due to " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
