package org.elpuentesearcy.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.ContainerCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.codepipeline.CodePipelineClient;

@Configuration
@Profile( "beta" )
public class CodePipelineConfig
{
    @Value( "${org.elpuentesearcy.aws.region}" )
    private String awsRegion;

    @Bean
    public CodePipelineClient codePipelineClient()
    {
        return CodePipelineClient
          .builder()
          .region( Region.of( awsRegion ) )
          .credentialsProvider( ContainerCredentialsProvider.builder().build() )
          .build();
    }
}
