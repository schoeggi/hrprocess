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
public class RetrieveBestRatedCandidate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(RetrieveBestRatedCandidate.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
 
        LOGGER.info("Start: RetrieveBestRatedCandidate called!!!");
        
        int best_candidate_id = 0;
        String best_candidate_firstname = "";
        String best_candidate_lastname = "";
        String best_candidate_email = "";
        String sql_best_candidate = "SELECT hiringprio, ID, firstname, lastname, email FROM applicant where hiringprio <=10 and hiringprio >=1 ORDER BY hiringprio ASC LIMIT 1";
        SqlRowSet rowSet_sql_best_candidate = jdbcTemplate.queryForRowSet(sql_best_candidate);   
        String mailSubjectAfterDMN = "";
        String mailBodyAfterDMN = "";
        int jobrefid = (int) execution.getVariable("jobrefid");
       	String jobTitle = (String) execution.getVariable("jobTitle");
    	String maturity = (String) execution.getVariable("maturity");
        
        while(rowSet_sql_best_candidate.next())
        {
        	best_candidate_id = rowSet_sql_best_candidate.getInt("id");
        	best_candidate_firstname = rowSet_sql_best_candidate.getString("firstname");  
            best_candidate_lastname = rowSet_sql_best_candidate.getString("lastname");  
        	best_candidate_email = rowSet_sql_best_candidate.getString("email");  
            LOGGER.info("id, firstname, lastname, email: " +best_candidate_id +" " +best_candidate_firstname +" " +best_candidate_lastname +" " +best_candidate_email);
        }
       
        execution.setVariable("best_candidate_id", best_candidate_id);
        execution.setVariable("best_candidate_firstname", best_candidate_firstname);
        execution.setVariable("best_candidate_lastname", best_candidate_lastname);
        execution.setVariable("best_candidate_email", best_candidate_email);
        
        mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        mailBodyAfterDMN = "Hi " +best_candidate_firstname +"\n\n" 
        					+"Congratulation, you've got the job!" +"\n"
        					+maturity +" " +jobTitle +"(job reference: " +jobrefid +")" +"\n"
        					+"Our HR department will get in contact with you very soon to clarify all the details!" +"\n\n" 
        					+"Best wishes and see you soon, your Fiusable Ltd Team";
        
        String applicantMailAddress =  best_candidate_email;
        execution.setVariable("applicantMailAddress", applicantMailAddress);
        execution.setVariable("mailSubjectAfterDMN", mailSubjectAfterDMN);
        execution.setVariable("mailBodyAfterDMN", mailBodyAfterDMN);     
               

        LOGGER.info("End: RetrieveBestRatedCandidate called!!!");
   
    }
}








