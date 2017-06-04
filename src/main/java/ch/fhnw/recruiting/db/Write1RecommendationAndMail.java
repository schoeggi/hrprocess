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
public class Write1RecommendationAndMail implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(Write1RecommendationAndMail.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
 
        LOGGER.info("Start: Write1RecommendationAndMail called!!!");
        int candidate_id = (int) execution.getVariable("candidate_id");
        String recommendation1Interview = (String) execution.getVariable("recommendation1Interview");
        boolean outcome1Interview = (boolean) execution.getVariable("outcome1Interview");
        String sql_recommendation = "UPDATE applicant SET HRRECOMMENDATION='"+recommendation1Interview+"' , PASSED1INTERVIEW="+outcome1Interview+" WHERE id= "+candidate_id;
        LOGGER.info("Executed SQL statement HR recommendation:" +sql_recommendation);
        jdbcTemplate.execute(sql_recommendation);   
        
        
        String mailSubjectAfterDMN = "";
        String mailBodyAfterDMN = "";
        boolean passed1dmn = false;
        
        String firstname = (String) execution.getVariable("firstname");
        String lastname = (String) execution.getVariable("lastname");
        int jobrefid = (int) execution.getVariable("jobrefid");
       	String jobTitle = (String) execution.getVariable("jobTitle");
    	String maturity = (String) execution.getVariable("maturity");


        if (outcome1Interview == true){
        	mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        	mailBodyAfterDMN = "Hi " +firstname +"\n\n" 
        						+"Congratulation, you passed the first interview for the following position:" +"\n"
        						+maturity +" " +jobTitle +"(job reference: " +jobrefid +")" +"\n"
        						+"Our HR department will get in contact with you very soon to find a suitable appointment for your 2. interview." +"\n\n" 
        						+"Good luck for the next application steps!" +"\n\n" 
        						+"Best wishes, your Fiusable Ltd Team";
        }
        else{
        	mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        	mailBodyAfterDMN = "Hi " +firstname +"\n\n" 
        						+"Unfortunately we have more suitable candidates, so your application won't be considered for the open position." +"\n"
        						+"All the best in your futher career," +"\n\n" 
        						+"best wishes, your Fiusable Ltd Team";
        }
        execution.setVariable("mailSubjectAfterDMN", mailSubjectAfterDMN);
        execution.setVariable("mailBodyAfterDMN", mailBodyAfterDMN);     
       
        LOGGER.info("End: Write1RecommendationAndMail called!!!");
   
    }
}








