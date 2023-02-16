package com.example.bundle.client;

import com.example.bundle.common.Booking;
import com.example.bundle.common.BookingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
    private ServiceTracker<BookingService,BookingService> bookingServiceTrace;
    private ServiceRegistration clientServiceRegistration;
    private Display display;
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        bookingServiceTrace = new ServiceTracker<BookingService,BookingService>(bundleContext, BookingService.class,null){
            public BookingService addingService(ServiceReference<BookingService>reference){
                BookingService bookingService = bundleContext.getService(reference);

                ClientServiceImpl clientService = new ClientServiceImpl();
                clientService.setBookingService(bookingService);
                clientServiceRegistration = bundleContext.registerService(ClientService.class,clientService,null);

                display = new Display();
                display.setClientService(clientService);
                display.init();

                return bookingService;
            }

            public void removedService(ServiceReference<BookingService> reference,BookingService service){
                display.destroy();
                clientServiceRegistration.unregister();
            }
        };

        bookingServiceTrace.open();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        bookingServiceTrace.close();
    }
}
