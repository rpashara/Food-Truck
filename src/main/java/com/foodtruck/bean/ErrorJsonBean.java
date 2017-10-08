package com.foodtruck.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import lombok.Data;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public  class ErrorJsonBean {

    private List<ErrorBean> errors;

    public List<ErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBean> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorJSONBean [errors=" + errors + "]";
    }

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Data
    public static class ErrorBean{


        @JsonProperty("error_code")
        private String errorCode;

        @JsonProperty("element_name")
        private String elementName;

        @JsonProperty("developer_message")
        private String developerMessage;

        @JsonProperty("user_message")
        private String userMessage;

        @JsonProperty("more_info")
        private String moreInfo;

        public ErrorBean(){}

        public ErrorBean(String error_code, String developer_message,
                         String user_message, String field) {
            this.errorCode = error_code;
            this.developerMessage = developer_message;
            this.userMessage = user_message;
            this.elementName = field;
        }

        @Override
        public String toString() {
            return "{errors: [{\""
                    + "error_code\":" + "\"" + errorCode + "\""
                    + ", \"developer_message\":" + "\"" + developerMessage + "\""
                    + (StringUtils.isNoneBlank(userMessage)?", \"user_message\":"+ "\""  + userMessage + "\"":"")
                    + (StringUtils.isNoneBlank(elementName)?", \"element_name\":"  + "\""+ elementName + "\"":"")
                    + (StringUtils.isNoneBlank(moreInfo)?", \"more_info\":"  + "\""+ moreInfo + "\"":"")
                    + "}"
                    + "]"
                    + "}";
        }

    }
}

