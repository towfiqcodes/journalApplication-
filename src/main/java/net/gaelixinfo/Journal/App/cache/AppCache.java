package net.gaelixinfo.Journal.App.cache;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import net.gaelixinfo.Journal.App.entity.ConfigJournalApp;
import net.gaelixinfo.Journal.App.repository.ConfigJournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class AppCache {


    private Map<String,String> appCache;

    public enum keys{
    WEATHER_API
    }

    @Autowired
    ConfigJournalAppRepo configJournalAppRepo;



    @PostConstruct
    public void  init(){
        appCache = new HashMap<>();
        List<ConfigJournalApp> all = configJournalAppRepo.findAll();
        for(ConfigJournalApp configJournalApp : all){
            appCache.put(configJournalApp.getKey(),configJournalApp.getValue());
        }
    }
}
