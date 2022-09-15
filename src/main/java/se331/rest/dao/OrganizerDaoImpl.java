package se331.rest.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Organizer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init(){
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .id(100L)
                .organizationName("HelloWorld")
                .address("Chiang Mai")
                .build());
        organizerList.add(Organizer.builder()
                .id(200L)
                .organizationName("ByeWorld")
                .address("Bangkok")
                .build());
        organizerList.add(Organizer.builder()
                .id(300L)
                .organizationName("linlin")
                .address("Chiang Mai")
                .build());
        organizerList.add(Organizer.builder()
                .id(400L)
                .organizationName("langlang")
                .address("Chiang Mai")
                .build());
        organizerList.add(Organizer.builder()
                .id(500L)
                .organizationName("whatsyourname")
                .address("Phuket")
                .build());
        organizerList.add(Organizer.builder()
                .id(600L)
                .organizationName("howareyou")
                .address("Lampang")
                .build());
    }


    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null?1:page;
        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<Organizer>(organizerList.subList(firstIndex, firstIndex + pageSize), PageRequest.of(page, pageSize), organizerList.size());
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream().filter(event ->
                event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer) {
        organizer.setId(organizerList.get(organizerList.size()-1).getId()+1);
        organizerList.add(organizer);
        return organizer;
    }
}
