package org.example;

import com.azure.storage.blob.BlobServiceClientBuilder;

public class App
{
    static final String CONNECTION_STRING="接続文字列";

    public static void main( String[] args ) {

        // Create BlobServiceClient
        var blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(CONNECTION_STRING)
                .buildClient();

        SAS sas = new SAS();
        // Create service SAS for Blob
        System.out.println( "======" );
        System.out.println( "Create service SAS for Blob Service" );
        System.out.println( sas.getServiceSasUriForBlob(blobServiceClient) );


        //Create a unique name for the container, and BlobContainerClient
        var containerName = "STORAGE_CONTAINER" + java.util.UUID.randomUUID();
        var containerClient = blobServiceClient.createBlobContainer(containerName);

        // Create service SAS for Blob container
        System.out.println( "======" );
        System.out.println( "Create service SAS for Blob container" );
        System.out.println( sas.getServiceSasUriForBlobContainer(containerClient) );
    }
}
