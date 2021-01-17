package com.iioannou.cameletlprocessor.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.microsoft.azure.storage.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
@Slf4j
public class AzureConfig {

    @Value("${azure.accessKey}")
    private String accessKey;
    @Value("${azure.accountName}")
    private String accountName;
    @Value("${azure.containerName}")
    private String containerName;
    @Value("${azure.url}")
    private String url;

    @Bean("azureClient")
    public BlobServiceClient azureBlobClient() throws URISyntaxException, StorageException {

        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accessKey);
        String uri = String.format(url, accountName, containerName);
        BlobServiceClient client = new BlobServiceClientBuilder()
                .endpoint(uri)
                .credential(credential)
                .buildClient();

        client.listBlobContainers().stream().filter(bci -> bci.getName().contentEquals("testContainer")).findFirst()
                .ifPresentOrElse(cont ->
                        log.info("Container {} found", cont.getName()), () -> {
                    final BlobContainerClient blobContainer = client.createBlobContainer("testContainer");
                    log.info("Container created, URL = {}", blobContainer.getBlobContainerUrl());
                });

        return client;
    }
}
