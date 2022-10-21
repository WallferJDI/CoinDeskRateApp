package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bitcoin {

    private Currency code;
    @JsonProperty("rate_float")
    private float rate;
    private String description;
}
