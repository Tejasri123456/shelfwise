package com.example.library.service;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.scheduling.annotation.Async;import org.springframework.stereotype.Service;
@Service public class AuditService {private static final Logger log=LoggerFactory.getLogger(AuditService.class);@Async public void record(String event,String actor){log.info("AUDIT event={} actor={}",event,actor);}}
