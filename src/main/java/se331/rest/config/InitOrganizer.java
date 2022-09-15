package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;

@Component
public class InitOrganizer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    OrganizerRepository organizerRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        organizerRepository.save(Organizer.builder()
                .id(1000L)
                .organizationName("So Sleepy")
                .address("Chiang Mai, Thailand")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(2000L)
                .organizationName("Go to Bed")
                .address("Bangkok, Thailand")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(3000L)
                .organizationName("It is Hot")
                .address("Chiang Mai, Thailand")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(4000L)
                .organizationName("Too tired")
                .address("Busan, Korea")
                .build());
    }
}
