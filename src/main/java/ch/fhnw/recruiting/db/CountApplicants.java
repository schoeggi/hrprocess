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
public class CountApplicants implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(CountApplicants.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	int applicantCounter = 0;

        LOGGER.info("Start: Count applicants called!!!");
        
        String sql_count = "SELECT COUNT(*) as count FROM APPLICANT";
        SqlRowSet rowSet_count = jdbcTemplate.queryForRowSet(sql_count);     
        while(rowSet_count.next())
        {
        	applicantCounter = rowSet_count.getInt("count");
          LOGGER.info("Number of applicants: " + applicantCounter);
        }
        execution.setVariable("applicantCounter", applicantCounter);
        LOGGER.info("End: Count applicants called!!!");
        
        
    }
}








