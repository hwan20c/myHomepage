package com.bh.tb.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JasyptConfig {

  @Bean("jasyptStringEncryptor")
  public StringEncryptor stringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(getJasyptPassword());
    // config.setPassword("HWAN@1218");
    System.out.println("@@@@@ : " + getJasyptPassword());
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    encryptor.setConfig(config);
    return encryptor;
  }

  private String getJasyptPassword() {
    try {
      ClassPathResource resource = new ClassPathResource("jasypt_password.txt");
      return Files.readAllLines(Paths.get(resource.getURI())).stream()
              .collect(Collectors.joining(" "));
    } catch (IOException e) {
      throw new RuntimeException("Not found Jasypt password");
    }
  }

  public String getEncryptedPlainText(String plainText) {
    PooledPBEStringEncryptor standardPBEStringEncryptor = new PooledPBEStringEncryptor ();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();


    //SET YOUR PASSWORD
		config.setPassword(getJasyptPassword());


    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
		standardPBEStringEncryptor.setConfig(config);

		String encrpytedText = standardPBEStringEncryptor.encrypt(plainText);
		String decrpytedText = standardPBEStringEncryptor.decrypt(encrpytedText);

		System.out.println("@@@@@@@@@@@@@@@ " + encrpytedText);
		System.out.println("@@@@@@@@@@@@@@@ " + decrpytedText);

    return encrpytedText;
  }

  public String getDecrpytedPlainText(String encryptedText) {
    PooledPBEStringEncryptor standardPBEStringEncryptor = new PooledPBEStringEncryptor ();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();


    //SET YOUR PASSWORD
    config.setPassword(getJasyptPassword());


    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    standardPBEStringEncryptor.setConfig(config);

    // String encrpytedText = standardPBEStringEncryptor.encrypt(encryptedText);
    String decrpytedText = standardPBEStringEncryptor.decrypt(encryptedText);

    // System.out.println("@@@@@@@@@@@@@@@ " + encrpytedText);
    System.out.println("@@@@@@@@@@@@@@@ " + decrpytedText);

    return decrpytedText;
  }
}
