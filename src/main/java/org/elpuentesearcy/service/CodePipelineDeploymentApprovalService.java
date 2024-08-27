package org.elpuentesearcy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.codepipeline.CodePipelineClient;
import software.amazon.awssdk.services.codepipeline.model.ApprovalStatus;
import software.amazon.awssdk.services.codepipeline.model.PutApprovalResultResponse;

import java.util.Optional;

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
        log.info( "Approving deployment of beta site to production..." );
        putApprovalResult( ApprovalStatus.APPROVED, "Approved via Go Live button" )
          .ifPresentOrElse(
            response -> log.info( "Approved at {}", response.approvedAt() ),
            () -> log.warn( "Unable to approve deployment at this time." )
          );
    }

    @Override
    public void reject()
    {
        log.info( "Rejecting deployment of beta site to production..." );
        putApprovalResult( ApprovalStatus.REJECTED, "Rejected via Decline button" )
          .ifPresentOrElse(
            response -> log.info( "Rejected at {}", response.approvedAt() ),
            () -> log.warn( "Unable to reject deployment at this time." )
          );
    }

    @Override
    public boolean isReadyForApproval()
    {
        return fetchToken().isPresent();
    }

    private Optional<PutApprovalResultResponse> putApprovalResult( ApprovalStatus approvalStatus, String summary )
    {
        Optional<PutApprovalResultResponse> response = Optional.empty();

        Optional<String> token = fetchToken();
        if ( token.isPresent() )
        {
            log.debug( "Using approval token: {}", token.get() );

            response = Optional.of(
              codePipelineClient.putApprovalResult( request ->
                request
                  .pipelineName( pipelineName )
                  .stageName( stageName )
                  .actionName( actionName )
                  .result( result ->
                    result
                      .status( approvalStatus )
                      .summary( summary )
                      .build()
                  )
                  .token( token.get() )
                  .build()
              ) );
        }

        return response;
    }

    private Optional<String> fetchToken()
    {
        return codePipelineClient
          .getPipelineState( request -> request.name( pipelineName ).build() )
          .stageStates()
          .stream()
          .filter( stageState -> stageState.stageName().equals( stageName ) )
          .flatMap( stageState -> stageState.actionStates().stream() )
          .filter( actionState -> actionState.actionName().equals( actionName ) )
          .map( actionState -> actionState.latestExecution().token() )
          .findAny();
    }
}
