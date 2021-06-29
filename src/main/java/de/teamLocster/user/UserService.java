package de.teamLocster.user;

import de.teamLocster.actions.Action;
import de.teamLocster.core.BaseRepository;
import de.teamLocster.core.BaseService;
import de.teamLocster.enums.OnlineStatus;
import de.teamLocster.enums.PrivacyStatus;
import de.teamLocster.enums.Sex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class UserService extends BaseService<User>
{
    BaseRepository<Action> actionRepository;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    @Autowired
    public UserService(BaseRepository<User> userRepository)
    {
        super(userRepository);
    }

    public boolean registerNewUser(
            String firstName,
            String lastName,
            String birthday,
            String sex,
            String email,
            String password
    ) {
        try {
            // TODO passwortsicherheit und andere anforderungen prüfen DEFAULT VALUES IN ENUMS
            // city (required?)
            // occupation (required?)
            // beziehungsstatus (required?)
            // sex (select? default?)
            // privatssphäre (default?)
            // onlinestatus (default?)
            User userToRegister = new User(
                    email,
                    Integer.toString(password.hashCode()),
                    firstName,
                    lastName,
                    null,
                    formatter.parse(birthday),
                    null,
                    null,
                    "männlich".equals(sex) ? Sex.MALE : Sex.FEMALE, // TODO
                    "pseudo/path",
                    "Apparently, this user prefers to keep an air of mystery about them.",
                    "Hey, I'm using Locster!",
                    PrivacyStatus.PRIVATE, // TODO
                    OnlineStatus.ONLINE,
                    new HashSet<>()
            );

            repository.save(userToRegister);
            return true;
        }
        catch (Exception e) {
            // TODO LOGGING
            System.out.println("EXCEPTION  |  " + e.toString());
            return false;
        }
    }
}
