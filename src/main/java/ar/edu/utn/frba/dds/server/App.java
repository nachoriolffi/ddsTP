package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.server.crons.SchedulerMain;

public class App {
    public static void main(String[] args){
        Server.init();
        SchedulerMain.main(new String[]{});
    }
}
