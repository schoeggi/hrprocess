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
    	int last_candidate_id = 0;
    	boolean travel = false;
    	int salary = 0;

        LOGGER.info("InitializeHRprocess called!!!");

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
          
        String sql_travel = "SELECT TRAVEL FROM APPLICANT WHERE ID=" +last_candidate_id;
        SqlRowSet rowSet_travel = jdbcTemplate.queryForRowSet(sql_travel);  
        while(rowSet_travel.next())
        {
        travel = rowSet_travel.getBoolean("travel");  
          LOGGER.info("travel: " + travel);
        }    
        execution.setVariable("travel", travel);
        
        String sql_salary = "SELECT SALARY FROM APPLICANT WHERE ID=" +last_candidate_id;
        SqlRowSet rowSet_salary = jdbcTemplate.queryForRowSet(sql_salary);  
        while(rowSet_salary.next())
        {
        salary = rowSet_salary.getInt("salary");
          LOGGER.info("salary: " + salary);
        }    
        execution.setVariable("salary", salary);
        
        LOGGER.info("Start: Insert applicant to DB");
        //jdbcTemplate.execute("INSERT INTO APPLICANT (ID,FIRSTNAME,LASTNAME,EMAIL,SALARY) VALUES (" +applicantid +",'Georg','Buzzi','joelenrico.lehner@students.fhnw.ch',100000)");
      //jdbcTemplate.execute("SELECT TRAVEL FROM APPLICANT");
      LOGGER.info("End: Insert applicant to DB");

    }
}








