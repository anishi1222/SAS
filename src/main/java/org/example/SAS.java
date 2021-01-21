package org.example;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.sas.BlobContainerSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.AccountSasPermission;
import com.azure.storage.common.sas.AccountSasResourceType;
import com.azure.storage.common.sas.AccountSasService;
import com.azure.storage.common.sas.AccountSasSignatureValues;

import java.time.Duration;
import java.time.OffsetDateTime;

public class SAS {
    public SAS() {
    }

    public String getServiceSasUriForBlob(BlobServiceClient client) {

        // AccountSasPermission.parse("rwacd") でも可
        var permissions = new AccountSasPermission()
                .setReadPermission(true)
                .setWritePermission(true)
                .setAddPermission(true)
                .setCreatePermission(true)
                .setDeletePermission(true);

        // AccountSasResourceType.parse("oc") でも可
        var resourceTypes = new AccountSasResourceType()
                .setContainer(true)
                .setObject(true);

        //  AccountSasService.parse("b") でも可
        var services = new AccountSasService().setBlobAccess(true);

        // SASトークンの有効期限は5分
        var expiryTime = OffsetDateTime.now().plus(Duration.ofMinutes(5));

        // SASトークンを作成
        var sasValues = new AccountSasSignatureValues(expiryTime, permissions, services, resourceTypes);
        return client.generateAccountSas(sasValues);
    }

    public String getServiceSasUriForBlobContainer(BlobContainerClient client) {

        // BlobSasPermission.parse("rwacd") でも可
        var permissions = new BlobContainerSasPermission()
                .setReadPermission(true)
                .setWritePermission(true)
                .setAddPermission(true)
                .setCreatePermission(true)
                .setDeletePermission(true);

        // SASトークンの有効期限は5分
        var expiryTime = OffsetDateTime.now().plus(Duration.ofMinutes(5));

        // SASトークンを作成
        var sasValues = new BlobServiceSasSignatureValues(expiryTime, permissions);
        return client.generateSas(sasValues);
    }
}
