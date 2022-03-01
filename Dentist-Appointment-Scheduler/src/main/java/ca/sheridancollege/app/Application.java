package ca.sheridancollege.app;
/**
 * 
 * Author: Jordon Peters 
 * Student ID: 991524979
 * 
 *Description:
 * Base of Week3Lec2ICE modifying to work with MongoDB
 */
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import ca.sheridancollege.beans.Appointment;
import ca.sheridancollege.repositories.AppointmentRepository;

@SpringBootApplication(scanBasePackages={
"ca.sheridancollege.controllers"
})

@EnableMongoRepositories({"ca.sheridancollege.repositories"})

public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private AppointmentRepository apptRep;
		
	@Override
	public void run(String[] args) throws Exception {	
		System.out.println("BOOTSTRAP: Adding sample data to database...");
		Appointment appt = new Appointment();
		appt.setPurpose("Visit dentist for checkup");
		appt.setApptDate(LocalDateTime.now().toString());
		
		if(apptRep.findAll().size()==0)
			apptRep.save(appt);
	}
}
