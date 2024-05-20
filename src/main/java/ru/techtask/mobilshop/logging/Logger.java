package ru.techtask.mobilshop.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {
    private static final String FILL_DATA_INTO_DATA_BASE = "Fill data into data base: {}";
    private static final String MAKE_QUERY_INTO_DATA_BASE = "Make query into data base: {}";
    private static final String LIST_QUERY_INTO_DATA_BASE = "List query into data base: {}";
    private static final String GET_LIST_PHONES_FROM_DATA_BASE = "Get list phones from data base: {}";
    private static final String GET_LIST_TRANSACTIONS_FROM_DATA_BASE = "Get list transactions from data base: {}";
    private static final String INSERT_VALUE_IS_NOT_VALID = "Insert value is not valid: {}";


    public void logFillData(String incomingString) {
        log.info(FILL_DATA_INTO_DATA_BASE, incomingString);
    }

    public void logMakeQuery(String incomingString) {
        log.info(MAKE_QUERY_INTO_DATA_BASE, incomingString);
    }

    public void logListQuery(String incomingString) {
        log.info(LIST_QUERY_INTO_DATA_BASE, incomingString);
    }

    public void logListPhonesQuery(String incomingString) {
        log.info(GET_LIST_PHONES_FROM_DATA_BASE, incomingString);
    }

    public void logListTransactionsQuery(String incomingString) {
        log.info(GET_LIST_TRANSACTIONS_FROM_DATA_BASE, incomingString);
    }

    public static void logConvertString(String incomingString) {
        log.info(INSERT_VALUE_IS_NOT_VALID, incomingString);
    }
}
