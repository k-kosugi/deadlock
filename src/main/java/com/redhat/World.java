package com.redhat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/world")
public class World {

    @GET
    public String hello(){

        synchronized (Lock.object2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Lock.object1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return "world";
    }

}
