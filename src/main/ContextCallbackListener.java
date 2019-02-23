package main;

import javafx.util.Pair;
import main.utils.constants.Constants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class ContextCallbackListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loadCountriesList(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void loadCountriesList(ServletContextEvent sce) {
        ResourceBundle rb = ResourceBundle.getBundle("main/resources/country");
        HashMap<String, String> map = new HashMap<>();
        List<Pair<String, String>> sortedCountriesList = new ArrayList<>();
        for (final String key: rb.keySet()) {
            String value = rb.getString(key);
            map.put(key, value);
            sortedCountriesList.add(new Pair<>(key, value));
        }
        sortedCountriesList.sort(Comparator.comparing(Pair::getValue));
        sce.getServletContext().setAttribute(Constants.COUNTRIES_MAP, map);
        sce.getServletContext().setAttribute(Constants.SORTED_COUNTRIES_LIST, sortedCountriesList);
    }
}
