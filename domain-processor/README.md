# domain processor service

1) consume domains info from web-domains-topic
2) distinguish between active and inactive domains
3) publish active domains to active.web-domains-topic
4) publish inactive domains to inactive.web-domains-topic