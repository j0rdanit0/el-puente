package org.elpuentesearcy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@Slf4j
@SpringBootApplication
public class ElPuenteBoot
{
    public static final String IMAGE_FOLDER = "/images/";
    public static final String DOWNLOADS_FOLDER = "/downloads/";
    public static final String WELL_KNOWN_FOLDER = "/.well-known/";
    public static String IMAGE_DIRECTORY;
    public static String DOWNLOADS_DIRECTORY;
    public static String WELL_KNOWN_DIRECTORY;

    public static void main( String[] args ) throws IOException
    {
        String basePath = new File( "." ).getCanonicalPath();
        IMAGE_DIRECTORY = basePath + IMAGE_FOLDER;
        log.info( "IMAGE_DIRECTORY: " + IMAGE_DIRECTORY );
        DOWNLOADS_DIRECTORY = basePath + DOWNLOADS_FOLDER;
        log.info( "DOWNLOADS_DIRECTORY: " + DOWNLOADS_DIRECTORY );
        WELL_KNOWN_DIRECTORY = basePath + WELL_KNOWN_FOLDER;
        log.info( "WELL_KNOWN_DIRECTORY: " + WELL_KNOWN_DIRECTORY );
        SpringApplication.run( ElPuenteBoot.class, args );
    }
}