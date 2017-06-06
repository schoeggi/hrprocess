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
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class GatherCandidateInformationFromDB implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(GatherCandidateInformationFromDB.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	int candidate_id = 0;
    	boolean travel = false;
    	int jobexperience = 0;
    	int jobrefid = 0;
    	String applicantMailAddress = "";
    	String firstname = "";
    	String lastname = ""; 
    	String jobTitle = "";
    	String maturity = "";

        LOGGER.info("Start: GatherCandidateInformationFromDB called!!!");

       /* execution.setVariable("hrprocess_start_date", new Date()); */
        
        String sql_id = "SELECT MAX(id) as id FROM applicant";
        SqlRowSet rowSet_id = jdbcTemplate.queryForRowSet(sql_id);     
        while(rowSet_id.next())
        {
        	candidate_id = rowSet_id.getInt("id");
            // if (lastapplicantid < 1){
        	//  lastapplicantid = 100;
            //}
          LOGGER.info("candidate_id: " +candidate_id);
        }
        execution.setVariable("candidate_id", candidate_id);

          
        String sql_candidate_details = "SELECT * FROM APPLICANT WHERE ID=" +candidate_id;
        SqlRowSet rowSet_candidate_details = jdbcTemplate.queryForRowSet(sql_candidate_details);  
        while(rowSet_candidate_details.next())
        {
	        travel = rowSet_candidate_details.getBoolean("travel");  
	        firstname = rowSet_candidate_details.getString("firstname");
	        lastname = rowSet_candidate_details.getString("lastname");  
	        jobexperience = rowSet_candidate_details.getInt("jobexperience");
	        applicantMailAddress = rowSet_candidate_details.getString("email");
	        jobrefid = rowSet_candidate_details.getInt("jobrefid");
	        jobTitle = rowSet_candidate_details.getString("jobtitle");
	        maturity = rowSet_candidate_details.getString("maturity");
	        
	        LOGGER.info("travel: " + travel);
        }    
        execution.setVariable("travel", travel);
        execution.setVariable("firstname", firstname);
        execution.setVariable("lastname", lastname);
        execution.setVariable("jobexperience", jobexperience);
        execution.setVariable("applicantMailAddress", applicantMailAddress);
        execution.setVariable("jobTitle", jobTitle);
        execution.setVariable("maturity", maturity);
        execution.setVariable("jobrefid", jobrefid);


  
        LOGGER.info("travel:" + travel);
        LOGGER.info("firstname:" + firstname);
        LOGGER.info("lastname:" + lastname);
        LOGGER.info("jobexperience:" + jobexperience);
        LOGGER.info("applicantMailAddress:" + applicantMailAddress);
        LOGGER.info("jobTitle:" + jobTitle);
        LOGGER.info("maturity:" + maturity);
        LOGGER.info("jobrefid:" + jobrefid);

        /*LOGGER.info("Start: Insert applicant to DB");*/
         
        //jdbcTemplate.execute("INSERT INTO APPLICANT (ID,FIRSTNAME,LASTNAME,EMAIL,SALARY) VALUES (" +applicantid +",'Georg','Buzzi','joelenrico.lehner@students.fhnw.ch',100000)");
        //jdbcTemplate.execute("SELECT TRAVEL FROM APPLICANT");
        //LOGGER.info("End: Insert applicant to DB");
        //
    }
}








