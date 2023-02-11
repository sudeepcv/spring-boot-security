package com.sudeepcv;

import com.sudeepcv.model.People;
import com.sudeepcv.model.UserInfo;
import com.sudeepcv.repository.PeopleRepository;
import com.sudeepcv.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityApplication {
	@Autowired
	PeopleRepository peopleRepository;

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}
	@PostConstruct
	public void addData(){
		peopleRepository.save(People.builder().name("sudeeep").build());
		peopleRepository.save(People.builder().name("anju").build());

		userInfoRepository.save(UserInfo.builder().name("admin").password(passwordEncoder.encode("admin")).roles("ROLE_ADMIN").build());
		userInfoRepository.save(UserInfo.builder().name("user").password(passwordEncoder.encode("user")).roles("ROLE_USER").build());
	}

}
