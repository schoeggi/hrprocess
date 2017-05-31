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
public class WriteRecomendationToDB implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(WriteRecomendationToDB.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	int last_candidate_id = 0;
    	boolean travel = false;
    	int jobexperience = 0;
    	String applicantMailAddress = "";

        LOGGER.info("Start: WriteRecomendationToDB called!!!");

       /** execution.setVariable("hrprocess_start_date", new Date()); */
        
        String sql_id = "SELECT MAX(id) as id FROM applicant";
        SqlRowSet rowSet_id = jdbcTemplate.queryForRowSet(sql_id);     
        while(rowSet_id.next())
        {
        	last_candidate_id = rowSet_id.getInt("id");
         // if (lastapplicantid < 1){
        	//  lastapplicantid = 100;
          //}
        	  
          /*String lastName = rowSet.getString("lastname");*/
          LOGGER.info("Last last_candidate_id: " + last_candidate_id);
        }
          
       // String sql_recommendation = "INSERT INTO APPLICANT (HRRECOMENDATION) VALUES (+hrrecommendation) where ID=last_candidate_id";
        SqlRowSet rowSet_recommendation = jdbcTemplate.queryForRowSet(sql_id);     
        while(rowSet_recommendation.next())
        {
        	last_candidate_id = rowSet_recommendation.getInt("id");
         // if (lastapplicantid < 1){
        	//  lastapplicantid = 100;
          //}
        	  
          /*String lastName = rowSet.getString("lastname");*/
          LOGGER.info("Last last_candidate_id: " + last_candidate_id);
        }
      
        execution.setVariable("applicantMailAddress", applicantMailAddress);
        
        LOGGER.info("End: WriteRecomendationToDB called!!!");

        
        
 
         /*LOGGER.info("Start: Insert applicant to DB");*/
         
        //jdbcTemplate.execute("INSERT INTO APPLICANT (ID,FIRSTNAME,LASTNAME,EMAIL,SALARY) VALUES (" +applicantid +",'Georg','Buzzi','joelenrico.lehner@students.fhnw.ch',100000)");
      //jdbcTemplate.execute("SELECT TRAVEL FROM APPLICANT");
      //LOGGER.info("End: Insert applicant to DB");
      //
    }
}








