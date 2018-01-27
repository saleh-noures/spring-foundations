package com.noures.common.util;

public class Worker {
    private String preamble;
    private String text;
    private boolean isDev;

    public Worker(String preamble, String text,boolean isDev ){
        this.preamble = preamble;
        this.text = text;
        this.isDev = isDev;
        System.out.println("New Instance");
    }

    public void execute(){
        System.out.println(preamble + " " + text + "is dev: " + isDev);
    }

}
