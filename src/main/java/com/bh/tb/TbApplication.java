package com.bh.tb;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TbApplication {

	public static void main(String[] args) {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor ();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("HWAN@1218");
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
		standardPBEStringEncryptor.setConfig(config);
		System.out.println("@@@" + encryptText);
		SpringApplication.run(TbApplication.class, args);
	}

}
