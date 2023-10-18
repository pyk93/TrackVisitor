package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;




@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},  // basePackageClasses에 지정
        basePackages = {"kr.pyk93.numble.deepdive.backend.TrackVisitor"}) // basePackages도 추가로 반드시 지정해줘야 한다
@SpringBootApplication
public class TrackVisitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackVisitorApplication.class, args);
	}

}
