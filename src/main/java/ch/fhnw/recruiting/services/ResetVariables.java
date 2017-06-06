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
	String FormField_jobTitle;
	String maturity;
	String FormField_maturity;
	int maxsalary;
	int FormField_maxSalary;
	String FormField_ReasonIfRejected = "";
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
      
    	LOGGER.info("Start: ResetVariables");
 
    	
    	
        //execution.setVariable("jobrefid", jobrefid);      
        execution.setVariable("jobTitle", jobTitle);
        execution.setVariable("FormField_jobTitle", FormField_jobTitle);
        execution.setVariable("maturity", maturity);   
        execution.setVariable("FormField_maturity", FormField_maturity);
        execution.setVariable("maxsalary", maxsalary);
        execution.setVariable("FormField_maxSalary", FormField_maxSalary);
        execution.setVariable("FormField_ReasonIfRejected", FormField_ReasonIfRejected);


        LOGGER.info("End: ResetVariables");
    }
}








