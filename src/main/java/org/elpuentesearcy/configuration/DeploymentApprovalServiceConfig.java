package org.elpuentesearcy.configuration;

import org.elpuentesearcy.service.CodePipelineDeploymentApprovalService;
import org.elpuentesearcy.service.DeploymentApprovalService;
import org.elpuentesearcy.service.LogDeploymentApprovalService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.codepipeline.CodePipelineClient;

@Configuration
public class DeploymentApprovalServiceConfig
{
    @Bean
    @ConditionalOnBean( CodePipelineClient.class )
    public DeploymentApprovalService codePipelineDeploymentApprovalService( CodePipelineClient codePipelineClient )
    {
        return new CodePipelineDeploymentApprovalService( codePipelineClient );
    }

    @Bean
    @ConditionalOnMissingBean( DeploymentApprovalService.class )
    public DeploymentApprovalService logDeploymentApprovalService()
    {
        return new LogDeploymentApprovalService();
    }
}
