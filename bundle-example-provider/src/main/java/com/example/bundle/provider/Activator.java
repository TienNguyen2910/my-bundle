package com.example.bundle.provider;

import com.example.bundle.common.BookingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration;
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        // create a booking service impl instance
        BookingServiceImpl bookingService = new BookingServiceImpl();
        //registering the booking service in the registry
        serviceRegistration = bundleContext.registerService(BookingService.class, bookingService,null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if(serviceRegistration != null){
            //remove the service from the service registry
            serviceRegistration.unregister();
        }
    }
}
