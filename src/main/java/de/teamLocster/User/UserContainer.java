package de.teamLocster.User;/*
===================================
== Matthias Gabel
===================================
*/

import de.teamLocster.Exceptions.EmailException;
import de.teamLocster.Exceptions.PasswordException;
import de.teamLocster.Utility.ValidationUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserContainer
{
    /*
    ===================================
    == Variable
    ===================================
    */
    private ArrayList<User> users;
    /*
    ===================================
    == Constructor
    ===================================
    */
    public UserContainer()
    {
        users = new ArrayList<User>();
    }
    /*
    ===================================
    == Methods
    ===================================
    */
    public void registerUser(String firstName, String lastName, String userName, String email,
                             String password, Date Birthdate, PersonalInfo.Sex sex) throws EmailException, PasswordException
    {
        this.checkEmail(email);

        this.checkPassword(password);

        final User user = new User(firstName, lastName, userName, email, password, Birthdate, sex);

        this.users.add(user);
    }

    public ArrayList<User> activeUsers()
    {
        ArrayList<User> activeUser = new ArrayList<User>();
        for (User user : this.users) // for-each user in users
        {
            if (user.getOnlineStatus() == User.OnlineStatus.online)
            {
                activeUser.add(user);
            }
        }
        return activeUser;
    }

    public void deleteUser(User userToDelete)
    {
        this.users.removeIf(user -> user == userToDelete);
    }

    public ArrayList<User> getUserList() //TODO: have to do
    {
        return this.users;
    }

    public void sortBy()
    {
        // TODO: Add functionality
    }

    public boolean checkEmail(String email) throws EmailException//TODO: access modifier may have to be changed
    {
        if (ValidationUtility.isValidEmail(email))
        {
             if (!ValidationUtility.stringAlreadyExistInArray(email, this.getEntireEmailOfAllUsers()))
                {
                    return true;
                }
             else
             {
                 throw new EmailException("Email already exist!");
             }
        }
        else
        {
            throw new EmailException("Invalid Email!");
        }
    }

    public boolean checkPassword(String password) throws PasswordException// TODO: checkPassword might be extendend in future with more validations (next semester)
    {
        if (ValidationUtility.isValidPassword(password))
        {
            return true;
        }
        else
        {
            throw new PasswordException("Invalid Password!");
        }
    }

    public ArrayList<String> getEntireEmailOfAllUsers()
    {
        ArrayList<String> StringContainer = new ArrayList<String>();

        for (User user : this.users)
        {
            StringContainer.add(user.getAccountDetails().getMailAddress());
        }

        return StringContainer;
    }


    /*
    ===================================
    Getter & Setter
    ===================================
    */
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
