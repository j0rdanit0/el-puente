package org.elpuentesearcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

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
        DOWNLOADS_DIRECTORY = basePath + DOWNLOADS_FOLDER;
        WELL_KNOWN_DIRECTORY = basePath + WELL_KNOWN_FOLDER;
        SpringApplication.run( ElPuenteBoot.class, args );
    }
}