package com.forcen.senbank.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {
    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.senbank.sn/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create("/email-already-used");
    public static final URI USERNAME_ALREADY_USED_TYPE = URI.create("/username-already-used");

}
