package com.onelake.workflowexecutor.cli;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.cli.*;
/**
 * Created by ravi on 8/21/17.
 */
public class CommandLineOptions {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineOptions.class.getName());

    public static final String HELP="help",
            JOB_JSON = "jobJson",
            APP_YML = "appYml",
            WORKFLOW_JSON = "workflowJson",
            REPO_YML = "repoYml";
    private Options commandOptions = new Options();
    private Map<String, String> optionsMap = new HashMap<String, String>();

    public CommandLineOptions(String[] args) {
        this.commandOptions = createOptions();
    }

    private Options createOptions() {
        Options options = new Options();
        options.addOption(buildRequiredOption(JOB_JSON, true, "[Required] - name of the file containing the job json."));
        options.addOption(buildRequiredOption(APP_YML, true, "[Required] - name of the file containing application yml."));
        options.addOption(buildRequiredOption(REPO_YML, true, "[Required] - name of the file containing components."));
        options.addOption(buildRequiredOption(WORKFLOW_JSON, true, "[Required] - name of the file containing workflow json."));
        options.addOption(HELP, false, "[Optional] - show help.");
        return options;
    }

    private Option buildRequiredOption(String optionName, boolean hasArg, String description) {
        Option option = new Option(optionName,hasArg,description);
        option.setRequired(true);
        return option;
    }

    public void parse(String[] args) throws OnelakeException {
        try {
            CommandLine cmd = (new BasicParser()).parse(commandOptions, args);
            if (cmd.hasOption(HELP)) {
                printHelp();
            }

            List<Option> optionsList = new ArrayList<>(commandOptions.getOptions());
            for (Option option : optionsList) {
                optionsMap.put(option.getOpt(), readOptionFromCommand(cmd, option));
            }
        } catch (ParseException pe) {
            printHelp();
            logger.error(WorkflowErrorCode.InsufficientCommandOptions.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.InsufficientCommandOptions, pe);
        }
    }

    private String readOptionFromCommand(CommandLine cmd, Option option) throws OnelakeException {
        String optionValue = null;
        String optionName = option.getOpt();
        if (cmd.hasOption(optionName)) {
            optionValue = cmd.getOptionValue(optionName);
        } else if (option.isRequired()) {
            printHelp();
            logger.error(WorkflowErrorCode.InsufficientCommandOptions.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.InsufficientCommandOptions);
        }
        return optionValue;
    }

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("App", commandOptions);
    }

    public String get(String optionName) { return optionsMap.get(optionName); }
}
