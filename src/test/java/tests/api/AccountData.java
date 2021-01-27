package tests.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountData {
    @JsonProperty("data")
    private List<AccountResponse> data;

    @JsonProperty("data")
    public List<AccountResponse> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<AccountResponse> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "data=" + data +
                '}';
    }
}
