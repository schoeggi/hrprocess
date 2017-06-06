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
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class ResetVariables implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(ResetVariables.class.getName());
   
    int jobrefid;
	String jobTitle;
	String maturity;
	int maxsalary;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
      
    	LOGGER.info("Start: ResetVariables");
 
    	
    	
        execution.setVariable("jobrefid", jobrefid);      
        execution.setVariable("jobTitle", jobTitle);
        execution.setVariable("maturity", maturity);
        execution.setVariable("maxsalary", maxsalary);

        LOGGER.info("End: ResetVariables");
    }
}








