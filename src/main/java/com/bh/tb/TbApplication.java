package com.bh.tb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class TbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TbApplication.class, args);
	}
}
