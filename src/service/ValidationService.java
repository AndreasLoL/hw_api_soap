package service;

public class ValidationService {

    public boolean tweetIsValid(String message) {
        return message.length() <= 144 && message.length() > 0;
    }

}
