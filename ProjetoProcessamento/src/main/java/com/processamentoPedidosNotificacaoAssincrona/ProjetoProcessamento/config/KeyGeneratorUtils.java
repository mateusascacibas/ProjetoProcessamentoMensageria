package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyGeneratorUtils {

    private static KeyPair keyPair;

    static {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair = generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAPublicKey publicKey() {
        return (RSAPublicKey) keyPair.getPublic();
    }

    public static KeyPair generateRsaKey() {
        return keyPair;
    }
}
