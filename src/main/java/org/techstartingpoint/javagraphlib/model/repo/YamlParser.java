package org.techstartingpoint.javagraphlib.model.repo;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;

public class YamlParser<T> {
    private static final Logger logger = LoggerFactory.getLogger(YamlParser.class.getName());
    public T readYaml(String fileName, Class<T> c) throws OnelakeException {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            T t = mapper.readValue(new File(fileName), c);
            logger.info(ReflectionToStringBuilder.toString(t,ToStringStyle.MULTI_LINE_STYLE));
            return t;
        } catch (Exception e) {
            logger.error(WorkflowErrorCode.UnableToReadFile.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.UnableToReadFile, e).set("fileName", fileName).set("class", c);
        }

    }
}

