package org.elpuentesearcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ElPuenteBoot
{
    public static String IMAGE_DIRECTORY;

    public static void main( String[] args ) throws IOException
    {
        IMAGE_DIRECTORY = new File( "." ).getCanonicalPath() + "/images/";
        SpringApplication.run( ElPuenteBoot.class, args );
    }
}

