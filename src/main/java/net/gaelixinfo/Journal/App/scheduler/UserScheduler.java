package net.gaelixinfo.Journal.App.scheduler;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.UserRepoImpl;
import net.gaelixinfo.Journal.App.service.EmailService;
import net.gaelixinfo.Journal.App.service.SentimentAnalysisService;
import net.gaelixinfo.Journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;


    @Scheduled(cron = "0 0 9 * * sun")
    public void fetchUsersAndSendSAMail(){
        List<User> users = userRepoImpl.getUsersForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();

            List<String> filterEntries = journalEntries.stream()
                    .filter(x -> x.getDate()
                            .isAfter(LocalDateTime.now().minusDays(7))).map(x->x.getContent())
                    .toList();

            String join = String.join(", ", filterEntries);

            String sentiment = sentimentAnalysisService.getSentiment(join);

            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }


    }

}
