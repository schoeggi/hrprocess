package ch.fhnw.recruiting.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class PutApplicantToDB implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(PutApplicantToDB.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	int lastapplicantid = 0;
    	int applicantid = 0;
        LOGGER.info("InitializeHRprocess called!!!");

       /** execution.setVariable("hrprocess_start_date", new Date()); */
        
        String sql = "SELECT MAX(jobrefid) as jobrefid FROM applicant";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
         
        while(rowSet.next())
        {
        lastapplicantid = rowSet.getInt("jobrefid");
          if (lastapplicantid < 1){
        	  lastapplicantid = 100;
          }
        	  
          /*String lastName = rowSet.getString("lastname");*/
          applicantid = lastapplicantid + 1;
          LOGGER.info("Last lastapplicantid: " + lastapplicantid);
          LOGGER.info("New applicantid: " + applicantid);
        }
        
       
        execution.setVariable("jobrefid", applicantid);
        
        LOGGER.info("Start: Insert applicant to DB");
        jdbcTemplate.execute("INSERT INTO APPLICANT (ID,FIRSTNAME,LASTNAME,EMAIL,SALARY) VALUES (" +applicantid +",'Georg','Buzzi','joelenrico.lehner@students.fhnw.ch',100000)");
        LOGGER.info("End: Insert applicant to DB");

    }
}








