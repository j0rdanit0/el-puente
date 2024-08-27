package org.elpuentesearcy.service;

public interface DeploymentApprovalService
{
    void approve();
    void reject();
    boolean isReadyForApproval();
}
