package com.example.bundle.client;

import com.example.bundle.common.Booking;

public class Display {
    private ClientService clientService;
    private BookingDisplayThread thread;

    //This setter is used by Blueprint to inject the client service
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    public void init(){
        thread = new BookingDisplayThread(clientService);
        thread.start();
    }
    public void destroy(){
        thread.terminate();
    }
    private class BookingDisplayThread extends Thread {
        private ClientService clientService;
        private volatile  boolean running = true;
        private BookingDisplayThread(ClientService clientService) {
            this.clientService = clientService;
        }
        public void run(){
           if(running){
               Booking booking = new Booking("Tien Nguyen","AF3586");
               clientService.addBooking(booking);

               System.out.println(displayBookings());
           }
        }
        public String displayBookings(){
            StringBuilder builder = new StringBuilder();
            for (Booking booking: clientService.bookings()) {
                builder.append(booking.getId()).append(" | ").append(booking.getCustomer()).append(" | ").append(booking.getFlight()).append("\n");
            }
            return builder.toString();
        }

        public void terminate(){
            running = false;
        }
    }
}
