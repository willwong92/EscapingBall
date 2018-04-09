package com.example.willwong.escapingball;

/**
 * Created by willwong on 11/27/2016.
 */

public class MoveException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() { return "Wrong! Wrong! Wrong!";}
}
