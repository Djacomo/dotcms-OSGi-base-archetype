package it.djacomo.osgi.actionlet;

import com.dotmarketing.portlets.workflows.actionlet.WorkFlowActionlet;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionFailureException;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HelloWorldActionlet extends WorkFlowActionlet {
    
    private static final long serialVersionUID = 5137595864924365799L;
    
    private static final String REQ_PARAM_KEY = "required";
    private static final String REQ_PARAM_DISPLAY_NAME = "Required Parameter";
    private static final String REQ_PARAM_DEFAULT_VALUE = "https://djacomo.it";
    
    private static final String OPT_PARAM_KEY = "optional";
    private static final String OPT_PARAM_DISPLAY_NAME = "Optional Parameter";
    private static final String OPT_PARAM_DEFAULT_VALUE = "https://djacomo.it";
    
    private final Logger log = LogManager.getLogger(this.getClass());
    
    
    @Override
    public List<WorkflowActionletParameter> getParameters() {
    
        List<WorkflowActionletParameter> workflowActionletParameterList = new ArrayList<>();
    
        WorkflowActionletParameter requiredParameter = new WorkflowActionletParameter(REQ_PARAM_KEY, REQ_PARAM_DISPLAY_NAME, REQ_PARAM_DEFAULT_VALUE, true);
        workflowActionletParameterList.add(requiredParameter);
        
        WorkflowActionletParameter optionalParameter = new WorkflowActionletParameter(OPT_PARAM_KEY, OPT_PARAM_DISPLAY_NAME, OPT_PARAM_DEFAULT_VALUE, false);
        workflowActionletParameterList.add(optionalParameter);
    
        return workflowActionletParameterList;
    }
    
    @Override
    public String getName() {
        return "Hello World Actionlet";
    }
    
    @Override
    public String getHowTo() {
        return null;
    }
    
    @Override
    public void executeAction(WorkflowProcessor processor, Map<String, WorkflowActionClassParameter> params) throws WorkflowActionFailureException {
    
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendSeconds().appendSuffix(" seconds ago")
                .appendMinutes().appendSuffix(" minutes ago")
                .appendHours().appendSuffix(" hours ago")
                .appendDays().appendSuffix(" days ago")
                .appendWeeks().appendSuffix(" weeks ago")
                .appendMonths().appendSuffix(" months ago")
                .appendYears().appendSuffix(" years ago")
                .printZeroNever()
                .toFormatter();
        
        LocalDateTime initTime = LocalDateTime.now();
        
        for (int i = 0; i < 100; i++) {
            log.info("Hello World {} ", i);
        }
        
        LocalDateTime finishTime = LocalDateTime.now();
        Period period = new Period(initTime, finishTime);
        
        log.info("Time elapsed {} for do nothing!", period);
        
        log.info("Elapsed {} !", formatter.print(period));
        
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HelloWorldActionlet that = (HelloWorldActionlet) o;
        return Objects.equals(log, that.log);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(log);
    }
}