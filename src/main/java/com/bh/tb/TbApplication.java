package com.bh.tb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bh.tb.config.JasyptConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class TbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TbApplication.class, args);

		// JasyptConfig jasyptConfig = new JasyptConfig();

		// jasyptConfig.getEncryptedPlainText("hwan255777!@");
	}

}
