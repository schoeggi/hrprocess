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
public class UpdateCandidateStatusPrepMail implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(UpdateCandidateStatusPrepMail.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Start: FlagApplicant1Round called!!!");
        String mailSubjectAfterDMN = "";
        String mailBodyAfterDMN = "";
        boolean passed1dmn = false;
        LOGGER.info("just before var gathering!!!");

        String firstname = (String) execution.getVariable("firstname");
        String lastname = (String) execution.getVariable("lastname");
        int candidate_id = (int) execution.getVariable("candidate_id");
        String applicabilityResult = (String) execution.getVariable("applicabilityResult");
        int jobrefid = (int) execution.getVariable("jobrefid");
       	String jobTitle = (String) execution.getVariable("jobTitle");
    	String maturity = (String) execution.getVariable("maturity");

        if (applicabilityResult.equals("A")){
        	LOGGER.info("entered if");
        	passed1dmn = true;
        	mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        	mailBodyAfterDMN = "Hi " +firstname +"\n\n" 
        						+"Thank you for your application at Fiusable Ltd. Congratulation, you passed the first round (DMN) for the following position:" +"\n" 
        						+maturity +" " +jobTitle +"(job reference: " +jobrefid +")" +"\n\n"
        						+"Our HR department will get in contact with you very soon to find a suitable appointment for your 1. interview." +"\n" 
        						+"Good luck for the next application steps!" +"\n\n" 
        						+"Best wishes, your Fiusable Ltd Team";	
        }
        else{
        	LOGGER.info("entered else");
        	passed1dmn = false;
        	mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        	mailBodyAfterDMN = "Hi " +firstname +"\n\n" +"Thanks for your application at Fiusable Ltd. Unfortunately you're application dropped out in our first round (DMN) for position:" +"\n" 
								+maturity +" " +jobTitle +"(job reference: " +jobrefid +")" +"\n\n"
        						+"All the best in your further career," +"\n\n" 
        						+"Best wishes, your Fiusable Ltd Team";
        }
        execution.setVariable("mailSubjectAfterDMN", mailSubjectAfterDMN);
        execution.setVariable("mailBodyAfterDMN", mailBodyAfterDMN);

        String sql_recommendation = "UPDATE applicant SET PASSED1DMN="+passed1dmn +" WHERE id="+candidate_id;
    	LOGGER.info("just before execute sql");    
        jdbcTemplate.execute(sql_recommendation);     
        LOGGER.info("executed SQL Statement:" +sql_recommendation);
        LOGGER.info("End: FlagApplicant1Round");
    }
}








