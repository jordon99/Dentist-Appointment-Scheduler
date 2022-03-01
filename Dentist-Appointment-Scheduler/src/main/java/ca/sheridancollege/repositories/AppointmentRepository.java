package ca.sheridancollege.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.sheridancollege.beans.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
	
}
