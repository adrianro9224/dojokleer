package com.kleer.dojo.component;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyPairComponent {

    private  KeyPair keysPair;

    private Logger logger;



    public KeyPairComponent(
            @Value("${keyPairs.public.location}") String publicKeyPath,
            @Value("${keyPairs.private.location}") String privateKeyPath
    ) {
        //this.keysPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        this.logger = Logger.getLogger(KeyPairComponent.class);
        try {
            this.keysPair = new KeyPair(loadPublic(publicKeyPath), loadPrivate(privateKeyPath));
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static PrivateKey loadPrivate(String filename)
            throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public static PublicKey loadPublic(String filename)
            throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public KeyPair getKeysPair() {
        return keysPair;
    }
}
