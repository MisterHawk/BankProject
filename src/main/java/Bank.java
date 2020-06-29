package main.java;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    /**
     * The name of the bank
     */
    private String name;
    /**
     * The list of customers the bank has
     */
    private ArrayList<User> customers;
    /**
     * The list of accounts the bank has
     */
    private ArrayList<Account> accounts;

    /**
     * Create a new Bank object with lists of users and accounts
     * @param name the name of the bank
     */
    public Bank(String name){

        this.name = name;
        this.customers = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }

    /**
     * Generate a new universally unique ID for a user
     * @return the uuid
     */
    public String getNewUserUUID(){
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping while nonUnique is equal to true 
        do {
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //Check to make sure the uuid is unique
            nonUnique = false;
            for(User u : this.customers){
                if(uuid.compareTo(u.getUuid()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * Generate a new universally unique ID for an account
     * @return
     */
    public String getNewAccountUUID(){
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping while nonUnique is equal to true 
        do {
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //Check to make sure the uuid is unique
            nonUnique = false;
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUuid()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * Add an Account
     * @param anAcct the Account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    /**
     * Create a new user of the bank
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's pin
     * @return the new User Object
     */
    public User addUser(String firstName, String lastName, String pin){

        // Create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.customers.add(newUser);

        // Create a savings account
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the User Object associated with a particular userID and pin if they are valid
     * @param userID the UUID of the user to log in 
     * @param pin the pin of the user
     * @return the User Object if the login was successful or null if not
     */
    public User userLogin(String userID, String pin){

        // Search through list of users
        for (User u : customers){
            // Check if user ID is correct
            if (u.getUuid().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        // if we haven't found the user
        return null;
    }

    public String getName(){
        return this.name;
    }
    
}