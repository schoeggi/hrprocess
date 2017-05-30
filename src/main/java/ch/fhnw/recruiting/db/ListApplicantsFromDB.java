package ch.fhnw.recruiting.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.ResultSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class ListApplicantsFromDB implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(ListApplicantsFromDB.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("ListApplicantsFromDB started!!!");
        
        String sql = "SELECT * FROM applicant";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
         
        while(rowSet.next())
        {
          String firstName = rowSet.getString("firstname");
          String lastName = rowSet.getString("lastname");
          LOGGER.info("Vorname: " + firstName);
          LOGGER.info("Nachname: " + lastName);
          LOGGER.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }

        LOGGER.info("ListApplicantsFromDB started!!!");

    }
}








