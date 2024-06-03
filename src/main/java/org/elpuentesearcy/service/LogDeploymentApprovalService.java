package org.elpuentesearcy.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogDeploymentApprovalService implements DeploymentApprovalService
{
    @PostConstruct
    public void postConstruct()
    {
        log.info( "No deployment approval service found, defaulting to {}", getClass() );
    }

    @Override
    public void approve()
    {
        log.info( "No-op approval service" );
    }
}
