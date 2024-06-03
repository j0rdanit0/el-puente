package org.elpuentesearcy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.codepipeline.CodePipelineClient;
import software.amazon.awssdk.services.codepipeline.model.ApprovalStatus;
import software.amazon.awssdk.services.codepipeline.model.PutApprovalResultResponse;

@Slf4j
@RequiredArgsConstructor
public class CodePipelineDeploymentApprovalService implements DeploymentApprovalService
{
    private final CodePipelineClient codePipelineClient;

    @Value( "${org.elpuentesearcy.aws.codepipeline.pipeline-name}" )
    private String pipelineName;
    @Value( "${org.elpuentesearcy.aws.codepipeline.stage-name}" )
    private String stageName;
    @Value( "${org.elpuentesearcy.aws.codepipeline.action-name}" )
    private String actionName;

    @Override
    public void approve()
    {
        log.info( "Approving beta site for deployment to production..." );

        String token = codePipelineClient
          .getPipelineState( request ->
            request
              .name( pipelineName )
              .build()
          ).stageStates()
          .stream()
          .filter( stageState -> stageState.stageName().equals( stageName ) )
          .flatMap( stageState -> stageState.actionStates().stream() )
          .filter( actionState -> actionState.actionName().equals( actionName ) )
          .map( actionState -> actionState.latestExecution().token() )
          .findAny()
          .orElseThrow();

        log.debug( "Using approval token: {}", token );

        PutApprovalResultResponse response = codePipelineClient.putApprovalResult( request ->
          request
            .pipelineName( pipelineName )
            .stageName( stageName )
            .actionName( actionName )
            .result( result ->
              result
                .status( ApprovalStatus.APPROVED )
                .summary( "Approved via Go Live button" )
                .build()
            )
            .token( token )
            .build()
        );

        log.info( "Approved at {}", response.approvedAt() );
    }
}
