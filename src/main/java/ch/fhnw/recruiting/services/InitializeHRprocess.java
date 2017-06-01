package ch.fhnw.recruiting.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class InitializeHRprocess implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(InitializeHRprocess.class.getName());
 
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	int lastjobrefid = 0;
    	int jobrefid = 0;
        LOGGER.info("InitializeHRprocess called!!!");
        
        String sql = "SELECT MAX(jobrefid) as jobrefid FROM applicant";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
         
        while(rowSet.next())
        {
          lastjobrefid = rowSet.getInt("jobrefid");
          if (lastjobrefid < 1){
              lastjobrefid = 10000;
          }
        	  
          /*String lastName = rowSet.getString("lastname");*/
          jobrefid = lastjobrefid + 1;
          LOGGER.info("Last jobrefid: " + lastjobrefid);
          LOGGER.info("New jobrefid: " + jobrefid);
        }
          
        execution.setVariable("jobrefid", jobrefid);
        	
        /** execution.setVariable("hrprocess_start_date", new Date()); */

    }
}








