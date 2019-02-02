package ru.proshkina.restaurantvoting.util.exception;

import org.springframework.http.HttpStatus;

public class VotingTimeIsOutException extends ApplicationException {
    public static final String VOTING_TIME_IS_OUT_EXCEPTION = "exception.common.votingTimeIsOut";

    //  http://stackoverflow.com/a/22358422/548473
    public VotingTimeIsOutException(String arg) {
        super(ErrorType.VOTING_TIME_IS_OUT, VOTING_TIME_IS_OUT_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}
