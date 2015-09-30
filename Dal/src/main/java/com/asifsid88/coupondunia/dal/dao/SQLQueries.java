package com.asifsid88.coupondunia.dal.dao;

import java.util.List;

/**
 * SQL Queries: Could move this into Stored Proc:
 *
 * This could have been much better, in fact can be totally removed and moved into Stored Procedures
 * Moreover, the callee could have changed to Callable Statement, or we can also integrate ORM (viz., Hibernate) to handle
 * things
 */
public final class SQLQueries {
    public static String getEmail() {
        String query = "SELECT `id` `id`  " +
                            " ,`from_email` `fromEmail`  " +
                            " , `to_email` `toEmail`  " +
                            " , `SUBJECT` `SUBJECT`  " +
                            " , `body` `body`  " +
                            " , `status` `status`  " +
                            " FROM `EmailQueue` " +
                            " WHERE `status` = 0";

        return query;
    }

    public static String getEmailsByIdsInBatch(Long id, int limit) {
        String query = "SELECT `id` `id`  " +
                            " ,`from_email` `fromEmail`  " +
                            " , `to_email` `toEmail`  " +
                            " , `SUBJECT` `SUBJECT`  " +
                            " , `body` `body`  " +
                            " , `status` `status`  " +
                            " FROM `EmailQueue` " +
                            " WHERE `status` = 0 " +
                            " AND `id` >  " + id +
                            " LIMIT " + limit;

        return query;
    }

    public static String getEmailsInBatch(int limit) {
        String query = "SELECT `id` `id`  " +
                " ,`from_email` `fromEmail`  " +
                " , `to_email` `toEmail`  " +
                " , `SUBJECT` `SUBJECT`  " +
                " , `body` `body`  " +
                " , `status` `status`  " +
                " FROM `EmailQueue` " +
                " WHERE `status` = 0 " +
                " LIMIT " + limit;

        return query;
    }

    public static String deleteEmailsById(List<Long> ids) {
        String query = "DELETE FROM `EmailQueue` " +
                        " WHERE `id` IN (" + listToCommaSeparatedValue(ids) + ")";

        return query;
    }

    private static String listToCommaSeparatedValue(List<Long> ids) {
        if(ids == null || ids.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(Long id : ids) {
            sb.append(id);
            sb.append(",");
        }

        String s = sb.toString();

        return s.substring(0, s.length()-1);
    }
}
