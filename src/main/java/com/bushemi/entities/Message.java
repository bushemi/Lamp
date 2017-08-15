package com.bushemi.entities;

import java.time.LocalDateTime;

/**
 * Created by igor on 15.08.17.
 */
public class Message {
    private long id = -1;
    private String content;
    private LocalDateTime timeSent;
    private long idPersonFrom;
    private long idPersonTo;
}
