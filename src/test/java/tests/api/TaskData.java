package tests.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskData {
    @JsonProperty("data")
    private List<TaskResponse> data;

    @JsonProperty("data")
    public List<TaskResponse> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<TaskResponse> data) {
        this.data = data;
    }
}
