package vritika.app.startstuf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 12;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {

        if (position==1)
        {
            return new DummyItem(String.valueOf(position), "Web design & Development", makeDetails(position));
        }
        else if(position==2) {
            return new DummyItem(String.valueOf(position), "Digital Marketing", makeDetails(position));
        }

        else if(position==3) {
            return new DummyItem(String.valueOf(position), "E-commerce Development", makeDetails(position));
        }

        else if(position==4) {
            return new DummyItem(String.valueOf(position), "Customized Web Applications", makeDetails(position));
        }

        else if(position==5) {
            return new DummyItem(String.valueOf(position), "Mobile Application Development", makeDetails(position));
        }

        else if(position==6) {
            return new DummyItem(String.valueOf(position), "Bulk SMS Services", makeDetails(position));
        }
        else if(position==7) {
            return new DummyItem(String.valueOf(position), "Invoice & Billing Software", makeDetails(position));
        }
        else if(position==8) {
            return new DummyItem(String.valueOf(position), "Online Room Reservation", makeDetails(position));
        }
        else if(position==9) {
            return new DummyItem(String.valueOf(position), "Online Food Ordering" , makeDetails(position));
        }
        else if(position==10) {
            return new DummyItem(String.valueOf(position), "Education Software", makeDetails(position));
        }
        else if(position==11) {
            return new DummyItem(String.valueOf(position), "Domain & Hosting Services", makeDetails(position));
        }
        else  {
            return new DummyItem(String.valueOf(position), "Payment Gateway Integration", makeDetails(position));
        }

        }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        if (position==1)
        {
            builder.append("It is not enough that we build products and services but we also need to showcase them designing creative website and promote in a strategic manner." +
                    " VTPL provide you affordable solutions for your customized requirements.");
        }
        if (position==2)
        {
            builder.append("Digital marketing is an online promotion concept which leverage digital channels such as Google search, social media platforms, forums, blogs etc. to connect with prospective customers. " +
                    "VTPL only company providing effective Digital Marketing service in Belgaum.");
        }
        if (position==3)
        {
            builder.append("E-commerce is selling your goods and services through online, " +
                    "e-commerce platform which ease to customers buying requirements. " +
                    "VTPL will provide customize solution for customer with affordable prices and maintenance.");
        }
        if (position==4)
        {
            builder.append("Customized application are which requires according to company processes and companies looking up to their standard of process and policies." +
                    " VTPL work on the same and we build a High-Tech system process in organization.");
        }
        if (position==5)
        {
            builder.append("Mobile applications are made for easy access of services and products which built on Android and iOS" +
                    " platforms which will helps customer to routine their buying and selling. VTPL provide customizable mobile " +
                    "applications for your businesses.");
        }
        if (position==6)
        {
            builder.append("We are value ambitious company in the field of providing Bulk promotional," +
                    " transactional SMS and voice call with API integrations to your applications with affordable prices " +
                    "and promoting strategies for your businesses. http://www.vtplbulksms.co.in");
        }
        if (position==7)
        {
            builder.append("Billing and invoice are a part of your products and services process to sell and track your payments, expenses, " +
                    "targets and so on. VTPL Asaan Billing platform provides you wide range of features with customizable options." +
                    " http://www.asaanbilling.co.in");
        }
        if (position==8)
        {
            builder.append("Every individual wants to enjoy his/her holidays, we have a perfect partner" +
                    " to book you stay in everywhere in India. One of the largest room booking network and" +
                    " the motive is to experience your hotel stay in across the country. http://www.gharsure.com");
        }
        if (position==9)
        {
            builder.append("Are you lazy enough to go out and get your food? Here we go with Ravinto " +
                    "mobile application which provides you very explore restaurants and hotels in Belgaum. HEY what you thinking –" +
                    " Go and explore our world class Ravinto Mobile App (Coming Soon). http://www.ravinto.co.in");
        }
        if (position==10)
        {
            builder.append("To track your student and parent information and keep update student progress," +
                    "we are here providing complete Educational ERP- software as service delivery model over " +
                    "secured private cloud platform. Lets go and explore!.\n" +
                    " http://www.vtus.co.in");
        }
        if (position==11)
        {
            builder.append("Find your relevant domain for your business and share your business information on" +
                    " cloud using our hosting services. We do Domain registration, Domain transfers, " +
                    "Site Lock, SSL Certificates and unmatchable data back and restore.");
        }

        if (position==12)
        {
            builder.append("We provide shopping cart checkout options with payment gateway to process your " +
                    "payments through online and most of the companies are involved doing" +
                    " transactions through online which will save your time and money.");
        }


        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
