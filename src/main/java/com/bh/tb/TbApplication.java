package com.bh.tb;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class TbApplication {

	// @Value("${jasypt.encryptor.password}")
	// private final static String adsf = "";

	public static void main(String[] args) {
		// PooledPBEStringEncryptor standardPBEStringEncryptor = new PooledPBEStringEncryptor ();
		// SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		// config.setPassword("HWAN@1218");
    // config.setAlgorithm("PBEWithMD5AndDES");
    // config.setKeyObtentionIterations("1000");
    // config.setPoolSize("1");
    // config.setProviderName("SunJCE");
    // config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    // config.setStringOutputType("base64");
		// standardPBEStringEncryptor.setConfig(config);

		// String plainText = "AP_NORTHEAST_2";

		// String encrpytedText = standardPBEStringEncryptor.encrypt(plainText);
		// String decrpytedText = standardPBEStringEncryptor.decrypt(encrpytedText);

		// System.out.println("@@@@@@@@@@@@@@@ " + encrpytedText);
		// System.out.println("@@@@@@@@@@@@@@@ " + decrpytedText);

		// System.out.println("@@@@@ " + adsf);
		
		SpringApplication.run(TbApplication.class, args);
	}

}
